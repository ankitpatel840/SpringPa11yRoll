package springpayroll.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import springpayroll.exception.E_CodeNotFoundException;
import springpayroll.model.BranchData;
import springpayroll.model.Ctc_Data;
import springpayroll.repo.CTC_Repo;
import springpayroll.repo.branch_Repo;
import springpayroll.service.CtcCalculation;

import java.util.List;

@Component
public class CtcControllerMethode implements CtcControllerImpl {

    private CTC_Repo ctc_repo;
    private branch_Repo branch_repo;
    private CtcCalculation ctcCalculation;
    private BranchData branchData;
    private Ctc_Data ctcData;

    String ecode;
    String ename;

    @Autowired
    public CtcControllerMethode(CTC_Repo ctc_repo, branch_Repo branch_repo, CtcCalculation ctcCalculation, BranchData branchData, Ctc_Data ctcData) {
        this.ctc_repo = ctc_repo;
        this.branch_repo = branch_repo;
        this.ctcCalculation = ctcCalculation;
        this.branchData = branchData;
        this.ctcData = ctcData;
    }


    public ModelAndView homePage() {

        return new ModelAndView("home.html");
    }


    public ModelAndView loginValidationCheaking(String e_Name, String e_code) {

        ModelAndView mv = new ModelAndView();
        //VARIFY_IN_DATA_BASE_BY_PASSING_DATA_IS_TRUE/FALSE_________________________________________________________________________________
        boolean e_Code_Varify_Data_Base = ctc_repo.existsById(e_code);

        boolean a = ctc_repo.existsByEname(e_Name);
        System.out.println(a);
        ecode = e_code;
        ename = e_Name;
        System.out.println(a);
        //Cheak_IN_Data_Base_And _Given__Ture/False This __Condistion______________________________________________________________
        if (e_Code_Varify_Data_Base && a) {
            Ctc_Data ctcData = new Ctc_Data();
            mv.setViewName("index");
        } else {
            mv.setViewName("ReturnHtml");
            throw new E_CodeNotFoundException("Invalied Code Enter");
        }
        return mv;
    }


    public String all_the_calcution(long ctc, String state, Model model, ModelAndView modelAndView) {
        System.out.println("ankit");
        Ctc_Data ctcData = new Ctc_Data();
        BranchData get_data_to_breanch_repo = branch_repo.findById(state).get();
        System.out.println(get_data_to_breanch_repo);
        Long get_minimum_Wage_to_database = get_data_to_breanch_repo.getMINIMUM_WAGES();
        System.out.println(get_minimum_Wage_to_database);

        Long get_Hra_Percentage_To_DataBase = get_data_to_breanch_repo.getHRA_PER();


        // Basic_Calculation_Methode_is_started_here_________________________________________________________________________________
        Long got_basic_calculation_by_basic = ctcCalculation.basie_calculation_methode(ctc, get_minimum_Wage_to_database);

        //Bonus_Calculation_Methode_IS_Started_here_____________________________________________________________________________
        Long got_bonis_calculation_bonus = ctcCalculation.bonusCalulation(got_basic_calculation_by_basic);

        //Employer_PF_Calculation_Methode_IS_Given_Output_________________________________________________________________________
        Long got_Employer_PF_claculation_by_employer_pf = ctcCalculation.employePfCalculation(got_basic_calculation_by_basic);

        //Gratutity_Methode_IS_Given_Output________________________________________________________________________________________
        Long got_gratutity_calculation_by_gratutity = ctcCalculation.gratutityCalculation(got_basic_calculation_by_basic);

        //Gross_Calculation_Methode_IS_Given_Output_______________________________________________________________________________
        Long got_Gross_Calculation_by_gross = ctcCalculation.grossCalculation(ctc, got_Employer_PF_claculation_by_employer_pf, got_gratutity_calculation_by_gratutity);

        //Employer_ESI_Calculation_IS_Given_Output________________________________________________________________________________________
        Long got_Employer_Esi_Calculation_by_employer_esi_ = ctcCalculation.employerEsiCalculation(got_Gross_Calculation_by_gross);

        //Employee_PF__Calculation__IS_Given_Output
        Long got_Employee_PF_Calculation_by_employee_pf = ctcCalculation.employeePf(got_basic_calculation_by_basic);

        //Employee_ESI_Calculation_IS_Output__________________________________________________________________________________
        Long got_Employee_ESI_Calculation_by_employee_esi = ctcCalculation.employeeEsiCalculation(got_Gross_Calculation_by_gross);

        //Employee_Goss_Ana_Calculation_IS Given_Output_______________________________________________________________________________________
        Long got_Gross_And_Deduction_Calculation_by_gross_and_deduction = ctcCalculation.grossAndDeductionCalculation(got_Employee_ESI_Calculation_by_employee_esi, got_Employee_PF_Calculation_by_employee_pf);

        //Net_Pay_Calculation_IS_Given_Output_______________________________________________________________________________________
        Long got_Net_Pay_Calculation_net_pay = ctcCalculation.netPayCalucaltion(got_Gross_Calculation_by_gross, got_Employee_PF_Calculation_by_employee_pf, got_Employee_ESI_Calculation_by_employee_esi);

        //Net_Take_Home_IS_Given_Output__________________________________________________________________________________________________
        Long got_Net_Take_Home_Calculation_by_net_take_home = ctcCalculation.netTakeHomeCalculation(got_Gross_Calculation_by_gross, got_Gross_And_Deduction_Calculation_by_gross_and_deduction);

        //PT_Gross_CAlculation_IS_Created________________________________________________________________________________________________

        Long got_PT_Gross_Calculation_by_pt_gross = ctcCalculation.ptGrossCalculation(got_Net_Pay_Calculation_net_pay, got_Gross_And_Deduction_Calculation_by_gross_and_deduction);

        //DIFFERENCE_Calculation_IS_Given_Output_________________________________________________________________________________________
        Long got_DIFFERNECE_Calculation_by_differnence = ctcCalculation.differneceCalculation(got_Net_Take_Home_Calculation_by_net_take_home, got_Net_Pay_Calculation_net_pay);

        //Home_Ret_Allowance__IS__Given_Output______________________________________________________________________________________________
        Long got_HOME_RENT_Allowance_Calculation_by_hra = ctcCalculation.home_Rent_Allowance(got_basic_calculation_by_basic, got_bonis_calculation_bonus, got_Gross_And_Deduction_Calculation_by_gross_and_deduction, got_Net_Pay_Calculation_net_pay, get_Hra_Percentage_To_DataBase);

        //Repository_Object_IS_Created_To_Set_In_Data_Base___________________________________________________________________________
        ctcData.setCtc(ctc);

        ctcData.setBasic(got_basic_calculation_by_basic);
        ctcData.setNet_Take__Home(got_Net_Take_Home_Calculation_by_net_take_home);
        ctcData.setBonus(got_bonis_calculation_bonus);
        ctcData.setEmployee_Esi(got_Employee_ESI_Calculation_by_employee_esi);
        ctcData.setEmployee_Pf(got_Employee_PF_Calculation_by_employee_pf);
        ctcData.setEmployer_Esi(got_Employee_ESI_Calculation_by_employee_esi);
        ctcData.setGross_Ded(got_Gross_And_Deduction_Calculation_by_gross_and_deduction);
        ctcData.setGross(got_Gross_Calculation_by_gross);
        ctcData.setH_R_A(got_HOME_RENT_Allowance_Calculation_by_hra);
        ctcData.setNet_pay(got_Net_Pay_Calculation_net_pay);
        ctcData.setEmployer_Pf(got_Employer_PF_claculation_by_employer_pf);
        ctcData.setDiff(got_DIFFERNECE_Calculation_by_differnence);
        ctcData.setGratuity(got_gratutity_calculation_by_gratutity);
        ctcData.setPt_Gross(got_PT_Gross_Calculation_by_pt_gross);
        ctcData.setlOC(state);
        ctcData.setMinimum_Wage(get_minimum_Wage_to_database);
        ctcData.setEname(ename);
        ctcData.setE_code(ecode);
        System.out.println(ecode);
        System.out.println(ename);
        ctc_repo.save(ctcData);
        System.out.println("ankdfaunighbf");
        //ctc_repo.save(ctcData);


        modelAndView.addObject(ctcData);
        return ctc_repo.findAll().toString();
    }


    public Ctc_Data ctcCalculationDataSavingInDataBase(long ctc, String e_code, String state, String e_Name) {
        if (ctc_repo.existsById(e_code)) {
            BranchData get_data_to_breanch_repo = branch_repo.findById(state).get();
            Long get_minimum_Wage_to_database = get_data_to_breanch_repo.getMINIMUM_WAGES();
            System.out.println(get_minimum_Wage_to_database);
            System.out.println(state);
            Long get_Hra_Percentage_To_DataBase = get_data_to_breanch_repo.getHRA_PER();
            Ctc_Data ctcData = new Ctc_Data();
            // Basic_Calculation_Methode_is_started_here_________________________________________________________________________________
            Long got_basic_calculation_by_basic = ctcCalculation.basie_calculation_methode(ctc, get_minimum_Wage_to_database);

            //Bonus_Calculation_Methode_IS_Started_here_____________________________________________________________________________
            Long got_bonis_calculation_bonus = ctcCalculation.bonusCalulation(got_basic_calculation_by_basic);

            //Employer_PF_Calculation_Methode_IS_Given_Output_________________________________________________________________________
            Long got_Employer_PF_claculation_by_employer_pf = ctcCalculation.employePfCalculation(got_basic_calculation_by_basic);

            //Gratutity_Methode_IS_Given_Output________________________________________________________________________________________
            Long got_gratutity_calculation_by_gratutity = ctcCalculation.gratutityCalculation(got_basic_calculation_by_basic);

            //Gross_Calculation_Methode_IS_Given_Output_______________________________________________________________________________
            Long got_Gross_Calculation_by_gross = ctcCalculation.grossCalculation(ctc, got_Employer_PF_claculation_by_employer_pf, got_gratutity_calculation_by_gratutity);

            //Employer_ESI_Calculation_IS_Given_Output________________________________________________________________________________________
            Long got_Employer_Esi_Calculation_by_employer_esi_ = ctcCalculation.employerEsiCalculation(got_Gross_Calculation_by_gross);
            System.out.println(got_Employer_Esi_Calculation_by_employer_esi_);

            //Employee_PF__Calculation__IS_Given_Output
            Long got_Employee_PF_Calculation_by_employee_pf = ctcCalculation.employeePf(got_basic_calculation_by_basic);
            System.out.println(got_Employee_PF_Calculation_by_employee_pf);

            //Employee_ESI_Calculation_IS_Output__________________________________________________________________________________
            Long got_Employee_ESI_Calculation_by_employee_esi = ctcCalculation.employeeEsiCalculation(got_Gross_Calculation_by_gross);


            //Employee_Goss_Ana_Calculation_IS Given_Output_______________________________________________________________________________________
            Long got_Gross_And_Deduction_Calculation_by_gross_and_deduction = ctcCalculation.grossAndDeductionCalculation(got_Employee_ESI_Calculation_by_employee_esi, got_Employee_PF_Calculation_by_employee_pf);
            System.out.println(got_Gross_And_Deduction_Calculation_by_gross_and_deduction);


            //Net_Pay_Calculation_IS_Given_Output_______________________________________________________________________________________
            Long got_Net_Pay_Calculation_net_pay = ctcCalculation.netPayCalucaltion(got_Gross_Calculation_by_gross, got_Employee_PF_Calculation_by_employee_pf, got_Employee_ESI_Calculation_by_employee_esi);
            System.out.println(got_Net_Pay_Calculation_net_pay);

            //Net_Take_Home_IS_Given_Output__________________________________________________________________________________________________

            Long got_Net_Take_Home_Calculation_by_net_take_home = ctcCalculation.netTakeHomeCalculation(got_Gross_Calculation_by_gross, got_Gross_And_Deduction_Calculation_by_gross_and_deduction);
            System.out.println(got_Net_Take_Home_Calculation_by_net_take_home);

            //PT_Gross_CAlculation_IS_Created________________________________________________________________________________________________
            Long got_PT_Gross_Calculation_by_pt_gross = ctcCalculation.ptGrossCalculation(got_Net_Pay_Calculation_net_pay, got_Gross_And_Deduction_Calculation_by_gross_and_deduction);
            System.out.println(got_PT_Gross_Calculation_by_pt_gross);

            //DIFFERENCE_Calculation_IS_Given_Output_________________________________________________________________________________________

            Long got_DIFFERNECE_Calculation_by_differnence = ctcCalculation.differneceCalculation(got_Net_Take_Home_Calculation_by_net_take_home, got_Net_Pay_Calculation_net_pay);
            System.out.println(got_DIFFERNECE_Calculation_by_differnence);

            //Home_Ret_Allowance__IS__Given_Output______________________________________________________________________________________________

            Long got_HOME_RENT_Allowance_Calculation_by_hra = ctcCalculation.home_Rent_Allowance(got_basic_calculation_by_basic, got_bonis_calculation_bonus, got_Gross_And_Deduction_Calculation_by_gross_and_deduction, got_Net_Pay_Calculation_net_pay, get_Hra_Percentage_To_DataBase);
            System.out.println(got_HOME_RENT_Allowance_Calculation_by_hra);


            //Repository_Object_IS_Created_To_Set_In_Data_Base___________________________________________________________________________
            ctcData.setCtc(ctc);
            ctcData.setlOC(state);
            ctcData.setBasic(got_basic_calculation_by_basic);
            ctcData.setNet_Take__Home(got_Net_Take_Home_Calculation_by_net_take_home);
            ctcData.setBonus(got_bonis_calculation_bonus);
            ctcData.setEmployee_Esi(got_Employee_ESI_Calculation_by_employee_esi);
            ctcData.setEmployee_Pf(got_Employee_PF_Calculation_by_employee_pf);
            ctcData.setEmployer_Esi(got_Employee_ESI_Calculation_by_employee_esi);
            ctcData.setGross_Ded(got_Gross_And_Deduction_Calculation_by_gross_and_deduction);
            ctcData.setGross(got_Gross_Calculation_by_gross);
            ctcData.setH_R_A(got_HOME_RENT_Allowance_Calculation_by_hra);
            ctcData.setNet_pay(got_Net_Pay_Calculation_net_pay);
            ctcData.setEmployer_Pf(got_Employer_PF_claculation_by_employer_pf);
            ctcData.setDiff(got_DIFFERNECE_Calculation_by_differnence);
            ctcData.setGratuity(got_gratutity_calculation_by_gratutity);
            ctcData.setPt_Gross(got_PT_Gross_Calculation_by_pt_gross);
            ctcData.setMinimum_Wage(get_minimum_Wage_to_database);
            ctcData.setEname(e_Name);
            ctcData.setE_code(e_code);
            System.out.println("asdgg");
            ctc_repo.save(ctcData);
            //  modelAndView.addObject(ctcData);
            return ctcData;
        } else {
            throw new E_CodeNotFoundException("Invalid Code Exception");
        }


    }


    public Ctc_Data userCredentialCheaking(Ctc_Data ctc_data) {

        System.out.println("ankiy");


        ctc_repo.save(ctc_data);
        System.out.println("anadfaf");
        return ctc_data;
        //return  ctc_data;
    }


    public List<Ctc_Data> getAllUsersCtcData() {
        System.out.println("ankit");
        return ctc_repo.findAll();
    }


    public Ctc_Data getUserCtcData(String e_code) {
        System.out.println("ankit");


        return ctc_repo.getOne(e_code);

    }


    public String deleteOneUserCtcData(String e_code) {
        if (ctc_repo.existsById(e_code)) {
            System.out.println(e_code);
            Ctc_Data ab = ctc_repo.getOne(e_code);
            System.out.println("ADS");
            ctc_repo.delete(ab);
            System.out.println("df");
            return "This";
        } else {
            throw new E_CodeNotFoundException("Invalied E_Code");
        }
    }


    public Ctc_Data updateUserCtcData(String e_code, Ctc_Data ctc_data) {
        if (ctc_repo.existsById(e_code)) {

            return (ctc_repo.findById(e_code)
                    .map(ctc_data1 -> {
                        ctc_data1.setEname(ctc_data.getEname());

                        return ctc_repo.save(ctc_data1);
                    })
                    .orElseGet(() -> {
                        ctc_data.setE_code(e_code);
                        return ctc_repo.save(ctc_data);
                    })
            );
        } else {
            throw new E_CodeNotFoundException("Invalid ECode  ");
        }


    }

    public String deleteAllUserCtcData() {
        ctc_repo.deleteAll();
        return " All Data  Deleted";
    }

}

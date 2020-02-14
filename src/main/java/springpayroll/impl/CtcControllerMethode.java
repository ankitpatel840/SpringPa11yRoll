package springpayroll.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springpayroll.exception.ECodeNotFoundException;
import springpayroll.exception.InvalidStateCodeCException;
import springpayroll.exception.UserAllreadyExistException;
import springpayroll.model.BranchData;
import springpayroll.model.CtcData;
import springpayroll.repo.BranchRepo;
import springpayroll.repo.CtcRepo;
import springpayroll.service.CtcCalculation;
import springpayroll.service.RabbitMQSender;

import java.util.List;


@Service
public class CtcControllerMethode implements CtcControllerImpl {

    @Autowired
    private CtcRepo ctcRepo;
    @Autowired
    private BranchRepo BranchRepo;
    @Autowired
    private CtcCalculation ctcCalculation;
    @Autowired
    private BranchData branchData;
    @Autowired
    private CtcData ctcData;


    @Autowired
    RabbitMQSender rabbitMQSender;

    //
    public CtcControllerMethode(CtcRepo ctcRepo, springpayroll.repo.BranchRepo branchRepo, CtcCalculation ctcCalculation, BranchData branchData, CtcData ctcData) {
        this.ctcRepo = ctcRepo;
        BranchRepo = branchRepo;
        this.ctcCalculation = ctcCalculation;
        this.branchData = branchData;
        this.ctcData = ctcData;
    }



    public CtcData newUserCrete(String ecode, String ename) throws UserAllreadyExistException {

        ctcData.setE_code(ecode);
        ctcData.setEname(ename);
        rabbitMQSender.send(ctcData);

        System.out.println(!ctcRepo.existsById(ecode));
        if (!ctcRepo.existsById(ecode)) {
            CtcData ctc_Data = new CtcData(ecode, ename);
            System.out.println(ctcRepo.save(ctc_Data));
            return ctcRepo.save(ctc_Data);

        }
            throw new UserAllreadyExistException("AllReady  User ECode Used");


    }








    public CtcData ctcCalculationDataSavingInDataBase(Long ctc, String e_code, String state, String e_Name) throws InvalidStateCodeCException, ECodeNotFoundException {

             rabbitMQSender.send(ctcData);
             ctcData.setEname(e_Name);
             ctcData.setE_code(e_code);
             ctcData.setlOC(state);
             ctcData.setCtc(ctc);

        System.out.println(BranchRepo.existsById(state) && ctcRepo.existsById(e_code));
        if (BranchRepo.existsById(state) && ctcRepo.existsById(e_code)) {
            BranchData get_data_to_breanch_repo = BranchRepo.findById(state).orElse(null);
            assert get_data_to_breanch_repo != null;
            Long get_minimum_Wage_to_database = get_data_to_breanch_repo.getMINIMUM_WAGES();
            System.out.println(get_minimum_Wage_to_database);
            System.out.println(state);
            Long get_Hra_Percentage_To_DataBase = get_data_to_breanch_repo.getHRA_PER();
            CtcData CTCData = new CtcData();
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

            //Net_Pay_Calculation_IS_Given_Output_______________________________________________________________________________________
            Long got_Net_Pay_Calculation_net_pay = ctcCalculation.netPayCalucaltion(got_Gross_Calculation_by_gross, got_Employee_PF_Calculation_by_employee_pf, got_Employee_ESI_Calculation_by_employee_esi);


            //Net_Take_Home_IS_Given_Output__________________________________________________________________________________________________

            Long got_Net_Take_Home_Calculation_by_net_take_home = ctcCalculation.netTakeHomeCalculation(got_Gross_Calculation_by_gross, got_Gross_And_Deduction_Calculation_by_gross_and_deduction);


            //PT_Gross_CAlculation_IS_Created________________________________________________________________________________________________
            Long got_PT_Gross_Calculation_by_pt_gross = ctcCalculation.ptGrossCalculation(got_Net_Pay_Calculation_net_pay, got_Gross_And_Deduction_Calculation_by_gross_and_deduction);
            System.out.println(got_PT_Gross_Calculation_by_pt_gross);

            //DIFFERENCE_Calculation_IS_Given_Output_________________________________________________________________________________________

            Long got_DIFFERNECE_Calculation_by_differnence = ctcCalculation.differneceCalculation(got_Net_Take_Home_Calculation_by_net_take_home, got_Net_Pay_Calculation_net_pay);


            //Home_Ret_Allowance__IS__Given_Output______________________________________________________________________________________________

            Long got_HOME_RENT_Allowance_Calculation_by_hra = ctcCalculation.home_Rent_Allowance(got_basic_calculation_by_basic, got_bonis_calculation_bonus, got_Gross_And_Deduction_Calculation_by_gross_and_deduction, got_Net_Pay_Calculation_net_pay, get_Hra_Percentage_To_DataBase);


            //Repository_Object_IS_Created_To_Set_In_Data_Base___________________________________________________________________________
            CTCData.setCtc(ctc);
            CTCData.setlOC(state);
            CTCData.setBasic(got_basic_calculation_by_basic);
            CTCData.setNet_Take__Home(got_Net_Take_Home_Calculation_by_net_take_home);
            CTCData.setBonus(got_bonis_calculation_bonus);
            CTCData.setEmployee_Esi(got_Employee_ESI_Calculation_by_employee_esi);
            CTCData.setEmployee_Pf(got_Employee_PF_Calculation_by_employee_pf);
            CTCData.setEmployer_Esi(got_Employee_ESI_Calculation_by_employee_esi);
            CTCData.setGross_Ded(got_Gross_And_Deduction_Calculation_by_gross_and_deduction);
            CTCData.setGross(got_Gross_Calculation_by_gross);
            CTCData.setH_R_A(got_HOME_RENT_Allowance_Calculation_by_hra);
            CTCData.setNet_pay(got_Net_Pay_Calculation_net_pay);
            CTCData.setEmployer_Pf(got_Employer_PF_claculation_by_employer_pf);
            CTCData.setDiff(got_DIFFERNECE_Calculation_by_differnence);
            CTCData.setGratuity(got_gratutity_calculation_by_gratutity);
            CTCData.setPt_Gross(got_PT_Gross_Calculation_by_pt_gross);
            CTCData.setMinimum_Wage(get_minimum_Wage_to_database);
            CTCData.setEname(e_Name);
            CTCData.setE_code(e_code);

            ctcRepo.save(CTCData);

            return CTCData;
        } else if(!ctcRepo.existsById(e_code))
        {
            throw  new ECodeNotFoundException("Invalid Ecode Exception");
        }
        else
        {
            throw new InvalidStateCodeCException("Please Enter Correct StateCode");
        }



    }

    public CtcData getUserCtcData(String e_code) throws ECodeNotFoundException {
        ctcData=new CtcData();
        ctcData.setE_code(e_code);
        rabbitMQSender.send(ctcData);

        System.out.println(ctcRepo.existsById(e_code));
        if (ctcRepo.existsById(e_code)) {
            System.out.println(e_code);
            System.out.println(ctcRepo.getOne(e_code));
            return ctcRepo.getOne(e_code);
        } else {
            throw new ECodeNotFoundException("Invalid ECode");
        }
    }

    public List<CtcData> getAllUsersCtcData() {


        System.out.println(ctcRepo.findAll());
        return ctcRepo.findAll();
    }



    public CtcData updateUserCtcData(String e_code, CtcData ctc_data) throws ECodeNotFoundException {


        if (ctcRepo.existsById(e_code)) {

            return (ctcRepo.findById(e_code)
                    .map(ctc_data1 -> {
                        System.out.println(ctc_data1);
                        System.out.println(ctcRepo.save(ctc_data1));
                        ctc_data1.setEname(ctc_data.getEname());


                        return ctcRepo.save(ctc_data1);
                    })
                    .orElseGet(() -> {
                        ctc_data.setE_code(e_code);
                        return ctcRepo.save(ctc_data);
                    })
            );
        } else {
            throw new ECodeNotFoundException("Invalid ECode");
        }


    }



    public String deleteOneUserCtcData(String e_code) throws ECodeNotFoundException {

        if (ctcRepo.existsById(e_code)) {

            CtcData ab = ctcRepo.getOne(e_code);

            ctcRepo.delete(ab);

            return "Your Ctc Data Deleted";
        } else {
            throw new ECodeNotFoundException("Invalid ECode");
        }

    }


    public String deleteAllUserCtcData() {
        ctcRepo.deleteAll();
        return " All Data  Deleted";
    }

}

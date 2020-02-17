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
            rabbitMQSender.send(ctc_Data);
            return ctcRepo.save(ctc_Data);

        }

        throw new UserAllreadyExistException("AllReady  User ECode Used");


    }


    public CtcData ctcCalculationDataSavingInDataBase(Long ctc, String e_code, String state, String e_Name) throws InvalidStateCodeCException, ECodeNotFoundException {


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
          CtcData   data = new CtcData();
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
            data.setCtc(ctc);
            data.setlOC(state);
            data.setBasic(got_basic_calculation_by_basic);
            data.setNet_Take__Home(got_Net_Take_Home_Calculation_by_net_take_home);
            data.setBonus(got_bonis_calculation_bonus);
            data.setEmployee_Esi(got_Employee_ESI_Calculation_by_employee_esi);
            data.setEmployee_Pf(got_Employee_PF_Calculation_by_employee_pf);
            data.setEmployer_Esi(got_Employee_ESI_Calculation_by_employee_esi);
            data.setGross_Ded(got_Gross_And_Deduction_Calculation_by_gross_and_deduction);
            data.setGross(got_Gross_Calculation_by_gross);
            data.setH_R_A(got_HOME_RENT_Allowance_Calculation_by_hra);
            data.setNet_pay(got_Net_Pay_Calculation_net_pay);
            data.setEmployer_Pf(got_Employer_PF_claculation_by_employer_pf);
            data.setDiff(got_DIFFERNECE_Calculation_by_differnence);
            data.setGratuity(got_gratutity_calculation_by_gratutity);
            data.setPt_Gross(got_PT_Gross_Calculation_by_pt_gross);
            data.setMinimum_Wage(get_minimum_Wage_to_database);
            data.setEname(e_Name);
            data.setE_code(e_code);

            ctcRepo.save(data);
            rabbitMQSender.send(data);
            return data;
        } else if (!ctcRepo.existsById(e_code)) {
            rabbitMQSender.sendDelete("Invalid Ecode Exception");
            throw new ECodeNotFoundException("Invalid Ecode Exception");
        } else {
            rabbitMQSender.sendDelete("Please Enter Correct StateCode");
            throw new InvalidStateCodeCException("Please Enter Correct StateCode");
        }


    }

    public CtcData getUserCtcData(String e_code) throws ECodeNotFoundException {
        System.out.println(ctcRepo.existsById(e_code));
        if (ctcRepo.existsById(e_code)) {
            System.out.println(e_code);
            System.out.println(ctcRepo.getOne(e_code));
            rabbitMQSender.send(ctcRepo.getOne(e_code));
            return ctcRepo.getOne(e_code);
        } else {
            throw new ECodeNotFoundException("Invalid ECode");
        }
    }

    public List<CtcData> getAllUsersCtcData() {

        rabbitMQSender.sendList(ctcRepo.findAll());
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

                        rabbitMQSender.send(ctc_data1);
                        return ctcRepo.save(ctc_data1);
                    }).orElse(null)
            );
        } else {
            rabbitMQSender.sendDelete("Invalid ECode");
            throw new ECodeNotFoundException("Invalid ECode");
        }


    }


    public String deleteOneUserCtcData(String e_code) throws ECodeNotFoundException {

        if (ctcRepo.existsById(e_code)) {

            CtcData deleteCtcData = ctcRepo.getOne(e_code);

            ctcRepo.delete(deleteCtcData);
            rabbitMQSender.send(deleteCtcData);
            rabbitMQSender.sendDelete("Your Ctc Data Deleted");
            return "Your Ctc Data Deleted";
        } else {
            rabbitMQSender.sendDelete("Invalid ECode");
            throw new ECodeNotFoundException("Invalid ECode");
        }

    }


    public String deleteAllUserCtcData() {
        ctcRepo.deleteAll();
        rabbitMQSender.sendDelete("All CtcData Deleted");
        return " All Data  Deleted";
    }

}

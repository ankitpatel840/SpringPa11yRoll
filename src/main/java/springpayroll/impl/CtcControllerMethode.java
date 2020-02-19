package springpayroll.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springpayroll.exception.ECodeNotFoundException;
import springpayroll.exception.InvalidStateCodeCException;
import springpayroll.exception.UserAllreadyExistException;
import springpayroll.model.BranchData;
import springpayroll.model.CtcComponent;
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
      CtcComponent ctcComponent;
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


    public CtcData newUserCrete(CtcData ctcData) throws UserAllreadyExistException {

        if (!ctcRepo.existsById(ctcData.getE_code())) {
            rabbitMQSender.send(ctcData);
            return ctcRepo.save(ctcData); }

        throw new UserAllreadyExistException("AllReady  User ECode Used");
    }


    public CtcData ctcCalculationDataSavingInDataBase(CtcData ctcData) throws InvalidStateCodeCException, ECodeNotFoundException {

       String data =ctcData.getLOC();
       if (BranchRepo.existsById(ctcData.getLOC()) && ctcRepo.existsById(ctcData.getE_code())) {
            BranchData get_data_to_breanch_repo = BranchRepo.findById(ctcData.getLOC()).orElse(null);
            assert get_data_to_breanch_repo != null;
            Long get_minimum_Wage_to_database = get_data_to_breanch_repo.getMINIMUM_WAGES();
            Long get_Hra_Percentage_To_DataBase = get_data_to_breanch_repo.getHRA_PER();
            // Basic_Calculation_Methode_is_started_here_________________________________________________________________________________
            Long got_basic_calculation_by_basic = ctcCalculation.basie_calculation_methode(ctcData.getCtc(), get_minimum_Wage_to_database);

            //Bonus_Calculation_Methode_IS_Started_here_____________________________________________________________________________
            Long got_bonis_calculation_bonus = ctcCalculation.bonusCalulation(got_basic_calculation_by_basic);

            //Employer_PF_Calculation_Methode_IS_Given_Output_________________________________________________________________________
            Long got_Employer_PF_claculation_by_employer_pf = ctcCalculation.employePfCalculation(got_basic_calculation_by_basic);

            //Gratutity_Methode_IS_Given_Output________________________________________________________________________________________
            Long got_gratutity_calculation_by_gratutity = ctcCalculation.gratutityCalculation(got_basic_calculation_by_basic);

            //Gross_Calculation_Methode_IS_Given_Output_______________________________________________________________________________
            Long got_Gross_Calculation_by_gross = ctcCalculation.grossCalculation(ctcData.getCtc(), got_Employer_PF_claculation_by_employer_pf, got_gratutity_calculation_by_gratutity);

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

          //  data=new CtcData(ctcData.getE_code(),ctcData.getEname(),get_Hra_Percentage_To_DataBase,got_Net_Take_Home_Calculation_by_net_take_home,ctcData.getCtc(),got_basic_calculation_by_basic,got_bonis_calculation_bonus,got_Employer_Esi_Calculation_by_employer_esi_,got_gratutity_calculation_by_gratutity,got_Gross_Calculation_by_gross,got_Employee_PF_Calculation_by_employee_pf,got_Employee_ESI_Calculation_by_employee_esi,got_Employer_PF_claculation_by_employer_pf,got_Gross_And_Deduction_Calculation_by_gross_and_deduction,got_DIFFERNECE_Calculation_by_differnence,got_PT_Gross_Calculation_by_pt_gross,ctcData.getLOC(),got_Net_Pay_Calculation_net_pay,get_minimum_Wage_to_database);
              ctcComponent=new CtcComponent(got_HOME_RENT_Allowance_Calculation_by_hra,got_Net_Take_Home_Calculation_by_net_take_home, got_basic_calculation_by_basic, got_bonis_calculation_bonus, got_Employee_ESI_Calculation_by_employee_esi, got_gratutity_calculation_by_gratutity, got_Gross_Calculation_by_gross, got_Employee_PF_Calculation_by_employee_pf, got_Employee_ESI_Calculation_by_employee_esi, got_Employer_PF_claculation_by_employer_pf, got_Gross_And_Deduction_Calculation_by_gross_and_deduction, got_DIFFERNECE_Calculation_by_differnence, got_PT_Gross_Calculation_by_pt_gross, got_Net_Pay_Calculation_net_pay, get_minimum_Wage_to_database);
              ctcData.getMap().put(data,ctcComponent);


              ctcRepo.save(ctcData);
            rabbitMQSender.send(ctcData);
            return ctcData;
        } else if (!ctcRepo.existsById(ctcData.getE_code())) {
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

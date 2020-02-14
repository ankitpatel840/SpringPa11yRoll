package springpayroll.service;

import org.springframework.stereotype.Service;

@Service
public interface CtcCalculation {

    Long basie_calculation_methode(Long ctc, Long winimum_Wage);

    Long bonusCalulation(long BasicGet);

    Long employePfCalculation(long basicGet);

    Long gratutityCalculation(long basic);

    Long grossCalculation(long ctc_session, long employerPf, long gratuity);

    Long employerEsiCalculation(long gross);

    Long employeePf(long basicGet);

    Long employeeEsiCalculation(long gross);

    Long grossAndDeductionCalculation(long employee_esi, long employee_pf);

    Long netPayCalucaltion(long gross, long employee_pf, long employee_esi);

    Long netTakeHomeCalculation(long gross, long grossDed);

    Long ptGrossCalculation(long netpay, long grossDed);

    Long differneceCalculation(long netTakeHome, long netPay);

    Long home_Rent_Allowance(long basic, long bonus, long gross_deducted, long netpay, long hr);
}

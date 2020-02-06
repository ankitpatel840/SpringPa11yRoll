package springpayroll.service;

import org.springframework.stereotype.Component;

@Component
public interface CtcCalculation
{
    public Long basie_calculation_methode(Long ctc, Long winimum_Wage);


    public Long bonusCalulation(long BasicGet);

    public Long employePfCalculation(long basicGet);

    public Long gratutityCalculation(long basic);

    public Long grossCalculation(long ctc_session, long employerPf, long gratuity);

    public Long employerEsiCalculation(long gross);

    public  Long employeePf(long basicGet);

    public  Long employeeEsiCalculation(long gross);

    public  Long grossAndDeductionCalculation(long employee_esi, long employee_pf);

    public  Long netPayCalucaltion(long gross, long employee_pf, long employee_esi);

    public  Long netTakeHomeCalculation(long gross, long grossDed);

    public  Long ptGrossCalculation(long netpay, long grossDed);

    public Long differneceCalculation(long netTakeHome, long netPay);

    public  Long home_Rent_Allowance(long basic, long bonus, long gross_deducted, long netpay, long hr);
}

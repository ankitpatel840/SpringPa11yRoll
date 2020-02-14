package springpayroll.service;

import org.springframework.stereotype.Service;

@Service
class CtcImplementation implements CtcCalculation {
    // Basic_Calculation_Methode_Is_Created________________________________________________________________________________________________

    public Long basie_calculation_methode(Long ctc, Long winimum_Wage) {
        long basic;
        long ctcCheck = (ctc * 30 / 100);
        if (ctcCheck > winimum_Wage) {
            basic = (ctcCheck) + ((ctcCheck) - winimum_Wage) * 12 / 100 * 76 / 100;
        } else {
            if (ctcCheck > winimum_Wage) {
                basic = Math.round(ctcCheck);
            } else {
                basic = Math.round(winimum_Wage);
            }
        }
        System.out.println("........" + basic);
        return basic;

    }

//Bonus_Caluculation_Methode_Is_Created_____________________________________________________________________________________________


    public Long bonusCalulation(long BasicGet) {

        return (Math.round(BasicGet * 8.33 / 100));
    }

//Employer_PF_Calculation_Methode_IS_Created_______________________________________________________________________________________


    public Long employePfCalculation(long basicGet) {
        return (Math.round(basicGet * 12.0 / 100));
    }

    //Gratutity_Claculation_Methode_IS_Created__________________________________________________________________________________________
    public Long gratutityCalculation(long basic) {
        return Math.round((basic * 4.81 / 100));
    }

    //Gross_Claculation_Methode_IS_Created___________________________________________________________________________________________
    public Long grossCalculation(long ctc_session, long employerPf, long gratuity) {
        return Math.round((ctc_session - (employerPf + gratuity)) / (1 + 0.0475));

    }

    //Emloyer_ESI_Calculation_IS_Created______________________________________________________________________________________________
    public Long employerEsiCalculation(long gross) {
        return Math.round((gross * 4.75 / 100));
    }


    //Employee_PF_Calculation_IS_Created__________________________________________________________________________________________________
    public Long employeePf(long basicGet) {
        return Math.round((basicGet * 12.0 / 100));
    }

    //Employe_ESI_Calculation_IS_Created_______________________________________________________________________________________________
    public Long employeeEsiCalculation(long gross) {
        return Math.round((gross * 1.75 / 100));

    }

    //Gross_AND_Deduction_Calculation_IS_Created_______________________________________________________________________________________-
    public Long grossAndDeductionCalculation(long employee_esi, long employee_pf) {
        return employee_esi + employee_pf;
    }


    //NET_PAY_Calculation_IS_Created______________________________________________________________________________________________
    public Long netPayCalucaltion(long gross, long employee_pf, long employee_esi) {
        return gross - employee_pf - employee_esi;
    }

    //NET_TAKE_HOME_IS_CREATED__________________________________________________________________________________________________________
    public Long netTakeHomeCalculation(long gross, long grossDed) {
        return gross - grossDed;
    }

    //PT_Gross_Calculation_IS_Created________________________________________________________________________________________________
    public Long ptGrossCalculation(long netpay, long grossDed) {
        return netpay + grossDed;
    }

    //Difference_Calculation_IS Created_______________________________________________________________________________________________
    public Long differneceCalculation(long netTakeHome, long netPay) {

        return netTakeHome - netPay;
    }


    public Long home_Rent_Allowance(long basic, long bonus, long gross_deducted, long netpay, long hr) {
        long hra_inter_if;
        long hra;
        long hra_calc = netpay + gross_deducted - basic - bonus;
        hra_inter_if = Math.max(hra_calc, 0);
        long basic_mul_hra_percentage = basic * hr / 100;

        if (hra_inter_if < basic_mul_hra_percentage) {
            hra = hra_calc;
        } else {
            hra = basic * hr / 100;
        }
        return hra;
    }


}

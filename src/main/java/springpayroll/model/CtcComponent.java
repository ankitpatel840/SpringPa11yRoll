package springpayroll.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Embeddable;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CtcComponent {


    private Long h_R_A;

    private Long net_Take__Home;


    private Long basic;

    private Long bonus;

    private Long employer_Esi;

    private Long gratuity;

    private Long gross;

    private Long employee_Pf;

    private Long employee_Esi;

    private Long employer_Pf;

    private Long gross_Ded;

    private Long diff;

    private Long pt_Gross;


    private Long net_pay;


    private Long minimum_Wage;


}

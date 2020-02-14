package springpayroll.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class CtcData {


    @Id()
    private String e_code;
    @Column

    private String ename;

    @Column
    private Long h_R_A;
    @Column
    private Long net_Take__Home;
    @Column
    private Long ctc;
    @Column
    private Long basic;
    @Column
    private Long bonus;
    @Column
    private Long employer_Esi;
    @Column
    private Long gratuity;
    @Column
    private Long gross;
    @Column
    private Long employee_Pf;
    @Column
    private Long employee_Esi;
    @Column
    private Long employer_Pf;
    @Column
    private Long gross_Ded;
    @Column
    private Long diff;
    @Column
    private Long pt_Gross;
    @Column
    private String lOC;
    @Column
    private Long net_pay;

    @Column
    private Long minimum_Wage;


    public CtcData(String e_code) {

    }

    public CtcData(String e_code, String ename) {
        this.e_code = e_code;
        this.ename = ename;
    }

    public void setlOC(String lOC) {
        this.lOC = lOC;
    }
}


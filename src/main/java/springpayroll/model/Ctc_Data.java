package springpayroll.model;

import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity

@Service
public class Ctc_Data {


    @Id()

    private String e_code;
    @Column
//    @Setter
//    @Getter
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

    public String getE_code() {
        return e_code;
    }

    public void setE_code(String e_Code) {
        this.e_code = e_Code;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String e_Name) {
        this.ename = e_Name;
    }

    public Long getH_R_A() {
        return h_R_A;
    }

    public void setH_R_A(Long h_R_A) {
        this.h_R_A = h_R_A;
    }

    public Long getNet_Take__Home() {
        return net_Take__Home;
    }

    public void setNet_Take__Home(Long net_Take__Home) {
        this.net_Take__Home = net_Take__Home;
    }

    public Long getCtc() {
        return ctc;
    }

    public void setCtc(Long ctc) {
        this.ctc = ctc;
    }

    public Long getBasic() {
        return basic;
    }

    public void setBasic(Long basic) {
        this.basic = basic;
    }

    public Long getBonus() {
        return bonus;
    }

    public void setBonus(Long bonus) {
        this.bonus = bonus;
    }

    public Long getEmployer_Esi() {
        return employer_Esi;
    }

    public void setEmployer_Esi(Long employer_Esi) {
        this.employer_Esi = employer_Esi;
    }

    public Long getGratuity() {
        return gratuity;
    }

    public void setGratuity(Long gratuity) {
        this.gratuity = gratuity;
    }

    public Long getGross() {
        return gross;
    }

    public void setGross(Long gross) {
        this.gross = gross;
    }

    public Long getEmployee_Pf() {
        return employee_Pf;
    }

    public void setEmployee_Pf(Long employee_Pf) {
        this.employee_Pf = employee_Pf;
    }

    public Long getEmployee_Esi() {
        return employee_Esi;
    }

    public void setEmployee_Esi(Long employee_Esi) {
        this.employee_Esi = employee_Esi;
    }

    public Long getEmployer_Pf() {
        return employer_Pf;
    }

    public void setEmployer_Pf(Long employer_Pf) {
        this.employer_Pf = employer_Pf;
    }

    public Long getGross_Ded() {
        return gross_Ded;
    }

    public void setGross_Ded(Long gross_Ded) {
        this.gross_Ded = gross_Ded;
    }

    public Long getDiff() {
        return diff;
    }

    public void setDiff(Long diff) {
        this.diff = diff;
    }

    public Long getPt_Gross() {
        return pt_Gross;
    }

    public void setPt_Gross(Long pt_Gross) {
        this.pt_Gross = pt_Gross;
    }

    public String getlOC() {
        return lOC;
    }

    public void setlOC(String lOC) {
        this.lOC = lOC;
    }

    public Long getNet_pay() {
        return net_pay;
    }

    public void setNet_pay(Long net_pay) {
        this.net_pay = net_pay;
    }

    public Long getMinimum_Wage() {
        return minimum_Wage;
    }

    public void setMinimum_Wage(Long minimum_Wage) {
        this.minimum_Wage = minimum_Wage;
    }

    @Override
    public String toString() {
        return "CtcData{" +
                "e_Code='" + e_code + '\'' +
                ", e_Name='" + ename + '\'' +
                ", h_R_A=" + h_R_A +
                ", net_Take__Home=" + net_Take__Home +
                ", ctc=" + ctc +
                ", basic=" + basic +
                ", bonus=" + bonus +
                ", employer_Esi=" + employer_Esi +
                ", gratuity=" + gratuity +
                ", gross=" + gross +
                ", employee_Pf=" + employee_Pf +
                ", employee_Esi=" + employee_Esi +
                ", employer_Pf=" + employer_Pf +
                ", gross_Ded=" + gross_Ded +
                ", diff=" + diff +
                ", pt_Gross=" + pt_Gross +
                ", lOC='" + lOC + '\'' +
                ", net_pay=" + net_pay +
                ", minimum_Wage=" + minimum_Wage +
                '}';
    }

    public Ctc_Data() {
    }

    public Ctc_Data(String e_code) {

    }

    public Ctc_Data(String e_code, String ename) {
        this.e_code = e_code;
        this.ename = ename;
    }
}


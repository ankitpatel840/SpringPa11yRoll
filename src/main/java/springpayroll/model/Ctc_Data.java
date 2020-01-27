package springpayroll.model;

import javax.persistence.*;

@org.springframework.stereotype.Repository

@Entity
public class Ctc_Data
{


  //  @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Id
    private    String e_Code;
    @Column
    private     String e_Name;
   @Column
  private   Long h_R_A;
   @Column
 private Long net_Take__Home;
   @Column
  private  Long ctc;
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
   private  Long employer_Pf;
   @Column
     private Long gross_Ded;
   @Column
    private  Long diff;
   @Column
    private  Long pt_Gross;
   @Column

    private  String lOC;
   @Column
   private Long net_pay;

@Column
    private Long minimum_Wage;

public String getE_Code() {
        return e_Code;
    }

    public void setE_Code(String e_Code) {
        this.e_Code = e_Code;
    }

    public String getE_Name() {
        return e_Name;
    }

    public void setE_Name(String e_Name) {
        this.e_Name = e_Name;
    }

    public long getH_R_A() {
        return h_R_A;
    }

    public void setH_R_A(long h_R_A) {
        this.h_R_A = h_R_A;
    }

    public long getNet_Take__Home() {
        return net_Take__Home;
    }

    public void setNet_Take__Home(long net_Take__Home) {
        this.net_Take__Home = net_Take__Home;
    }

    public long getCtc() {
        return ctc;
    }

    public void setCtc(long ctc) {
        this.ctc = ctc;
    }

    public long getBasic() {
        return basic;
    }

    public void setBasic(long basic) {
        this.basic = basic;
    }

    public long getBonus() {
        return bonus;
    }

    public void setBonus(long bonus) {
        this.bonus = bonus;
    }

    public long getEmployer_Esi() {
        return employer_Esi;
    }

    public void setEmployer_Esi(long employer_Esi) {
        this.employer_Esi = employer_Esi;
    }

    public long getGratuity() {
        return gratuity;
    }

    public void setGratuity(long gratuity) {
        this.gratuity = gratuity;
    }

    public long getGross() {
        return gross;
    }

    public void setGross(long gross) {
        this.gross = gross;
    }

    public long getEmployee_Pf() {
        return employee_Pf;
    }

    public void setEmployee_Pf(long employee_Pf) {
        this.employee_Pf = employee_Pf;
    }

    public long getEmployee_Esi() {
        return employee_Esi;
    }

    public void setEmployee_Esi(long employee_Esi) {
        this.employee_Esi = employee_Esi;
    }

    public long getEmployer_Pf() {
        return employer_Pf;
    }

    public void setEmployer_Pf(long employer_Pf) {
        this.employer_Pf = employer_Pf;
    }

    public long getGross_Ded() {
        return gross_Ded;
    }

    public void setGross_Ded(long gross_Ded) {
        this.gross_Ded = gross_Ded;
    }

    public long getDiff() {
        return diff;
    }

    public void setDiff(long diff) {
        this.diff = diff;
    }

    public long getPt_Gross() {
        return pt_Gross;
    }

    public void setPt_Gross(long pt_Gross) {
        this.pt_Gross = pt_Gross;
    }

    public String getlOC() {
        return lOC;
    }

    public void setlOC(String lOC) {
        this.lOC = lOC;
    }

    public long getNet_pay() {
        return net_pay;
    }

    public void setNet_pay(long net_pay) {
        this.net_pay = net_pay;
    }

    public long getMinimum_Wage() {
        return minimum_Wage;
    }

    public void setMinimum_Wage(long minimum_Wage) {
        this.minimum_Wage = minimum_Wage;
    }


    @Override
    public String toString() {
        return "CtcData{" +
                "e_Code='" + e_Code + '\'' +
                ", e_Name='" + e_Name + '\'' +
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
}


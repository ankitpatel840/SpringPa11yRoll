package springpayroll.model;

import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Service
public class BranchData {


    @Id
    private String STATE;
    @Column()
    private String STATE_CODE;
    @Column
    private Long MINIMUM_WAGES;
    @Column()
    private Long HRA_PER;

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getSTATE_CODE() {
        return STATE_CODE;
    }

    public void setSTATE_CODE(String STATE_CODE) {
        this.STATE_CODE = STATE_CODE;
    }

    public Long getMINIMUM_WAGES() {
        return MINIMUM_WAGES;
    }

    public void setMINIMUM_WAGES(Long MINIMUM_WAGES) {
        this.MINIMUM_WAGES = MINIMUM_WAGES;
    }

    public Long getHRA_PER() {
        return HRA_PER;
    }

    public void setHRA_PER(Long HRA_PER) {
        this.HRA_PER = HRA_PER;
    }
}

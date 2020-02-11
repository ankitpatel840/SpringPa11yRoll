package springpayroll.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Service
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BranchData {


    @Id
    private String STATE;
    @Column()
    private String STATE_CODE;
    @Column
    private Long MINIMUM_WAGES;
    @Column()
    private Long HRA_PER;

}

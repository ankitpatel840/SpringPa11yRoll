package springpayroll.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;


@Entity
@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ctc")
public class CtcData {


    @Id
    private String e_code;

    private String ename;

    private String lOC;

    private Long ctc;
    @ElementCollection
    Map<String, CtcComponent> map = new HashMap<>();

    public CtcData(String e_code) {

    }

    public CtcData(String e_code, String ename) {
        this.e_code = e_code;
        this.ename = ename;
    }


}


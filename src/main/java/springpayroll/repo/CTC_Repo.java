package springpayroll.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springpayroll.model.Ctc_Data;




@Repository
public interface CTC_Repo extends JpaRepository<Ctc_Data, String>
{



// List<CtcData> findByE_Name(String N_Name);

}

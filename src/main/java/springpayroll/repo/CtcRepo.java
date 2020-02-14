package springpayroll.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springpayroll.model.CtcData;


@Repository
public interface CtcRepo extends JpaRepository<CtcData, String>
{

 boolean existsByEname(String ename);


// @Query("select * from ctcdata where loc=? and  ")
// boolean existByLoc(String loc);


}

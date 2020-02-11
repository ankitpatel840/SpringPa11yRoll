package springpayroll.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import springpayroll.model.BranchData;

public interface BranchRepo extends JpaRepository<BranchData,String>
{

}

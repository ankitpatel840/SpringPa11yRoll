package springpayroll.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springpayroll.model.BranchData;

@Repository
public interface branch_Repo extends JpaRepository<BranchData,String>
{

}

package springpayroll.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import springpayroll.model.BranchData;

@org.springframework.stereotype.Repository("branchdata")
public interface branch_Repo extends JpaRepository<BranchData,String>
{

}

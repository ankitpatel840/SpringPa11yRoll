package springpayroll.impl;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import springpayroll.model.BranchData;
import springpayroll.model.Ctc_Data;
import springpayroll.repo.CTC_Repo;
import springpayroll.repo.branch_Repo;
import springpayroll.service.CtcCalculation;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@Service
class CtcControllerMethodeTest {


   @MockBean
    CTC_Repo ctc_repo;
   @MockBean
    branch_Repo branch_repo;

    @MockBean
    CtcCalculation ctcCalculation;

   @MockBean
    BranchData branchData;
      @MockBean
    Ctc_Data ctcData;
    @MockBean
      CtcControllerImpl ctcControllerImpl;




    @BeforeEach
    public void setUp()
    {
       MockitoAnnotations.initMocks(this);

        ctc_repo= org.mockito.Mockito.mock(CTC_Repo.class);
        branch_repo= org.mockito.Mockito.mock(branch_Repo.class);
        ctcData=org.mockito.Mockito.mock(Ctc_Data.class);
        ctcControllerImpl=org.mockito.Mockito.mock(CtcControllerImpl.class);


    }








    @Test
    void login_methode_cheaking()
    {
//        Ctc_Data ctcData=new Ctc_Data("123","Ank");
//        when
    }

    @Test
    void all_the_calcution() {
    }

    @Test
    void all_the_calcution_find_New() {
    }

    @Test
    void login_methode_Post()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllCtData()
    {

        MockitoAnnotations.initMocks(this);

        Ctc_Data ctc_Data=new Ctc_Data("Ank1","Ank");
        List<Ctc_Data> list= Arrays.asList(ctcData,ctc_Data);
        when(ctc_repo.findAll()).thenReturn(list);
        when(ctcControllerImpl.getAllUsersCtcData()).thenReturn(list);
        System.out.println(ctc_repo.findAll());
        System.out.println(ctcControllerImpl.getAllUsersCtcData());
        List<Ctc_Data>getList=ctcControllerImpl.getAllUsersCtcData();
        verify(ctc_repo).findAll();
        assertThat(getList,is(list));
    }

    @Test
    void getCtcData()
    {
       // MockitoAnnotations.initMocks(this);
        String id="Ank12";
       Ctc_Data ctcData=new Ctc_Data("Ank12","Ank");
        when(ctc_repo.getOne(id)).thenReturn(ctcData);
        System.out.println(ctc_repo.getOne(id));

        Ctc_Data ctcController1=ctcControllerImpl.getUserCtcData(id);
        System.out.println(ctcController1);


        MatcherAssert.assertThat(ctcController1,is(ctc_repo.getOne(id)));



    }

    @Test
    void deleteOne()
    {
//        when(queueReository.findByUserId("tarun")).thenReturn(defaultQueue);
//
//        DefaultQueue deletedQueue = queueServices.DeleteUserQueue("tarun");
//
//        verify(queueServices).DeleteUserQueue("tarun");
//        assertThat(deletedQueue,is(defaultQueue));
//
//        System.out.println(e_code);
//        Ctc_Data ab=ctc_repo.getOne(e_code);
//        System.out.println("ADS");
//        ctc_repo.delete(ab);
//        System.out.println("df");
        Ctc_Data ctcData=new Ctc_Data("Ak11","Ank");

         when(ctc_repo.getOne("Ak11")).thenReturn(ctcData);



    }

    @Test
    void put_CtcData()
    {

    }

    @Test
    void deleteAllData()
    {

    }
}
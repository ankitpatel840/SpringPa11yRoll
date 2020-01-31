package springpayroll.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import springpayroll.model.BranchData;
import springpayroll.model.Ctc_Data;
import springpayroll.repo.CTC_Repo;
import springpayroll.repo.branch_Repo;
import springpayroll.service.CtcCalculation;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

class CtcControllerMethodeTest {


     @MockBean
    CTC_Repo ctc_repo;
     @MockBean
    private branch_Repo branch_repo;

    @MockBean
    CtcCalculation ctcCalculation;

   @MockBean
    BranchData branchData;
      @MockBean
    Ctc_Data ctcData;
      @MockBean
      CtcControllerImpl ctcController;




    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        ctc_repo= org.mockito.Mockito.mock(CTC_Repo.class);
        branch_repo= org.mockito.Mockito.mock(branch_Repo.class);
        ctcController=org.mockito.Mockito.mock(CtcControllerImpl.class);

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
    void login_methode_Post() {
    }

    @Test
    void getAllCtData()
    {
//        DefaultQueue defaultQueue1 = new DefaultQueue("tarun-2","queue-2");
//
//        List<DefaultQueue> list = Arrays.asList(defaultQueue,defaultQueue1);
//
//        when(queueReository.findAll()).thenReturn(list);
//
//        List<DefaultQueue> getList = queueServices.getMessage();
//
//        verify(queueReository).findAll();
//
//        assertThat(getList,is(list));


        Ctc_Data ctc_Data=new Ctc_Data("Ank1","Ank");
        List<Ctc_Data> list= Arrays.asList(ctcData,ctc_Data);
        when(ctc_repo.findAll()).thenReturn(list);
        System.out.println(ctc_repo.findAll());
                  List<Ctc_Data> ctcData1=ctcController.getAllCtData();
        List<Ctc_Data>getList=ctc_repo.findAll();
       // verify(ctc_repo).findAll();
        assertThat(ctcData1,is(list));
    }

    @Test
    void getCtcData()
    {
//        MockitoAnnotations.initMocks(this);
//        String id="Ank12";
//       Ctc_Data ctcData=new Ctc_Data("Ank12","Ank");
//        when(ctc_repo.findById(id)).thenReturn(java.util.Optional.of(ctcData));
//      //  Optional<Ctc_Data> ctcController1=ctcController.getCtcData(id);
//
      //  assertThat(,is(ctc_repo.findById(id)));

    }

    @Test
    void deleteOne() {
    }

    @Test
    void put_CtcData() {
    }

    @Test
    void deleteAllData() {
    }
}
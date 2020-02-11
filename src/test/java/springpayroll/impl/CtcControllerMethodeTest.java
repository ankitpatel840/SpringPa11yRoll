package springpayroll.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import springpayroll.exception.ECodeNotFoundException;
import springpayroll.exception.UserAllreadyExistException;
import springpayroll.model.BranchData;
import springpayroll.model.CtcData;
import springpayroll.repo.BranchRepo;
import springpayroll.repo.CtcRepo;
import springpayroll.service.CtcCalculation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@WebMvcTest(value = CtcControllerMethode.class)
class CtcControllerMethodeTest {
    @Autowired
    CtcControllerMethode controllerMethode;

    @MockBean
    CtcRepo ctcRepo;
    @MockBean
    BranchRepo BranchRepo;
    @MockBean
    CtcCalculation ctcCalculation;
    @MockBean
    BranchData branchData;
    @MockBean
    CtcData CTCData;


    @BeforeEach
    public void setUp() {
        //MockitoAnnotations.initMocks(this);

        ctcRepo = org.mockito.Mockito.mock(CtcRepo.class);
        BranchRepo = org.mockito.Mockito.mock(BranchRepo.class);

        ctcCalculation = org.mockito.Mockito.mock(CtcCalculation.class);
        CTCData = org.mockito.Mockito.mock(CtcData.class);

    }


    @Test
    void ctcCalculationDataSavingInDataBase() {
    }

    @Test
    void newUserCrete() throws ECodeNotFoundException, UserAllreadyExistException {
       // MockitoAnnotations.initMocks(this);
        CtcData ctcData = new CtcData("Ankit", "123");
        when(ctcRepo.save(ctcData)).thenReturn(ctcData);
        System.out.println(ctcRepo.save(ctcData));
        System.out.println(controllerMethode);

        CtcData ctcData1 = controllerMethode.newUserCrete("Ankit", "123");
        System.out.println(ctcData1);
        assertEquals(ctcData, ctcData1);


    }

    @Test
    void getAllUsersCtcData() {
       // MockitoAnnotations.initMocks(this);
        CtcData ctcData = new CtcData("123", "Gunjan");
        CtcData ctcData1 = new CtcData("12", "Guj");
             List<CtcData> ctcList=new ArrayList<CtcData>();
                ctcList.add(ctcData);
                ctcList.add(ctcData1);
        System.out.println(ctcData1);
        when(ctcRepo.findAll()).thenReturn(ctcList);
        System.out.println(ctcRepo.findAll());
        List<CtcData> as = controllerMethode.getAllUsersCtcData();
        System.out.println(as);
        assertEquals(ctcList, as);


    }

    @Test
    void getUserCtcData() throws ECodeNotFoundException {
        MockitoAnnotations.initMocks(this);
        String e = "123";
        CtcData ctcData = new CtcData();
        ctcData.setE_code(e);
        ctcData.setEname("Ank");

        when(ctcRepo.getOne(e)).thenReturn(ctcData);
        System.out.println(ctcRepo.getOne(e));
        System.out.println(controllerMethode);

        CtcData ctcData1 = controllerMethode.getUserCtcData(e);
        System.out.println(ctcData1);
        assertEquals(ctcData, ctcData1);
    }

    @Test
    void deleteOneUserCtcData() throws ECodeNotFoundException {
        CtcData ctcData = new CtcData("123", "Ank");
        CtcData  ctcData1=ctcRepo.getOne("123");
           ctcRepo.delete(ctcData1);

           String s=controllerMethode.deleteOneUserCtcData("123");
          assertEquals("Your Ctc Data Deleted",s);

    }

    @Test
    void updateUserCtcData() throws ECodeNotFoundException {
        // MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(this);
        CtcData CTCData1 = new CtcData("123", "as");
        CtcData CTCData = new CtcData("123", "Ank");
        when(ctcRepo.existsById("123")).thenReturn(true);
        boolean as=ctcRepo.existsById("123");
        assertTrue(as);

        when(ctcRepo.save( CTCData1)).thenReturn(CTCData);

        CtcData ctcData=controllerMethode.updateUserCtcData("123",CTCData1);

        assertEquals(CTCData,ctcData);



    }

    @Test
    void deleteAllUserCtcData() {
    }
}
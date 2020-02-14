package springpayroll.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import springpayroll.exception.ECodeNotFoundException;
import springpayroll.exception.InvalidStateCodeCException;
import springpayroll.exception.UserAllreadyExistException;
import springpayroll.model.BranchData;
import springpayroll.model.CtcData;
import springpayroll.repo.BranchRepo;
import springpayroll.repo.CtcRepo;
import springpayroll.service.CtcCalculation;
import springpayroll.service.RabbitMQSender;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(value = CtcControllerMethode.class)
class CtcControllerMethodeTest {
    @InjectMocks
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
    CtcData ctcData;
@MockBean
RabbitMQSender rabbitMQSender;

    @BeforeEach
    public void setUp() {

        ctcRepo = org.mockito.Mockito.mock(CtcRepo.class);
        BranchRepo = org.mockito.Mockito.mock(BranchRepo.class);

        ctcCalculation = org.mockito.Mockito.mock(CtcCalculation.class);
        ctcData = org.mockito.Mockito.mock(CtcData.class);
        rabbitMQSender=org.mockito.Mockito.mock(RabbitMQSender.class);


    }


    @Test
    void ctcCalculationDataSavingInDataBase() throws InvalidStateCodeCException, ECodeNotFoundException {
        MockitoAnnotations.initMocks(this);

        String e_code = "123";
        String ename = "Ank";
        String state = "JAIPUR";
        long ctc = 0L;

        BranchData branchData1 = new BranchData();


        when(BranchRepo.existsById(state)).thenReturn(true);
        when(ctcRepo.existsById(e_code)).thenReturn(true);


        when(BranchRepo.findById(state)).thenReturn(java.util.Optional.of(branchData));

        when(branchData.getMINIMUM_WAGES()).thenReturn(0L);

        when(branchData.getHRA_PER()).thenReturn(50L);
        when(branchData.getSTATE_CODE()).thenReturn(state);
        when(ctcCalculation.basie_calculation_methode(19990L, 12244L)).thenReturn(12244L);
        when(ctcCalculation.bonusCalulation(12244L)).thenReturn(122444L);
        when(ctcCalculation.employePfCalculation(12244L)).thenReturn(12244L);
        when(ctcCalculation.gratutityCalculation(12244L)).thenReturn(122244L);
        when(ctcCalculation.grossCalculation(ctc, 12244L, 122244L)).thenReturn(12244L);

        when(ctcCalculation.employerEsiCalculation(1222)).thenReturn(12244L);
        when(ctcCalculation.employeePf(12244L)).thenReturn(12244L);
        when(ctcCalculation.employeeEsiCalculation(12444L)).thenReturn(12244L);
        when(ctcCalculation.grossAndDeductionCalculation(12244L, 122244L)).thenReturn(12244L);
        when(ctcCalculation.netPayCalucaltion(1222444L, 122244L, 12244L)).thenReturn(12244L);
        when(ctcCalculation.netTakeHomeCalculation(122244L, 122244L)).thenReturn(12244L);
        when(ctcCalculation.ptGrossCalculation(122244, 12244L)).thenReturn(122244L);
        when(ctcCalculation.differneceCalculation(122244, 1222444L)).thenReturn(12244L);
        when(ctcCalculation.home_Rent_Allowance(12244L, 122444L, 12244L, 12244L, 50L)).thenReturn(12244L);

        CtcData ctcData = new CtcData("123", "Ank", 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, state, 0L, 0L);
        System.out.println("AaDA");

        CtcData ctcData1 = controllerMethode.ctcCalculationDataSavingInDataBase(ctc, e_code, state, ename);


        assertEquals(ctcData, ctcData1);


    }





    @Test
    void ctcCalculationDataSavingInDataBaseException() throws ECodeNotFoundException {
        MockitoAnnotations.initMocks(this);
      try {
          String e_code = "123";
          String ename = "Ank";
          String state = "JAIPUR";
          long ctc = 0L;



          System.out.println(BranchRepo);
          System.out.println(ctcRepo);


          when(BranchRepo.existsById("Bangalore")).thenReturn(true);
          when(ctcRepo.existsById(e_code)).thenReturn(true);

          System.out.println(BranchRepo.existsById(state) && ctcRepo.existsById(e_code));
          when(BranchRepo.findById(state)).thenReturn(java.util.Optional.of(branchData));

          when(branchData.getMINIMUM_WAGES()).thenReturn(0L);

          when(branchData.getHRA_PER()).thenReturn(50L);
          when(branchData.getSTATE_CODE()).thenReturn(state);
          when(ctcCalculation.basie_calculation_methode(19990L, 12244L)).thenReturn(12244L);
          when(ctcCalculation.bonusCalulation(12244L)).thenReturn(122444L);
          when(ctcCalculation.employePfCalculation(12244L)).thenReturn(12244L);
          when(ctcCalculation.gratutityCalculation(12244L)).thenReturn(122244L);
          when(ctcCalculation.grossCalculation(ctc, 12244L, 122244L)).thenReturn(12244L);

          when(ctcCalculation.employerEsiCalculation(1222)).thenReturn(12244L);
          when(ctcCalculation.employeePf(12244L)).thenReturn(12244L);
          when(ctcCalculation.employeeEsiCalculation(12444L)).thenReturn(12244L);
          when(ctcCalculation.grossAndDeductionCalculation(12244L, 122244L)).thenReturn(12244L);
          when(ctcCalculation.netPayCalucaltion(1222444L, 122244L, 12244L)).thenReturn(12244L);
          when(ctcCalculation.netTakeHomeCalculation(122244L, 122244L)).thenReturn(12244L);
          when(ctcCalculation.ptGrossCalculation(122244, 12244L)).thenReturn(122244L);
          when(ctcCalculation.differneceCalculation(122244, 1222444L)).thenReturn(12244L);
          when(ctcCalculation.home_Rent_Allowance(12244L, 122444L, 12244L, 12244L, 50L)).thenReturn(12244L);

          CtcData ctcData = new CtcData("123", "Ank", 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, state, 0L, 0L);
          System.out.println("AaDA");

          CtcData ctcData1 = controllerMethode.ctcCalculationDataSavingInDataBase(ctc, e_code, state, ename);


          assertEquals(ctcData, ctcData1);
      }
      catch (InvalidStateCodeCException e)
      {
          e.printStackTrace();
      }


    }


    @Test
    void ctcCalculationDataSavingInDataBaseECodeException() throws InvalidStateCodeCException {
        MockitoAnnotations.initMocks(this);
        try {
            String e_code = "123";
            String ename = "Ank";
            String state = "JAIPUR";
            long ctc = 0L;



            System.out.println(BranchRepo);
            System.out.println(ctcRepo);


            when(BranchRepo.existsById("Bangalore")).thenReturn(true);
            when(ctcRepo.existsById("1234")).thenReturn(true);

            System.out.println(BranchRepo.existsById(state) && ctcRepo.existsById(e_code));
            when(BranchRepo.findById(state)).thenReturn(java.util.Optional.of(branchData));

            when(branchData.getMINIMUM_WAGES()).thenReturn(0L);

            when(branchData.getHRA_PER()).thenReturn(50L);
            when(branchData.getSTATE_CODE()).thenReturn(state);
            when(ctcCalculation.basie_calculation_methode(19990L, 12244L)).thenReturn(12244L);
            when(ctcCalculation.bonusCalulation(12244L)).thenReturn(122444L);
            when(ctcCalculation.employePfCalculation(12244L)).thenReturn(12244L);
            when(ctcCalculation.gratutityCalculation(12244L)).thenReturn(122244L);
            when(ctcCalculation.grossCalculation(ctc, 12244L, 122244L)).thenReturn(12244L);

            when(ctcCalculation.employerEsiCalculation(1222)).thenReturn(12244L);
            when(ctcCalculation.employeePf(12244L)).thenReturn(12244L);
            when(ctcCalculation.employeeEsiCalculation(12444L)).thenReturn(12244L);
            when(ctcCalculation.grossAndDeductionCalculation(12244L, 122244L)).thenReturn(12244L);
            when(ctcCalculation.netPayCalucaltion(1222444L, 122244L, 12244L)).thenReturn(12244L);
            when(ctcCalculation.netTakeHomeCalculation(122244L, 122244L)).thenReturn(12244L);
            when(ctcCalculation.ptGrossCalculation(122244, 12244L)).thenReturn(122244L);
            when(ctcCalculation.differneceCalculation(122244, 1222444L)).thenReturn(12244L);
            when(ctcCalculation.home_Rent_Allowance(12244L, 122444L, 12244L, 12244L, 50L)).thenReturn(12244L);

            CtcData ctcData = new CtcData("123", "Ank", 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, state, 0L, 0L);
            System.out.println("AaDA");

            CtcData ctcData1 = controllerMethode.ctcCalculationDataSavingInDataBase(ctc, e_code, state, ename);


            assertEquals(ctcData, ctcData1);
        }
        catch (ECodeNotFoundException e)
        {
            e.printStackTrace();
        }


    }




    @Test
    void newUserCrete() throws UserAllreadyExistException {
        MockitoAnnotations.initMocks(this);

          when(ctcRepo.existsById("12325236353253")).thenReturn(true);

        CtcData ctcData = new CtcData("Ankit", "123");
        when(ctcRepo.save(ctcData)).thenReturn(ctcData);

        CtcData ctcData1 = controllerMethode.newUserCrete("Ankit", "123");

        assertEquals(ctcData, ctcData1);


    }


    @Test
    void newUserCreteException() throws  UserAllreadyExistException {
        MockitoAnnotations.initMocks(this);
        try {
        when(ctcRepo.existsById("Ankit")).thenReturn(true);

        CtcData ctcData = new CtcData("Ankit", "123");
        when(ctcRepo.save(ctcData)).thenReturn(ctcData);

        CtcData ctcData1 = controllerMethode.newUserCrete("Ankit", "123");

        assertEquals(ctcData, ctcData1);}
      catch (UserAllreadyExistException e)
      {
          e.printStackTrace();
      }


    }









    @Test
    void getAllUsersCtcData() {
        MockitoAnnotations.initMocks(this);
        CtcData defaultQueue1 = new CtcData("tarun-2", "queue-2");
        CtcData defaultQueue = new CtcData("tarun-1", "queue-1");

        List<CtcData> list = Arrays.asList(defaultQueue, defaultQueue1);

        when(ctcRepo.findAll()).thenReturn(list);

        List<CtcData> getList = controllerMethode.getAllUsersCtcData();


        assertThat(getList, is(list));


    }

    @Test
    void getUserCtcData() throws ECodeNotFoundException {
        MockitoAnnotations.initMocks(this);
        String e_code = "123";
        CtcData ctcData = new CtcData();
        ctcData.setE_code(e_code);
        ctcData.setEname("Ank");
        when(ctcRepo.existsById(e_code)).thenReturn(true);


        when(ctcRepo.getOne(e_code)).thenReturn(ctcData);

        CtcData ctcData1 = controllerMethode.getUserCtcData(e_code);

        assertEquals(ctcData, ctcData1);
    }


    @Test
    void getUserCtcDataException() {
        try {
            MockitoAnnotations.initMocks(this);
            String e_code = "123";
            CtcData ctcData = new CtcData();
            ctcData.setE_code(e_code);
            ctcData.setEname("Ank");
            when(ctcRepo.existsById("1")).thenReturn(true);


            when(ctcRepo.getOne(e_code)).thenReturn(ctcData);

            CtcData ctcData1 = controllerMethode.getUserCtcData(e_code);

            assertEquals(ctcData, ctcData1);
        }catch (ECodeNotFoundException e)
        {
          e.printStackTrace();
        }

    }





    @Test
    void deleteOneUserCtcData() throws ECodeNotFoundException {
        MockitoAnnotations.initMocks(this);
        CtcData ctcData = new CtcData("123", "Ank");
        CtcData ctcData1 = ctcRepo.getOne("123");
        ctcRepo.delete(ctcData1);
        when(ctcRepo.existsById("123")).thenReturn(true);
        String s = controllerMethode.deleteOneUserCtcData("123");
        assertEquals("Your Ctc Data Deleted", s);

    }




    @Test
    void deleteOneUserCtcDataException() {
        try {
            MockitoAnnotations.initMocks(this);
            //CtcData
                    ctcData = new CtcData("123", "Ank");
          //  CtcData
                    ctcData = ctcRepo.getOne("123");
            ctcRepo.delete(ctcData);
            when(ctcRepo.existsById("12311")).thenReturn(true);
            String s = controllerMethode.deleteOneUserCtcData("123");
            assertEquals("Your Ctc Data Deleted", s);
        }catch (ECodeNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    void updateUserCtcData() throws ECodeNotFoundException {
        // MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(this);

        CtcData CTCData1 = new CtcData("123", "as");
        CtcData CTCData = new CtcData("123", "Ank");
        when(ctcRepo.existsById("123")).thenReturn(true);
        when(ctcRepo.findById("123")).thenReturn(java.util.Optional.of(CTCData));
        when(ctcRepo.save(CTCData)).thenReturn(CTCData);

        CtcData ctcData = controllerMethode.updateUserCtcData("123", CTCData1);

        assertEquals(CTCData, ctcData);

    }




@Test
    void updateUserCtcDataException() {
       try {
           MockitoAnnotations.initMocks(this);

           CtcData CTCData1 = new CtcData("123", "as");
           CtcData CTCData = new CtcData("123", "Ank");
           when(ctcRepo.existsById("124223")).thenReturn(true);
           when(ctcRepo.findById("123")).thenReturn(java.util.Optional.of(CTCData));
           when(ctcRepo.save(CTCData)).thenReturn(CTCData);

           CtcData ctcData = controllerMethode.updateUserCtcData("123", CTCData1);

           assertEquals(CTCData, ctcData);
       }catch (ECodeNotFoundException e)
       {
           e.printStackTrace();
       }

    }


}
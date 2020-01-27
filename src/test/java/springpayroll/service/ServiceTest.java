package springpayroll.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

   // Service service;


    @BeforeEach
    void setUp()
    {

    }

    @Test
    void basie_calculation_methode()
    {
        CtcImplementation service=new CtcImplementation();

        Long basic=service.basie_calculation_methode(19967L,12244L);
        assertEquals(12244,basic);
    }
@Test
    void basie_calculation_methode_Exception()
    {
        CtcImplementation service=new CtcImplementation();
        int ctcc=12244;
        String ctc="122dd";
        try {


            if (ctc.equals(ctcc)) {
                Long basic = service.basie_calculation_methode(19967L, 12244L);

                assertEquals(12244, basic);
            } else {

                throw new NumberFormatException("Invalied Long Number Enter ");


            }
        }catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void basie_calculation_methode_AirthmethicException() throws ArithmeticException
    {
        CtcImplementation service=new CtcImplementation();

       try {

               Long basic = service.basie_calculation_methode(19967L/0, 12244L/0);

               assertEquals(12244, basic);

       }catch (ArithmeticException e)
       {
           e.printStackTrace();
       }


    }
@Test
    void basie_calculation_methode_CException()
    {
        CtcImplementation service=new CtcImplementation();

        try {
            long a=19967L; long b=12244L;
             if(a==19967)
             {
                 throw  new ClassCastException("Should Give Long Number");
              }
             else{
                 Long basic=service.basie_calculation_methode(a,b);
                 assertEquals(12244, basic);
             }



        }catch (ClassCastException e)
        {
            e.printStackTrace();
        }


    }


    @Test
    void bonusCalulation()
    {
        CtcImplementation service=new CtcImplementation();
        Long bonus=service.bonusCalulation(12244);
        assertEquals(1020,bonus);
    }

    @Test
    void bonusCalulation_Number_Formet_Exception()
    {
        try {
            CtcImplementation service = new CtcImplementation();
            String as = "abbd";
            Long b = 12244L;
            if (!as.equals(b)) {
                throw new NumberFormatException("Invalied Long Number Enter ");
            }
            Long bonus = service.bonusCalulation(12244);
            assertEquals(1020, bonus);
        }catch (NumberFormatException E)
        {
            E.printStackTrace();
        }
    }


    @Test
    void bonusCalulation_Airthmetic_Exception()
    {
        try {
            CtcImplementation service = new CtcImplementation();

            Long b = 12244L;
            if (b!=0) {
                throw new ArithmeticException("Divide by zero");
            }
            Long bonus = service.bonusCalulation(12244);
            assertEquals(1020, bonus);
        }catch (ArithmeticException E)
        {
            E.printStackTrace();
        }
    }


    @Test
    void bonusCalulation_ClassCast_Exception()
    {
        try {
            CtcImplementation service = new CtcImplementation();

            Long b = 12244L;

            if (b!=12244) {
                throw new ClassCastException(" Number Should Be Long");
            }
            Long bonus = service.bonusCalulation(12244);
            assertEquals(1020, bonus);
        }catch (ClassCastException E)
        {
            E.printStackTrace();
        }
    }






    @Test
    void employePfCalculation()
    {
        CtcImplementation service=new CtcImplementation();
        Long bonus=service.employePfCalculation(12244);
        assertEquals(1469,bonus);
    }



    @Test
    void employePfCalculation_Number_Formet_Exception()
    {
        try {
            CtcImplementation service = new CtcImplementation();
            String as = "abbd";
            Long b = 12244L;
            if (!as.equals(b)) {
                throw new NumberFormatException("Invalied Long Number Enter ");
            }
            Long bonus = service.employePfCalculation(12244);
            assertEquals(1020, bonus);
        }catch (NumberFormatException E)
        {
            E.printStackTrace();
        }
    }

@Test
    void employePfCalculation_Airthmetic_Exception()
    {
        try {
            CtcImplementation service = new CtcImplementation();

            Long b = 12244L;
            if (b!=0) {
                throw new ArithmeticException("Divide by zero");
            }
            Long bonus = service.employePfCalculation(12244L);
            assertEquals(1020, bonus);
        }catch (ArithmeticException E)
        {
            E.printStackTrace();
        }
    }


    @Test
    void employePfCalculation_Class_Cast_Exception()
    {
        try {
            CtcImplementation service = new CtcImplementation();

            Long b = 12244L;

            if (b!=12244) {
                throw new ClassCastException(" Number Should Be Long");
            }
            Long bonus = service.employePfCalculation(12244);
            assertEquals(1469, bonus);
        }catch (ClassCastException E)
        {
            E.printStackTrace();
        }
    }

    @Test
    void gratutityCalculation()
    {
        CtcImplementation service=new CtcImplementation();
        Long bonus=service.gratutityCalculation(12244);
        assertEquals(589,bonus);
    }

    @Test
    void gratutityCalculation_Number_Formet_Exception()
    {
        try {
            CtcImplementation service = new CtcImplementation();
            String as = "abbd";
            Long b = 12244L;
            if (!as.equals(b)) {
                throw new NumberFormatException("Invalied Long Number Enter ");
            }
            Long bonus = service.gratutityCalculation(12244);
            assertEquals(589, bonus);
        }catch (NumberFormatException E)
        {
            E.printStackTrace();
        }
    }
    @Test
    void gratutityCalculation_Airthmethic_Exception()
    {
        try {
            CtcImplementation service = new CtcImplementation();

            Long b = 12244L;
            if (b!=0) {
                throw new ArithmeticException("Divide by zero");
            }
            Long bonus = service.gratutityCalculation(12244L);
            assertEquals(589, bonus);
        }catch (ArithmeticException E)
        {
            E.printStackTrace();
        }

    }
    @Test
    void gratutityCalculation_Class_Cast_Exception()
    {
        try {
            CtcImplementation service = new CtcImplementation();

            Long b = 12244L;

            if (b!=12244) {
                throw new ClassCastException(" Number Should Be Long");
            }
            Long bonus = service.gratutityCalculation(12244);
            assertEquals(589, bonus);
        }catch (ClassCastException E)
        {
            E.printStackTrace();
        }
    }

    @Test
    void grossCalculation()
    {
        CtcImplementation service=new CtcImplementation();
        Long  gross=service.grossCalculation(19967,1469,589);
        assertEquals(17097,gross);
    }


    @Test
    void grossCalculation_Number_Formet_Exception()
    {
        String  a="19967cxv";
        Long b=19967L;
        try {
            CtcImplementation service=new CtcImplementation();
            if (!a.equals(b)) {
                throw new NumberFormatException("Invalied Long Number Enter ");
            }
            Long gross = service.grossCalculation(19967, 1469, 589);
            assertEquals(17097,gross);
        }catch (NumberFormatException e)
        {
            e.printStackTrace();

        }
    }
    @Test
    void grossCalculation_Airthmethic_Exception()
    {
        try{
        CtcImplementation service=new CtcImplementation();
        Long gross = service.grossCalculation(19967, 1469, 589);
        assertEquals(17097,gross);}
        catch (ArithmeticException e)
        {
            e.printStackTrace();
        }
    }
    @Test
    void grossCalculation_Class_Cast_Exception() {
        try {
            CtcImplementation service = new CtcImplementation();
            Long a = 19967L;
            if (a == 19967) {
                throw new ClassCastException(" Number Should Be Long");
            }
            Long gross = service.grossCalculation(19967, 1469, 589);
            assertEquals(17097, gross);
        }catch (ClassCastException e)
        {
            e.printStackTrace();
        }
    }



    @Test
    void employerEsiCalculation()
    {
        CtcImplementation service = new CtcImplementation();
        Long em_ESi=service.employerEsiCalculation(17097);
        assertEquals(812,em_ESi);
    }


    @Test
    void employerEsiCalculation_Number_Formet_Exception()
    {
        try{
        String a="17097sgf";
        Long b=17097L;
        if(a.equals(b))
        {
            throw new NumberFormatException("Invalied Long Number Enter ");
        }
        CtcImplementation service = new CtcImplementation();
        Long em_ESi=service.employerEsiCalculation(17097);
        assertEquals(812,em_ESi);}
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
    }
    @Test
    void employerEsiCalculation_Airthmethic_Exception() {
        try {
            CtcImplementation service = new CtcImplementation();
            Long em_ESi = service.employerEsiCalculation(17097 / 0);
            assertEquals(812, em_ESi);

    }catch (ArithmeticException e)
        {
            e.printStackTrace();
        }
    }
    @Test
    void employerEsiCalculation_Class_Cast_Exception() {
        try {


            CtcImplementation service = new CtcImplementation();
            Long a = 812L;
            if (a == 812) {
                throw new ClassCastException(" Number Should Be Long");
            }
            Long em_ESi = service.employerEsiCalculation(17097);
            assertEquals(812, em_ESi);
        }catch (ClassCastException e)
        {
            e.printStackTrace();
        }
    }



    @Test
    void employeePf()
    {
        CtcImplementation service=new CtcImplementation();
        Long bonus=service.employePfCalculation(12244);
        assertEquals(1469,bonus);
    }

    @Test
    void employeePf_Number_Formet_Exception()
    {
        try {
        CtcImplementation service = new CtcImplementation();
        String as = "abbd";
        Long b = 12244L;
        if (!as.equals(b)) {
            throw new NumberFormatException("Invalied Long Number Enter ");
        }
        Long bonus = service.employePfCalculation(12244);
        assertEquals(1469, bonus);
    }catch (NumberFormatException E)
        {
            E.printStackTrace();
        }
    }


    @Test
    void employeePf_Aithmethic_Exception()
    {
        try {
            CtcImplementation service = new CtcImplementation();

            Long b = 12244L;
            if (b!=0) {
                throw new ArithmeticException("Divide by zero");
            }
            Long bonus = service.employePfCalculation(12244L);
            assertEquals(1020, bonus);
        }catch (ArithmeticException E)
        {
            E.printStackTrace();
        }
    }

    @Test
    void employeePf_Class_Cast_ExCeption()
    {
        try {
            CtcImplementation service = new CtcImplementation();

            Long b = 12244L;

            if (b!=12244) {
                throw new ClassCastException(" Number Should Be Long");
            }
            Long bonus = service.employePfCalculation(12244);
            assertEquals(1469, bonus);
        }catch (ClassCastException E)
        {
            E.printStackTrace();
        }
    }


    @Test
    void employeeEsiCalculation()
    {
        CtcImplementation service = new CtcImplementation();
        Long em_ESi=service.employeeEsiCalculation(17097);
        assertEquals(299,em_ESi);
    }

    @Test
    void employeeEsiCalculation_Number_Formet_Exception()
    {
        try{
            String a="17097sgf";
            Long b=17097L;
            if(!a.equals(b))
            {
                throw new NumberFormatException("Invalied Long Number Enter ");
            }
            CtcImplementation service = new CtcImplementation();
            Long em_ESi=service.employeeEsiCalculation(17097);
            assertEquals(299,em_ESi);}
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }

    }
    @Test
    void employeeEsiCalculation_AirthMethic_Exception()
    {
        try {
            CtcImplementation service = new CtcImplementation();
            Long em_ESi = service.employeeEsiCalculation(17097 / 0);
            assertEquals(299, em_ESi);

        }catch (ArithmeticException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void employeeEsiCalculation_Class_Cast_Exception()
    {
        CtcImplementation service=new CtcImplementation();
        Long a=812L;
        if(a!=812)
        {
            throw new ClassCastException(" Number Should Be Long");
        }
        Long em_ESi=service.employerEsiCalculation(17097);
        assertEquals(812,em_ESi);
    }



    @Test
    void grossAndDeductionCalculation()
    {
        CtcImplementation service=new CtcImplementation();
        Long grossDEd=service.grossAndDeductionCalculation(299,1469);
        assertEquals(1768,grossDEd);
    }
    @Test
    void grossAndDeductionCalculation_Number_Formet_Exception()
    {
        try {
            String a = "299A";
            Long b = 299L;
            if (!a.equals(b)) {
                throw new NumberFormatException("Invalied Long Number Enter ");
            }

            CtcImplementation service = new CtcImplementation();
            Long grossDEd = service.grossAndDeductionCalculation(299, 1469);
            assertEquals(1768, grossDEd);
        }catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
    }

    void grossAndDeductionCalculation_Airthmethic_Exception() {
    }

    void grossAndDeductionCalculation_Class_Cast_Exception() {
    }


    @Test
    void netPayCalucaltion() {
    }

    @Test
    void netTakeHomeCalculation() {
    }

    @Test
    void ptGrossCalculation() {
    }

    @Test
    void differneceCalculation() {
    }

    @Test
    void home_Rent_Allowance() {
    }
}
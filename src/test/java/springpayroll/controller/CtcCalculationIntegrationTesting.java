package springpayroll.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import springpayroll.model.CtcData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CtcCalculationIntegrationTesting {


    @LocalServerPort
    private int port = 8085;

    @Autowired
    private TestRestTemplate restTemplate;


    HttpHeaders headers = new HttpHeaders();


    public String url(){
       return  "http://localhost:" + this.port;
    }

    @Test
    void newUserCtcDataSaving() throws Exception {

        CtcData employee = new CtcData();
        employee.setE_code("Ank13445");
        employee.setEname("Kapoor");

        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity(url() + "/ctc-ecode-ename", employee, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());

    }

    @Test
    void all_the_calcution_find_New() {

        String a = "JAIPUR";
        CtcData employee = new CtcData();
        employee.setE_code("Ank13445");
        employee.setEname("Kapoor");
        employee.setCtc(4442345345686544L);

        employee.setLOC(a);
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity(url() + "/ctcCalculation"
                        , employee, String.class);

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void getUserCtcData() {


        String employeeCode = "Ank13445";
        ResponseEntity<String> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + this.port + "/ctc/" + employeeCode, String.class);

        assertEquals(200, responseEntity.getStatusCodeValue());


    }

    @Test
    void getAllUserCtcData() {
        ResponseEntity<String> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + this.port + "/ctc", String.class);

        String expected =
                "[{\"e_code\":\"os\",\"ename\":\"mack\",\"loc\":null,\"h_R_A\":null," +
                        "\"net_Take_Home\":null,\"ctc\":null,\"basic\":null,\"bonus\":null,\"net_pay\":null," +
                        "\"gross\":null,\"employer_Pf\":null,\"employer_Esi\":null,\"employee_Pf\":null," +
                        "\"employee_Esi\":null,\"gratuity\":null,\"gross_Ded\":null,\"diff\":null," +
                        "\"pt_Gross\":null" +
                        "}" + ","
                        + "{\"e_code\":\"Raj\",\"ename\":\"Kapoor\",\"loc\":\"MUMBAI\",\"h_R_A\":685," +
                        "\"net_Take_Home\":37877,\"ctc\":44444,\"basic\":13719,\"bonus\":1143,\"net_pay\":37877," +
                        "\"gross\":40227,\"employer_Pf\":1646,\"employer_Esi\":1911,\"employee_Pf\":1646," +
                        "\"employee_Esi\":704,\"gratuity\":660,\"gross_Ded\":2350,\"diff\":0," +
                        "\"pt_Gross\":40227}]";


        System.out.println(responseEntity.getBody());
        assertNotNull(Objects.requireNonNull(responseEntity.getBody()));
        assertEquals(200, responseEntity.getStatusCodeValue());

        if (expected.equals(responseEntity.getBody())) {
            System.out.println("Your Test Passed");
        } else {
            System.out.println("not");
        }

    }

    @Test
    void updateUserCtcData() {

        CtcData employee = new CtcData();
        employee.setEname("Kapoor");
        Map<String, String> params = new HashMap<>();
        params.put("e_code", "Ank13445");

        this.restTemplate
                .put("http://localhost:" + this.port + "/ctc/" + employee.getE_code()

                        , employee, String.class);
    }

    @Test
    void userCtcDataDelete() {

        CtcData employee = new CtcData();
        employee.setE_code("Ank13445");
        Map<String, String> params = new HashMap<>();
        params.put("EmployeeCode", "Raj");
        this.restTemplate.delete("http://localhost:" + this.port + "/ctc/" + employee.getE_code(),
                employee.getE_code(), params);

    }


}
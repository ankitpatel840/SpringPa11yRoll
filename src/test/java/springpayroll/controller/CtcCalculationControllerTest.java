package springpayroll.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springpayroll.impl.CtcControllerImpl;
import springpayroll.model.Ctc_Data;
import springpayroll.repo.CTC_Repo;

import java.util.ArrayList;
import java.util.List;;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;import static org.hamcrest.MatcherAssert.assertThat;import static org.hamcrest.core.Is.is;


class CtcCalculationControllerTest {



   @MockBean
    private MockMvc mockMvc;

 @MockBean
    private CTC_Repo ctc_repo;
    @MockBean

    CtcControllerImpl ctcControllerImpl;

    @MockBean
 CtcCalculationController ctcCalculationController;
@MockBean
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp()
    {

    }

    @Test
    void homePage() {
    }

    @Test
    void login_methode_cheaking() {
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
    void getAllCtcData() throws Exception {
         Ctc_Data ctcData=new Ctc_Data("123","Gunjan");
         Ctc_Data ctcData1=new Ctc_Data("12","Guj");
        List<Ctc_Data> list=new ArrayList<>();
        list.add(ctcData);
        list.add(ctcData1);
     //  System.out.println(list);
       //
//
//        when(queueServices.getMessage()).thenReturn(list);
//
//        MockHttpServletResponse response = mockMvc.perform(get("/queue/listusers")).andReturn().getResponse();
//
//        assertThat(response.getStatus(),is(HttpStatus.OK.value()));
//        assertThat(response.getContentAsString(),is(objectMapper.writeValueAsString(queueServices.getMessage())));

          when(ctcControllerImpl.getAllCtcData()).thenReturn(list);

        MockHttpServletResponse response=mockMvc.perform(get("ctc")).andReturn().getResponse();
        System.out.println(response.getStatus());

        assertThat(response.getStatus(),is(HttpStatus.OK.value()));
        assertThat(response.getContentAsString(),is(objectMapper.writeValueAsString(ctcControllerImpl.getAllCtcData())));

    }

    @Test
    void getCtcData()
    {

    }

    @Test
    void delete() {
    }

    @Test
    void put_CtcData() {
    }
}
package springpayroll.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import springpayroll.model.Ctc_Data;
import springpayroll.repo.CTC_Repo;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CtcCalculationControllerTest {



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CTC_Repo ctc_repo;

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
        RequestBuilder request = MockMvcRequestBuilders
                .get("ctc")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) ctc_repo.findAll())
                .andReturn();


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
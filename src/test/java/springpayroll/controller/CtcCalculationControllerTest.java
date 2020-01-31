package springpayroll.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;
import springpayroll.impl.CtcControllerImpl;
import springpayroll.model.Ctc_Data;
import springpayroll.repo.CTC_Repo;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


class CtcCalculationControllerTest {




   @Autowired
    private MockMvc mockMvc;

 @MockBean
    private CTC_Repo ctc_repo;
    @MockBean
    CtcControllerImpl ctcControllerImpl;



    @InjectMocks
 CtcCalculationController ctcCalculationController;
 @InjectMocks
    private ObjectMapper objectMapper;

//    @InjectMocks
//    private HelloResource helloResource;
    @MockBean
    ModelAndView modelAndView;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
       ctcControllerImpl= org.mockito.Mockito.mock(CtcControllerImpl.class);
        mockMvc = standaloneSetup(ctcCalculationController).build();
    }

    @Test
    void homePage() throws Exception {
        MockitoAnnotations.initMocks(this);
      when(ctcControllerImpl.homePage()).thenReturn("");
        MockHttpServletResponse response=mockMvc.perform(get("/home")).andReturn().getResponse();
        assertThat(response.getStatus(),is(HttpStatus.OK.value()));




    }



    @Test
    void all_the_calcution_find_New() throws Exception {

        MockitoAnnotations.initMocks(this);
        Ctc_Data ctcData=new Ctc_Data("as12","AQW");
        when(ctcControllerImpl.all_the_calcution_find_New(19967L,"as12","BANGALORE","AQW")).thenReturn(ctcData);
        MockHttpServletResponse response=mockMvc.perform(get("/ctc/as12/AQW/19967/BANGALORE")).andReturn().getResponse();
        System.out.println(response.getStatus());
        assertThat(response.getStatus(),is(HttpStatus.OK.value()));
    }

    @Test
    void login_methode_Post() throws Exception {
//
        MockitoAnnotations.initMocks(this);
        Ctc_Data ctcData=new Ctc_Data("123","Gunjan");

        String json=objectMapper.writeValueAsString(ctcData);
        System.out.println(json);

        MockHttpServletResponse response=mockMvc.perform(post("/ctc/").content(json).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        System.out.println("asfe");
        System.out.println(response.getStatus());
        assertThat(response.getStatus(),is(HttpStatus.CREATED.value()));



    }

    @Test
    void getAllCtcData() throws Exception {
        MockitoAnnotations.initMocks(this);
        Ctc_Data ctcData=new Ctc_Data("123","Gunjan");
         Ctc_Data ctcData1=new Ctc_Data("12","Guj");
        List<Ctc_Data> list=new ArrayList<>();
        list.add(ctcData);
        list.add(ctcData1);
        when(ctcControllerImpl.getAllCtData()).thenReturn(list);
        MockHttpServletResponse response=mockMvc.perform(get("/ctc")).andReturn().getResponse();
        assertThat(response.getStatus(),is(HttpStatus.OK.value()));
        assertThat(response.getContentAsString(),is(objectMapper.writeValueAsString(ctcControllerImpl.getAllCtData())));
    }



    @Test
    void getCtcData() throws Exception {
        MockitoAnnotations.initMocks(this);
        Ctc_Data ctcData=new Ctc_Data("123","Gunjan");

        when(ctcControllerImpl.getCtcData("123")).thenReturn(java.util.Optional.of(ctcData));
        MockHttpServletResponse response=mockMvc.perform(get("/ctc/123")).andReturn().getResponse();


        assertThat(response.getStatus(),is(HttpStatus.OK.value()));

      //  assertThat(response.getContentAsString(),is(objectMapper.writeValueAsString(ctcControllerImpl.getCtcData("123"))));

    }

    @Test
    void delete() throws Exception {

        MockitoAnnotations.initMocks(this);
        lenient().when(ctcControllerImpl.deleteOne("rishabh123")).thenReturn("This");
        mockMvc.perform(MockMvcRequestBuilders.delete("/ctc/rishabh123"))
              .andExpect(status().isOk());

    }

    @Test
    void put_CtcData() throws Exception {
        MockitoAnnotations.initMocks(this);
        Ctc_Data ctcData1=new Ctc_Data("123","as");
      Ctc_Data ctcData=new Ctc_Data("123","Ank");
        when(ctcControllerImpl.put_CtcData("123",ctcData1)).thenReturn(ctcData);
        MockHttpServletResponse response=mockMvc.perform(MockMvcRequestBuilders.put("/ctc/123").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        System.out.println(response.getStatus());
        System.out.println(objectMapper.writeValueAsString(ctcControllerImpl.put_CtcData("123",ctcData1)));
        System.out.println(response.getContentAsString());
        assertThat(response.getStatus(),is(HttpStatus.OK.value()));
     // assertThat(response.getContentAsString(),is(objectMapper.writeValueAsString(ctcControllerImpl.put_CtcData("123",ctcData1))));



    }
    @Test
    void deleteAll() throws Exception {
        MockitoAnnotations.initMocks(this);

          Ctc_Data ctcData=new Ctc_Data();



        when(ctcControllerImpl.deleteAllData()).thenReturn("Deleted");

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/ctc")).andReturn().getResponse();

        verify(ctcControllerImpl).deleteAllData();
        assertThat(response.getStatus(),is(HttpStatus.OK.value()));


    }

}
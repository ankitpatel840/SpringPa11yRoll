package springpayroll.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import springpayroll.exception.ECodeNotFoundException;
import springpayroll.exception.InvalidStateCodeCException;
import springpayroll.impl.CtcControllerImpl;
import springpayroll.model.CtcData;

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



    private MockMvc mockMvc;

    @MockBean
    CtcControllerImpl ctcControllerImpl;

    @InjectMocks
    CtcCalculationController ctcCalculationController;
    @InjectMocks
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ctcControllerImpl = org.mockito.Mockito.mock(CtcControllerImpl.class);
        mockMvc = standaloneSetup(ctcCalculationController).build();
    }


    @Test
    void all_the_calcution_find_New() throws Exception, InvalidStateCodeCException {
        MockitoAnnotations.initMocks(this);


        CtcData CTCData = new CtcData("as12", "AQW");
        when(ctcControllerImpl.ctcCalculationDataSavingInDataBase(19967L, "as12", "BANGALORE", "AQW")).thenReturn(CTCData);
        System.out.println("Aasds");

        System.out.println(ctcControllerImpl.ctcCalculationDataSavingInDataBase(19967L, "as12", "BANGALORE", "AQW"));
        MockHttpServletResponse response = mockMvc.perform(get("/ctc/as12/AQW/19967/BANGALORE")).andReturn().getResponse();
        System.out.println(response.getStatus());
        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
    }

    @Test
    void login_methode_Post() throws Exception {
//
        MockitoAnnotations.initMocks(this);
        CtcData CTCData = new CtcData("123", "Gunjan");
        System.out.println(objectMapper);
        String json = objectMapper.writeValueAsString(CTCData);

        System.out.println("dafac");
        System.out.println(mockMvc);
        MockHttpServletResponse response = mockMvc.perform(post("/ctc/123/Gunjan").content(json).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        System.out.println(response);
        System.out.println("asfe");
        System.out.println(response.getStatus());
        assertThat(response.getStatus(), is(HttpStatus.CREATED.value()));


    }

    @Test
    void getAllCtcData() throws Exception {
        MockitoAnnotations.initMocks(this);
        CtcData CTCData = new CtcData("123", "Gunjan");
        CtcData CTCData1 = new CtcData("12", "Guj");
        List<CtcData> list = new ArrayList<>();
        list.add(CTCData);
        list.add(CTCData1);
        when(ctcControllerImpl.getAllUsersCtcData()).thenReturn(list);
        MockHttpServletResponse response = mockMvc.perform(get("/ctc")).andReturn().getResponse();
        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
        assertThat(response.getContentAsString(), is(objectMapper.writeValueAsString(ctcControllerImpl.getAllUsersCtcData())));
    }


    @Test
    void getCtcData() throws Exception, ECodeNotFoundException
    {
        MockitoAnnotations.initMocks(this);
        CtcData CTCData = new CtcData("123", "Gunjan");

        when(ctcControllerImpl.getUserCtcData("123")).thenReturn((CTCData));
        MockHttpServletResponse response = mockMvc.perform(get("/ctc/123")).andReturn().getResponse();


        assertThat(response.getStatus(), is(HttpStatus.OK.value()));

        assertThat(response.getContentAsString(), is(objectMapper.writeValueAsString(ctcControllerImpl.getUserCtcData("123"))));

    }

    @Test
    void delete() throws Exception, ECodeNotFoundException {

        MockitoAnnotations.initMocks(this);
        lenient().when(ctcControllerImpl.deleteOneUserCtcData("rishabh123")).thenReturn("This");
        mockMvc.perform(MockMvcRequestBuilders.delete("/ctc/rishabh123"))
                .andExpect(status().isOk());

    }

    @Test
    void put_CtcData() throws Exception, ECodeNotFoundException {
        MockitoAnnotations.initMocks(this);
        CtcData CTCData1 = new CtcData("123", "as");
        CtcData CTCData = new CtcData("123", "Ank");
        when(ctcControllerImpl.updateUserCtcData("123", CTCData1)).thenReturn(CTCData);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/ctc/123").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        System.out.println(response.getStatus());
        System.out.println(objectMapper.writeValueAsString(ctcControllerImpl.updateUserCtcData("123", CTCData1)));
        System.out.println(response.getContentAsString());
        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
        //     assertThat(response.getContentAsString(),is(objectMapper.writeValueAsString(ctcControllerImpl.put_CtcData("123",CTCData1))));


    }

    @Test
    void deleteAll() throws Exception {
        MockitoAnnotations.initMocks(this);

        CtcData CTCData = new CtcData();


        when(ctcControllerImpl.deleteAllUserCtcData()).thenReturn("Deleted");

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/ctc")).andReturn().getResponse();

        verify(ctcControllerImpl).deleteAllUserCtcData();
        assertThat(response.getStatus(), is(HttpStatus.OK.value()));


    }


}
package springpayroll.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springpayroll.model.Ctc_Data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes=CtcCalculationController.class)
@WebMvcTest(value = CtcCalculationController.class)
@ContextConfiguration
class CtcCalculationControllerIntegrationTest {

     @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;








    Ctc_Data ctcData = new Ctc_Data("123", "Gunjan");
    @BeforeEach
    public void setup(WebApplicationContext context) {

        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    void login_methode_Post() throws Exception
    {
        Ctc_Data ctcData = new Ctc_Data("123", "Gunjan");
        String json = objectMapper.writeValueAsString(ctcData);

    //    when(ctcController.login_methode_Post(ctcData)).thenReturn(ctcData);

        System.out.println("dafac");
        System.out.println(mockMvc);
        System.out.println(json);
        MockHttpServletResponse response = mockMvc.perform(post("/ctc").content(json).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        System.out.println(response);
        System.out.println("asfe");
        System.out.println(response.getStatus());
        assertThat(response.getStatus(), is(HttpStatus.CREATED.value()));
    }
}
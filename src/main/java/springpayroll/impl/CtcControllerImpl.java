package springpayroll.impl;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import springpayroll.model.Ctc_Data;

import java.util.List;

@Component
public interface CtcControllerImpl {
    public ModelAndView homePage();

    public ModelAndView loginValidationCheaking(String e_Name, String e_code);

    public String all_the_calcution(long ctc, String state, Model model, ModelAndView modelAndView);

    public Ctc_Data ctcCalculationDataSavingInDataBase(long ctc, String e_code, String state, String e_Name);

    public Ctc_Data userCredentialCheaking(Ctc_Data ctc_data);

    public List<Ctc_Data> getAllUsersCtcData();

    public Ctc_Data getUserCtcData(String e_code);

    public String deleteOneUserCtcData(String e_code);

    public Ctc_Data updateUserCtcData(String e_code, Ctc_Data ctc_data);

    public String deleteAllUserCtcData();

}

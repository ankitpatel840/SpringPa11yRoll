package springpayroll.impl;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import springpayroll.model.Ctc_Data;

import java.util.List;
import java.util.Optional;

public interface CtcControllerImpl
{
    public String homePage();

    public ModelAndView login_methode_cheaking( String e_Name,String e_code, ModelAndView mv);

    public String all_the_calcution(  long ctc,  String state, Model model, ModelAndView modelAndView);

    public List<Ctc_Data> all_the_calcution_find_New(long ctc, String e_code, String state, String e_Name, ModelAndView modelAndView);

    public Ctc_Data login_methode_Post(Ctc_Data ctc_data );

    public List<Ctc_Data> getAllCtcData();

    public Optional<Ctc_Data> getCtcData(String e_code);

    public String delete(String e_code);

    public Ctc_Data put_CtcData(String e_code, Ctc_Data ctc_data);

}

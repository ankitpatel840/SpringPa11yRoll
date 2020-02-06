package springpayroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springpayroll.impl.CtcControllerImpl;
import springpayroll.model.Ctc_Data;


@Controller

public class CtcCalculationController {
    CtcControllerImpl ctcControllerImpl;

    @Autowired
    public CtcCalculationController(CtcControllerImpl ctcControllerImpl) {
        this.ctcControllerImpl = ctcControllerImpl;
    }


    @RequestMapping("home")

    public ModelAndView homePage() {
        return ctcControllerImpl.homePage();
    }


    @RequestMapping("login")
    public ModelAndView userCredentialChecking(@RequestParam(value = "e_Name") String e_Name, @RequestParam(value = "e_code") String e_code) {
        return ctcControllerImpl.loginValidationCheaking(e_Name, e_code);
    }


    @RequestMapping("FinalCalulation")
    @ResponseBody
    public String allUserCtcDataSavingInDataBase(@RequestParam(value = "ctc") long ctc, @RequestParam(value = "state") String state, Model model, ModelAndView modelAndView) {

        return ctcControllerImpl.all_the_calcution(ctc, state, model, modelAndView);
    }

    @RequestMapping("ctc/{e_code}/{e_Name}/{ctc}/{state}")
    @ResponseBody
    public ResponseEntity<Object> all_the_calcution_find_New(@PathVariable long ctc, @PathVariable String e_code, @PathVariable String state, @PathVariable String e_Name) {
        return new ResponseEntity<>(ctcControllerImpl.ctcCalculationDataSavingInDataBase(ctc, e_code, state, e_Name), HttpStatus.OK);
    }


    @PostMapping("ctc")
    @ResponseBody

    public ResponseEntity<Ctc_Data> newUserCtcDataSaving(Ctc_Data ctc_data) {
        System.out.println("ankit");
        return new ResponseEntity<>(ctcControllerImpl.userCredentialCheaking(ctc_data), HttpStatus.CREATED);
    }


    @RequestMapping(value = "ctc", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getAllUserCtcData() {
        return new ResponseEntity<>(ctcControllerImpl.getAllUsersCtcData(), HttpStatus.OK);
    }


    @RequestMapping(value = "ctc/{e_code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getUserCtcData(@PathVariable String e_code) {
        return new ResponseEntity<>(ctcControllerImpl.getUserCtcData(e_code), HttpStatus.OK);

    }


    @RequestMapping(value = "ctc/{e_code}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> userCtcDataDelete(@PathVariable String e_code) {
        System.out.println("ankit");

        return new ResponseEntity<>(ctcControllerImpl.deleteOneUserCtcData(e_code), HttpStatus.OK);
    }


    @PutMapping("ctc/{e_code}")
    @ResponseBody
    public ResponseEntity<Ctc_Data> updateUserCtcData(@PathVariable String e_code, Ctc_Data ctc_data) {
        return new ResponseEntity<>(ctcControllerImpl.updateUserCtcData(e_code, ctc_data), HttpStatus.OK);
    }


    @DeleteMapping("ctc")
    @ResponseBody
    public ResponseEntity<String> deleteAllUserCtcData() {
        return new ResponseEntity<>(ctcControllerImpl.deleteAllUserCtcData(), HttpStatus.OK);
    }
}





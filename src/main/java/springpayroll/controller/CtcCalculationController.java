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
import springpayroll.repo.branch_Repo;


@Controller
public class CtcCalculationController
{

    @Autowired
    private branch_Repo branch_repo;

    @Autowired
    CtcControllerImpl ctcControllerImpl;
    @Autowired
    Ctc_Data ctcData;



   @RequestMapping("home")
        public String homePage()


        { return ctcControllerImpl.homePage(); }


  @RequestMapping("login")
    public  ModelAndView  login_methode_cheaking(@RequestParam(value = "e_Name") String e_Name, @RequestParam(value = "e_code") String e_code, ModelAndView mv)
    {
        return ctcControllerImpl.login_methode_cheaking(e_Name,e_code,mv);
    }



@RequestMapping("FinalCalulation")
@ResponseBody
public String all_the_calcution(@RequestParam(value = "ctc")  long ctc, @RequestParam(value = "state")  String state, Model model, ModelAndView modelAndView)
   {

       return ctcControllerImpl.all_the_calcution(ctc,state,model,modelAndView);
   }

    @RequestMapping("ctc/{e_code}/{e_Name}/{ctc}/{state}")
    @ResponseBody
    public ResponseEntity<Object> all_the_calcution_find_New(@PathVariable  long ctc,@PathVariable String e_code, @PathVariable  String state,@PathVariable String e_Name)
    {
        return new ResponseEntity<>( ctcControllerImpl.all_the_calcution_find_New(ctc,e_code,state,e_Name),HttpStatus.OK);
    }




    @PostMapping("ctc")
    @ResponseBody
    public ResponseEntity<Ctc_Data> login_methode_Post(Ctc_Data ctc_data )
    {
        return new ResponseEntity<>( ctcControllerImpl.login_methode_Post(ctc_data),HttpStatus.CREATED);
    }




    @RequestMapping(value = "ctc",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getAllCtcData()
    { return new ResponseEntity<>(ctcControllerImpl.getAllCtData(),HttpStatus.OK); }





    @RequestMapping(value = "ctc/{e_code}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getCtcData(@PathVariable String e_code)
    {
        return new ResponseEntity<>(ctcControllerImpl.getCtcData(e_code),HttpStatus.OK) ;

    }



    @RequestMapping(value = "ctc/{e_code}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> userDelete(@PathVariable String e_code)
    {
        System.out.println("ankit");

        return new  ResponseEntity<>(ctcControllerImpl.deleteOne(e_code),HttpStatus.OK);
    }



    @PutMapping("ctc/{e_code}")
    @ResponseBody
    public ResponseEntity<Ctc_Data> put_CtcData(@PathVariable String e_code,Ctc_Data ctc_data)
    {
        return new  ResponseEntity<>(ctcControllerImpl.put_CtcData(e_code,ctc_data),HttpStatus.OK);
    }


    @DeleteMapping("ctc")
    @ResponseBody
    public ResponseEntity<String> deleteAll()
    {
        return new ResponseEntity<>(ctcControllerImpl.deleteAllData(),HttpStatus.OK);
    }
}





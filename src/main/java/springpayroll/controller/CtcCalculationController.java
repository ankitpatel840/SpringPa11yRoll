package springpayroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springpayroll.exception.ECodeNotFoundException;
import springpayroll.exception.InvalidStateCodeCException;
import springpayroll.exception.UserAllreadyExistException;
import springpayroll.impl.CtcControllerImpl;
import springpayroll.model.CtcData;

@RestController
public class CtcCalculationController {

    @Autowired
    CtcControllerImpl ctcControllerImpl;


    @RequestMapping(value = "/ctc-ecode-ename",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)


    public ResponseEntity<CtcData> newUserCtcDataSaving(@RequestBody CtcData ctcData) throws UserAllreadyExistException, ECodeNotFoundException {

        return new ResponseEntity<>(ctcControllerImpl.newUserCrete(ctcData), HttpStatus.CREATED);
    }



    @PostMapping("/ctcCalculation")
    public ResponseEntity<Object> all_the_calcution_find_New(@RequestBody CtcData ctcData) throws InvalidStateCodeCException, ECodeNotFoundException {

        return new ResponseEntity<>(ctcControllerImpl.ctcCalculationDataSavingInDataBase(ctcData), HttpStatus.OK);

    }


    @GetMapping("ctc/{e_code}")
    public ResponseEntity<Object> getUserCtcData(@PathVariable String e_code) throws ECodeNotFoundException {

        System.out.println("segseg");
        return new ResponseEntity<>(ctcControllerImpl.getUserCtcData(e_code), HttpStatus.OK);

    }



    @RequestMapping(value = "ctc", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> getAllUserCtcData() {


        return new ResponseEntity<>(ctcControllerImpl.getAllUsersCtcData(), HttpStatus.OK);
    }


    @PutMapping("ctc/{e_code}")
    public ResponseEntity<CtcData> updateUserCtcData(@PathVariable String e_code, CtcData ctc_data) throws ECodeNotFoundException {
        return new ResponseEntity<>(ctcControllerImpl.updateUserCtcData(e_code, ctc_data), HttpStatus.OK);
    }



    @RequestMapping(value = "ctc/{e_code}", method = RequestMethod.DELETE)

    public ResponseEntity<String> userCtcDataDelete(@PathVariable String e_code) throws ECodeNotFoundException {
        return new ResponseEntity<>(ctcControllerImpl.deleteOneUserCtcData(e_code), HttpStatus.OK);
    }



    @DeleteMapping("ctc")
    public ResponseEntity<String> deleteAllUserCtcData() {
        return new ResponseEntity<>(ctcControllerImpl.deleteAllUserCtcData(), HttpStatus.OK);
    }
}





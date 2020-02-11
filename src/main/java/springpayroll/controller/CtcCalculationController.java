package springpayroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springpayroll.exception.ECodeNotFoundException;
import springpayroll.exception.UserAllreadyExistException;
import springpayroll.exception.InvalidStateCodeCException;
import springpayroll.impl.CtcControllerImpl;
import springpayroll.model.CtcData;


@RestController
public class CtcCalculationController {

    @Autowired
    CtcControllerImpl ctcControllerImpl;




    @GetMapping("ctc/{e_code}/{e_Name}/{ctc}/{state}")
    public ResponseEntity<Object> all_the_calcution_find_New(@PathVariable long ctc, @PathVariable String e_code, @PathVariable String state, @PathVariable String e_Name) throws InvalidStateCodeCException {

        return new ResponseEntity<>(ctcControllerImpl.ctcCalculationDataSavingInDataBase(ctc, e_code, state, e_Name), HttpStatus.OK);

    }


    @PostMapping("ctc/{ecode}/{ename}")
    public ResponseEntity<CtcData> newUserCtcDataSaving(@PathVariable String ecode, @PathVariable String ename) throws UserAllreadyExistException, ECodeNotFoundException {
        return new ResponseEntity<>(ctcControllerImpl.newUserCrete(ecode,ename), HttpStatus.CREATED);
    }


    @RequestMapping(value = "ctc", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> getAllUserCtcData() {
        return new ResponseEntity<>(ctcControllerImpl.getAllUsersCtcData(), HttpStatus.OK);
    }

      @GetMapping("ctc/{e_code}")
      public ResponseEntity<Object> getUserCtcData(@PathVariable String e_code) throws ECodeNotFoundException {


        return new ResponseEntity<>(ctcControllerImpl.getUserCtcData(e_code), HttpStatus.OK);

    }


    @RequestMapping(value = "ctc/{e_code}", method = RequestMethod.DELETE)

    public ResponseEntity<String> userCtcDataDelete(@PathVariable String e_code) throws ECodeNotFoundException {
        return new ResponseEntity<>(ctcControllerImpl.deleteOneUserCtcData(e_code), HttpStatus.OK);
    }


    @PutMapping("ctc/{e_code}")
    public ResponseEntity<CtcData> updateUserCtcData(@PathVariable String e_code, CtcData ctc_data) throws ECodeNotFoundException {
        return new ResponseEntity<>(ctcControllerImpl.updateUserCtcData(e_code, ctc_data), HttpStatus.OK);
    }


    @DeleteMapping("ctc")
    public ResponseEntity<String> deleteAllUserCtcData() {
        return new ResponseEntity<>(ctcControllerImpl.deleteAllUserCtcData(), HttpStatus.OK);
    }
}





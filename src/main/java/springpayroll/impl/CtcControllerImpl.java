package springpayroll.impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import springpayroll.exception.ECodeNotFoundException;
import springpayroll.exception.UserAllreadyExistException;
import springpayroll.exception.InvalidStateCodeCException;
import springpayroll.model.CtcData;

import java.util.List;

@Service
@Configuration
public interface CtcControllerImpl {



    CtcData ctcCalculationDataSavingInDataBase(Long ctc, String e_code, String state, String e_Name) throws InvalidStateCodeCException;

    CtcData newUserCrete(String ecode, String ename) throws UserAllreadyExistException, ECodeNotFoundException;

    List<CtcData> getAllUsersCtcData();

    CtcData getUserCtcData(String e_code) throws ECodeNotFoundException;

    String deleteOneUserCtcData(String e_code) throws ECodeNotFoundException;

    CtcData updateUserCtcData(String e_code, CtcData ctc_data) throws ECodeNotFoundException;

    String deleteAllUserCtcData();

}

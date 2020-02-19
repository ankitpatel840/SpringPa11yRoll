package springpayroll.impl;

import springpayroll.exception.ECodeNotFoundException;
import springpayroll.exception.InvalidStateCodeCException;
import springpayroll.exception.UserAllreadyExistException;
import springpayroll.model.CtcData;

import java.util.List;


public interface CtcControllerImpl {


    CtcData ctcCalculationDataSavingInDataBase(CtcData ctcData) throws InvalidStateCodeCException, ECodeNotFoundException;

    CtcData newUserCrete(CtcData ctc) throws UserAllreadyExistException, ECodeNotFoundException;

    List<CtcData> getAllUsersCtcData();

    CtcData getUserCtcData(String e_code) throws ECodeNotFoundException;

    String deleteOneUserCtcData(String e_code) throws ECodeNotFoundException;

    CtcData updateUserCtcData(String e_code, CtcData ctc_data) throws ECodeNotFoundException;

    String deleteAllUserCtcData();

}

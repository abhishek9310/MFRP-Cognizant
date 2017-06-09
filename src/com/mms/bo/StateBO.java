package com.mms.bo;

import java.util.List;

import com.mms.dao.StateDAO;
import com.mms.model.StateTO;

public class StateBO {
    public List<StateTO> getStateByCountryId(String countryId){
    	System.out.println("BO : StateBO : getStateByCountryId : start");
    	StateDAO stateDAO=new StateDAO();
    	System.out.println("BO : StateBO : getStateByCountryId : end");
    	return stateDAO.getStateByCountryId(countryId);
    }
}

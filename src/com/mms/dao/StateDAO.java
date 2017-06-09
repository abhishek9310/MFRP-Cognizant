package com.mms.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mms.constants.QueryConstants;
import com.mms.exceptions.MMSApplicationException;
import com.mms.model.CountryTO;
import com.mms.model.StateTO;
import com.mms.utils.DbUtil;

public class StateDAO {

	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;

	public List<StateTO> getStateByCountryId(String coString) {
		List<StateTO> stateTOs = new ArrayList<StateTO>();
		return stateTOs;
	}

	public List<StateTO> getAllState() throws MMSApplicationException {
		System.out.println("DAO : StateDAO : getAllState : start");
		List<StateTO> stateTOs = new ArrayList<StateTO>();
		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_STATES);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				StateTO stateTO = new StateTO();
				stateTO.setStateId(resultSet.getString("id"));
				stateTO.setStateName(resultSet.getString("state_name"));
				stateTO.setCountryTO(new CountryTO(resultSet
						.getString("country_id")));
				stateTOs.add(stateTO);
			}
		} catch (ClassNotFoundException e) {
			throw new MMSApplicationException();
		} catch (SQLException e) {
			throw new MMSApplicationException();
		} catch (IOException e) {
			throw new MMSApplicationException();
		} finally {
			closeConnection();
		}
		System.out.println("DAO : StateDAO : getAllState : end");
		return stateTOs;
	}

	public boolean validateState(StateTO stateTO)
			throws MMSApplicationException {
		boolean isStateAvailable = false;
		try {
			System.out.println("DAO : StateDAO : validateState : start");
			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.VALIDATE_STATE);
			System.out.println(stateTO.getStateId());
			System.out.println(stateTO.getCountryTO().getCountryId());
			preparedStatement.setString(1, stateTO.getStateId());
			preparedStatement.setString(2, stateTO.getCountryTO()
					.getCountryId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isStateAvailable = true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new MMSApplicationException();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MMSApplicationException();
		} catch (IOException e) {
			throw new MMSApplicationException();
		} finally {
			closeConnection();
		}
		System.out.println("DAO : StateDAO : validateState : end");
		return isStateAvailable;
	}

	public void closeConnection() throws MMSApplicationException {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new MMSApplicationException();
		}
	}
}

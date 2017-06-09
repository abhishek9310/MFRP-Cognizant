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
import com.mms.utils.DbUtil;

public class CountryDAO {

	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;

	public List<CountryTO> getAllCountry() throws MMSApplicationException {
		System.out.println("DAO : CountryDAO : getAllCountry : start");
		List<CountryTO> countryTOs = new ArrayList<CountryTO>();

		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_COUNTRIES);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				CountryTO countryTO = new CountryTO();
				countryTO.setCountryId(resultSet.getString("id"));
				countryTO.setCountryName(resultSet.getString("country_name"));
				countryTOs.add(countryTO);
			}
		} catch (ClassNotFoundException e) {
			throw new MMSApplicationException();
		} catch (SQLException e) {
			throw new MMSApplicationException();
		} catch (IOException e) {
			throw new MMSApplicationException();
		}finally {
			closeConnection();
		}
		System.out.println("DAO : CountryDAO : getAllCountry : end");
		return countryTOs;
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
			throw new MMSApplicationException(e.getMessage());
		}
	}
}

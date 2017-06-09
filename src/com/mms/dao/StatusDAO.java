package com.mms.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mms.constants.QueryConstants;
import com.mms.exceptions.MMSApplicationException;
import com.mms.model.StatusTO;
import com.mms.utils.DbUtil;

public class StatusDAO {
	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;

	public StatusTO getStatusIdByAge(int age) throws MMSApplicationException {
		StatusTO statusTO = new StatusTO();
		System.out.println("DAO : StatusDAO : getStatusIdAge : start");
		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_STATUS_ID);
			preparedStatement.setInt(1, age);
			preparedStatement.setInt(2, age);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				statusTO.setStatusId(resultSet.getString("id"));
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
		System.out.println("DAO : StatusDAO : getStatusIdAge : end");
		return statusTO;
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

package com.mms.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mms.constants.QueryConstants;
import com.mms.exceptions.MMSApplicationException;
import com.mms.model.AdminTO;
import com.mms.utils.DbUtil;

public class AdminDAO {

	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;

	public int validateAdmin(AdminTO userTO) throws MMSApplicationException {
		int result = 0;
		System.out.println("DAO : AdminDAO : validateAdmin : start");
		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GETADMIN);
			System.out.println(userTO.getUsername());
			preparedStatement.setString(1, userTO.getUsername());
			System.out.println(userTO.getUsername());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String pass = resultSet.getString("password");
				if (pass.equals(userTO.getPassWord())) {
					result = 1;
				} else {
					result = -1;
				}
			} else {
				result = 0;
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
		System.out.println("DAO : AdminDAO : validateAdmin : end");
		return result;
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

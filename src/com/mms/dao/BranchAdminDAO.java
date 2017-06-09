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
import com.mms.model.BranchAdminTO;
import com.mms.model.MedicineTO;
import com.mms.utils.AESEncryptor;
import com.mms.utils.DbUtil;

public class BranchAdminDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public String registerBranchAdmin(BranchAdminTO branchAdminTO)
			throws MMSApplicationException {
		System.out
				.println("DAO : BranchAdminDAO : registerBranchAdmin : start");
		String branchAdminId = null;
		String encryptedPassword;
		try {
			System.out.println("before encryptiona");
			encryptedPassword = AESEncryptor.encrypt(branchAdminTO
					.getPassword());
			System.out.println("tHIS IS THE LENGTH OF PASSWORDD "
					+ encryptedPassword.length());
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new MMSApplicationException();
		}
		try {
			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.SET_BRANCH_ADMIN);
			preparedStatement.setString(1, branchAdminTO.getFirstName());
			preparedStatement.setString(2, branchAdminTO.getLastName());
			preparedStatement.setString(3, branchAdminTO.getAddress());
			preparedStatement.setString(4, branchAdminTO.getStateTO()
					.getStateId());
			preparedStatement.setString(5, branchAdminTO.getEmail());
			preparedStatement.setString(6, encryptedPassword);
			preparedStatement.setString(7, branchAdminTO.getGender());
			preparedStatement.setString(8, branchAdminTO.getMaritalStatus());
			preparedStatement.setString(9, branchAdminTO.getContactNo());
			preparedStatement.setString(10, branchAdminTO.getStatusTO()
					.getStatusId());
			preparedStatement.setString(11, branchAdminTO.getDateOfBirth());
			preparedStatement.setString(12, branchAdminTO.getIdDocumentTO()
					.getIdDocumentId());
			preparedStatement.executeUpdate();
			System.out.println("after update");
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_LAST_BRANCH_ADMIN_ID);
			resultSet = preparedStatement.executeQuery();

			System.out.println(resultSet.getRow());
			if (resultSet.next()) {
				branchAdminId = resultSet.getString("id");
			}
		} catch (SQLException sqlException) {
			throw new MMSApplicationException();

		} catch (ClassNotFoundException e) {
			throw new MMSApplicationException();
		} catch (IOException e) {
			throw new MMSApplicationException();
		} finally {
			closeConnection();
		}
		System.out.println("DAO : BranchAdminDAO : registerBranchAdmin : end");
		return branchAdminId;
	}

	/*public BranchAdminTO[] getAllBranchAdmin() {
		List<BranchAdminTO> branchAdminTOs = new ArrayList<BranchAdminTO>();
		System.out.println("DAO : MedicineDAO : getAllMedicine : start");
		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_ALL_BRANCH_ADMIN);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BranchAdminTO branchAdminTO = new BranchAdminTO();
				
				medicineTO.setMedicineId(resultSet.getString("id"));
				medicineTO
						.setMedicineName(resultSet.getString("medicine_name"));
				medicineTO.setDosageValue(resultSet.getString("dosage_value"));
				medicineTO.setDosageUnit(resultSet.getString("dosage_unit"));
				medicineTO.setPriceOfStrip(resultSet
						.getString("price_of_strip"));
				medicineTO.setDescription(resultSet.getString("description"));
				medicineTO.setNoOfRequestedStrips(resultSet
						.getString("requested_strips"));
				medicineTO.setMedicinesInStrip(resultSet
						.getString("medicines_in_strip"));
				medicineTO.setRegistrationDate(resultSet
						.getString("registration_date"));
				medicineTO.setManufactureDate(resultSet
						.getString("manufacture_date"));
				medicineTO.setExpiryDate(resultSet.getString("expiry_date"));
				medicineTOs.add(medicineTO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new MMSApplicationException();
		} finally {
			closeConnection();
		}
		System.out.println("DAO : MedicineDAO : getAllMedicine : end");
		return medicineTOs;
	}*/

	public int validateBranchAdmin(BranchAdminTO branchAdminTO)
			throws MMSApplicationException {
		int result = 0;
		System.out
				.println("DAO : BranchAdminDAO : validateBranchAdmin : start");
		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_BRANCH_ADMIN_BY_ID);
			preparedStatement.setString(1, branchAdminTO.getBranchAdminId());
			resultSet = preparedStatement.executeQuery();
			String encryptedPassword = null;
			try {

				encryptedPassword = AESEncryptor.encrypt(branchAdminTO
						.getPassword());
				System.out.println(encryptedPassword);
			} catch (Exception e) {
				throw new MMSApplicationException();
			}
			if (resultSet.next()) {
				String pass = resultSet.getString("password");
				System.out.println(pass);
				if (pass.equals(encryptedPassword)) {
					result = 1;
				} else {
					result = -1;
				}
			} else {
				result = 0;
			}

		} catch (ClassNotFoundException e) {
			throw new MMSApplicationException(e.getMessage());

		} catch (SQLException e) {
			throw new MMSApplicationException(e.getMessage());
		} catch (IOException e) {
			throw new MMSApplicationException();
		} finally {
			closeConnection();
		}
		System.out.println(result);
		System.out.println("DAO : BranchAdminDAO : validateBranchAdmin : end");
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
			// TODO: handle exception
			throw new MMSApplicationException();
		}
	}
}

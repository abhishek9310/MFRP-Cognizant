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
import com.mms.model.MedicineTO;
import com.mms.utils.DbUtil;

public class MedicineDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public int registerMedicine(MedicineTO medicineTO)
			throws MMSApplicationException {
		System.out.println("DAO : MedicineDAO : registerMedicine : start");
		try {
			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_LAST_MEDICINE_ID);
			resultSet = preparedStatement.executeQuery();
			String medicineId = null;
			System.out.println(resultSet.getRow());
			if (resultSet.next()) {
				medicineId = resultSet.getString("id");
			}

			preparedStatement = connection
					.prepareStatement(QueryConstants.SET_MEDICINE);
			if (medicineId == null) {
				preparedStatement.setString(1, "M0001");
			} else {
				Integer medicineIntId = Integer.parseInt(medicineId
						.substring(1));
				medicineIntId = medicineIntId + 1;
				StringBuffer formattedMedicineId = new StringBuffer("M");
				formattedMedicineId
						.append(String.format("%04d", medicineIntId));
				System.out.println(formattedMedicineId);
				preparedStatement.setString(1, formattedMedicineId.toString());
			}
			preparedStatement.setString(2, medicineTO.getMedicineName());
			preparedStatement.setString(3, medicineTO.getDosageValue());
			preparedStatement.setString(4, medicineTO.getDosageUnit());
			preparedStatement.setString(5, medicineTO.getPriceOfStrip());
			preparedStatement.setString(6, medicineTO.getDescription());
			preparedStatement.setString(7, "0");
			preparedStatement.setString(8, medicineTO.getMedicinesInStrip());
			preparedStatement.setString(9, medicineTO.getManufactureDate());
			preparedStatement.setString(10, medicineTO.getExpiryDate());
			preparedStatement.executeUpdate();
			System.out.println("after update");
		} catch (SQLException sqlException) {
			throw new MMSApplicationException();

		} catch (ClassNotFoundException e) {
			throw new MMSApplicationException();
		} catch (IOException e) {
			throw new MMSApplicationException();
		} finally {
			closeConnection();
		}
		System.out.println("DAO : MedicineDAO : registerMedicine : end");
		return 1;
	}

	public List<MedicineTO> getAllMedicine() throws MMSApplicationException {
		List<MedicineTO> medicineTOs = new ArrayList<MedicineTO>();
		System.out.println("DAO : MedicineDAO : getAllMedicine : start");
		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_ALL_MEDICINE);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MedicineTO medicineTO = new MedicineTO();
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
	}

	public MedicineTO getMedicineById(String medicineId)
			throws MMSApplicationException {
		System.out.println("DAO : MedicineDAO : getMedicineById : Start");
		MedicineTO medicineTO = new MedicineTO();
		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_MEDICINE_BY_ID);
			preparedStatement.setString(1, medicineId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
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
		System.out.println("DAO : MedicineDAO : getMedicineById : end");
		return medicineTO;
	}

	public List<MedicineTO> searchMedicine(String searchString)
			throws MMSApplicationException {
		System.out.println("DAO : MedicineDAO : getMedicineBySubName : start");
		List<MedicineTO> medicineTOs = new ArrayList<MedicineTO>();
		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.SEARCH_MEDICINE);
			preparedStatement.setString(1, searchString);
			preparedStatement.setString(2, "%" + searchString + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MedicineTO medicineTO = new MedicineTO();
				medicineTO.setMedicineId(resultSet.getString("id"));
				medicineTO
						.setMedicineName(resultSet.getString("medicine_name"));
				System.out.println(medicineTO.getMedicineName());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			throw new MMSApplicationException();
		} finally {
			closeConnection();
		}
		System.out.println("DAO : MedicineDAO : getMedicineBySubName : end");
		return medicineTOs;
	}

	public boolean validateMedicine(String medicineId)
			throws MMSApplicationException {
		boolean result = false;
		try {

			connection = DbUtil.getConnection();

			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_MEDICINE_BY_ID);
			preparedStatement.setString(1, medicineId);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				result = true;
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
			throw new MMSApplicationException(e.getMessage());
		}
	}

}

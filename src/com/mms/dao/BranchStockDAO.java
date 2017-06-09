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
import com.mms.model.BranchStockTO;
import com.mms.model.BranchTO;
import com.mms.model.MedicineRequestTO;
import com.mms.model.MedicineTO;
import com.mms.utils.DbUtil;

public class BranchStockDAO {
	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;

	public int registerStock(BranchStockTO branchStockTO)
			throws MMSApplicationException {
		System.out.println("DAO : BranchStockDAO : registerStock : start");
		try {
			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_LAST_STOCK_ID);
			resultSet = preparedStatement.executeQuery();
			String stockId = null;
			if (resultSet.next()) {
				stockId = resultSet.getString("id");
			}

			preparedStatement = connection
					.prepareStatement(QueryConstants.ADD_BRANCH_STOCK);
			if (stockId == null) {
				preparedStatement.setString(1, "ST0001");
			} else {
				Integer stockIntId = Integer.parseInt(stockId.substring(2));
				stockIntId = stockIntId + 1;
				StringBuffer formattedMedicineId = new StringBuffer("ST");
				formattedMedicineId.append(String.format("%04d", stockIntId));
				preparedStatement.setString(1, formattedMedicineId.toString());
			}
			preparedStatement.setString(2, branchStockTO.getMedicineTO().getMedicineId());
			preparedStatement.setString(3, branchStockTO.getBranchAdminTO().getBranchAdminId());
			preparedStatement.setString(4, branchStockTO.getBranchTO().getBranchId());
			preparedStatement.setString(5, branchStockTO.getNumberOfStrips());
			preparedStatement.setString(6, branchStockTO.getDescription());
			preparedStatement.executeUpdate();

		} catch (SQLException sqlException) {
			throw new MMSApplicationException(sqlException);

		} catch (ClassNotFoundException e) {
			throw new MMSApplicationException();
		}catch (IOException e) {
			throw new MMSApplicationException();
		} finally {

			closeConnection();
		}
		System.out.println("DAO : BranchStockDAO : registerStock : end");
		return 1;
	}
	
	public List<BranchStockTO> getAllBranchAdminStocks(String branchAdminId)
			throws MMSApplicationException {
		List<BranchStockTO> branchStockTOs = new ArrayList<BranchStockTO>();
		try {

			connection = DbUtil.getConnection();

			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_STOCK_BY_BRANCH_ADMIN);
			preparedStatement.setString(1, branchAdminId);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				BranchStockTO branchStockTO = new BranchStockTO();
				branchStockTO.setStockId(resultSet.getString("id"));

				MedicineTO medicineTO = new MedicineTO();
				medicineTO.setMedicineId(resultSet.getString("medicine_id"));
				medicineTO
						.setMedicineName(resultSet.getString("medicine_name"));
				medicineTO.setDosageValue(resultSet.getString("dosage_value"));
				medicineTO.setDosageUnit(resultSet.getString("dosage_unit"));

				BranchTO branchTO = new BranchTO();
				branchTO.setBranchId(resultSet.getString("branch_id"));
				branchTO.setBranchName(resultSet.getString("branch_name"));

				branchStockTO.setMedicineTO(medicineTO);
				branchStockTO.setBranchTO(branchTO);
				branchStockTO.setNumberOfStrips(resultSet.getString("number_of_strips"));

				branchStockTO.setRegistrationDate(resultSet
						.getString("registration_date"));
				branchStockTOs.add(branchStockTO);

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
		System.out
				.println("DAO : MedicineRequestDAO : getRequestsByBranchAdminAndStatus : end");
		return branchStockTOs;
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

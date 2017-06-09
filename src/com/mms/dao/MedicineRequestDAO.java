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

public class MedicineRequestDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public int requestMedicine(MedicineRequestTO medicineRequestTO)
			throws MMSApplicationException {
		System.out
				.println("DAO : MedicineRequestDAO : requestMedicine : start");
		try {
			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_LAST_MEDICINE_REQUEST_ID);
			resultSet = preparedStatement.executeQuery();
			String stockId = null;
			if (resultSet.next()) {
				stockId = resultSet.getString("id");
			}
			System.out.println(stockId + " vishnu saini stock");
			preparedStatement = connection
					.prepareStatement(QueryConstants.Add_MEDICINE_REQUEST);
			if (stockId == null) {
				preparedStatement.setString(1, "REQ001");
			} else {
				Integer stockIntId = Integer.parseInt(stockId.substring(3));
				stockIntId = stockIntId + 1;

				StringBuffer formattedMedicineId = new StringBuffer("REQ");
				formattedMedicineId.append(String.format("%03d", stockIntId));
				preparedStatement.setString(1, formattedMedicineId.toString());
			}
			preparedStatement.setString(2, medicineRequestTO.getMedicineTO()
					.getMedicineId());
			preparedStatement.setString(3, medicineRequestTO.getNoOfStrips());
			preparedStatement.setString(4, medicineRequestTO.getBranchTO()
					.getBranchId());
			preparedStatement.setString(5, medicineRequestTO.getBranchAdminTO()
					.getBranchAdminId());
			preparedStatement.setString(6, "Pending");
			preparedStatement.executeUpdate();

		} catch (SQLException sqlException) {
			throw new MMSApplicationException(sqlException);

		} catch (ClassNotFoundException e) {
			throw new MMSApplicationException();
		} catch (IOException e) {
			throw new MMSApplicationException();
		} finally {

			closeConnection();
		}
		System.out.println("DAO : MedicineRequestDAO : requestMedicine : end");
		return 1;
	}

	public List<MedicineRequestTO> getAllMedicineRequests() {
		List<MedicineRequestTO> medicineRequestTOs = new ArrayList<MedicineRequestTO>();
		return medicineRequestTOs;
	}

	public List<MedicineRequestTO> getRequestsByStatus(String status) throws MMSApplicationException {
		List<MedicineRequestTO> medicineRequestTOs = new ArrayList<MedicineRequestTO>();

		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_ALL_REQUESTS_BY_STATUS);
			preparedStatement.setString(1, status);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MedicineRequestTO medicineRequestTO = new MedicineRequestTO();
				medicineRequestTO.setMedicineRequestId(resultSet
						.getString("id"));

				MedicineTO medicineTO = new MedicineTO();
				medicineTO.setMedicineId(resultSet.getString("medicine_id"));
				medicineTO
						.setMedicineName(resultSet.getString("medicine_name"));
				medicineTO.setDosageValue(resultSet.getString("dosage_value"));
				medicineTO.setDosageUnit(resultSet.getString("dosage_unit"));

				medicineRequestTO.setMedicineTO(medicineTO);
				medicineRequestTO.setNoOfStrips(resultSet
						.getString("no_of_strips"));

				BranchTO branchTO = new BranchTO();
				branchTO.setBranchId(resultSet.getString("branch_id"));
				branchTO.setBranchName(resultSet.getString("branch_name"));

				medicineRequestTO.setBranchTO(branchTO);
				medicineRequestTO.setRequestDate(resultSet
						.getString("request_date"));
				medicineRequestTO.setStatus(resultSet.getString("status"));
				medicineRequestTO.setBranchAdminTO(new BranchAdminTO(resultSet
						.getString("branch_name")));
				medicineRequestTOs.add(medicineRequestTO);
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
		return medicineRequestTOs;
	}

	public List<MedicineRequestTO> getRequestsByBranchAdminAndStatus(
			String status, String branchAdminId) throws MMSApplicationException {
		System.out
				.println("DAO : MedicineRequestDAO : getRequestsByBranchAdminAndStatus : start");
		List<MedicineRequestTO> medicineRequestTOs = new ArrayList<MedicineRequestTO>();

		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_REQUEST_BY_BRANCH_ADMIN);
			preparedStatement.setString(1, branchAdminId);
			preparedStatement.setString(2, status);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MedicineRequestTO medicineRequestTO = new MedicineRequestTO();
				medicineRequestTO.setMedicineRequestId(resultSet
						.getString("id"));

				MedicineTO medicineTO = new MedicineTO();
				medicineTO.setMedicineId(resultSet.getString("medicine_id"));
				medicineTO
						.setMedicineName(resultSet.getString("medicine_name"));
				medicineTO.setDosageValue(resultSet.getString("dosage_value"));
				medicineTO.setDosageUnit(resultSet.getString("dosage_unit"));

				medicineRequestTO.setMedicineTO(medicineTO);
				medicineRequestTO.setNoOfStrips(resultSet
						.getString("no_of_strips"));

				BranchTO branchTO = new BranchTO();
				branchTO.setBranchId(resultSet.getString("branch_id"));
				branchTO.setBranchName(resultSet.getString("branch_name"));

				medicineRequestTO.setBranchTO(branchTO);
				medicineRequestTO.setRequestDate(resultSet
						.getString("request_date"));
				medicineRequestTO.setStatus(resultSet.getString("status"));
				medicineRequestTO.setBranchAdminTO(new BranchAdminTO(resultSet
						.getString("branch_name")));
				medicineRequestTOs.add(medicineRequestTO);
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
		return medicineRequestTOs;
	}

	public List<MedicineRequestTO> getAllBranchAdminRequests(
			String branchAdminId) throws MMSApplicationException {

		List<MedicineRequestTO> medicineRequestTOs = new ArrayList<MedicineRequestTO>();

		try {

			connection = DbUtil.getConnection();

			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_ALL_REQUEST_BY_BRANCH_ADMIN);
			preparedStatement.setString(1, branchAdminId);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				MedicineRequestTO medicineRequestTO = new MedicineRequestTO();

				medicineRequestTO.setMedicineRequestId(resultSet
						.getString("id"));

				MedicineTO medicineTO = new MedicineTO();
				medicineTO.setMedicineId(resultSet.getString("medicine_id"));
				medicineTO
						.setMedicineName(resultSet.getString("medicine_name"));
				medicineTO.setDosageValue(resultSet.getString("dosage_value"));
				medicineTO.setDosageUnit(resultSet.getString("dosage_unit"));

				medicineRequestTO.setMedicineTO(medicineTO);
				medicineRequestTO.setNoOfStrips(resultSet
						.getString("no_of_strips"));

				BranchTO branchTO = new BranchTO();
				branchTO.setBranchId(resultSet.getString("branch_id"));
				branchTO.setBranchName(resultSet.getString("branch_name"));

				medicineRequestTO.setBranchTO(branchTO);
				medicineRequestTO.setRequestDate(resultSet
						.getString("request_date"));
				medicineRequestTO.setStatus(resultSet.getString("status"));
				medicineRequestTO.setBranchAdminTO(new BranchAdminTO(resultSet
						.getString("branch_name")));
				medicineRequestTOs.add(medicineRequestTO);
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
		return medicineRequestTOs;
	}

	public int cancleRequest(String requestId, String branchAdminId)
			throws MMSApplicationException {
		try {
			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.CANCEL_REQUEST);
			preparedStatement.setString(1, requestId);
			preparedStatement.setString(2, branchAdminId);
			preparedStatement.executeUpdate();
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

		return 1;
	}

	public int approveRequest(String requestId) throws MMSApplicationException {
		try {
			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.APPROVE_REQUEST);
			preparedStatement.setString(1, requestId);
			preparedStatement.executeUpdate();
			preparedStatement=connection.prepareStatement(QueryConstants.GET_REQUEST_DETAIL_BY_ID);
			preparedStatement.setString(1, requestId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				String medicineId=resultSet.getString("medicine_id");
				String numberOfStrips=resultSet.getString("no_of_Strips");
				preparedStatement=connection.prepareStatement(QueryConstants.GET_REQUESTED_MEDICINE_COUNT);
				preparedStatement.setString(1, medicineId);
				resultSet=preparedStatement.executeQuery();
				if(resultSet.next()){
					int medicineCount=Integer.parseInt(resultSet.getString("requested_strips"));
					String totalCount=String.valueOf(medicineCount+Integer.parseInt(numberOfStrips));
					preparedStatement=connection.prepareStatement(QueryConstants.UPDATE_MEDICINE_REQUESTED_COUNT);
					preparedStatement.setString(1,totalCount);
					preparedStatement.setString(2, medicineId);
					preparedStatement.executeUpdate();
				}
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

		return 1;
	}

	public int rejectRequest(String requestId) throws MMSApplicationException {
		try {
			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.REJECT_REQUEST);
			preparedStatement.setString(1, requestId);
			preparedStatement.executeUpdate();
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

		return 1;
	}

	public int validatePendingRequest(String requestId, String branchAdminId)
			throws MMSApplicationException {
		int requestAvailable = 0;
		try {
			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_PENDING_REQUEST_BY_BRANCH_ADMIN_ID_AND_REQUEST_ID);
			preparedStatement.setString(1, requestId);
			preparedStatement.setString(2, branchAdminId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				requestAvailable = 1;
			} else {
				requestAvailable = -1;
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
		return requestAvailable;
	}

	public int validatePendingRequestById(String requestId)
			throws MMSApplicationException {
		int requestAvailable = 0;
		try {
			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_PENDING_REQUEST_BY_ID);
			preparedStatement.setString(1, requestId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				requestAvailable = 1;
			} else {
				requestAvailable = -1;
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
		return requestAvailable;
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

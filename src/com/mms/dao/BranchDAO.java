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
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.BranchTO;
import com.mms.utils.DbUtil;

public class BranchDAO {
	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;

	public List<BranchTO> getBranchesByAdminId(String branchAdminId) throws MMSApplicationException {
		System.out.println("DAO : BranchDAO : getBranchesByAdminId : start");
		List<BranchTO> branchTOs = new ArrayList<BranchTO>();
		try {
			connection = DbUtil.getConnection();

			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_BRANCH_BY_ADMIN_ID);
			preparedStatement.setString(1, branchAdminId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BranchTO branchTO = new BranchTO();
				branchTO.setBranchId(resultSet.getString("id"));
				branchTO.setBranchName(resultSet.getString("branch_name"));
				branchTOs.add(branchTO);
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
		System.out.println("DAO : BranchDAO : getBranchesByAdminId : end");
		return branchTOs;
	}

	public List<BranchTO> getBranches() {
		List<BranchTO> branchTOs = new ArrayList<BranchTO>();
		return branchTOs;
	}

	public boolean validateBranch(String branchId, String branchAdminId)
			throws MMSApplicationException {
		boolean result = false;
		try {

			connection = DbUtil.getConnection();

			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_BRANCH_BY_ADMIN_ID_AND_BRANCH_ID);
			preparedStatement.setString(1, branchId);
			preparedStatement.setString(2, branchAdminId);

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
		}finally {
			closeConnection();
		}

		return result;
	}

	public List<BranchTO> getUnasignedBranches() throws MMSApplicationException {
		System.out.println("DAO : BranchDAO : getUnasignedBranches : start");
		List<BranchTO> branchTOs = new ArrayList<BranchTO>();
		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_UNASIGNED_BRANCHES);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BranchTO branchTO = new BranchTO();
				branchTO.setBranchName(resultSet.getString("branch_name"));
				branchTO.setBranchId(resultSet.getString("id"));
				branchTOs.add(branchTO);
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
		System.out.println("DAO : BranchDAO : getUnasignedBranches : end");
		return branchTOs;
	}

	public int setBranchAdminId(String branchAdminId, List<BranchTO> branches)
			throws MMSApplicationException {
		System.out.println("DAO : BranchDAO : setBranchAdminId : start");
		try {
			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.SET_BRANCH_ADMIN_IN_BRANCH);
			StringBuffer branchBuffer = new StringBuffer();
			for (BranchTO branchTO : branches) {
				branchBuffer.append(branchTO.getBranchId()).append(",");
			}
			if (branchBuffer.length() > 0) {
				branchBuffer.deleteCharAt(branchBuffer.length() - 1);
			}
			preparedStatement.setString(1, branchAdminId);
			preparedStatement.setString(2, branchBuffer.toString());
			preparedStatement.executeUpdate();
			System.out.println("after update");
		} catch (SQLException sqlException) {
			throw new MMSApplicationException(sqlException);

		} catch (ClassNotFoundException e) {
			throw new MMSApplicationException();
		} catch (IOException e) {
			throw new MMSApplicationException();
		}finally {
			closeConnection();
		}
		System.out.println("DAO : BranchDAO : setBranchAdminId : end");
		return 1;

	}

	public int validateBranches(List<BranchTO> branchTOs)
			throws MMSApplicationException, MMSBusinessException {
		System.out.println("DAO : BranchDAO : validateBranches : start");
		StringBuffer stringBuffer = new StringBuffer();
		ArrayList<String> assignedBranchesList = new ArrayList<String>();
		for (BranchTO branchTO : branchTOs) {
			stringBuffer.append(branchTO.getBranchId()).append(",");
		}
		if (stringBuffer.length() > 0) {
			stringBuffer.deleteCharAt(stringBuffer.length() - 1);
		}
		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_BRANCHES_BY_IDS);
			preparedStatement.setString(1, stringBuffer.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("branch_admin_id") != null) {
					assignedBranchesList.add(resultSet
							.getString("branch_admin_id"));
				}
			}
			if (assignedBranchesList.size() > 0) {
				StringBuffer branches = new StringBuffer();
				for (String branchName : assignedBranchesList) {
					branches.append(branchName).append(",");
				}
				branches.deleteCharAt(branches.length() - 1);
				throw new MMSBusinessException(branches.toString()
						+ " branch already registered");
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
		System.out.println("DAO : BranchDAO : validateBranches : end");
		return 1;
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

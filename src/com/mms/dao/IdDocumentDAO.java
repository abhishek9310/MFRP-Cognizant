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
import com.mms.model.IdDocumentTO;
import com.mms.model.StateTO;
import com.mms.utils.DbUtil;

public class IdDocumentDAO {
	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;

	public List<IdDocumentTO> getAllIdentificationDocument() throws MMSApplicationException {
		List<IdDocumentTO> idDocumentTOs = new ArrayList<IdDocumentTO>();
		System.out.println("DAO : IdDocumentDAO : getAllIdentificationDocument : start");
		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.GET_ID_DOCUMENTS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				IdDocumentTO idDocumentTO = new IdDocumentTO();
				idDocumentTO.setIdDocumentId(resultSet.getString("id"));
				idDocumentTO.setDocumentType(resultSet
						.getString("id_document_type"));
				idDocumentTO.setPrefix(resultSet.getString("prefix_format"));
				idDocumentTOs.add(idDocumentTO);
			}
		} catch (ClassNotFoundException e) {
			throw new MMSApplicationException();
		} catch (SQLException e) {
			throw new MMSApplicationException();
		}catch (IOException e) {
			throw new MMSApplicationException();
		}finally{
			closeConnection();
		}
		System.out.println("DAO : IdDocumentDAO : getAllIdentificationDocument : end");
		return idDocumentTOs;
	}

	public boolean validateIdentityProof(IdDocumentTO idDocumentTO) throws MMSApplicationException {
		boolean isIdAvailable=false;
		System.out.println("DAO : IdDocumentDAO : validateIdentityProof : start");
		try {

			connection = DbUtil.getConnection();
			preparedStatement = connection
					.prepareStatement(QueryConstants.VALIDATE_ID_DOCUMENT);
			preparedStatement.setString(1, idDocumentTO.getIdDocumentId());
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				isIdAvailable=true;
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
		System.out.println("DAO : IdDocumentDAO : validateIdentityProof : end");
		return isIdAvailable;
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

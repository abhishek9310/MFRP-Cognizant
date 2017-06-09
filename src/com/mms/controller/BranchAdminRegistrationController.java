package com.mms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mms.bo.BranchAdminBO;
import com.mms.bo.BranchBO;
import com.mms.bo.StatusBO;
import com.mms.constants.ErrorConstant;
import com.mms.dao.BranchDAO;
import com.mms.dao.CountryDAO;
import com.mms.dao.IdDocumentDAO;
import com.mms.dao.StateDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.BranchAdminTO;
import com.mms.model.BranchTO;
import com.mms.model.CountryTO;
import com.mms.model.IdDocumentTO;
import com.mms.model.StateTO;
import com.mms.model.StatusTO;

/**
 * Servlet implementation class BranchAdminSignupController
 */
public class BranchAdminRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BranchAdminRegistrationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out
				.println("Controller : BranchAdminRegistrationController : doGet : start");
		RequestDispatcher requestDispatcher = null;
		try {

			try {
				setDataWithRequest(request);
			} catch (MMSApplicationException e) {
				requestDispatcher = request
						.getRequestDispatcher(ErrorConstant.ERRORPAGE);
				request.setAttribute("errorMsg", e.getMessage());
				System.out
						.println("Controller : BranchAdminRegistrationController : doGet : end");
				requestDispatcher.forward(request, response);
			}
			requestDispatcher = request
					.getRequestDispatcher("/jsps/admin/RegisterBranchAdmin.jsp");
			request.setAttribute("currentPage", "registerBranchAdmin");
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			requestDispatcher = request
					.getRequestDispatcher(ErrorConstant.ERRORPAGE);

			request.setAttribute("errorMsg",
					ErrorConstant.CONTROLLER_EXCEPTION_MSG);
			System.out
					.println("Controller : BranchAdminRegistrationController : doGet : end");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out
				.println("Controller : BranchAdminRegistrationController : doPost : start");
		String[] branches;
		BranchAdminBO branchAdminBO = new BranchAdminBO();
		BranchBO branchBO = new BranchBO();
		BranchAdminTO branchAdminTO = new BranchAdminTO();
		StateTO stateTO = new StateTO();
		List<BranchTO> branchTOs = new ArrayList<BranchTO>();
		RequestDispatcher requestDispatcher;
		try {

			branches = request.getParameterValues("branches");
			if (branches != null) {
				for (int i = 0; i < branches.length; i++) {

					BranchTO branchTO = new BranchTO();
					branchTO.setBranchId(branches[i]);
					branchTOs.add(branchTO);
				}
			}

			branchAdminTO
					.setFirstName(request.getParameter("firstName").trim());
			branchAdminTO.setLastName(request.getParameter("lastName").trim());
			branchAdminTO.setAddress(request.getParameter("address").trim());

			stateTO.setStateId(request.getParameter("state"));
			String countryId=request.getParameter("country");
			stateTO.setCountryTO(new CountryTO(countryId));

			branchAdminTO.setStateTO(stateTO);
			branchAdminTO.setEmail(request.getParameter("email").trim());
			branchAdminTO.setPassword(request.getParameter("password"));
			branchAdminTO.setGender(request.getParameter("gender"));
			branchAdminTO.setMaritalStatus(request
					.getParameter("maritalStatus"));
			branchAdminTO
					.setContactNo(request.getParameter("contactNo").trim());

			branchAdminTO.setDateOfBirth(request.getParameter("dob").trim());
			branchAdminTO.setIdDocumentTO(new IdDocumentTO(request
					.getParameter("idDocument")));
			
			String branchAdminId = branchAdminBO.registerBranchAdmin(branchAdminTO,branchTOs);
			
			requestDispatcher = request.getRequestDispatcher("/jsps/admin/RegisterBranchAdmin.jsp");
			request.setAttribute("currentPage", "registerBranchAdmin");
			request.setAttribute("branchAdminId", branchAdminId);
			requestDispatcher.forward(request, response);

		} catch (MMSBusinessException e) {
			request.setAttribute("branchAdmin", branchAdminTO);
			try {
				setDataWithRequest(request);
			} catch (MMSApplicationException exception) {
				requestDispatcher = request
						.getRequestDispatcher(ErrorConstant.ERRORPAGE);
				request.setAttribute("errorMsg", exception.getMessage());
				requestDispatcher.forward(request, response);
			}
			request.setAttribute("branchAdminTO", branchAdminTO);
			requestDispatcher = request
					.getRequestDispatcher("/jsps/admin/RegisterBranchAdmin.jsp");
			request.setAttribute("currentPage", "registerBranchAdmin");
			request.setAttribute("errorMap", e.getErrorMap());
			request.setAttribute("branchAdminTO", branchAdminTO);
			requestDispatcher.forward(request, response);
		} catch (MMSApplicationException e) {
			request.setAttribute("errorMsg", e);
			requestDispatcher = request.getRequestDispatcher("/jsps/Error.jsp");
			requestDispatcher.forward(request, response);
		}
		System.out
				.println("Controller : BranchAdminRegistrationController : doPost : end");
	}

	private void setDataWithRequest(HttpServletRequest request)
			throws MMSApplicationException {
		CountryDAO countryDAO = new CountryDAO();
		StateDAO stateDAO = new StateDAO();
		IdDocumentDAO idDocumentDAO = new IdDocumentDAO();
		BranchDAO branchDAO = new BranchDAO();
		List<IdDocumentTO> idDocumentList = null;
		List<CountryTO> countryList = null;
		List<StateTO> stateList = null;
		List<BranchTO> unAssignedBranches = null;

		idDocumentList = idDocumentDAO.getAllIdentificationDocument();
		countryList = countryDAO.getAllCountry();
		stateList = stateDAO.getAllState();
		unAssignedBranches = branchDAO.getUnasignedBranches();

		request.setAttribute("countries", countryList);
		request.setAttribute("states", stateList);
		request.setAttribute("idDocuments", idDocumentList);
		request.setAttribute("unassignedBranches", unAssignedBranches);
	}

}

package com.mms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mms.bo.MedicineRequestBO;
import com.mms.constants.ErrorConstant;
import com.mms.dao.MedicineRequestDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.MedicineRequestTO;

/**
 * Servlet implementation class ReviewController
 */
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String requestId = request.getParameter("reqId");
		String action = request.getParameter("action");
		MedicineRequestDAO medicineRequestDAO = new MedicineRequestDAO();
		MedicineRequestBO medicineRequestBO = new MedicineRequestBO();
		RequestDispatcher requestDispatcher;
		if (requestId == null || action == null) {

			List<MedicineRequestTO> medicineRequestTOs;
			HttpSession session = request.getSession();
			try {
				medicineRequestTOs = medicineRequestDAO.getRequestsByStatus("Pending");
				request.setAttribute("requests", medicineRequestTOs);
				request.setAttribute("type", "Pending");
				requestDispatcher = request
						.getRequestDispatcher("/jsps/admin/ReviewRequests.jsp");
				requestDispatcher.forward(request, response);
			} catch (MMSApplicationException e) {
			}
		} else if(action.equals("approve")) {

			try {
				medicineRequestBO.approveRequest(requestId);
				requestDispatcher = request
						.getRequestDispatcher("/jsps/admin/ReviewRequests.jsp");
				request.setAttribute("success", "Approved");
				requestDispatcher.forward(request, response);
			} catch (MMSBusinessException e) {
				requestDispatcher = request
						.getRequestDispatcher("/jsps/admin/ReviewRequests.jsp");
				request.setAttribute("errorMsg", e.getMessage());
				requestDispatcher.forward(request, response);
			} catch (MMSApplicationException e) {
				requestDispatcher = request
						.getRequestDispatcher(ErrorConstant.ERRORPAGE);
				request.setAttribute("errorMsg", e.getMessage());
				requestDispatcher.forward(request, response);
			}

		}else if(action.equals("reject")) {
			try {
				medicineRequestBO.rejectRequest(requestId);
				requestDispatcher = request
						.getRequestDispatcher("/jsps/admin/ReviewRequests.jsp");
				request.setAttribute("success", "Approved");
				requestDispatcher.forward(request, response);
			} catch (MMSBusinessException e) {
				requestDispatcher = request
						.getRequestDispatcher("/jsps/admin/ReviewRequests.jsp");
				request.setAttribute("errorMsg", e.getMessage());
				requestDispatcher.forward(request, response);
			} catch (MMSApplicationException e) {
				requestDispatcher = request
						.getRequestDispatcher(ErrorConstant.ERRORPAGE);
				request.setAttribute("errorMsg", e.getMessage());
				requestDispatcher.forward(request, response);
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

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
import com.mms.dao.BranchDAO;
import com.mms.dao.MedicineRequestDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.BranchStockTO;
import com.mms.model.MedicineRequestTO;

/**
 * Servlet implementation class MedicineCancleRequestController
 */
public class MedicineCancleRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MedicineCancleRequestController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestId = request.getParameter("reqId");
		MedicineRequestDAO medicineRequestDAO = new MedicineRequestDAO();
		MedicineRequestBO medicineRequestBO = new MedicineRequestBO();
		RequestDispatcher requestDispatcher;
		if (requestId == null) {

			System.out
					.println("Controller : MedicineRequestController : doGet : start");

			List<MedicineRequestTO> medicineRequestTOs;
			HttpSession session = request.getSession();
			String branchAdminId = (String) session.getAttribute("userId");
			String branchId = null;

			try {
				medicineRequestTOs = medicineRequestDAO
						.getRequestsByBranchAdminAndStatus("Pending",
								branchAdminId);
				request.setAttribute("requests", medicineRequestTOs);
				requestDispatcher = request
						.getRequestDispatcher("/jsps/branchAdmin/CancelRequest.jsp");
				request.setAttribute("currentPage", "cancelRequest");
				System.out
						.println("Controller : MedicineRequestController : doGet : end");
				requestDispatcher.forward(request, response);

			} catch (MMSApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			HttpSession session = request.getSession();
			String branchAdminId = (String) session.getAttribute("userId");
			try {
				medicineRequestBO.cancleRequest(requestId, branchAdminId);
				requestDispatcher = request
						.getRequestDispatcher("/jsps/branchAdmin/CancelRequest.jsp");
				request.setAttribute("success", "true");
				requestDispatcher.forward(request, response);
			} catch (MMSBusinessException e) {
				requestDispatcher = request
						.getRequestDispatcher("/jsps/branchAdmin/CancelRequest.jsp");
				request.setAttribute("currentPage", "cancelRequest");
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

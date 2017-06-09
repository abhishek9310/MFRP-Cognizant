package com.mms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mms.dao.MedicineRequestDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.model.MedicineRequestTO;

/**
 * Servlet implementation class RequestFetchController
 */
public class RequestFetchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestFetchController() {
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
		String status = request.getParameter("type");
		MedicineRequestDAO medicineRequestDAO = new MedicineRequestDAO();
		List<MedicineRequestTO> medicineRequestTOs = null;

		try {
			if (status != null) {
				if (status.equals("Pending")) {

					medicineRequestTOs = medicineRequestDAO.getRequestsByStatus("Pending");
					request.setAttribute("requests", medicineRequestTOs);
					request.setAttribute("type", "Pending");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsps/admin/MedicineRequests.jsp");
					requestDispatcher.forward(request, response);
				} else if (status.equals("Approved")) {
					medicineRequestTOs = medicineRequestDAO
							.getRequestsByStatus("Approved");
					request.setAttribute("requests", medicineRequestTOs);
					request.setAttribute("type", "Approved");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsps/admin/MedicineRequests.jsp");
					requestDispatcher.forward(request, response);
				} else if (status.equals("Rejected")) {
					medicineRequestTOs = medicineRequestDAO
							.getRequestsByStatus("Rejected");
					request.setAttribute("requests", medicineRequestTOs);
					request.setAttribute("type", "Rejected");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsps/admin/MedicineRequests.jsp");
					requestDispatcher.forward(request, response);
				}
			}
		} catch (MMSApplicationException e) {
			e.printStackTrace();
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

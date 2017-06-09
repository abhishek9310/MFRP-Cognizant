package com.mms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mms.dao.MedicineDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.model.MedicineTO;

/**
 * Servlet implementation class SearchMedicineForStock
 */
public class SearchMedicineController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchMedicineController() {
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
		String searchString = request.getParameter("searchString");
		String searchFor = request.getParameter("val");
		MedicineDAO medicineDAO = new MedicineDAO();
		List<MedicineTO> medicineTOs;
		if (searchFor.equals("stock")) {
			try {
				medicineTOs = medicineDAO.searchMedicine(searchString);
				request.setAttribute("medicines", medicineTOs);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("/jsps/branchAdmin/MedicineForStock.jsp");
				requestDispatcher.forward(request, response);
			} catch (MMSApplicationException e) {
				PrintWriter printWriter = response.getWriter();
				printWriter
						.println("Application Not Responding Please Contact System Adminstration");
			}
		} else if (searchFor.equals("request")) {
			try {
				medicineTOs = medicineDAO.searchMedicine(searchString);
				request.setAttribute("medicines", medicineTOs);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("/jsps/branchAdmin/MedicineForRequest.jsp");
				requestDispatcher.forward(request, response);
			} catch (MMSApplicationException e) {
				PrintWriter printWriter = response.getWriter();
				printWriter
						.println("Application Not Responding Please Contact System Adminstration");
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

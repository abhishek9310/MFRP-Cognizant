package com.mms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mms.bo.MedicineBO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.MedicineTO;

/**
 * Servlet implementation class MedicineRegisterController
 */
public class MedicineRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MedicineRegisterController() {
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
		System.out.println("Controller : MedicineRegisterController : doGet : start");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsps/admin/RegisterMedicine.jsp");
		request.setAttribute("currentPage", "registerMedicine");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Controller : MedicineRegisterController : doPost : start");
		MedicineTO medicineTO = new MedicineTO();
		MedicineBO medicineBO = new MedicineBO();
		medicineTO.setMedicineName(request.getParameter("medicineName"));
		medicineTO.setDosageValue(request.getParameter("dosageValue"));
		medicineTO.setDosageUnit(request.getParameter("dosageUnit"));
		medicineTO.setNoOfRequestedStrips(request.getParameter("stripNumber"));
		/*medicineTO.setMedicinesInStrip(request.getParameter("medicineInStrip"));*/
		medicineTO.setDescription(request.getParameter("description"));
		medicineTO.setExpiryDate(request.getParameter("expiryDate"));
		medicineTO.setManufactureDate(request.getParameter("manufactureDate"));
		medicineTO.setPriceOfStrip(request.getParameter("stripPrice"));
		System.out.println("hererrww "+medicineTO.getDescription());
		RequestDispatcher dispatcher;
		try {
			System.out.println("Controller : MedicineRegisterController : doPost : end");
			try {
				medicineBO.registerMedicine(medicineTO);
				dispatcher = request.getRequestDispatcher("/jsps/admin/RegisterMedicine.jsp");
				request.setAttribute("success", "Registration Successful");
				dispatcher.forward(request, response);
			} catch (MMSBusinessException e) {
				// TODO Auto-generated catch block
				dispatcher = request.getRequestDispatcher("/jsps/admin/RegisterMedicine.jsp");
				request.setAttribute("errorMap", e.getErrorMap());
				request.setAttribute("medicineTO", medicineTO);
				System.out.println(e.getErrorMap());
				dispatcher.forward(request, response);
			}
		} catch (MMSApplicationException e) {
			// TODO Auto-generated catch block
			dispatcher = request.getRequestDispatcher("/jsps/admin/RegisterMedicine.jsp");
			request.setAttribute("errorMessage", e.getMessage());
			dispatcher.forward(request, response);
		}
	}

}

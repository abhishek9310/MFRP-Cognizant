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
import com.mms.dao.BranchDAO;
import com.mms.dao.MedicineDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.model.BranchAdminTO;
import com.mms.model.BranchTO;
import com.mms.model.MedicineRequestTO;
import com.mms.model.MedicineTO;

/**
 * Servlet implementation class MedicineRequestController
 */
public class MedicineRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MedicineRequestController() {
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
		// TODO Auto-generated method stub
		System.out
				.println("Controller : MedicineRequestController : doGet : start");
		HttpSession session = request.getSession();
		String medicineId = request.getParameter("medicineId");
		String branchAdminId = (String) session.getAttribute("userId");
		if (medicineId == null) {
			MedicineDAO medicineDAO = new MedicineDAO();
			try {
				List<MedicineTO> medicineTOs = medicineDAO.getAllMedicine();
				request.setAttribute("medicines", medicineTOs);

			} catch (MMSApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/jsps/branchAdmin/RequestMedicine.jsp");
			request.setAttribute("currentPage", "requestMedicine");
			System.out
					.println("Controller : MedicineRequestController : doGet : end");
			requestDispatcher.forward(request, response);
		} else {
			BranchDAO branchDAO = new BranchDAO();
			System.out.println("branch admin id is here");
			System.out.println(branchAdminId);

			MedicineDAO medicineDAO = new MedicineDAO();
			MedicineTO medicineTO = null;
			List<BranchTO> branchTOs=null;
			try {
				branchTOs = branchDAO.getBranchesByAdminId(branchAdminId);
				medicineTO = medicineDAO.getMedicineById(medicineId);

			} catch (MMSApplicationException e) {
				e.printStackTrace();
			}
			request.setAttribute("medicine", medicineTO);
			request.setAttribute("branches", branchTOs);
			System.out.println("vishnu is here");
			for (BranchTO branchTO : branchTOs) {
				System.out.println(branchTO.getBranchName());
			}
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/jsps/branchAdmin/RequestMedicine.jsp");
			System.out
					.println("Controller : MedicineRequestController : doGet : end");
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
				.println("Controller : MedicineRequestController : doPost : start");
		MedicineRequestTO medicineRequestTO = new MedicineRequestTO();
		HttpSession session = request.getSession();
		medicineRequestTO.setBranchAdminTO(new BranchAdminTO((String) session
				.getAttribute("userId")));
		medicineRequestTO.setBranchTO(new BranchTO(request
				.getParameter("branchId")));
		medicineRequestTO.setNoOfStrips(request.getParameter("noOfStrips"));
		medicineRequestTO.setMedicineTO(new MedicineTO(request
				.getParameter("medicineId")));

		MedicineRequestBO medicineRequestBO = new MedicineRequestBO();
		try {
			System.out
					.println("Controller : MedicineRequestController : doPost : end");
			medicineRequestBO.requestMedicine(medicineRequestTO);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsps/branchAdmin/RequestMedicine.jsp");
			request.setAttribute("success", "Medicine Rquest Submited Successful");
			requestDispatcher.forward(request, response);
			
		} catch (MMSApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

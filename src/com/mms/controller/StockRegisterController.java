package com.mms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mms.bo.BranchStockBO;
import com.mms.constants.ErrorConstant;
import com.mms.dao.BranchDAO;
import com.mms.dao.MedicineDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.BranchAdminTO;
import com.mms.model.BranchStockTO;
import com.mms.model.BranchTO;
import com.mms.model.MedicineTO;

/**
 * Servlet implementation class StockRegisterController
 */
public class StockRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StockRegisterController() {
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
		System.out
				.println("Controller : StockRegisterController : doGet : start");
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
					.getRequestDispatcher("/jsps/branchAdmin/StockRegister.jsp");
			request.setAttribute("currentPage", "registerStock");
			System.out
					.println("Controller : StockRegisterController : doGet : end");
			requestDispatcher.forward(request, response);
		} else {
			BranchDAO branchDAO = new BranchDAO();
			System.out.println("branch admin id is here");
			System.out.println(branchAdminId);
			List<BranchTO> branchTOs = null;
			MedicineDAO medicineDAO = new MedicineDAO();
			MedicineTO medicineTO = null;

			try {
				 branchTOs = branchDAO
						.getBranchesByAdminId(branchAdminId);
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
					.getRequestDispatcher("/jsps/branchAdmin/StockRegister.jsp");
			request.setAttribute("currentPage", "registerStock");
			System.out
					.println("Controller : StockRegisterController : doGet : end");
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
				.println("Controller : StockRegisterController : doPost : start");
		RequestDispatcher requestDispatcher;
		BranchStockTO branchStockTO = new BranchStockTO();
		BranchDAO branchDAO = new BranchDAO();
		HttpSession session = request.getSession();
		String branchAdminId = (String) session.getAttribute("userId");

		branchStockTO.setBranchAdminTO(new BranchAdminTO(branchAdminId));

		MedicineTO medicineTO = new MedicineTO();
		medicineTO.setMedicineId(request.getParameter("medicineId"));

		branchStockTO.setMedicineTO(medicineTO);
		branchStockTO
				.setBranchTO(new BranchTO(request.getParameter("branchId")));
		branchStockTO.setNumberOfStrips(request.getParameter("noOfStrips"));
		branchStockTO.setDescription(request.getParameter("stockDescription"));

		BranchStockBO branchStockBO = new BranchStockBO();

		try {
			branchStockBO.registereStock(branchStockTO);
			response.sendRedirect("stockDetails");
		} catch (MMSApplicationException e) {
			requestDispatcher = request
					.getRequestDispatcher(ErrorConstant.ERRORPAGE);
			request.setAttribute("errorMsg", e.getMessage());
			requestDispatcher.forward(request, response);
		} catch (MMSBusinessException e) {
			List<BranchTO> branchTOs = null;
			MedicineDAO medicineDAO = new MedicineDAO();
			try {
				 branchTOs = branchDAO
						.getBranchesByAdminId(branchAdminId);
				medicineTO = medicineDAO.getMedicineById(medicineTO
						.getMedicineId());

			} catch (MMSApplicationException ex) {
				requestDispatcher = request
						.getRequestDispatcher(ErrorConstant.ERRORPAGE);
				request.setAttribute("errorMsg", e.getMessage());
				requestDispatcher.forward(request, response);
			}
			requestDispatcher = request
					.getRequestDispatcher("/jsps/branchAdmin/StockRegister.jsp");
			request.setAttribute("medicine", medicineTO);
			request.setAttribute("branches", branchTOs);
			request.setAttribute("errorMap", e.getErrorMap());
			request.setAttribute("currentPage", "registerStock");
			System.out
					.println("Controller : StockRegisterController : doGet : end");
			requestDispatcher.forward(request, response);
		}

	}

}

package com.mms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mms.bo.MedicineBO;
import com.mms.bo.MedicineRequestBO;
import com.mms.model.MedicineRequestTO;
import com.mms.model.MedicineTO;

/**
 * Servlet implementation class MedicineApproveRejectController
 */
public class MedicineReviewRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedicineReviewRequestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsps/Admin/reviewRequests.jsp");
		request.setAttribute("currentPage", "reviewMedicine");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MedicineRequestBO medicineRequestBO =new MedicineRequestBO();
		MedicineRequestTO medicineRequestTO=new MedicineRequestTO();
		/*medicineRequestBO.approveRejectRequest(medicineRequestTO);*/
	}

}

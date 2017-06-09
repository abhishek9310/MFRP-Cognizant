package com.mms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mms.dao.MedicineRequestDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.model.MedicineRequestTO;

/**
 * Servlet implementation class BranchAdminAllRequest
 */
public class BranchAdminAllRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BranchAdminAllRequestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session=request.getSession();
		String branchAdminId=(String) session.getAttribute("userId");
		MedicineRequestDAO medicineRequestDAO=new MedicineRequestDAO();
		List<MedicineRequestTO> medicineRequestTOs;
		try {
			medicineRequestTOs=medicineRequestDAO.getAllBranchAdminRequests(branchAdminId);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("/jsps/branchAdmin/AllRequests.jsp");
			request.setAttribute("requests", medicineRequestTOs);
			requestDispatcher.forward(request, response);
			
		} catch (MMSApplicationException e) {
			
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

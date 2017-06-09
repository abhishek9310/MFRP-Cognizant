package com.mms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mms.dao.BranchStockDAO;
import com.mms.dao.MedicineRequestDAO;
import com.mms.exceptions.MMSApplicationException;
import com.mms.model.BranchStockTO;
import com.mms.model.MedicineRequestTO;

/**
 * Servlet implementation class StockController
 */
public class StockController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		String branchAdminId=(String) session.getAttribute("userId");
		BranchStockDAO branchStockDAO=new BranchStockDAO();
		List<BranchStockTO> branchStockTOs;
		try {
			branchStockTOs=branchStockDAO.getAllBranchAdminStocks(branchAdminId);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("/jsps/branchAdmin/StockDetails.jsp");
			request.setAttribute("StockDetails", branchStockTOs);
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

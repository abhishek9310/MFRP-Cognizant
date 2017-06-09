package com.mms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mms.bo.AdminBO;
import com.mms.bo.BranchAdminBO;
import com.mms.constants.ErrorConstant;
import com.mms.exceptions.MMSApplicationException;
import com.mms.exceptions.MMSBusinessException;
import com.mms.model.AdminTO;
import com.mms.model.BranchAdminTO;
import com.mms.utils.ServletUtilities;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG=Logger.getLogger(LoginController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Controller : LoginController : doGet : start");
		RequestDispatcher dispatcher = null;
		try {
			dispatcher = request.getRequestDispatcher("/jsps/Login.jsp");
			System.out.println("Controller : LoginController : doGet : end");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			dispatcher = request.getRequestDispatcher(ErrorConstant.ERRORPAGE);
			request.setAttribute("errorMsg",
					ErrorConstant.CONTROLLER_EXCEPTION_MSG);
			System.out.println("Controller : LoginController : doGet : end");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Controller : LoginController : doPost : start");
		PrintWriter pw = response.getWriter();
		RequestDispatcher dispatcher = null;

		String userId = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		LOG.info("userName = "+userId);
		String role = request.getParameter("role");

		try {
			if (role == null) {
				pw.print("Bad Request");
			} else if (role.equals("admin")) {

				AdminTO adminTO = new AdminTO();
				adminTO.setUserName(userId);
				adminTO.setPassWord(passWord);
				AdminBO loginBO = new AdminBO();
				int result;

				try {
					result = loginBO.loginAdmin(adminTO);
					System.out.println("Controller : LoginController : doPost : end");
					if (result == 1) {
						HttpSession session = request.getSession();
						session.setAttribute("userName", adminTO.getUsername());
						session.setAttribute("role", "Admin");
						System.out.println("redirection");
						response.sendRedirect(ServletUtilities
								.getBaseUrl(request) + "/admin/home");
					}
				} catch (MMSBusinessException e) {
					
					dispatcher = request
							.getRequestDispatcher("/jsps/Login.jsp");
					request.setAttribute("errorMsg", e.getMessage());
					System.out.println("Controller : LoginController : doPost : end");
					dispatcher.forward(request, response);
				} catch (MMSApplicationException e) {
					dispatcher = request
							.getRequestDispatcher(ErrorConstant.ERRORPAGE);
					request.setAttribute("errorMsg", e.getMessage());
					dispatcher.forward(request, response);
				}
			} else if (role.equals("branchAdmin")) {
				BranchAdminTO branchAdminTO = new BranchAdminTO();
				branchAdminTO.setBranchAdminId(userId);
				branchAdminTO.setPassword(passWord);

				BranchAdminBO branchAdminBO = new BranchAdminBO();

				try {
					int result = branchAdminBO.loginBranchAdmin(branchAdminTO);
					if (result == 1) {
						HttpSession session = request.getSession();
						session.setAttribute("userId",
								branchAdminTO.getBranchAdminId());
						session.setAttribute("role", "BranchAdmin");
						response.sendRedirect(ServletUtilities
								.getBaseUrl(request) + "/branchAdmin/home");
					}
				} catch (MMSBusinessException e) {
					dispatcher = request
							.getRequestDispatcher("/jsps/Login.jsp");
					request.setAttribute("errorMsg", e.getMessage());
					dispatcher.forward(request, response);
				} catch (MMSApplicationException e) {
					dispatcher = request
							.getRequestDispatcher(ErrorConstant.ERRORPAGE);
					request.setAttribute("errorMsg", e.getMessage());
					System.out.println("Controller : LoginController : doPost : end");
					dispatcher.forward(request, response);
				}

			} else {

				pw.println("Bad Request");
			}
		} catch (Exception e) {
			dispatcher = request.getRequestDispatcher(ErrorConstant.ERRORPAGE);
			request.setAttribute("errorMsg",
					ErrorConstant.CONTROLLER_EXCEPTION_MSG);
			dispatcher.forward(request, response);
		}
	}
}

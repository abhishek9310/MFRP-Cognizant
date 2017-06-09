package com.mms.log;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.mms.constants.SpecialFilePaths;

public class LoggerInitializer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(LoggerInitializer.class);

	public void init(final ServletConfig config) throws ServletException {
		final String realPath = config.getServletContext().getRealPath("/");
		final String log4jFile = realPath + SpecialFilePaths.LOG4J_FILE;
		PropertyConfigurator.configure(log4jFile);
		LOG.info("Application Initialized");

	}

}

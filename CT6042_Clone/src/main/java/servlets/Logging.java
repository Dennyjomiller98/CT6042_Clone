package servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.AttackConnections;
import org.apache.log4j.Logger;
import org.bson.Document;

@WebServlet(name = "Logging")
public class Logging extends HttpServlet
{
	static final Logger LOG = Logger.getLogger(Logging.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		LOG.info("Attempting redirect to Logging Attack page");
		try
		{
			response.sendRedirect(request.getContextPath() + "/jsp/logging.jsp");
		}
		catch (IOException e)
		{
			LOG.error("Unable to redirect.",e);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		LOG.info("Attempting form request for Logging attack page.");
		//Standard login attempt for Logging Attack.
		Document userDocument = new Document();
		userDocument.append("Username", request.getParameter("username"));
		userDocument.append("Password", request.getParameter("pword"));

		AttackConnections conn = new AttackConnections();
		//Login is theoretical, no session created as this is just to prove Insufficient logging problem
		boolean loggedIn = conn.attemptLogin(userDocument);
		if(loggedIn)
		{
			LOG.info("Login successful");
			request.getSession(true).setAttribute("loggingResults", "You logged in.");
		}
		else
		{
			LOG.warn("An Error has occurred when User: " + request.getParameter("username") + " attempted to log in. " + System.currentTimeMillis());
			request.getSession(true).setAttribute("loggingResults", "Login Failure, if this was a potential hack, no logging supplied to help.");
		}

		try
		{
			LOG.info("Attempting redirect to Logging Attack page");
			response.sendRedirect(request.getContextPath() + "/jsp/logging.jsp");
		}
		catch (IOException e)
		{
			LOG.error("Unable to redirect.",e);
		}
	}
}
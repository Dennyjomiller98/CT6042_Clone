package servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.AttackConnections;
import org.apache.log4j.Logger;
import org.bson.Document;

@WebServlet(name = "SensitiveDataExposure")
public class SensitiveDataExposure extends HttpServlet
{
	static final Logger LOG = Logger.getLogger(SensitiveDataExposure.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		redirectMe(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		LOG.info("Attempting form request for SensitiveDataExposure attack.");
		//Standard login attempt for SDA Attack.
		Document userDocument = new Document();
		userDocument.append("Username", request.getParameter("username"));
		userDocument.append("Password", request.getParameter("pword"));

		try
		{
			AttackConnections conn = new AttackConnections();
			//Login is theoretical, no session created as this is just to prove Insufficient logging problem
			String loggedInInfo = conn.sensitiveData(userDocument);
			if(loggedInInfo != null)
			{
				LOG.info("Logged in information retrieved successfully");
				request.getSession(true).setAttribute("sensitiveDataResults", "Username: " + userDocument.get("Username"));
			}
			else
			{
				LOG.warn("Alerting user on failed Login attempt.");
				request.getSession(true).setAttribute("sensitiveDataResults", "Login credentials are incorrect for User: " + userDocument.get("Username") + ". Any logging shown in the Log file could lead to Data exposure.");
			}

			redirectMe(request, response);
		}
		catch(Exception e)
		{
			LOG.error("Error with retrieving user information.", e);
		}
	}

	private void redirectMe(HttpServletRequest request, HttpServletResponse response)
	{
		LOG.info("Attempting Redirect to Sensitive Data Exposure attack page");
		try
		{
			response.sendRedirect(request.getContextPath() + "/jsp/sensitivedataexposure.jsp");
		}
		catch (IOException e)
		{
			LOG.error("Unable to redirect.", e);
		}
	}
}
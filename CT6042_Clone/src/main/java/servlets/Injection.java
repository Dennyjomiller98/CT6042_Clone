package servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.AttackConnections;
import org.apache.log4j.Logger;
import org.bson.Document;

@WebServlet(name = "Injection")
public class Injection extends HttpServlet
{
	static final Logger LOG = Logger.getLogger(Injection.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			response.sendRedirect(request.getContextPath() + "/jsp/injection.jsp");
		}
		catch (IOException e)
		{
			LOG.error("Unable to redirect.",e);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		Document userDocument = new Document();
		userDocument.append("Username", request.getParameter("username"));

		AttackConnections attackConn = new AttackConnections();
		String returnedDBData = attackConn.getDataFromUsername(userDocument);
		if(returnedDBData != null)
		{
			request.getSession(true).setAttribute("injectionResults", returnedDBData);
		}

		try
		{
			response.sendRedirect(request.getContextPath() + "/jsp/injection.jsp");
		}
		catch (IOException e)
		{
			LOG.error("Unable to return to Injection Attack Page.",e);
		}
	}
}

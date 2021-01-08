package servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet(name = "XSS")
public class XSS extends HttpServlet
{
	static final Logger LOG = Logger.getLogger(XSS.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			response.sendRedirect(request.getContextPath() + "/jsp/xss.jsp");
		}
		catch (IOException e)
		{
			LOG.error("Unable to redirect.",e);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		String input = request.getParameter("input");
		if(input != null)
		{
			request.getSession(true).setAttribute("xssResults", input);
		}

		try
		{
			response.sendRedirect(request.getContextPath() + "/jsp/xss.jsp");
		}
		catch (IOException e)
		{
			LOG.error("Unable to return to XSS Attack Page.",e);
		}
	}
}
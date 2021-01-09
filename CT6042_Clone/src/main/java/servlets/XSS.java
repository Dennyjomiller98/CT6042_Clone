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
		LOG.info("Attempting redirect to XSS Attack page.");
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
		LOG.info("Attempting XSS Form request");
		String input = request.getParameter("input");
		if(input != null)
		{
			String sanitizedInput = secureInputFromXSS(input);
			request.getSession(true).setAttribute("xssResults", sanitizedInput);
		}

		try
		{
			LOG.info("Attempting redirect to XSS Attack page.");
			response.sendRedirect(request.getContextPath() + "/jsp/xss.jsp");
		}
		catch (IOException e)
		{
			LOG.error("Unable to return to XSS Attack Page.",e);
		}
	}

	private String secureInputFromXSS(String input)
	{
		String ret;
		//Prevent Script attacks by encoding and sanitizing data before returning to user.
		ret = input.replace("&", "&amp;");
		ret = ret.replace("<", "&lt;");
		ret = ret.replace(">", "&gt;");
		ret = ret.replace("\"", "&quot;");
		ret = ret.replace("'", "&#x27;");
		return ret;
	}
}
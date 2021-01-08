package servlets;

import java.io.IOException;
import java.net.URL;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet(name = "FileTraversal")
public class FileTraversal extends HttpServlet
{
	static final Logger LOG = Logger.getLogger(FileTraversal.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			response.sendRedirect(request.getContextPath() + "/jsp/filetraversal.jsp");
		}
		catch (IOException e)
		{
			LOG.error("Unable to redirect.",e);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		//Retrieve an image using User Input
		String imageChoice = request.getParameter("imageChoice");
		URL resource = null;
		if(imageChoice != null && imageChoice.trim().length() > 0)
		{
			resource = this.getClass().getResource("/" + imageChoice.trim().replace(" ", ""));
		}

		if (resource != null)
		{
			//File is found, return File location
			request.getSession(true).setAttribute("fileTraversalResults", "Found File location: " + resource.getPath());
		}
		else
		{
			request.getSession(true).setAttribute("fileTraversalResults", "Unable to find File, User input failure.");
		}

		try
		{
			response.sendRedirect(request.getContextPath() + "/jsp/filetraversal.jsp");
		}
		catch (IOException e)
		{
			LOG.error("Unable to redirect.",e);
		}
	}
}
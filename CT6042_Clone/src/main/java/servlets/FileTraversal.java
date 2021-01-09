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
		LOG.info("Redirecting to File Traversal page");
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
		LOG.info("Form Submit for File Traversal Attack.");
		String imageChoice = request.getParameter("imageChoice");
		URL resource = null;
		if(imageChoice != null && imageChoice.trim().length() > 0)
		{
			LOG.info("User Input is not empty, attempting to locate resource with name: " + imageChoice);
			resource = this.getClass().getResource("/" + imageChoice.trim().replace(" ", ""));
		}

		if (resource != null)
		{
			boolean isValid = validateResource(resource, imageChoice);
			if(isValid)
			{
				LOG.info("Resource has been found, returning result to the user.");
				//File is found, return File location
				request.getSession(true).setAttribute("fileTraversalResults", "Found File location: " + resource.getPath());
			}
			else
			{
				LOG.info("Resource is not valid.");
				request.getSession(true).setAttribute("fileTraversalResults", "File is not a valid image FileType, cannot retrieve resource for: " + imageChoice);
			}

		}
		else
		{
			LOG.warn("Resource was not found, please clarify input was correct (Resource find is Case Sensitive!)");
			request.getSession(true).setAttribute("fileTraversalResults", "Unable to find File, User input failure.");
		}

		try
		{
			LOG.info("Attempting Redirect to File Traversal Page.");
			response.sendRedirect(request.getContextPath() + "/jsp/filetraversal.jsp");
		}
		catch (IOException e)
		{
			LOG.error("Unable to redirect.",e);
		}
	}

	private boolean validateResource(URL resource, String imageChoice)
	{
		boolean ret = false;
		String file = resource.getFile();
		if(file.endsWith(".png") || file.endsWith(".jpg") || file.endsWith(".jpeg") || file.endsWith(".gif"))
		{
			LOG.info("File Type for resource: " + imageChoice + " is a valid image.");
			ret = true;
		}
		else
		{
			LOG.error("File Type is not an image, will not return location.");
		}
		return ret;
	}
}
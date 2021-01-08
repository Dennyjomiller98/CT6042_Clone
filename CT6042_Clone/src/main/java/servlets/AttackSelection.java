package servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet(name = "AttackSelection")
public class AttackSelection extends HttpServlet
{
    static final Logger LOG = Logger.getLogger(AttackSelection.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            response.sendRedirect(request.getContextPath() + "/jsp/attackselection.jsp");
        }
        catch (IOException e)
        {
            LOG.error("Unable to redirect.",e);
        }
    }
}
<%--
  Created by IntelliJ IDEA.
  User: Denny-Jo
  Date: 04/01/2021
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en-GB">
<link href=${pageContext.request.contextPath}/css/navbar.css rel="stylesheet" type="text/css">
<link href=${pageContext.request.contextPath}/css/mainbody.css rel="stylesheet" type="text/css">
    <head>
        <title>Logging</title>
    </head>
    <body>
        <%--Navbar--%>
        <div class="navbar">
            <div class="bottomnavdiv">
                <a class="bodyA" style="display: inline; margin-right: 200px;margin-bottom: 10px;" href=${pageContext.request.contextPath}/servlets/AttackSelection>&nbsp;Return Home&nbsp;</a>
            </div>
            <div class="topnav">
                <h2>Insecure/Insufficient Logging Attack</h2>
            </div>
            <div class="topnavdiv">
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/Injection>&nbsp;Injection (SQL)&nbsp;</a>
                &verbar;
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/XSS>&nbsp;Cross Site Scripting (XSS)&nbsp;</a>
                &verbar;
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/FileTraversal>&nbsp;File Traversal Attack&nbsp;</a>
                &verbar;
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/SensitiveDataExposure>&nbsp;Sensitive Data Exposure&nbsp;</a>

            </div>
        </div>

        <div class="mainBody">
            <p>
                This page is for Insecure and Insufficient Logging. <br/>
                <strong>Note: </strong>The custom Log File to view Outputted information is available in the Glassfish Domain file (With the name 'logOutput.log')<br/>
                In the following Form below, attempt to log in. <br/><br/>
                <strong>Valid Credentials:</strong><br/>
                Username: Test <br/>
                Password: Test <br/><br/>
            </p>

            <form action="${pageContext.request.contextPath}/servlets/Logging" method="POST">
                <label for="username">Username:</label>
                <input type="text" name="username" id="username" required/>
                <br/>
                <label for="pword">Password:</label>
                <input type="password" name="pword" id="pword" required/>
                <br/>
                <input type="reset" value="Clear">
                <input type="submit" value="Submit">
            </form>

            <div class="results">
                <p>
                    Any logging results appear below.<br/>
                    By using both Valid or invalid credentials, there is now sufficient logging to help track session login or login attempts. <br/>
                    This can aid developers/companies in the circumstance of an attack, as the detailed logs can provide relevant information, including timestamps and any errors/exceptions the application runs in to, which may be the cause for the security flaw attack.
                </p>
                <% String loggingResults = null;
                    if (session.getAttribute("loggingResults") != null)
                    {
                        loggingResults = session.getAttribute("loggingResults").toString();
                    }
                    if(loggingResults != null) {%>
                <strong>
                    <%=loggingResults%> <br/>
                </strong>
                (End of returned data)
                <%}%>
            </div>
        </div>
    </body>
</html>

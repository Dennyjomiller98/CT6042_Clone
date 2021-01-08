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
        <title>Sensitive Data Exposure Attack</title>
    </head>
    <body>
        <%--Navbar--%>
        <div class="navbar">
            <div class="bottomnavdiv">
                <a class="bodyA" style="display: inline; margin-right: 200px;margin-bottom: 10px;" href=${pageContext.request.contextPath}/servlets/AttackSelection>&nbsp;Return Home&nbsp;</a>
            </div>
            <div class="topnav">
                <h2>Sensitive Data Exposure Attack</h2>
            </div>
            <div class="topnavdiv">
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/Injection>&nbsp;Injection (SQL)&nbsp;</a>
                &verbar;
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/XSS>&nbsp;Cross Site Scripting (XSS)&nbsp;</a>
                &verbar;
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/FileTraversal>&nbsp;File Traversal Attack&nbsp;</a>
                &verbar;
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/Logging>&nbsp;Insecure Logging&nbsp;</a>
            </div>
        </div>

        <div class="mainBody">
            <p>
                This page is for Sensitive Data Exposure. (Following on from <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/Logging>&nbsp;<strong>Insufficient Logging</strong>&nbsp;</a>) <br/>
                <strong>Note: </strong>The custom Log File to view Outputted information is available in the Glassfish Domain file (With the name 'logOutput.log')<br/>
                In the following form, the use of how information is used and outputted to the user can be shown as a security risk: <br/> <br/>

                <strong>Valid Credentials: </strong> <br/>
                Username: Test <br/>
                Password: Test <br/><br/>

                <strong>Invalid Credentials:</strong><br/>
                Username: Test <br/>
                Password: WrongPassword <br/><br/>

                <strong>OR</strong> <br/><br/>

                Username: WrongUser <br/>
                Password: Test <br/><br/>

                Even giving away information such as a users' name can be crucial to sensitive data exposure. <br/>
                Any login attempt results would appear below the form. On an insecure site, crucial information may be shown to the wrong user, leading to sensitive data exposure (potentially opening up the application for an attack). <br/>
                In compliance to GDPR, user information should not be shared and should be managed securely. Because of this, users names should not be provided in the logs.
            </p>

            <form action="${pageContext.request.contextPath}/servlets/SensitiveDataExposure" method="POST">
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
                <% String sensitiveDataResults = null;
                    if (session.getAttribute("sensitiveDataResults") != null)
                    {
                        sensitiveDataResults = session.getAttribute("sensitiveDataResults").toString();
                    }
                    if(sensitiveDataResults != null) {%>
                <strong>
                    <%=sensitiveDataResults%> <br/>
                </strong>
                (End of returned data)
                <%}%>
            </div>
        </div>
    </body>
</html>

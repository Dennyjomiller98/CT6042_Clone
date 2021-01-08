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
        <title>File Traversal Attack</title>
    </head>
    <body>
        <%--Navbar--%>
        <div class="navbar">
            <div class="bottomnavdiv">
                <a class="bodyA" style="display: inline; margin-right: 200px;margin-bottom: 10px;" href=${pageContext.request.contextPath}/servlets/AttackSelection>&nbsp;Return Home&nbsp;</a>
            </div>
            <div class="topnav">
                <h2>File Traversal Attack</h2>
            </div>
            <div class="topnavdiv">
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/Injection>&nbsp;Injection (SQL)&nbsp;</a>
                &verbar;
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/XSS>&nbsp;Cross Site Scripting (XSS)&nbsp;</a>
                &verbar;
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/Logging>&nbsp;Insecure Logging&nbsp;</a>
                &verbar;
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/SensitiveDataExposure>&nbsp;Sensitive Data Exposure&nbsp;</a>
            </div>
        </div>

        <div class="mainBody">
            <p>
                This page is for Path Traversal/File Traversal, showing how this vulnerability can be exploited. <br/>
                In the following Form below, please input the image you would like to access. <br/><br/>

                <strong>Valid Data:</strong><br/>
                UoGLogo.png<br/><br/>
                <strong>Malicious Data:</strong><br/>
                /Config/log4j.properties<br/><br/>

                As shown, without restrictions on file protection if user input is required, and without limiting the filetype allowed to be retrieved, Traversal to access other resources is possible.
            </p>

            <form action="${pageContext.request.contextPath}/servlets/FileTraversal" method="POST">
                <label for="imageChoice">Image Choice:</label>
                <input type="text" name="imageChoice" id="imageChoice" required/>
                <br/>
                <input type="reset" value="Clear">
                <input type="submit" value="Submit">
            </form>

            <div class="results">
                <p>Found File results would appear below.</p>
                <% String fileTraversalResults = null;
                    if (session.getAttribute("fileTraversalResults") != null)
                    {
                        fileTraversalResults = session.getAttribute("fileTraversalResults").toString();
                    }
                    if(fileTraversalResults != null) {%>
                <strong>
                    <%=fileTraversalResults%> <br/>
                </strong>
                (End of returned data)
                <%}%>
            </div>
        </div>
    </body>
</html>

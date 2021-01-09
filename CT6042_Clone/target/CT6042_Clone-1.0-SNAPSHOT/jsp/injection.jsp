<%--
  Created by IntelliJ IDEA.
  User: Denny-Jo
  Date: 04/01/2021
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en-GB">
<link href=${pageContext.request.contextPath}/css/navbar.css rel="stylesheet" type="text/css">
<link href=${pageContext.request.contextPath}/css/mainbody.css rel="stylesheet" type="text/css">
    <head>
        <title>Injection</title>
    </head>
    <body>
        <%--Navbar--%>
        <div class="navbar">
            <div class="bottomnavdiv">
                <a class="bodyA" style="display: inline; margin-right: 200px;margin-bottom: 10px;" href=${pageContext.request.contextPath}/servlets/AttackSelection>&nbsp;Return Home&nbsp;</a>
            </div>
            <div class="topnav">
                <h2>SQL Injection Attack</h2>
            </div>
            <div class="topnavdiv">
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/XSS>&nbsp;Cross Site Scripting (XSS)&nbsp;</a>
                &verbar;
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/FileTraversal>&nbsp;File Traversal Attack&nbsp;</a>
                &verbar;
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/Logging>&nbsp;Insecure Logging&nbsp;</a>
                &verbar;
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/SensitiveDataExposure>&nbsp;Sensitive Data Exposure&nbsp;</a>
            </div>
        </div>

        <div class="mainBody">
            <p>
                This page is for an SQL Injection attack. In the following Form below, please submit credentials. (These credentials should be in the System due to Database Startup Script): <br/><br/>
                <strong>Username: Test </strong><br/><br/>
                By using SQL in the form, you can break the query to access data unrelated to the user above. This can also allow data manipulation of the database. <br/><br/>
                <strong>Injection Example:</strong><br/>
                T' OR 1=1 --'
            </p>

            <form action="${pageContext.request.contextPath}/servlets/Injection" method="POST">
                <label for="username">Username:</label>
                <input type="text" name="username" id="username" required />
                <br/>
                <input type="submit" value="Get Details">
            </form>

            <div class="results">
                <p>
                    As proven with the examples shown above, using pre-prepared statements, along with the implementation of JavaBeans, it is safer to perform SQL queries, as the Malicious attempt no longer works. <br/>
                    <% String injectionResults = null;
                        if (session.getAttribute("injectionResults") != null)
                        {
                            injectionResults = session.getAttribute("injectionResults").toString();
                        }
                        if(injectionResults != null) {%>
                    The data retrieved is as follows: <br/>
                    <strong>
                        <%=injectionResults%> <br/>
                    </strong>
                    (End of returned data)
                    <%}%>
                </p>
            </div>
        </div>
    </body>
</html>

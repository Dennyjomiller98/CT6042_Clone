<%--
  Created by IntelliJ IDEA.
  User: Denny-Jo
  Date: 11/11/2020
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en-GB">
<link href=${pageContext.request.contextPath}/css/navbar.css rel="stylesheet" type="text/css">
<link href=${pageContext.request.contextPath}/css/mainbody.css rel="stylesheet" type="text/css">
    <head>
        <title>Home</title>
    </head>
    <body>
        <%--Navbar--%>
        <div class="navbar">
            <div class="bottomnavdiv"></div>
            <div class="topnav">
                <h2>Vulnerable Examples</h2>
            </div>
            <div class="topnavdiv">
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/Injection>&nbsp;Injection (SQL)&nbsp;</a>
                &verbar;
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

            <span>CT6042</span>
            <span>S1707031</span>
            <span>Denny-Jo Miller</span>

            <p>
                Welcome. From here, please select a Vulnerability to view more information and an example of each Security Risk. <br/>
                <strong>Note:</strong> This Site only shows solutions for reducing vulnerabilities, proving the malicious attempts are now prevented. <br/>
                To attempt an attack, you will need to look at the "Vulnerabilities" Branch (or the CT6042_s1707031_Alternatives_Source_Code), available here: <a>https://github.com/Dennyjomiller98/CT6042_Clone/tree/Alternatives</a>
            </p>

            <div>
                <a class="bodyA" href=${pageContext.request.contextPath}/servlets/Injection>&nbsp;Injection (SQL)&nbsp;</a>
                <a class="bodyA" href=${pageContext.request.contextPath}/servlets/XSS>&nbsp;Cross Site Scripting (XSS)&nbsp;</a>
                <a class="bodyA" href=${pageContext.request.contextPath}/servlets/FileTraversal>&nbsp;File Traversal Attack&nbsp;</a>
                <a class="bodyA" href=${pageContext.request.contextPath}/servlets/Logging>&nbsp;Insecure Logging&nbsp;</a>
                <a class="bodyA" href=${pageContext.request.contextPath}/servlets/SensitiveDataExposure>&nbsp;Sensitive Data Exposure&nbsp;</a>
            </div>
        </div>
    </body>
</html>



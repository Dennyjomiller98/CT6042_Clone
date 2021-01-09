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
        <title>XSS</title>
    </head>
    <body>
        <%--Navbar--%>
        <div class="navbar" style="width: 100%">
            <div class="bottomnavdiv">
                <a class="bodyA" style="display: inline; margin-right: 200px;margin-bottom: 10px;" href=${pageContext.request.contextPath}/servlets/AttackSelection>&nbsp;Return Home&nbsp;</a>
            </div>
            <div class="topnav">
                <h2>Cross-Site Scripting (XSS) Attack</h2>
            </div>
            <div class="topnavdiv">
                <a class="bodyA" style="display: inline" href=${pageContext.request.contextPath}/servlets/Injection>&nbsp;Injection (SQL)&nbsp;</a>
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
                This page is for Insecure and Insufficient Logging, showing how this vulnerability can be exploited. <br/>
                In the following Form below, insert the script that allows exploitation of the forms Request. <br/> <br/>
                <Strong>Valid Input:</Strong><br/>
                Hi, thanks for outputting what was entered.<br/><br/>
                <Strong>Malicious Input:</Strong> <br/>
                &lt;script&gt; alert("oops, now look what happened"); &lt;/script&gt; You got Hacked!
            </p>

            <form action="${pageContext.request.contextPath}/servlets/XSS" method="POST">
                <label for="input">Enter what you want me to display below:</label>
                <input type="text" name="input" id="input" required />
                <br/>
                <input type="submit" value="Submit">
            </form>

            <div class="results">
                <p>
                    With the help of HtmlEncoding, the Form is secure against the above Malicious attack. <br/>
                </p>
                Anything inserted above should appear here below: <br/>
                <% String xssResults = null;
                    if (session.getAttribute("xssResults") != null)
                    {
                        xssResults = session.getAttribute("xssResults").toString();
                    }
                    if(xssResults != null) {%>
                <strong>
                    <%=xssResults%> <br/>
                </strong>
                (End of returned data)
                <%}%>
            </div>
        </div>
    </body>
</html>

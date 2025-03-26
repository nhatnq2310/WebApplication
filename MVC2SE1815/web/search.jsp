<%-- 
    Document   : search
    Created on : Mar 3, 2025, 4:12:22 PM
    Author     : ADMIN
--%>


<%-- <%@page import="duyd.registration.RegistrationDTO"%>
<%@page import="java.util.List"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <%-- Expression Language (EL) --%>
        <font color="red">
        Welcome, ${sessionScope.USER.fullName}, <a href="DispatchServlet?btAction=Logout">Log out</a>
        </font>
        <h1>Search Page</h1><br/>
        <form action="DispatchServlet">
            Search Value  <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Lastname</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                            <form action="DispatchServlet" method="POST">
                            
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${dto.username}
                                        <input type="hidden" name="txtUsername" value="${dto.username}" />
                                    </td>
                                    <td>
                                        
                                        <input type="text" name="txtPassword" value="${dto.password}" />
                                    </td>
                                    <td>
                                        ${dto.fullName}
                                    </td>
                                    <td>
                                        <input type="checkbox" name="cbAdmin" value="ON" 
                                               <c:if test="${dto.role}">
                                                   checked="checked"
                                               </c:if>
                                               />
                                    </td>
                                    <td>
                                        <c:url var="deleteLink" value="DispatchServlet">
                                            <c:param name="btAction" value="Delete"/>
                                            <c:param name="pk" value="${dto.username}"/>
                                            <c:param name="classSearchValue" value="${searchValue}"/>
                                        </c:url>
                                        <a href="${deleteLink}">Delete</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Update" name="btAction">
                                        <input type="hidden" name="txtLastSearchValue" value="${searchValue}" />
                                    </td>
                            </tr>
                            </form>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>
                <c:if test="${empty result}">
                    <font color="red">
                        No record is matched!!
                    </font>
                </c:if>
        </c:if>
        <%--<%
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                Cookie lasCookie = cookies(cookies.length - 1);
                String username = lasCookie.getName();
                %>
                <font>
        <%
            }
        %>
        <h1>Search Page</h1><br/>
        <form action="DispatchServlet">
            Search Value  <input type="text" name="txtSearchValue" value="<%= request.getParameter("txtSearchValue") %>" />
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>)request.getAttribute("SEARCH_RESULT");
                if (result != null) { //have result
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Lastname</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 0;
                                for (RegistrationDTO dto : result) {
                                    String urlRewriting = "DispatchServlet"
                                            + "?btAction=Delete"
                                            + "&pk=" + dto.getUsername()
                                            + "&classSearchValue=" + searchValue;
                                    %>
                        <form action="DispatchServlet" method="POST">    
                                    <tr>
                                <td>
                                    <%= ++count %>
.</td>
                                <td>
                                    <%= dto.getUsername() %>
                                    <input type="hidden" name="txtUsername" 
                                           value="<%= dto.getUsername() %>" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="<%= dto.getPassword()%>" />
                                </td>
                                <td>
                                    <%= dto.getFullName()%>
                                </td>
                                <td>
                                    <input type="checkbox" name="cbAdmin" value="ON" 
                                           <%
                                               if (dto.isRole()) {
                                                   %>
                                                   checked="checked"
                                                   <%
                                               }// end user is admin
                                           %>
                                           />
                                </td>
                                <td>
                                    <a href="<%= urlRewriting%>">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="txtLastSearchValue" 
                                           value="<%= searchValue%>" />
                                </td>
                            </tr>
                        
                        </form>
                            <%
                                }//traverse list result
                            %>
                        </tbody>
                    </table>
 
        <%
                } else {//no result
                    %>
                    <h2>
                        <font color="red">
                            No record is matches
                        </font>
                    </h2>
        <%
                }
            }//search value is not exiested 
           
        %> --%>
    </body>
</html>
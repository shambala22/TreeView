<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shambala
  Date: 22.08.17
  Time: 5:24
  To change this template use File | Settings | File Templates.
--%>
<c:forEach var="folder" items="${folder.children}">
    <!-- TODO: print the node here -->
    
    <c:set var="folder" value="${folder}" scope="request"/>
    <jsp:include page="folder.jsp"/>
</c:forEach>

<%--
        Document: aboutl.jsp
        Created On: Feb 4, 2016
        Authors: Deepak Rohan, Abhishek

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Section to display description --%>
<section class="copyright">
    &copy; Researchers Exchange Participations
    <div>
        <span><c:out value="${cookie.host.name}"/>:<c:out value="${cookie.host.value}"/></span> / <span><c:out value="${cookie.port.name}"/>:<c:out value="${cookie.port.value}"/></span>
    </div>
</section>
</body>
</html>


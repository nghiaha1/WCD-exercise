<%@ page import="com.wcdexercise.wcdexercise.entity.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Customer customer = (Customer) request.getAttribute("student");
%>
<jsp:include page="../includes/header.jsp">
    <jsp:param name="title" value="${student.getFullName()} | Student detail"/>
</jsp:include>
<jsp:include page="../includes/menu.jsp"></jsp:include>
    <a href="/admin/students/list">Back to list</a> &nbsp;
    <a href="/admin/students/create">Create new student</a>
    <div>
        Rollnumber: <%=customer.getID()%>
    </div>
    <div>
        Fullname: <%=customer.getName()%>
    </div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
</body>
</html>

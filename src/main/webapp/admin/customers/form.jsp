<%@ page import="com.wcdexercise.wcdexercise.entity.Customer" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Customer customer = (Customer) request.getAttribute("customer");
    int action = (int) request.getAttribute("action");
    HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");
    String url = "/admin/customers/create";
    if(action == 2){
        url = "/admin/customers/edit";
    }
    if(errors == null){
        errors = new HashMap<>();
    }
%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="../includes/head.jsp"></jsp:include>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="../includes/navbar.jsp"></jsp:include>

    <!-- Main Sidebar Container -->
    <jsp:include page="../includes/sidebar.jsp"></jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Student management</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="/admin/customers/list">Student Management</a></li>
                            <li class="breadcrumb-item active">Create new</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <%
                    if(errors != null && errors.size() > 0){
                %>
                <div class="row">
                    <div class="col-12">
                        <div class="callout callout-danger">
                            <h5>Please fix error below</h5>
                            <ul>
                            <%
                                for (String msg: errors.values()){
                            %>
                                <li class="text-danger"><%=msg%></li>
                            <%
                                }
                            %>
                            </ul>
                        </div>
                    </div>
                </div>
                <%}%>
                <div class="row">
                    <div class="col-12">
                        <div class="card card-warning">
                            <div class="card-header">
                                <h3 class="card-title">Please enter information below</h3>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <form action="<%=url%>" method="post">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <label>Customer's ID</label>
                                                <input type="text" name="id" value="<%=customer.getID()%>" class="form-control" placeholder="Please enter customer's ID">
                                                <%if(errors.containsKey("phone")){%>
                                                <span class="text-danger">* <%=errors.get("ID")%></span>
                                                <%}%>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <!-- text input -->
                                            <div class="form-group">
                                                <label>Full name</label>
                                                <input type="text" name="name" value="<%=customer.getName()%>" class="form-control" placeholder="Please enter customer's name">
                                                <%if(errors.containsKey("fullName")){%>
                                                    <span class="text-danger">* <%=errors.get("fullName")%></span>
                                                <%}%>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <label>Phone</label>
                                                <input type="text" name="phone" value="<%=customer.getPhone()%>" class="form-control" placeholder="Please enter customer's phone">
                                                <%if(errors.containsKey("phone")){%>
                                                    <span class="text-danger">* <%=errors.get("phone")%></span>
                                                <%}%>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <label>Email</label>
                                                <input type="email" name="img" value="<%=customer.getImg()%>" class="form-control" placeholder="Please enter student's image">
                                                <%if(errors.containsKey("email")){%>
                                                <span class="text-danger">* <%=errors.get("email")%></span>
                                                <%}%>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <label>Birthday</label>
                                                <div class="input-group date" id="reservationdate" data-target-input="nearest">
                                                    <input name="dob" value="<%=customer.getDob()%>" type="text" class="form-control datetimepicker-input" data-target="#reservationdate">
                                                    <%if(errors.containsKey("birthday")){%>
                                                        <span class="text-danger">* <%=errors.get("birthday")%></span>
                                                    <%}%>
                                                    <div class="input-group-append" data-target="#reservationdate" data-toggle="datetimepicker">
                                                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <button class="btn btn-primary">Save</button>
                                                <input type="reset" class="btn btn-default" value="Reset">
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- /.card-body -->
                        </div>
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="../includes/footer.jsp"></jsp:include>
    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<jsp:include page="../includes/script.jsp"></jsp:include>
<script>
    document.addEventListener('DOMContentLoaded', function (){
        $('#reservationdate').datetimepicker({
            format: 'YYYY-MM-DD'
        });
    })
</script>
</body>
</html>
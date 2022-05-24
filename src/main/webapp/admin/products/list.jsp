<%@ page import="com.wcdexercise3.wcdexercise3.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.wcdexercise3.wcdexercise3.entity.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Product> list = (List<Product>)request.getAttribute("list");
    if(list == null) {
        list = new ArrayList<>();
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
                        <h1>DataTables</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active">DataTables</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h3 class="card-title">DataTable with default features</h3>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Category ID</th>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th>Detail</th>
                                        <th>Thumbnail</th>
                                        <th>Price</th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%for (Product obj : list) {
                                    %>
                                    <tr>
                                        <td><%=obj.getID()%></td>
                                        <td><%=obj.getCategoryID()%></td>
                                        <td><%=obj.getName()%></td>
                                        <td><%=obj.getDescription()%></td>
                                        <td><%=obj.getDetail()%></td>
                                        <td><%=obj.getThumbnail()%></td>
                                        <td>
                                            <img class="img-bordered" src="<%=obj.getPrice()%>" alt="." width="150px">
                                        </td>
                                        <td><a href="/admin/products/detail?id=<%=obj.getID()%>">Detail</a>&nbsp</td>
                                        <td><a href="/admin/products/edit?id=<%=obj.getID()%>">Edit</a>&nbsp</td>
                                        <td><a href="/admin/products/delete?id=<%=obj.getID()%>" onclick="return confirm('Are you sure?')">Delete</a>&nbsp</td>
                                    </tr>
                                    <%}%>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <th>ID</th>
                                        <th>Category ID</th>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th>Detail</th>
                                        <th>Thumbnail</th>
                                        <th>Price</th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
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
</body>
</html>

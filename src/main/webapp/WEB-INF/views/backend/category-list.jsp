
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- directive của JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Dashboard - SB Admin</title>

    <jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>

    <!-- Custome css resource file -->
    <jsp:include page="/WEB-INF/views/backend/layout/css.jsp"></jsp:include>
</head>
<body class="sb-nav-fixed">
<%--Header--%>
<jsp:include page="/WEB-INF/views/backend/layout/header.jsp"></jsp:include>
<%--Header--%>
<div id="layoutSidenav">
    <%--    side-bar--%>
    <jsp:include page="/WEB-INF/views/backend/layout/left-side-bar.jsp"></jsp:include>
    <%--    side-bar--%>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Categories</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="${classpath }/admin/home">Dashboard</a></li>
                    <li class="breadcrumb-item active">Categories</li>
                </ol>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group mb-4">
                            <a href="${classpath }/admin/category-add" role="button" class="btn btn-primary">Add New Category</a>
                        </div>
                    </div>
                </div>
                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        List categories
                    </div>
                    <div class="card-body">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">Id</th>
                                <th scope="col">Name</th>
                                <th scope="col">Create by</th>
                                <th scope="col">Update by</th>
                                <th scope="col">Create date</th>
                                <th scope="col">Update date</th>
                                <th scope="col">Status</th>
                                <th scope="col">Description</th>
                                <th scope="col">Actions</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">Id</th>
                                <th scope="col">Name</th>
                                <th scope="col">Create by</th>
                                <th scope="col">Update by</th>
                                <th scope="col">Create date</th>
                                <th scope="col">Update date</th>
                                <th scope="col">Status</th>
                                <th scope="col">Description</th>
                                <th scope="col">Actions</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach var="category" items="${categories }" varStatus="loop">
                                <tr>
                                    <td>${loop.index + 1 }</td>
                                    <td>${category.id }</td>
                                    <td>${category.name }</td>
                                    <td>${category.userCreateCategory.username }</td>
                                    <td>${category.userUpdateCategory.username }</td>
                                    <td>
                                        <fmt:formatDate value="${category.createDate }" pattern="dd-MM-yyyy"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${category.updateDate }" pattern="dd-MM-yyyy"/>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${category.status }">
                                                <span>Active</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span>Inactive</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${category.description }</td>
                                    <td>
                                        <a href="${classpath }/admin/category-edit/${category.id }" role="button"
                                           class="btn btn-success">Edit</a>
                                        <a href="${classpath }/admin/category-delete/${category.id }" role="button"
                                           class="btn btn-danger">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
        <%--Footer--%>
        <jsp:include page="/WEB-INF/views/backend/layout/footer.jsp"></jsp:include>
        <%--Footer--%>
    </div>
</div>

<%--Script--%>
<jsp:include page="/WEB-INF/views/backend/layout/js.jsp"></jsp:include>
</body>
</html>

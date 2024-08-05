<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- directive của JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

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
                    <li class="breadcrumb-item active">Edit</li>
                </ol>

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <sf:form class="form" action="${classpath }/admin/category-edit-save" method="post" modelAttribute="category" enctype="multipart/form-data">
                                    <div class="form-body">

                                        <sf:hidden path="id"/> <!-- id > 0 -> Edit -->
                                        <div class="row">

                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="name">Category name</label>
                                                    <sf:input path="name" type="text" class="form-control" id="name" name="name" placeholder="category name"></sf:input>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="category">Create by</label>
                                                    <sf:select path="userCreateCategory.id" class="form-control" id="userCreateCategory">
                                                        <sf:options items="${users }" itemValue="id" itemLabel="username"></sf:options>
                                                    </sf:select>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="category">Update by</label>
                                                    <sf:select path="userUpdateCategory.id" class="form-control" id="userUpdateCategory">
                                                        <sf:options items="${users }" itemValue="id" itemLabel="username"></sf:options>
                                                    </sf:select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="createDate">Create date</label>

                                                    <sf:input path="createDate" class="form-control" type="date"
                                                              id="createDate" name="createDate"></sf:input>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="updateDate">Update date</label>

                                                    <sf:input path="updateDate" class="form-control" type="date"
                                                              id="updateDate" name="updateDate"></sf:input>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <label for="description">Description</label>
                                                    <sf:textarea path="description" id="description" name="description"
                                                                 class="form-control" rows="3" placeholder="desription..."></sf:textarea>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">

                                            <div class="col-md-10">
                                                <div class="form-group mb-4">
                                                    <label for="status">&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                    <sf:checkbox path="status" class="form-check-input" id="status" name="status"></sf:checkbox>
                                                    <label for="status">Active</label>
                                                </div>
                                            </div>

                                        </div>


                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <a href="${classpath }/admin/category-list" class="btn btn-secondary active" role="button" aria-pressed="true">
                                                        Back to list
                                                    </a>
                                                    <button type="submit" class="btn btn-primary">Save edit category</button>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </sf:form>
                            </div>
                        </div>
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


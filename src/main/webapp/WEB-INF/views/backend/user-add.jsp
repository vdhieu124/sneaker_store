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
                <h1 class="mt-4">Users</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="${classpath }/admin/home">Dashboard</a></li>
                    <li class="breadcrumb-item active">Users</li>
                    <li class="breadcrumb-item active">Add</li>
                </ol>

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <sf:form class="form" action="${classpath }/admin/user-add-save" method="post" modelAttribute="user" enctype="multipart/form-data">
                                    <div class="form-body">

                                        <div class="row">

                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="role">User role</label>
                                                    <select class="form-control" id="role" name="role">
                                                        <c:forEach items="${roles }" var="role">
                                                            <option value="${role.id }" label="${role.name }"></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="status">&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                    <sf:checkbox path="status" class="form-check-input" id="status" name="status"></sf:checkbox>
                                                    <label for="status">Active</label>
                                                </div>
                                            </div>

                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="username">User name</label>
                                                    <sf:input path="username" type="text" class="form-control" id="username" name="username" placeholder="user name"></sf:input>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="password">Password</label>
                                                    <sf:input path="password" type="passward" class="form-control" id="password" name="password" placeholder="password"></sf:input>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="name">Full name</label>
                                                    <sf:input path="name" type="text" class="form-control" id="name" name="name" placeholder="full name"></sf:input>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="mobile">Mobile</label>
                                                    <sf:input path="mobile" type="text" class="form-control" id="mobile" name="mobile" placeholder="mobile"></sf:input>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="email">Email</label>
                                                    <sf:input path="email" type="text" class="form-control" id="email" name="email" placeholder="email"></sf:input>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="address">Address</label>
                                                    <sf:input path="address" type="text" class="form-control" id="address" name="address" placeholder="address"></sf:input>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="role">Create by</label>
                                                    <sf:select path="userCreateUser.id" class="form-control" id="userCreateUser">
                                                        <sf:options items="${users }" itemValue="id" itemLabel="username"></sf:options>
                                                    </sf:select>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="role">Update by</label>
                                                    <sf:select path="userUpdateUser.id" class="form-control" id="userUpdateUser">
                                                        <sf:options items="${users }" itemValue="id" itemLabel="username"></sf:options>
                                                    </sf:select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="createdate">Create date</label>

                                                    <sf:input path="createDate" class="form-control" type="date"
                                                              id="createDate" name="createDate"></sf:input>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="updatedate">Update date</label>

                                                    <sf:input path="updateDate" class="form-control" type="date"
                                                              id="updateDate" name="updateDate" ></sf:input>
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
                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <a href="${classpath }/admin/user-list" class="btn btn-secondary active" role="button" aria-pressed="true">
                                                        Back to list
                                                    </a>
                                                    <button type="submit" class="btn btn-primary">Save user</button>
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

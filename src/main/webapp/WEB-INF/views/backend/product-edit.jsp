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
                <h1 class="mt-4">Products</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="${classpath }/admin/home">Dashboard</a></li>
                    <li class="breadcrumb-item active">Products</li>
                    <li class="breadcrumb-item active">Edit</li>
                </ol>

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <sf:form class="form" action="${classpath }/admin/product-edit-save" method="post" modelAttribute="product" enctype="multipart/form-data">
                                    <div class="form-body">

                                        <sf:hidden path="id"/> <!-- id > 0 -> Edit -->

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="category">Select category</label>
                                                    <sf:select path="category.id" class="form-control" id="category">
                                                        <sf:options items="${categories }" itemValue="id" itemLabel="name"></sf:options>
                                                    </sf:select>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="name">Product name</label>
                                                    <sf:input path="name" type="text" class="form-control" id="name" name="name" placeholder="product name"></sf:input>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="price">Price</label>
                                                    <sf:input path="price" type="number" autocomplete="off" id="price" name="price" class="form-control" placeholder="price"></sf:input>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="salePrice">Sale price</label>
                                                    <sf:input path="salePrice" type="number" autocomplete="off" id="salePrice" name="salePrice" class="form-control" placeholder="Sale price"></sf:input>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="create">Create by</label>
                                                    <sf:select path="userCreateProduct.id" class="form-control" id="createBy">
                                                        <sf:options items="${users }" itemValue="id" itemLabel="username"></sf:options>
                                                    </sf:select>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group mb-4">
                                                    <label for="update">Update by</label>
                                                    <sf:select path="userUpdateProduct.id" class="form-control" id="updateBy">
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
                                                              id="updateDate" name="updateDate"></sf:input>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <label for="description">Description</label>
                                                    <sf:textarea path="shortDescription" id="shortDescription" name="shortDescription"
                                                                 class="form-control" rows="3" placeholder="Short desription..."></sf:textarea>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <label for="detailDescription">Detail description</label>
                                                    <sf:textarea path="detailDescription" id="detailDescription" name="detailDescription"
                                                                 class="form-control" rows="3" placeholder="detail desription..."></sf:textarea>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-2">
                                                <div class="form-group mb-4">
                                                    <label for="isHot">&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                    <sf:checkbox path="isHot" class="form-check-input" id="isHot" name="isHot"></sf:checkbox>
                                                    <label for="isHot">Is a hot product?</label>

                                                </div>
                                            </div>

                                            <div class="col-md-10">
                                                <div class="form-group mb-4">
                                                    <label for="isHot">&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                    <sf:checkbox path="status" class="form-check-input" id="status" name="status"></sf:checkbox>
                                                    <label for="status">Active</label>

                                                </div>
                                            </div>

                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <label for="avatarFile">Choose product Avatar</label>
                                                    <input id="avatarFile" name="avatarFile" type="file" class="form-control-file" multiple="multiple" >
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <label for="image">Choose product Image</label>
                                                    <input id="imageFiles" name="imageFiles" type="file" class="form-control-file" multiple="multiple" >
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <label for="image">Choose product Image</label>
                                                    <input id="imageFiles" name="imageFiles" type="file" class="form-control-file" multiple="multiple" >
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <label for="image">Choose product Image</label>
                                                    <input id="imageFiles" name="imageFiles" type="file" class="form-control-file" multiple="multiple" >
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <label for="image">Choose product Image</label>
                                                    <input id="imageFiles" name="imageFiles" type="file" class="form-control-file" multiple="multiple" >
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <a href="${classpath }/admin/product-list" class="btn btn-secondary active" role="button" aria-pressed="true">
                                                        Back to list
                                                    </a>
                                                    <button type="submit" class="btn btn-primary">Save edit product</button>
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


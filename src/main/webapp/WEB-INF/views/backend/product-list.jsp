
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
<c:set var="currentPage" value="${currentPage }" scope="session"></c:set>
<c:set var="totalPage" value="${totalPage }" scope="session"></c:set>

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
                </ol>
                <form action="${classpath }/admin/product-list" method="get">
                    <div class="row">
                        <div class="col-md-2">
                            <div class="form-group mb-4">
                                <a href="${classpath }/admin/product-add" role="button"
                                   class="btn btn-primary">Add new product</a>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group mb-4">
                                <h3>Total products: &nbsp ${productSearch.totalItems }</h3>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group mb-4">
                                <label>Current page</label>
                                <input id="page" name="page" class="form-control" value="${productSearch.currentPage}">
                            </div>
                        </div>
                    </div>

                    <!-- Tìm kiếm -->
                    <div class="row">
                        <div class="col-md-2">
                            <div class="form-group mb-4">
                                <!--
                                <label for="status">&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                <input type="checkbox" class="form-check-input" id="status" name="status" checked="checked" />
                                <label for="status">Active</label>
                                 -->
                                <select class="form-control"
                                        id="status" name="status">
                                    <option value="2">All</option>
                                    <option value="1">Active</option>
                                    <option value="0">Inactive</option>
                                </select>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <select class="form-control" id="categoryId" name="categoryId" style="margin-right: 10px;">
                                <option value="0">Select category</option>
                                <c:forEach items="${categories }" var="category">
                                    <option value="${category.id }">${category.name }</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-2">
                            <input class="form-control" type="date"
                                   id="beginDate" name="beginDate"/>
                        </div>
                        <div class="col-md-2">
                            <input class="form-control"
                                   type="date" id="endDate" name="endDate" />
                        </div>

                        <div class="col-md-3">
                            <input type="text" class="form-control" id="keyword"
                                   name="keyword" placeholder="Search keyword" />
                        </div>

                        <div class="col-md-1">
                            <button type="submit" id="btnSearch" name="btnSearch" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                    <!-- Hết tìm kiếm -->

                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table me-1"></i>
                            List products
                        </div>
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th scope="col">No.</th>
                                    <th scope="col">Id</th>
                                    <th scope="col">Category</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Sale price</th>
                                    <th scope="col">Avatar</th>
                                    <th scope="col">Description</th>
                                    <th scope="col">Create by</th>
                                    <th scope="col">Update by</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Is hot</th>
                                    <th scope="col">Actions</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach var="product" items="${products }" varStatus="loop">
                                    <tr>
                                        <th scope="row">${loop.index + 1 }</th>
                                        <td>${product.id }</td>
                                        <td>${product.category.name }</td>
                                        <td>${product.name }</td>
                                        <td align="right">
                                            <fmt:formatNumber value="${product.price }" minFractionDigits="0"></fmt:formatNumber>
                                        </td>
                                        <td align="right">${product.salePrice }</td>

                                        <td>
                                            <img width="40px" height="40px" src="${classpath }/FileUploads/${product.avatar }"
                                                 class="light-logo">
                                        </td>

                                        <td>${product.shortDescription }</td>
                                        <td>${product.userCreateProduct.username }</td>
                                        <td>${product.userUpdateProduct.username }</td>


                                        <td>
	                                                	<span id="_product_status_${product.id }">
	                                                		<c:choose>
                                                                <c:when test="${product.status }">
                                                                    <span>Active</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span>Inactive</span>
                                                                </c:otherwise>
                                                            </c:choose>
	                                                	</span>

                                        </td>
                                        <td>
	                                                	<span id="_product_isHot_${product.id }">
	                                                		<c:choose>
                                                                <c:when test="${product.isHot }">
                                                                    <span>Yes</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span>No</span>
                                                                </c:otherwise>
                                                            </c:choose>
	                                                	</span>
                                        </td>
                                        <td>
                                            <a href="${classpath }/admin/product-edit/${product.id }" role="button"
                                               class="btn btn-success">Edit</a>
                                            <a href="${classpath }/admin/product-delete/${product.id }" role="button"
                                               class="btn btn-danger">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="pagination float-right">
                                        <div id="paging"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
        </main>
        <%--Footer--%>
        <jsp:include page="/WEB-INF/views/backend/layout/footer.jsp"></jsp:include>
        <%--Footer--%>
    </div>
</div>

<%--Script--%>
<jsp:include page="/WEB-INF/views/backend/layout/js.jsp"></jsp:include>

<!-- pagination -->
<script type="text/javascript">
    $( document ).ready(function() {
        //Dat gia tri cua status ung voi dieu kien search truoc do
        $("#status").val(${productSearch.status});
        $("#categoryId").val(${productSearch.categoryId});

        $("#paging").pagination({
            currentPage: ${productSearch.currentPage}, //Trang hien tai
            items: ${productSearch.totalItems}, //Tong so san pham (total products)
            itemsOnPage: ${productSearch.sizeOfPage},
            cssStyle: 'light-theme',
            onPageClick: function(pageNumber, event) {
                $('#page').val(pageNumber);
                $('#btnSearch').trigger('click');
            },
        });
    });
</script>
</body>
</html>

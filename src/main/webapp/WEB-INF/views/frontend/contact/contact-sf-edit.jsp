
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="en">

<!-- Head -->
<head>
    <!-- Page Meta Tags-->
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="keywords" content="">

    <meta name="msapplication-TileColor" content="#da532c">
    <meta name="theme-color" content="#ffffff">

    <!-- Page Title -->
    <title>OldSkool | Bootstrap 5 HTML Template</title>
    <jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/views/frontend/layout/css.jsp"></jsp:include>

</head>
<body class="">

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/frontend/layout/header.jsp"></jsp:include>

<!-- Main Section-->
<section class="mt-0 overflow-hidden ">
    <!-- Page Content Goes Here -->

    <!-- / Top banner -->
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6">
                <h2>Contact Information</h2>
                <sf:form class="form" action="/contact-sf-edit-save" method="post" modelAttribute="contact">
                    <div class="mb-3">
                        <label for="txtName" class="form-label">Name</label>
                        <sf:input path="txtName" type="text" class="form-control" id="txtName" name="txtName" placeholder="Enter your name" />
                    </div>
                    <div class="mb-3">
                        <label for="txtAddress" class="form-label">Address</label>
                        <sf:input path="txtAddress" type="text" class="form-control" id="txtAddress" name="txtAddress" placeholder="Enter your address" />
                    </div>
                    <div class="mb-3">
                        <label for="txtPhone" class="form-label">Phone</label>
                        <sf:input path="txtPhone" type="tel" class="form-control" id="txtPhone" name="txtPhone" placeholder="Enter your phone number" />
                    </div>
                    <div class="mb-3">
                        <label for="txtEmail" class="form-label">Email</label>
                        <sf:input path="txtEmail" type="email" class="form-control" id="txtEmail" name="txtEmail" placeholder="Enter your email"/>
                    </div>
                    <div class="mb-3">
                        <label for="txtMessage" class="form-label">Message</label>
                        <sf:textarea path="txtMessage" class="form-control" id="txtMessage" name="txtMessage" rows="5" placeholder="Write your message"></sf:textarea>
                    </div>
                    <button type="button" onclick="_notification()" class="btn btn-primary">Save</button>
                </sf:form>
            </div>
            <div class="col-md-6">
                <h2>Follow Us</h2>
                <div class="d-flex">
                    <a href="#" class="me-3">
                        <img class="img-fluid d-table mx-auto" src="${classpath}/frontend/svgs/facebook.svg" alt="">
                    </a>
                    <a href="#" class="me-3">
                        <img class="img-fluid d-table mx-auto" src="${classpath}/frontend/svgs/instagram.svg" alt="">
                    </a>
                    <a href="#" class="me-3">
                        <img class="img-fluid d-table mx-auto" src="${classpath}/frontend/svgs/twitter.svg" alt="">
                    </a>
                    <a href="#" class="me-3">
                        <img class="img-fluid d-table mx-auto" src="${classpath}/frontend/svgs/tiktok-icon.svg" alt="">
                    </a>
                    <a href="#" class="me-3">
                        <img class="img-fluid d-table mx-auto" src="${classpath}/frontend/svgs/skype.svg" alt="">
                    </a>
                    <!-- Add more social media icons here -->
                </div>
                <div class="my-5">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3723.8347348034436!2d105.77081187509337!3d21.03929768061266!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x313454b5534fb3bf%3A0x70d71b071349fa94!2sDevPro%20Education!5e0!3m2!1svi!2s!4v1697732755453!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>
            </div>
        </div>
    </div>

    <!-- /Page Content -->
</section>
<!-- / Main Section-->

<%--    Footer--%>
<jsp:include page="/WEB-INF/views/frontend/layout/footer.jsp"></jsp:include>

<%--JS--%>
<jsp:include page="/WEB-INF/views/frontend/layout/js.jsp"></jsp:include>

<script type="text/javascript">
    function _notification() {
        //javascript object
        let data = {

            txtName : jQuery("#txtName").val(),
            txtEmail : jQuery("#txtEmail").val(), //Get by Id
            txtPhone : jQuery("#txtPhone").val(),
            txtAddress : jQuery("#txtAddress").val(),
            txtMessage : jQuery("#txtMessage").val(),

        };

        //$ === jQuery
        jQuery.ajax({
            url : "/contact-sf-edit-save",
            type : "POST",
            contentType: "application/json",
            data : JSON.stringify(data),
            dataType : "json", //Kieu du lieu tra ve tu controller la json

            success : function(jsonResult) {
                alert(jsonResult.code + ": " + jsonResult.message);
                //$("#notification").html(jsonResult.message);
            },

            error : function(jqXhr, textStatus, errorMessage) {
                alert("An error occur");
            }
        });
    }
</script>
</body>

</html>
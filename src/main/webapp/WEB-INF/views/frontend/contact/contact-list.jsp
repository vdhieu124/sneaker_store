<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<html>
<head>
    <title>List contact</title>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Phone</th>
        <th scope="col">Email</th>
        <th scope="col">Address</th>
        <th scope="col">Message</th>
        <th scope="col">Filename</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">1</th>
        <td>${contact.txtName}</td>
        <td>${contact.txtPhone}</td>
        <td>${contact.txtEmail}</td>
        <td>${contact.txtAddress}</td>
        <td>${contact.txtMessage}</td>
        <td>${filename}</td>
    </tr>
    </tbody>
</table>
</body>
</html>

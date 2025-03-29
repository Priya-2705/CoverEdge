<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Quote Result</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-success text-white">
            <h4 class="mb-0">Quote Calculation Result</h4>
        </div>
        <div class="card-body">
            <h5 class="card-title">Calculated Premium:</h5>
            <p class="display-6 text-primary">
                <fmt:formatNumber value="${premium}" type="currency"/>
            </p>
            <div class="mt-4">
                <a href="quote" class="btn btn-primary">New Quote</a>
                <a href="policies" class="btn btn-secondary">View Policies</a>
            </div>
        </div>
    </div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Renew Policies</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-warning text-dark">
            <h4>Policies Eligible for Renewal</h4>
        </div>
        <div class="card-body">
            <c:if test="${not empty success}">
                <div class="alert alert-success">Policy renewed successfully!</div>
            </c:if>
            
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Policy Number</th>
                        <th>Customer</th>
                        <th>End Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${policies}" var="policy">
                        <tr>
                            <td>${policy.policyNumber}</td>
                            <td>${policy.customer.firstName} ${policy.customer.lastName}</td>
                            <td><fmt:formatDate value="${policy.endDate}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <a href="policies?action=renewform&id=${policy.policyId}" 
                                   class="btn btn-sm btn-primary">Renew</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="policies" class="btn btn-secondary">Back to All Policies</a>
        </div>
    </div>
</body>
</html>
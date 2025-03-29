<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Manage Policies</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Policy Management</h2>
        <a href="policies?action=new" class="btn btn-success">+ Add Policy</a>
    </div>
    
    <table class="table table-hover table-striped">
        <thead class="table-dark">
            <tr>
                <th>Policy Number</th>
                <th>Customer</th>
                <th>Type</th>
                <th>Coverage</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="policy" items="${policies}">
                <tr>
                    <td>${policy.policyNumber}</td>
                    <td>${policy.customer.firstName} ${policy.customer.lastName}</td>
                    <td>${policy.policyType}</td>
                    <td><fmt:formatNumber value="${policy.coverageAmount}" type="currency"/></td>
                    <td>
                        <span class="badge 
                            ${policy.policyStatus == 'ACTIVE' ? 'bg-success' : 
                              policy.policyStatus == 'EXPIRED' ? 'bg-danger' : 'bg-warning'}">
                            ${policy.policyStatus}
                        </span>
                    </td>
                    <td>
                        <a href="policies?action=edit&id=${policy.policyId}" 
                           class="btn btn-sm btn-warning">Edit</a>
                        <a href="policies?action=delete&id=${policy.policyId}" 
                           class="btn btn-sm btn-danger"
                           onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
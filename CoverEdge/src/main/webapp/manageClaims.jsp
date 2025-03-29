<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Manage Claims</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Claim Management</h2>
        <a href="claims?action=new" class="btn btn-success">+ Submit New Claim</a>
    </div>

    <table class="table table-hover table-striped">
        <thead class="table-dark">
            <tr>
                <th>Policy Number</th>
                <th>Description</th>
                <th>Date Submitted</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="claim" items="${claims}">
                <tr>
                    <td>${claim.policy.policyNumber}</td>
                    <td>${claim.description}</td>
                    <td><fmt:formatDate value="${claim.dateSubmitted}" pattern="dd-MM-yyyy HH:mm"/></td>
                    <td>
                        <span class="badge 
                            ${claim.status == 'APPROVED' ? 'bg-success' : 
                              claim.status == 'REJECTED' ? 'bg-danger' : 'bg-warning'}">
                            ${claim.status}
                        </span>
                    </td>
                    <td>
                        <form action="claims" method="post" class="d-inline">
                            <input type="hidden" name="action" value="updateStatus">
                            <input type="hidden" name="claimId" value="${claim.id}">
                            <select name="status" class="form-select form-select-sm" 
                                    onchange="this.form.submit()">
                                <c:forEach items="${claimStatusValues}" var="status">
                                    <option value="${status}" ${claim.status == status ? 'selected' : ''}>
                                        ${status}
                                    </option>
                                </c:forEach>
                            </select>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
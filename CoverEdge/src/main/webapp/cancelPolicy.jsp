<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cancel Policy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-danger text-white">
            <h4>Cancel Policy</h4>
        </div>
        <div class="card-body">
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
            
            <form action="policies" method="post">
                <input type="hidden" name="action" value="docancel">
                
                <div class="mb-3">
                    <label class="form-label">Select Policy</label>
                    <select name="policyId" class="form-select" required>
                        <option value="">Choose Policy</option>
                        <c:forEach items="${policies}" var="policy">
                            <option value="${policy.policyId}">
                                ${policy.policyNumber} - ${policy.customer.firstName} ${policy.customer.lastName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="mb-3">
                    <label class="form-label">Cancellation Reason</label>
                    <textarea name="reason" class="form-control" rows="3" required></textarea>
                </div>
                
                <button type="submit" class="btn btn-danger">Confirm Cancellation</button>
                <a href="policies" class="btn btn-secondary">Cancel</a>
            </form>
        </div>
    </div>
</body>
</html>
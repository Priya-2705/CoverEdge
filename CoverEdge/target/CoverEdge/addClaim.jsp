<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Submit New Claim</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h4>Submit New Claim</h4>
        </div>
        <div class="card-body">
            <form action="claims" method="post">
                <input type="hidden" name="action" value="submit">
                
                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label">Policy</label>
                        <select name="policyId" class="form-select" required>
                            <option value="">Select Policy</option>
                            <c:forEach items="${policies}" var="policy">
                                <option value="${policy.policyId}">
                                    ${policy.policyNumber} (${policy.policyType})
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="col-12">
                        <label class="form-label">Description</label>
                        <textarea name="description" class="form-control" rows="4" required></textarea>
                    </div>
                </div>
                
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary">Submit Claim</button>
                    <a href="claims" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
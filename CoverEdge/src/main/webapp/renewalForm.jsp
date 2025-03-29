<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Renew Policy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h4>Renew Policy ${policy.policyNumber}</h4>
        </div>
        <div class="card-body">
            <form action="policies" method="post">
                <input type="hidden" name="action" value="renew">
                <input type="hidden" name="policyId" value="${policy.policyId}">

                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label">Current End Date</label>
                        <input type="text" class="form-control" 
                               value="<fmt:formatDate value="${policy.endDate}" pattern="yyyy-MM-dd"/>" 
                               readonly>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Renewal Term (Years)</label>
                        <select name="term" class="form-select" required>
                            <option value="1">1 Year</option>
                            <option value="2">2 Years</option>
                            <option value="5">5 Years</option>
                        </select>
                    </div>
                </div>

                <div class="mt-4">
                    <button type="submit" class="btn btn-primary">Renew Policy</button>
                    <a href="policies?action=renew" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
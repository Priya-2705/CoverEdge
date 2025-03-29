<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.humber.model.Policy" %>
<html>
<head>
    <title>Policy Quote</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Generate Policy Quote</h4>
        </div>
        <div class="card-body">
            <form action="quote" method="post">
                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label">Policy Type</label>
                        <select name="policyType" class="form-select" required>
                            <% for (Policy.PolicyType type : Policy.PolicyType.values()) { %>
                                <option value="<%= type.name() %>"><%= type %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Customer Age</label>
                        <input type="number" name="age" class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Coverage Amount</label>
                        <input type="number" step="0.01" name="coverage" 
                               class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Term (Years)</label>
                        <input type="number" name="term" class="form-control" required>
                    </div>
                </div>
                
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary">Calculate</button>
                    <a href="policies" class="btn btn-secondary">Back to Policies</a>
                </div>
                
                <c:if test="${not empty error}">
                    <div class="alert alert-danger mt-3">${error}</div>
                </c:if>
            </form>
        </div>
    </div>
</body>
</html>
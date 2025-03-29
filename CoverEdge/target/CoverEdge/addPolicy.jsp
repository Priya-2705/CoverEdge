<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.humber.model.Policy" %>
<html>
<head>
    <title>Add Policy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h4>Add New Policy</h4>
        </div>
        <div class="card-body">
            <form action="policies" method="post">
                <input type="hidden" name="action" value="save">
                
                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label">Policy Number</label>
                        <input type="text" name="policyNumber" class="form-control" required>
                    </div>
                    <div class="col-md-6">
				        <label class="form-label">Policy Type</label>
				        <select name="policyType" class="form-select" required>
				            <option value="">Select Type</option>
				            <% for (Policy.PolicyType type : Policy.PolicyType.values()) { %>
				                <option value="<%= type.name() %>"><%= type %></option>
				            <% } %>
				        </select>
				    </div>
                    <div class="col-md-6">
                        <label class="form-label">Coverage Amount</label>
                        <input type="number" step="0.01" name="coverageAmount" 
                               class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Premium Amount</label>
                        <input type="number" step="0.01" name="premiumAmount" 
                               class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Start Date</label>
                        <input type="date" name="startDate" class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">End Date</label>
                        <input type="date" name="endDate" class="form-control" required>
                    </div>
                    <div class="col-md-6">
				        <label class="form-label">Status</label>
				        <select name="policyStatus" class="form-select" required>
				            <option value="">Select Status</option>
				            <% for (Policy.PolicyStatus status : Policy.PolicyStatus.values()) { %>
				                <option value="<%= status.name() %>"><%= status %></option>
				            <% } %>
				        </select>
				    </div>
				    <div class="row g-3">
					    <div class="col-md-6">
					        <label class="form-label">Broker</label>
					        <select name="brokerId" class="form-select" required>
					            <option value="">Select Broker</option>
					            <c:forEach items="${brokers}" var="broker">
					                <option value="${broker.id}">${broker.name}</option>
					            </c:forEach>
					        </select>
					    </div>
                </div>
                
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary">Save Policy</button>
                    <a href="policies" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
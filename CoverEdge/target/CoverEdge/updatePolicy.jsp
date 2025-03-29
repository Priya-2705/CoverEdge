<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.humber.model.Policy" %>
<%@ page import="com.humber.model.Broker" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Policy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-warning text-dark">
            <h4>Edit Policy</h4>
        </div>
        <div class="card-body">
            <form action="policies" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="policyId" value="${policy.policyId}">
                
                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label">Policy Number</label>
                        <input type="text" name="policyNumber" 
                               value="${policy.policyNumber}" 
                               class="form-control" required>
                    </div>
                    <div class="col-md-6">
				        <label class="form-label">Policy Type</label>
				        <select name="policyType" class="form-select" required>
				            <% for (Policy.PolicyType type : Policy.PolicyType.values()) { %>
				                <option value="<%= type.name() %>" ${policy.policyType.name() eq type.name() ? 'selected' : ''}>
				                    <%= type %>
				                </option>
				            <% } %>
				        </select>
				    </div>
                    <div class="col-md-6">
                        <label class="form-label">Coverage Amount</label>
                        <input type="number" step="0.01" name="coverageAmount" 
                               value="${policy.coverageAmount}" 
                               class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Premium Amount</label>
                        <input type="number" step="0.01" name="premiumAmount" 
                               value="${policy.premiumAmount}" 
                               class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Start Date</label>
                        <input type="date" name="startDate" 
                               value="<fmt:formatDate value="${policy.startDate}" pattern="yyyy-MM-dd"/>" 
                               class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">End Date</label>
                        <input type="date" name="endDate" 
                               value="<fmt:formatDate value="${policy.endDate}" pattern="yyyy-MM-dd"/>" 
                               class="form-control" required>
                    </div>
                    <div class="col-md-6">
				        <label class="form-label">Status</label>
				        <select name="policyStatus" class="form-select" required>
				            <% for (Policy.PolicyStatus status : Policy.PolicyStatus.values()) { %>
				                <option value="<%= status.name() %>" ${policy.policyStatus.name() == status.name() ? 'selected' : ''}>
				                    <%= status %>
				                </option>
				            <% } %>
				        </select>
				    </div>
                    <div class="row g-3">
					    <div class="col-md-6">
					        <label class="form-label">Broker</label>
					        <select name="brokerId" class="form-select" required>
					            <option value="">Select Broker</option>
					            <c:forEach items="${brokers}" var="broker">
					                <option value="${broker.id}" 
					                    ${(not empty policy.broker) && (policy.broker.id == broker.id) ? 'selected' : ''}>
					                    ${broker.name}
					                </option>
					            </c:forEach>
					        </select>
					    </div>
					 </div>
                </div>
                
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary">Update Policy</button>
                    <a href="policies" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
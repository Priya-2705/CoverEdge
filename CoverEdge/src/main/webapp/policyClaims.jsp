<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Policy Claims History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-info text-white">
            <h4>Claims History for Policy ${policyNumber}</h4>
        </div>
        
        <div class="card-body">
            <!-- Filter Form -->
            <form class="mb-4">
                <input type="hidden" name="policyId" value="${param.policyId}">
                <input type="hidden" name="policyNumber" value="${policyNumber}">
                
                <div class="row g-3">
                    <div class="col-md-3">
                        <label class="form-label">Start Date</label>
                        <input type="date" name="startDate" class="form-control" 
                               value="${startDate}">
                    </div>
                    
                    <div class="col-md-3">
                        <label class="form-label">End Date</label>
                        <input type="date" name="endDate" class="form-control" 
                               value="${endDate}">
                    </div>
                    
                    <div class="col-md-3">
                        <label class="form-label">Status</label>
                        <select name="status" class="form-select">
                            <option value="">All Statuses</option>
                            <c:forEach items="${Claim.ClaimStatus.values()}" var="status">
                                <option value="${status}" 
                                    ${selectedStatus == status ? 'selected' : ''}>
                                    ${status}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="col-md-3 align-self-end">
                        <button type="submit" class="btn btn-primary">Filter</button>
                    </div>
                </div>
            </form>

            <!-- Claims Table -->
            <table class="table table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Date Submitted</th>
                        <th>Description</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty claims}">
                            <tr>
                                <td colspan="3" class="text-center">No claims found</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${claims}" var="claim">
                                <tr>
                                    <td>
                                        <fmt:formatDate value="${claim.dateSubmitted}" 
                                            pattern="dd-MM-yyyy HH:mm"/>
                                    </td>
                                    <td>${claim.description}</td>
                                    <td>
                                        <span class="badge 
                                            ${claim.status == 'APPROVED' ? 'bg-success' : 
                                              claim.status == 'REJECTED' ? 'bg-danger' : 'bg-warning'}">
                                            ${claim.status}
                                        </span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
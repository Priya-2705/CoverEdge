<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Update Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-warning text-dark">
            <h4>Edit Customer</h4>
        </div>
        <div class="card-body">
            <form action="customers" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="customerId" value="${customer.customerId}">
                
                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label">First Name</label>
                        <input type="text" name="firstName" 
                               value="${customer.firstName}" 
                               class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Last Name</label>
                        <input type="text" name="lastName" 
                               value="${customer.lastName}" 
                               class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Email</label>
                        <input type="email" name="email" 
                               value="${customer.email}" 
                               class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Phone</label>
                        <input type="tel" name="phoneNumber" 
                               value="${customer.phoneNumber}" 
                               class="form-control" required>
                    </div>
                    <div class="col-12">
                        <label class="form-label">Address</label>
                        <textarea name="address" class="form-control" 
                                  rows="3" required>${customer.address}</textarea>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Date of Birth</label>
                        <input type="date" name="dateOfBirth" 
                               value="<fmt:formatDate value="${customer.dateOfBirth}" pattern="yyyy-MM-dd"/>" 
                               class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">ID Number</label>
                        <input type="text" name="identificationNumber" 
                               value="${customer.identificationNumber}" 
                               class="form-control" required>
                    </div>
                </div>
                
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary">Update Customer</button>
                    <a href="customers" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
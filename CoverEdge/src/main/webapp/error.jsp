<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Error ${errorCode}</title>
    <style>
        .error-container { margin: 2em; padding: 1em; border: 1px solid #f00; }
        .error-details { color: #666; font-size: 0.9em; }
    </style>
</head>
<body>
    <div class="error-container">
        <h2>Error ${errorCode}</h2>
        <p>${errorMessage}</p>
        <div class="error-details">
            <p>Request URI: ${requestUri}</p>
            <p>Please contact support if this problem persists.</p>
        </div>
        <p><a href="/">Return to Homepage</a></p>
    </div>
</body>
</html>
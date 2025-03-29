<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Success</title>
    <style>
        .success-container { margin: 2em; padding: 1em; border: 1px solid #0a0; }
    </style>
</head>
<body>
    <div class="success-container">
        <h2>Success!</h2>
        <p>${successMessage}</p>
        <c:if test="${not empty redirectUrl}">
            <p>You will be automatically redirected in 5 seconds...</p>
            <script>
                setTimeout(function() {
                    window.location.href = '${redirectUrl}';
                }, 5000);
            </script>
        </c:if>
        <p><a href="/">Return to Homepage</a></p>
    </div>
</body>
</html>
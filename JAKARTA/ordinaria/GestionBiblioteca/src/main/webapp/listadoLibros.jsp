<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Libros</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
</head>
<body>


    <div class="container mt-5">
        <h2 class="text-center">${titulo}</h2>
    
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <ul class="list-group">

                    <%
                        List<String> libros =(java.util.List<String>)request.getAttribute("libros");
                        if (libros != null){
                            for(String libro : libros){
                        
                    %>

                            <li class="list-group-item text-center"><%=libro%></li>

                    <%
                            }
                        }
                    %>

                </ul>
            </div>
        </div>
    
        <p class="text-center mt-4">
            <a href="<%=request.getContextPath()%>/welcome.xhtml" class="btn btn-primary">Volver</a>
        </p>
    </div>
    



    <script src="<%=request.getContextPath()%>/css/bootstrap.bundle.min.js"></script>
</body>
</html>


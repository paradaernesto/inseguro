<%--
  Created by IntelliJ IDEA.
  User: ernestoparada
  Date: 7/29/16
  Time: 4:07 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>

    <div class="container">
        <div class="page-header text-center">
            <h1>${currentUser}</h1>
        </div>
        <div class="col-md-7">
            <div class="page-header">
                <h3>Foto</h3>
            </div>
            <div class="thumbnail">
                <g:img dir="images" file="${imageName}"/>
            </div>
        </div>
        <div class="col-md-5">
            <div class="page-header">
                <h3>Ranking ${fromDate} / ${toDate} </h3>
            </div>
            <table class="table table-bordered">
                <thead>
                    <tr class="text-center">
                        <th>Nombre</th>
                        <th>Cantidad</th>
                    </tr>
                </thead>
                <tbody>
                <g:each in="${users}" var="user">
                    <tr class="text-center">
                        <td>${user.name}</td>
                        <td>${user.count}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
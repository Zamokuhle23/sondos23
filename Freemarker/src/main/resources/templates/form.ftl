<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Form Data Binding and Validation</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<h2>Form Data Binding and Validation</h2>

<form class="form-inline" method="post" action="/add">
    <label for="firstName"></label><input
            type="text" class="form-control mb-2 mr-sm-2 mb-sm-0"
            id="firstName" name="firstName" placeholder="Student name" />

    <label for="lastName"></label><input
            type="text" class="form-control mb-2 mr-sm-2 mb-sm-0"
            id="lastName" name="lastName" placeholder="Customer name" />

    <label for="email"></label><input
            type="text"  class="form-control mb-2 mr-sm-2 mb-sm-0" id="email"
            placeholder="Email" name="email" />



    <button type="submit" class="btn btn-primary">Add</button>
</form>


<script src="/js/main.js"></script>
</body>
</html>

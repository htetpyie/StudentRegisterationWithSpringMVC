<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <spring:url value="/resources/css/test.css" var="testCss"/>
	<link href="${testCss}" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
       	 integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">    
   		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    
        <title>Course Registration</title>
</head>
<body>
	
    <div id="testheader">
        <div class="container">
            <div class=row>        
                <div class="col-md-5 ">
            <a href="/StudentRegisterationWithSpringMVC/showMenu"><h3>Student Registration</h3></a>
        </div>  
        <div class="col-md-6">
            <p>User: ${sessionScope.user.userId} ${sessionScope.user.userName}</p>
            <p>Current Date : ${sessionScope.date} </p>
        </div>  
        <div class="col-md-1" >
            <input type="button" class="btn-basic" id="lgnout-button" value="${sessionScope.user==null? 'Login': 'Log Out' }" onclick="location.href='/StudentRegisterationWithSpringMVC/Logout'">
        </div>        
    </div>
</div>

</div>
    <div class="container">
    <div class="sidenav">
        
        <button class="dropdown-btn" > Class Management <i class="fa fa-caret-down"></i></button>
        
            <div class="dropdown-container">
          <a href="/StudentRegisterationWithSpringMVC/showCourseRegister">Course Registration </a>
          <a href="/StudentRegisterationWithSpringMVC/showStudentRegister">Student Registration </a>
          <a href="ShowStudentServlet">Student Search </a>
        </div>
        <a href="/StudentRegisterationWithSpringMVC/showUser">Users Management</a>
      </div>
</body>
</html>
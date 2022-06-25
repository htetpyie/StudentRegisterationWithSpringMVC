<!DOCTYPE html>
<html lang="en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <jsp:include page="header.jsp"></jsp:include>
      <div class="main_contents">
    <div id="sub_content">
        <form:form action="/StudentManagementWithSpringMVC/userAdd" method="post" modelAttribute="userBean">

            <h2 class="col-md-6 offset-md-2 mb-3 mt-4">Add User</h2>
            <div class="row">
            	<div class="col-md-2"></div>
            	<div class="col-md-6">
		            <div style="color: red" class="alert alert-danger ${ (error==null)?'d-none' : '' } ">${error}</div>
         			<div style="color: green" class="alert  alert-success${(success==null)?'d-none' : '' } ">${success}</div>
         
            	</div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name</label>
                <div class="col-md-4">
                    <form:input type="text" class="form-control" id="name" path="userName"/>
                </div>
                <div class="col-md-4">
                    <form:errors  style="color:red"  path="userName"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label for="email" path="userEmail" class="col-md-2 col-form-label">Email</form:label>
                <div class="col-md-4">
                    <form:input type="email" class="form-control" id="email" name="email" path="userEmail"/>
                </div>
                <div class="col-md-4">
                    <form:errors  style="color:red"  path="userEmail"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label for="Passowrd" class="col-md-2 col-form-label" path="userPassword">Password</form:label>
                <div class="col-md-4">
                    <form:input type="password" class="form-control" id="name" name="password" path="userPassword" />
                </div>
                <div class="col-md-4">
                    <form:errors  style="color:red"  path="userPassword"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="confirmPassword"  class="col-md-2 col-form-label">Confirm Password</label>
                <div class="col-md-4">
                    <form:input type="password" class="form-control" id="confirmPassword" name="cfpassword" path="userCfPassword"/>
                </div>
                <div class="col-md-4">
                    <div  style="color:red" >${passwordError}</div>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="userRole" class="col-md-2 col-form-label">User Role</label>
                <div class="col-md-4">
                    <form:select class="form-select" path="userRole" aria-label="Education" name="role" id="userRole">
                        <form:option value="1" >Admin</form:option>
                        <form:option value="0">User</form:option>       
                    </form:select>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-4"></div>
    
                <div class="col-md-6">              
                   <button type="submit" class="btn btn-secondary col-md-2" data-bs-toggle="modal" data-bs-target="#exampleModa">Add</button>
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                        aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Student Registration</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                   
                                   <h5 style="color: rgb(127, 209, 131);">Registered Succesfully !</h5>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-success col-md-2" data-bs-dismiss="modal">Ok</button>
                                   
                                </div>
                            </div>
                        </div>
                </div>
    
            </div>
            </form:form>
    </div>
</div>
</div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
        <script>
            /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
            var dropdown = document.getElementsByClassName("dropdown-btn");
            var i;
            
            for (i = 0; i < dropdown.length; i++) {
              dropdown[i].addEventListener("click", function() {
              this.classList.toggle("active");
              var dropdownContent = this.nextElementSibling;
              if (dropdownContent.style.display === "block") {
              dropdownContent.style.display = "none";
              } else {
              dropdownContent.style.display = "block";
              }
              });
            }
            </script>
</body>

</html>

    

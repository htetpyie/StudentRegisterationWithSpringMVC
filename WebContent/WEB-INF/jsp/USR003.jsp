<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
        <form class="row g-3 mt-3 ms-2" action="/StudentManagementWithSpringMVC/userSearch" method="get">
            <div class="col-auto">
                <label for="staticEmail2" class="visually-hidden">User Id</label>
                <input type="text" class="form-control" id="staticEmail2" name="id" placeholder="User ID">
            </div>
            <div class="col-auto">
                <label for="inputPassword2" class="visually-hidden">User Name</label>
                <input type="text" class="form-control" name="name" id="inputPassword2" placeholder="User Name">
            </div>
    
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-3">Search</button>
            </div>
            <div class="col-auto">
                <button type="button" class="btn btn-secondary " onclick="location.href = '/StudentManagementWithSpringMVC/showAddUser';">
                    Add
                </button>
    
            </div>
            <div class="col-auto">
                <button type="reset" class="btn btn-danger mb-3">Reset</button>
            </div>
        </form>
    
        <table class="table table-striped" id="stduentTable">
            <thead>
                <tr>                    
                    <th scope="col">User ID</th>
                    <th scope="col">User Name</th>
                    <th scope="col">Details</th>                    
                </tr>
            </thead>            
            <tbody>     
                <c:forEach var = "user" items="${userList}">
                	<c:if test="${user.userName != null }">
		                <tr>                     
		                    <td>${user.userId}</td>
		                    <td>${user.userName}</td>                    
		                <td>
		                    <button type="button" class="btn btn-success  " onclick="location.href = '/StudentManagementWithSpringMVC/showUserUpdate/${user.userId}';">
		                        Update
		                    </button>
		                </td>
		                <td><button type="button" class="btn btn-secondary mb-3" ${user.userId.equals(sessionScope.user.userId)?'disabled':'' }
		                ${sessionScope.user.userRole.equals("1")?'':'disabled' }
		                data-bs-toggle="modal"  onclick="location.href = '/StudentManagementWithSpringMVC/userDelete/${user.userId}';"
		                    data-bs-target="#exampleModa">Delete</button></td>    
		                </tr>                 	                
                	</c:if>
                </c:forEach> 
                  
    
            </tbody>
        </table>
    
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Student Deletion</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        
                         <h5 style="color: rgb(127, 209, 131);">Are you sure want to delete !</h5>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success col-md-2" data-bs-dismiss="modal">Ok</button>
    
                    </div>
                </div>
            </div>
        </div>
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

    


    


<body>
    
</body>


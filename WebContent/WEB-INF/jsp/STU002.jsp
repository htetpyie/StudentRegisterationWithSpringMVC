<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
        <form:form modelAttribute="studentBean">

            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student Details</h2>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="studentID" class="col-md-2 col-form-label">Student ID</label>
                <div class="col-md-4">
                    <form:input type="text" class="form-control" path="studentId" id="studentID" disabled="true"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name</label>
                <div class="col-md-4">
                    <form:input type="text" class="form-control" path="studentName" disabled="true"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="dob" class="col-md-2 col-form-label">DOB</label>
                <div class="col-md-4">
                    <form:input type="date" class="form-control" path="studentDob" disabled="true"/>
                </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Gender</legend>
                <div class="col-md-4">
                    <div class="form-check-inline">
                        <form:radiobutton class="form-check-input" path="studentGender"  value="Male" label="Male" disabled="true"/>                       
                    </div>
                    <div class="form-check-inline">
                        <form:radiobutton class="form-check-input"  path="studentGender"  value="Female" label="Female" disabled="true"/>                  
                    </div>    
                </div>
            </fieldset>
    
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="phone" class="col-md-2 col-form-label">Phone</label>
                <div class="col-md-4">
                    <form:input class="form-control" path="studentPhone" disabled="true"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="education" class="col-md-2 col-form-label">Education</label>
                <div class="col-md-4">
                    <form:select class="form-select" aria-label="Education" path="studentEducation" disabled="true" id="education">
                        <form:option value="0" >Bachelor of Information Technology</form:option>
                        <form:option value="1" >Diploma in IT</form:option>
                        <form:option value="2" >Bachelor of Computer Science</form:option>    
                    </form:select>
                </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Attend</legend>          
    		
               <div class='col-md-4'>
                <form:checkboxes items="${courseList}" style="margin-left:20px;" class="form-check-input" path="studentCourse" disabled="true"/>
               </div> 
                
            </fieldset>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label" >Photo</label>
                <div class="col-md-4">
                    <form:input type="file" class="form-control" path="studentPhoto" disabled="true"/>
                </div>
            </div>
    
            <div class="row mb-4">
                <div class="col-md-4"></div>
    
                <div class="col-md-4">
                    <a href="/StudentManagementWithSpringMVC/showStudentUpdate/${studentBean.studentId}">
                        <button type="button" class="btn btn-secondary">
                          
                            <span>Update</span>
                        </button>
                    </a>
                        <!-- Button trigger modal -->
                    <button type="button" class="btn btn-danger" onclick ="location.href='/StudentManagementWithSpringMVC/deleteStudent/${studentBean.studentId}';" data-bs-toggle="modal" data-bs-target="#exampleModa">
                       
                       <span>Delete</span> 
                    </button>
    
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                        aria-hidden="true">
                        <div class="modal-dialog modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Student Deletion</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    Are you sure you want to delete?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Ok</button>
                                    <button type="button" class="btn btn-danger">Cancel</button>
                                </div>
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


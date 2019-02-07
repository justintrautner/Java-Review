<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<meta charset="UTF-8">
	<title>Edit</title>
</head>
<body class="mt-2 ml-3 mr-3">

	<h3>Edit <c:out value="${event.name}"/></h3>
	
	<form:form action="/events/${event.id}/edit" method="post" modelAttribute="updatedevent">
		<input type="hidden" name="_method" value="put">
		
			<p>
				<form:label path="name">Name: </form:label>
				<form:input path="name" />
			</p>
			<p>
				<form:errors class="text-danger" path="name" />
			</p>
			<p>
				<form:label path="date">Date: </form:label>
				<form:input type="date" path="date" />
			</p>
			<p>
				<form:errors class="text-danger" path="date" />
			</p>
			<p>
				<form:label path="location">Location: </form:label>
				<form:input path="location" />
			</p>
			<p>
				<form:errors class="text-danger" path="location" />
			</p>
			<p>
				<form:label path="state">State: </form:label>
				<form:input path="state" />
			</p>
			<p>
				<form:errors class="text-danger" path="state" />
			</p>
	
			<input type="submit" value="Update Event" />
	
	</form:form>
	

</body>
</html>
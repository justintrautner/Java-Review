<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Sign In</title>
</head>
<body class="mt-2 ml-3 mr-3">

	<div class="row">
		<div class="col-md-5">
			<h1>Login</h1>
			<p class="text-danger">
				<c:out value="${error}" />
			</p>
			<form method="post" action="/login">
				<p>
					<label type="email" for="email">Email: </label> <input type="text"
						id="email" name="email" />
				</p>
				<p>
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" />
				</p>
				<input type="submit" value="Login!" />
			</form>
		</div>
		<div class="col-md-7">

			<h1>Register!</h1>

			<p>
				<form:errors class="text-danger" path="user.*" />
			</p>

			<form:form method="POST" action="/registration" modelAttribute="user">
				<p>
					<form:label path="first_name">First Name:</form:label>
					<form:input type="first_name" path="first_name" />
				</p>
				<p>
					<form:label path="last_name">Last Name:</form:label>
					<form:input type="last_name" path="last_name" />
				</p>
				<p>
					<form:label path="email">Email:</form:label>
					<form:input type="email" path="email" />
				</p>
				<p>
					<form:label path="location">Location:</form:label>
					<form:input type="location" path="location" />
				</p>
								<p>
					<form:label path="state">State:</form:label>
					<form:input type="state" path="state" />
				</p>
				<p>
					<form:label path="password">Password:</form:label>
					<form:password path="password" />
				</p>
				<p>
					<form:label path="passwordConfirmation">Password Confirmation:</form:label>
					<form:password path="passwordConfirmation" />
				</p>
				<input type="submit" value="Register!" />
			</form:form>

		</div>

	</div>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<meta charset="UTF-8">
	<title>Event</title>
</head>
<body class="mt-2 ml-3 mr-3">
	<h1><c:out value="${event.name}" /></h1>
	
	<div class="row">
		<div class="col-md-6">
			<p>Host: <c:out value="${event.hostUser.first_name}" /></p>
			<p>Date: <c:out value="${event.date}" /></p>
			<p>Location: <c:out value="${event.location}" />, <c:out value="${event.state}" /></p>
			<p>People who are attending this event: <c:out value="${event.attendingUsers.size()}" /></p>
				
			<table class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Location</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${event.attendingUsers}" var="user">
						<tr>
							<td>
								<c:out value="${ user.first_name}"/>
							</td>
							<td>
								<c:out value="${ user.location}"/>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col-md-6">
			<h4>Create Message</h4>
				<c:forEach items="${event.messages}" var="message">
					<p><c:out value="${ message.user.first_name}"/> - <c:out value="${ message.content}"/></p>
				</c:forEach>
			<form method="post" action="/events/${event.id}">
				
				<input type="text" name="content">
				<input type="submit" value="Post Message">
			
			
			</form>

			
		</div>
		
	</div>

</body>
</html>
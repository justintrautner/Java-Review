<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body class="mt-2 ml-4 mr-4">

	<div class="row">
		<div class="col-md-10">
			<h1>Welcome <c:out value="${user.first_name}" /> <c:out value="${user.last_name}" /></h1>
		</div>
		<div class="col-md-2">
			<a href="/logout">Logout</a>
		</div>
	</div>

	<div class="row">
		<h4 class="ml-5">Here are some events in your state:</h4>
	
		<table class="table ml-5 mr-5">
			<thead>
				<tr>
					<th>Name</th>
					<th>Date</th>
					<th>Location</th>
					<th>Host</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${eventsInState}" var="event">
					<tr>
						<td>
							<a href="/events/${event.id}">
								<c:out value="${event.name}"/>
							</a>
						</td>
						
						<td>
							<fmt:formatDate value="${event.date}" type="date" dateStyle="long" />
						</td>
						<td><c:out value="${event.location}"/></td>
						<td><c:out value="${event.hostUser.first_name}"/></td>
						<td>
							<c:if test="${event.hostUser.id == u_id}">
								<form action="/events/${event.id}" method="post">
									<input type="hidden" name="_method" value="delete">
									<input type="submit" value="Delete">
								</form>
								<a href="/events/${event.id}/edit"><input type="submit" value="Edit"></a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>	
		</table>
	</div>
	
	<div class="row">
		<h4 class="ml-5">Here are some of the events in other states:</h4>
		
		<table class="table ml-5 mr-5">
			<thead>
				<tr>
					<th>Name</th>
					<th>Date</th>
					<th>Location</th>
					<th>State</th>
					<th>Host</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${eventsNotInState}" var="event">
					<tr>
						<td>
							<a href="/events/${event.id}">
								<c:out value="${event.name}"/>
							</a>
						</td>
						<td><fmt:formatDate value="${event.date}" type="date" dateStyle="long" /></td>
						<td><c:out value="${event.location}"/></td>
						<td><c:out value="${event.state}"/></td>
						<td><c:out value="${event.hostUser.first_name}"/></td>
						<td>
												
						
							
							<c:choose>
								<c:when test="${event.attendingUsers.contains(user)==false}">
									<form action="/events/${event.id}/join" method="post">
										<input type="submit" value="Join!">
									</form>
								</c:when>
								<c:otherwise>
									<form action="/events/${event.id}/cancel" method="post">
										<input type="submit" value="Cancel">
									</form>
								</c:otherwise>
							</c:choose>
							
						
							
							<c:if test="${event.hostUser.id == u_id}">
								<form action="/events/${event.id}" method="post">
									<input type="hidden" name="_method" value="delete">
									<input type="submit" value="Delete">
								</form>
								<a href="/events/${event.id}/edit"><input type="submit" value="Edit"></a>
							</c:if>
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="row mb-3">
		<div class="col-md-12">
			<h4>Create an event: </h4>
				<form:form action="/events" method="post" modelAttribute="newevent">
	
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
			<p><c:out value="${dateError}" /></p>
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
	
			<input type="submit" value="Create Event" />
		</form:form>
			
		</div>
	</div>


</body>
</html>
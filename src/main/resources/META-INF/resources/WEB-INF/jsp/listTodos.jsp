<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>
	<div class="container">
		<!-- <h3>Welcome to todos page ${myname}</h3> -->
		<hr>
		<h1>Your Todos</h1>
		<table class="table">
			<thead>
				<tr>
					<!-- <th>ID</th>-->
					<th>Description</th>
					<th>TargetDate</th>
					<th>IsDone?</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<!--  <td>${todo.id}</td>-->
						<td>${todo.description}</td>
						<td>${todo.targetDate}</td>
						<td>${todo.done}</td>
						<td><a href="delete-todo?id=${todo.id}"
							class="btn btn-warning">Delete</a></td>
						<td><a href="update-todo?id=${todo.id}"
							class="btn btn-success">Update</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="add-todo" class="btn btn-success">Add Todo</a>
	<%@include file="common/footer.jspf"%>
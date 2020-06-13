<%@ include file="common/header.jspf"  %>
<%@ include file="common/navBar.jspf" %>

<div class="container">
<h3 style="color:red">${error}</h3>
<h3 style="color:green">${success}</h3>
<h2>Add Category</h2>
<form:form action="addCategory" method="POST" modelAttribute="category">
	<form:input type="hidden" path="id" />
	<label for="name">Category name</label>
	<form:input type="text" path="name" id="name" class="form-control" />
	<form:errors path="name" style="color:red" />
	<label for="desc">Category description</label>
	<form:textarea type="text" path="description" id="desc" class="form-control"></form:textarea>
	<form:errors path="description" style="color:red" />
	<input type="submit" value="save" />
</form:form>
</div>
<%@ include file="common/footer.jspf" %>	
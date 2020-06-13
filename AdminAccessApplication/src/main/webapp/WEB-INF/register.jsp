<%@ include file="common/header.jspf" %>
<h2>Register Here</h2>
<h4 style="color:red">${error}</h4>
<h4 style="color:green">${success}</h4>
<h4 style="color:red">${msg}</h4>
<form:form method="POST" action="/save_user" modelAttribute="regUser">
	<form:input type="text" path="userName" />
	<form:errors path="userName" />
	<form:input type="password" path="password" />
	<form:errors path="password" />
	<form:button type="submit" class="btn">Save</form:button>
</form:form>
<a href="/login">Login</a>
<div class="test"></div>
<%@ include file="common/footer.jspf" %>
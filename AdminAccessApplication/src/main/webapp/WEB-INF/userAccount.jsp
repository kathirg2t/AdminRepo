<%@ include file="common/header.jspf"  %>
<%@ include file="common/navBar.jspf" %>

<h2>User Account</h2>

<h3>Name : ${user.userName}</h3>

<form:form method="POST" action="/saveAddr" modelAttribute="shipAddr">
	<form:textarea path="address" class="form-control">
	${shipAddr.address}
	</form:textarea>
	<form:input path="city"  placeHolder="City" class="form-control" value="${shipAddr.city}" />
	<form:input path="state"  placeHolder="State" class="form-control" value="${shipAddr.state}" />
	<form:input type="hidden" path="userdetails" value="${user.id}"  class="form-control"  />
	<form:input type="number" path="pinCode" value="${shipAddr.pinCode}"  placeHolder="Pin Code" class="form-control" />
	<form:input type="submit" value="Save" />
</form:form>

<%@ include file="common/footer.jspf" %>
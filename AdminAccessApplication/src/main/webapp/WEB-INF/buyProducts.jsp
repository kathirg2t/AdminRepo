<%@ include file="common/header.jspf" %>
<%@ include file="common/navBar.jspf" %>
Size: ${cartSize}
<div class="container">
	<p style="color:red">${error}</p>
	<p style="color:green">${success}</p>
	<div class="row">
		<c:set var="count" value="0" />
		<c:forEach items="${products}" var="product">
			<form:form action="/saveCart" method="POST" modelAttribute="cart">
			<c:if test="${product.quantity != 0}">
				<c:if test="${count%3 == 0 && count != 0}">
					</div><div class="row">
				</c:if>
				<div class="col-sm-4">
					<h3>${product.name}</h3>
					<img src="/productImages/${product.image}" style="height:100px;width:100px"  />
					<h4>Price : $${product.price}</h4>
					<label>Choose quantity</label>
					<form:input type="number" path="quantity" max="${product.quantity}" min="1" />
					<form:input type="hidden" path="userDetails" value="${user.id}"  />
					<form:input type="hidden" path="product" value="${product.id}" />
					<c:if test="${product.quantity < 4}">
						<p><b>Only <span style="color:red">${product.quantity}</span> stock is remaining</b></p>
					</c:if>
					<button class="btn btn-success">Add to Cart</button>
				</div>
				<c:set var="count" value="${count + 1}" />
			</c:if>
			</form:form>
		</c:forEach>
</div>


<%@ include file="common/footer.jspf" %>

<%@ include file="common/header.jspf" %>
<%@ include file="common/navBar.jspf" %>
<div class="container">
	${product}
	<h2>Add Product</h2>
	<h3 style="color:red">${error}</h3>
	<h3 style="color:green">${success}</h3>
	<form:form action="addProduct" method="POST" modelAttribute="product" enctype="multipart/form-data">
		<label>Product Name:</label>
		<form:input type="hidden" path="id" value="${id}" />
		<form:input type="text" path="name" id="productName" class="form-control"/>
		<form:errors path="name" style="color:red" />
		<label>Product Price:</label>
		<form:input type="number" path="price" class="form-control" />
		<form:errors path="price" style="color:red" />
		<label>Product Quantity:</label>
		<form:input type="number" path="quantity" class="form-control" />
		
		<form:errors path="quantity" style="color:red" />
		<form:input type="hidden" path="image" class="form-control" />
		<label>Product Image:</label>
		<input type="file" name="imageFile" accept="image/*"  />
		<c:if test="${!empty product.image}">
			<img src="/productImages/${product.image}"  style="height:100px;width:100px" /> 
		</c:if>
		
		<p style="color:red">${imageError}</p>
		
		<form:select path="category" class="form-control">
			<option value="">Select Category</option>
			<c:forEach var="category" items="${categories}">
				<option value="${category.id}" ${product.category.id == category.id ? 'selected' : '' } >${category.name}</option>
			</c:forEach>
		</form:select>
		<p style="color:red">${categoryError}</p>
		<input type="submit" value="save" />
	</form:form>
	
</div>
<%@ include file="common/footer.jspf" %>
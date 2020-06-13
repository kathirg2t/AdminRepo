<%@ include file="common/header.jspf" %>
<%@ include file="common/navBar.jspf" %>
	<h4 Style="color:green">${success}</h4>
	<a class="btn btn-primary" href="/addProduct">Add Product</a>
	<table class="table">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">Product Name</th>
      <th scope="col">Product Price</th>
      <th scope="col">Product quantity</th>
      <th scope="col">Category</th>
      <th>Action</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${products}" var="product">
	    <tr>
	      <th scope="row">${product.id}</th>
	      <td>${product.name}</td>
 	      <td>$${product.price}</td>
 	      <td>${product.quantity}</td>
	      <td>${product.category.name}</td>
	      <td><img src="/productImages/${product.image}" style="height:200px;width:200px"></td>
	      <td><a href="editProduct?productId=${product.id}">Edit</a>
	      <a href="deleteProduct?productId=${product.id}" onclick="return confirm('Are you Sure, Do you want to delete?');" >Delete</a>
	      </td>
	      
	    </tr>
    </c:forEach>
  </tbody>
</table>
	
<%@ include file="common/footer.jspf" %>
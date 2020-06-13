<%@ include file="common/header.jspf" %>
<%@ include file="common/navBar.jspf" %>
	<h4 Style="color:green">${success}</h4>
	<a class="btn btn-primary" href="/addCategory">Add Category</a>
	<table class="table">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">Category Name</th>
      <th>Action</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${categories}" var="category">
	    <tr>
	      <th scope="row">${category.id}</th>
 	      <td>${category.name}</td>
 	      <td><a href="editCategory?catId=${category.id}">Edit</a>
 	      <a href="deleteCategory?catId=${category.id}" onClick="return confirm("Are you sure? Do You want to delete?");">Delete</a>
 	      </td>
 	      
	    </tr>
    </c:forEach>
  </tbody>
</table>
	
<%@ include file="common/footer.jspf" %>
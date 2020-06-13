<%@ include file="common/header.jspf" %>
<%@ include file="common/navBar.jspf" %>
<h2>Order Placed</h2>

<h4 style="color:red"><b>${error}</b></h4>
<h4 style="color:green"><b>${success}</b></h4>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Product name</th>
      <th scope="col">Product price</th>
      <th scope="col">Quantity</th>
      <th>Action</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${orders}" var="order">
    <tr>
      <th scope="row">1</th>
      <td>${order.product.name}</td>
      <td>${order.product.price}</td>
      <td>
      <input type="hidden" class="productId" name="productId[]" value="${order.product.id}">
      <input type="hidden" class="userId" name="userId[]" value="${order.userDetails.id}">
      <input type="number" max="${order.product.quantity}" min="1" name="quantity[]" class="myproductQty" value="${order.quantity}"></td>
      <td><button class="updateQty">Update Qty</button></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
<h4>Total Price: ${totalPrice}</h4>
<button class="btn btn-success" name="place-order">Continue Order</button>

<form method="POST" action="/updateQty" id="updateQty-form">
	<input type="hidden" class="productIdNew" name="productID" value="">
    <input type="hidden" class="userIdNew" name="userId" value="">
    <input type="hidden" class="quantityNews" name="productQty" value="">
</form>

<%@ include file="common/footer.jspf" %>

<script>
jQuery(document).on("click", ".updateQty", function() {
	var productID = parseInt(jQuery(this).parents("tr").find(".productId").val());
	var userId = parseInt(jQuery(this).parents("tr").find(".userId").val());
	var productQty = parseInt(jQuery(this).parents("tr").find(".myproductQty").val());

	jQuery(".productIdNew").val(productID);
	jQuery(".userIdNew").val(userId);
	jQuery(".quantityNews").val(productQty);

	jQuery("#updateQty-form").trigger("submit");
	return false;

	/* $.ajax({
		type : "POST",
		url : "/updateQty",
		cotentType : false,
		cache : false,
		data : 	{ productQty : productQty, productID : productID, userId : userId },
		success : function(data) {
			alert(data);
		},
		error : function(err) {
			alert(err);
		} 
	}); */
});
</script>
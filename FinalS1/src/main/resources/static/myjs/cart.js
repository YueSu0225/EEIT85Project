document.addEventListener('DOMContentLoaded', function() {   
			    fetchCartItems();
			});
	
	// 購物車數據
	function fetchCartItems() {
	    fetch('/cart/items')
	        .then(response => {
	            if (!response.ok) {
	                throw new Error('Network response was not ok');
	            }
	            return response.json();
	        })
	        .then(cartItems => {
	            displayCartItems(cartItems); 
	            
	            const cartSummarySection = document.getElementById('cart-summary-section');
	            const cartItemTable = document.getElementById('cart-item-table');
	            const emptyCartContainer = document.getElementById('empty-cart-container')
	            
	            if (cartItems.length > 0) {
	            	cartSummarySection.style.display = 'flex';
	            	cartItemTable.style.display = 'flex';	
	            	
	            	emptyCartContainer.innerHTML = '';
	            } else {		            	
	            	cartSummarySection.style.display = 'none';
	            	cartItemTable.style.display = 'none';
	            	
	            	emptyCartContainer.innerHTML = '';
	            	const emptyCart = document.createElement('div');
	            	
	            	emptyCart.innerHTML = `
	            	<div class="container text-center">
	            		<h5>您的購物車沒有商品</h5>
		            	<a href="shop.html">查看更多商品</a>
	            	</div>
	            	
	            	`;
	            	
	            	emptyCartContainer.appendChild(emptyCart);
	            }
	        })
	        .catch(error => console.error('Error fetching cart items:', error));
	}
	
	
	// 將購物車內容丟到頁面
	function displayCartItems(cartItems) {
	    const cartItemsBody = document.getElementById('cart-items-body');
	    cartItemsBody.innerHTML = '';  // 清空之前的内容
	    let subtotal = 0;
	
	    cartItems.forEach(item => {
	        const row = document.createElement('tr');
	        row.classList.add('text-center');
	
	        row.innerHTML = `
	            <td class="product-remove" data-id="${item.id}"><a href="#" class="delete-item"><span class="ion-ios-close"></span></a></td>
	            <td class="image-prod">
	                <img src="data:image/jpeg;base64,${item.productVariant.product.image}" style="width:150px;height:200px;">
	            </td>
	            <td class="product-name">
	                <h3>${item.productVariant.product.name}</h3>
	                <p>${item.productVariant.product.description}</p>
	            </td>
	            <td class="cart-size">${item.productVariant.size.name}</td>
	            <td class="price">NT$${item.price.toFixed(2)}</td>
	            <td class="quantity">
	                <div class="input-group mb-3">
	                    <input type="number" name="quantity" class="quantity form-control input-number" value="${item.quantity}" min="1" max="10" data-variant-id="${item.productVariant.id}">
	                </div>
	            </td>
	            <td class="total">NT$${(item.price * item.quantity).toFixed(2)}</td>
	        `;
	
	        cartItemsBody.appendChild(row);
	        subtotal += item.price * item.quantity; // 類季商品總價
	
	        // 數量框確認
	        const quantityInput = row.querySelector('.quantity input');
			
	        quantityInput.addEventListener('change', function() {
	        	const newQuantity = parseInt(this.value);
	       		console.log('新數量:', newQuantity);
	        	const variantId = this.getAttribute('data-variant-id');
			
			async function updateCart(variantId, newQuantity){
	            if (newQuantity > 0) {
	                try{
						const response = await fetch('/cart/update',{
							method: 'POST',
							headers:{
								'content-type': 'application/json'
							},
							body: JSON.stringify({
								variantId: variantId,
								quantity: newQuantity
							})
						});
						
						if(!response.ok){
							const err = await response.json();
							throw new Error(err.message || '更新購物車失敗');
						}
						
						const cartData = await response.json();
						
						if(cartData.success){
							displayCartItems(cartData.cartItems);
						}else{
							alert('更新購物車失敗!請重試。');
						}							
					}catch(error){
						console.error('Error updating cart:', error);
					}
				}else{
					alert('數量不可小於1');
					//重置為1
					this.value = 1;
					}
				}
	     	});
	    });

	    updatePaymentDetails(subtotal); // foreach完後再更新	
	}
	
	function updatePaymentDetails(subtotal) {
	    const shipping = 0.00; // 運費0
	    const discount = 0.00; // 折扣0
	    const total = subtotal + shipping - discount;
	
	    document.querySelector('.cart-total .subtotal').innerText = `NT$${subtotal.toFixed(2)}`;
	    document.querySelector('.cart-total .shipping').innerText = `NT$${shipping.toFixed(2)}`;
	    document.querySelector('.cart-total .discount').innerText = `NT$${discount.toFixed(2)}`;
	    document.querySelector('.cart-total .total-price').innerText = `NT$${total.toFixed(2)}`;
	}
								
	
	// 刪除
	document.addEventListener('click', function(event) {
	    if (event.target.closest('.delete-item')) {
	        event.preventDefault();
	        
	        const row = event.target.closest('tr');
	        const itemId = event.target.closest('.product-remove').dataset.id;
	        
	        //彈出框
	        const confirmModal = document.getElementById('confirmModal');
	                confirmModal.style.display = 'flex';
	                
	                document.getElementById('confirmDelete').onclick = function () {
	                    // 如果用戶確認，則執行刪除操作
	                   deleteCartItem(itemId, row);
	                    confirmModal.style.display = 'none';
	                };

	                document.getElementById('cancelDelete').onclick = function () {
	                    // 如果用戶取消，隱藏模態框
	                    confirmModal.style.display = 'none';
	                };
	   		 }
	});
	
	function deleteCartItem(itemId, row) {
			fetch(`/cart/delete`, {
		        method: 'DELETE',
		        headers: {
		            'Content-Type': 'application/json',
		        },
		        body: JSON.stringify({ id: itemId }), // 發送請求，包含商品 ID
		    })
		    .then(response => {
		        if (!response.ok) {
		            throw new Error('Failed to delete item');
		        }
		        return response.text(); // 期待回傳的文字訊息
		    })
		    .then(data => {
		    	console.log('Item deleted successfully:', data);
		    	row.remove();
		    	
		    	fetchCartItems();  // 重新獲取購物車商品以更新介面 	
		    })

		    .catch(error => console.error('Error deleting item:', error));
		}
			
			
			
			
			
			
			
			
			
			
				
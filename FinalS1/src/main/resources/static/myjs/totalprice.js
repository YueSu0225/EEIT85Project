document.addEventListener('DOMContentLoaded', function() {
				    // 發送請求到後端獲取購物車數據
				    fetch('/cart/items')  // 確認 API 路徑正確
				        .then(response => {
				            if (!response.ok) {
				                throw new Error('Network response was not ok');
				            }
				            return response.json();
				        })
				        .then(data => {
							console.log("fetchdata:", data);
							totalprice(data); 
				        })
				        .catch(error => console.error('Error fetching cart data:', error));
				});


				
				function totalprice(cartItems) {
					const totalCount = cartItems.reduce((total, item) => total + item.quantity, 0);
					console.log('Total items in cart:', totalCount);
				   
					 let subtotal = 0;
				
				    // 直接計算商品總額
				    cartItems.forEach(item => {
				        subtotal += item.price * item.quantity; // 計算商品總額
				    });
				
				    // 更新付款詳情
				    updatePaymentDetails(subtotal);
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
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
				            const cartItems = data;  
				            updateCartCount(cartItems);  
				        })
				        .catch(error => console.error('Error fetching cart data:', error));
				});

				// 更新購物車數量的函數
				function updateCartCount(cartItems) {
					console.log('Updating cart count with items:', cartItems);
				    const totalCount = cartItems.reduce((total, item) => total + item.quantity, 0);
				    console.log('Total items in cart:', totalCount); // 查看計算的結果
				    
				    const cartCountElement = document.getElementById('cart-count');
				    cartCountElement.textContent = totalCount;
				}
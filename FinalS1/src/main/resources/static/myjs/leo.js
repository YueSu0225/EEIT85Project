// 監聽圖標點擊事件
	document.getElementById('cart-icon').addEventListener('click', function(event) {
	  event.preventDefault(); // 阻止默認連接行為
	  fetch('/final/checksession', {
	    method: 'GET',
	    credentials: 'include' // 確保攜帶 cookies
	  })
	  .then(response => {
	    if (response.status === 401) {
		  showModal();
	      return;
	    }
	    window.location.href = '/cart.html';
	  })
	  .catch(error => {
	    console.error('Error checking session:', error);
	    window.location.href = '/Home.html';
	  });
	});
	
	function showModal() {
	        const modal = document.getElementById('loginModal');
	        modal.style.display = 'flex';  
	    }

    
    function closeModal() {
        const modal = document.getElementById('loginModal');
        modal.style.display = 'none';
    }

    
    document.getElementById('loginButton').addEventListener('click', function() {
        window.location.href = '/Signin.html';  // 跳轉到登入頁面
    });

    document.getElementById('closeModalButton').addEventListener('click', closeModal);
	
	
	
	
	
	
	
	// 監聽圖標點擊事件
		document.getElementById('icon-wish').addEventListener('click', function(event) {
		  event.preventDefault(); // 阻止默認連接行為
		  fetch('/final/checksession', {
		    method: 'GET',
		    credentials: 'include' // 確保攜帶 cookies
		  })
		  .then(response => {
		    if (response.status === 401) {
			  showModal();
		      return;
		    }
		    window.location.href = '/wishlist.html';
		  })
		  .catch(error => {
		    console.error('Error checking session:', error);
		    window.location.href = '/Home.html';
		  });
		});
		
		function showModal() {
		        const modal = document.getElementById('loginModal');
		        modal.style.display = 'flex';  
		    }

	    
	    function closeModal() {
	        const modal = document.getElementById('loginModal');
	        modal.style.display = 'none';
	    }

	    
	    document.getElementById('loginButton').addEventListener('click', function() {
	        window.location.href = '/Signin.html';  // 跳轉到登入頁面
	    });

	    document.getElementById('closeModalButton').addEventListener('click', closeModal);
		
		
		
		
// addtocart

function addToCart() {
	    const quantity = document.getElementById('quantity').value || 1; // 默認數量為1
	    const selectedColor = document.getElementById('color-select').value || initialColor;
	    const selectedSize = document.getElementById('size-select').value || initialSize;

	    const urlParams = new URLSearchParams(window.location.search);
	    const productId = urlParams.get('productId');
	    console.log("Product ID:", productId);
	    console.log("選擇的顏色:", selectedColor);
	    console.log("選擇的尺寸:", selectedSize);
	    console.log("數量:", quantity);
	    
	    fetch(`/product-variants/product/${productId}`)
	        .then(response => response.json())
	        .then(variants => {
	            let selectedVariant = variants.find(variant => variant.color.name === selectedColor && variant.size.name === selectedSize);
	            if (selectedVariant) {
	                // 加到購物車
	                fetch('/cart/add', {
	                    method: 'POST',
	                    headers: {
	                        'Content-Type': 'application/json'
	                    },
	                    body: JSON.stringify({
	                        variantId: selectedVariant.id,
	                        quantity: parseInt(quantity)   
	                    })
	                })
	                .then(response => {
	                	if (!response.ok) {
	                        throw new Error('Network response was not ok');
	                    }
	                	
	                	return response.json() // 後端提供購物車數據
	                	
	                })
	                .then(cartData => { // 使用 cartData
	                	console.log('Cart Data:', cartData);
	                
	                    if (cartData.success) {
	                        alert('商品已成功添加到購物車！');
	                    } else {
	                        showModal();
	                    }
	                })
	                .catch(error => {
	                    console.error('Error adding to cart:', error);
	                    showModal();
	                });
	            } else {
	                console.error('No variant found for the selected color and size.');
	                alert('選擇的顏色或尺寸不正確，請重試。');
	            }
	        })
	        .catch(error => {
	            console.error('Error fetching product details:', error);
	            alert('獲取商品詳情時出現錯誤，請稍後重試。');
	        });
	}

	// 確保在點擊按鈕時調用 addToCart 函數
	document.querySelector('.btn-add-to-cart').addEventListener('click', addToCart);
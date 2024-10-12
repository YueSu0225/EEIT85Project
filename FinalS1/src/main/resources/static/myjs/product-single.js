document.addEventListener("DOMContentLoaded", function() {
		const quantityInput = document.getElementById('quantity');
	    const quantityMinus = document.querySelector('.quantity-left-minus');
	    const quantityPlus = document.querySelector('.quantity-right-plus');
	    let currentStock = 0; 
	    const urlParams = new URLSearchParams(window.location.search);
	    const productId = urlParams.get('productId');
	    
	    quantityMinus.addEventListener('click', function (e) {
	        e.preventDefault();
	        let currentValue = parseInt(quantityInput.value);
	        if (currentValue > 1) {
	            quantityInput.value = currentValue - 1;
	        }
	        validateQuantity();
	    });

	    quantityPlus.addEventListener('click', function (e) {
	        e.preventDefault();
	        let currentValue = parseInt(quantityInput.value);
	        if (currentValue < currentStock) {
	            quantityInput.value = currentValue + 1;
	        }
	        validateQuantity();
	    });
	    
	    // 監聽 quantity 輸入框的手動輸入
	    quantityInput.addEventListener('input', function() {
	        validateQuantity();
	    });


	    if (!productId) {
	        console.error('Product ID is missing in the URL');
	        return;
	    }

	    let initialColor, initialSize;

	    
	    fetch(`/product-variants/product/${productId}`)
	        .then(response => response.json())
	        .then(variants => {
	            if (variants.length > 0) {
	                const colorSelect = document.getElementById('color-select');
	                const sizeSelect = document.getElementById('size-select');
	                const priceElement = document.getElementById('price');
	                const stockElement = document.getElementById('stock');
	                const descriptionElement = document.getElementById('description');
	                const productName = document.getElementById('product-name');

	                
	                initialColor = variants[0].color.name;
	                initialSize = variants[0].size.name;

	                
	                priceElement.innerText = `NT$${variants[0].price}`;
	                stockElement.innerText = variants[0].stock;
	                productName.innerText = variants[0].product.name;
	                descriptionElement.innerText = variants[0].product.description;

	                
	                populateSelectOptions(variants, colorSelect, 'color');
	                populateSelectOptions(variants, sizeSelect, 'size');

	               
	                colorSelect.addEventListener('change', updateProductDetails);
	                sizeSelect.addEventListener('change', updateProductDetails);

	                
	                updateProductDetails();
	            }
	        })
	        .catch(error => console.error('Error fetching variants:', error));

	   
	    function updateProductDetails() {
	        const selectedColor = document.getElementById('color-select').value || initialColor;
	        const selectedSize = document.getElementById('size-select').value || initialSize;

	        fetch(`/product-variants/product/${productId}`)
	            .then(response => response.json())
	            .then(variants => {
	                let selectedVariant = variants.find(variant => variant.color.name === selectedColor && variant.size.name === selectedSize);
	                if (selectedVariant) {
	                    document.getElementById('price').innerText = `NT$${selectedVariant.price}`;
	                    document.getElementById('stock').innerText = selectedVariant.stock;

	                    currentStock = selectedVariant.stock; // 更新庫存
	                    validateQuantity();
	                    
	                    
	                    const addToCartButton = document.getElementById('add-to-cart-button');
	                    
	                    if (currentStock === 0) {
	                        // 禁用按鈕
	                        addToCartButton.style.pointerEvents = 'none';
	                        addToCartButton.style.opacity = '0.5'; // 改變透明度表示禁用狀態
	                        addToCartButton.innerText = "已售完"; // 顯示已售完
	                    } else {
	                        // 啟用按鈕
	                        addToCartButton.style.pointerEvents = 'auto';
	                        addToCartButton.style.opacity = '1'; // 恢復透明度
	                        addToCartButton.innerText = "加入購物車"; // 恢復正常文字
	                    }
	                    
	                    
	                    updateCarouselImages(selectedVariant);
	                } else {
	                    console.error('No variant found for the selected color and size.');
	                }
	            })
	            .catch(error => console.error('Error fetching product details:', error));
	    }
	    
	    function validateQuantity() {
	        let quantity = parseInt(quantityInput.value);

	        if (isNaN(quantity) || quantity < 1) {
	            quantityInput.value = 1;
	        } else if (quantity > currentStock) {
	            quantityInput.value = currentStock;
	        }
	    }

	  
	    function updateCarouselImages(variant) {
	        const carouselInner = document.getElementById('carousel-inner');

	        carouselInner.innerHTML = '';

	        let images = [variant.image];
	        if (variant.image2) images.push(variant.image2);
	        if (variant.image3) images.push(variant.image3);

	        images.forEach((image, index) => {
	            
	            let carouselItem = document.createElement('div');
	            carouselItem.classList.add('carousel-item');
	            if (index === 0) carouselItem.classList.add('active');
	            carouselItem.innerHTML = `<img src="data:image/jpeg;base64,${image}" class="d-block w-100" alt="Product Image">`;
	            carouselInner.appendChild(carouselItem);
	        });

	       
	        $('#carouselExampleIndicators').carousel();
	    }

	  
	    function populateSelectOptions(variants, selectElement, property) {
	        const uniqueOptions = new Set(variants.map(variant => variant[property].name));
	        selectElement.innerHTML = ''; 
	        uniqueOptions.forEach(optionValue => {
	            const option = document.createElement('option');
	            option.value = optionValue;
	            option.innerText = optionValue;
	            selectElement.appendChild(option);
	        });
	    }
	});


	
	

	
	//推薦商品
	document.addEventListener("DOMContentLoaded", function () {
    let wishlistItems = []; // 保存當前用戶的願望清單商品 ID

    // 初始化頁面，載入願望清單和商品
    initializeWishlist();
    loadRandomProducts();

    // 初始化願望清單，保存商品 ID
    function initializeWishlist() {
        fetch('/wishlist/items', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch wishlist items');
            }
            return response.json();
        })
        .then(data => {
            wishlistItems = data.map(item => item.product.id); // 保存願望清單中的商品 ID
        })
        .catch(error => console.error('Error fetching wishlist:', error));
    }

    // 呼叫 API 獲取所有商品資料
    function loadRandomProducts() {
        fetch('/products/random')
            .then(response => response.json())
            .then(data => {
                let productList = document.getElementById('product-list');
                productList.innerHTML = ""; // 清空當前列表

                
                let shuffledData = data.sort(() => 0.5 - Math.random());
                let selectedProducts = shuffledData.slice(0, 4); 

                // 動態插入商品資料
                selectedProducts.forEach(product => {
                    let inWishlist = wishlistItems.includes(product.id); // 檢查該商品是否在願望清單中
                    let heartClass = inWishlist ? 'added-to-wishlist' : '';

                    let productHTML = `
                        <div class="col-md-6 col-lg-3 ftco-animate1">
                            <div class="product">
                                <a href="product-single.html?productId=${product.id}" class="img-prod">
                                    <img class="img-fluid" src="data:image/jpeg;base64,${product.image}" alt="${product.name}">
                                    <div class="overlay"></div>
                                </a>
                                <div class="text py-3 pb-4 px-3 text-center">
                                    <h3><a href="product-single.html?productId=${product.id}">${product.name}</a></h3>
                                    <div class="pricing">
                                        <p class="price"><span class="price-sale">NT$${product.price}</span></p>
                                    </div>
                                    <div class="bottom-area d-flex px-3">
                                        <div class="m-auto d-flex">
                                            <a href="#" class="heart d-flex justify-content-center align-items-center ${heartClass}" data-product-id="${product.id}" data-product-name="${product.name}" 
                                            data-product-price="${product.price}" data-product-image="${product.image}" data-in-wishlist="${inWishlist}">
                                                <span><i class="ion-ios-heart"></i></span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    `;
                    productList.innerHTML += productHTML;
                });

                // 綁定心形按鈕事件
                bindWishlistEvents();
            })
            .catch(error => console.error('Error fetching products:', error));
    }

    // 綁定心形圖標的點擊事件
    function bindWishlistEvents() {
        document.querySelectorAll('.heart').forEach(item => {
            item.addEventListener('click', function (event) {
                event.preventDefault();  // 防止預設的跳轉行為

                // 獲取商品信息
                let productId = this.getAttribute('data-product-id');
                let productName = this.getAttribute('data-product-name');
                let productPrice = this.getAttribute('data-product-price');
                let productImage = this.getAttribute('data-product-image');
                let inWishlist = this.getAttribute('data-in-wishlist') === 'true';

                // 構造 API 請求數據
                let requestData = {
                    productId: productId
                };

                console.log('點擊的商品:', { productId });

                // 發送 POST 請求到後端的 toggle API
                fetch('/wishlist/toggle', {
                    method: 'POST',
                    credentials: 'include',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(requestData)
                })
                .then(response => {
                    if (response.status === 401) {
                        // 未授權，提示用戶登入
                        showModal();
                        throw new Error('User not authenticated'); // 拋出錯誤來中止操作
                    }
                    return response.json();
                })
                .then(data => {
                    console.log(data);
                    // 更新心形圖標的樣式
                    if (inWishlist) {
                        this.classList.remove('added-to-wishlist');
                        this.setAttribute('data-in-wishlist', 'false');
                        showTemporaryNotification(`已從願望清單移除: ${productName}`, productImage);
                        wishlistItems = wishlistItems.filter(id => id != productId); // 從願望清單中移除商品
                    } else {
                        this.classList.add('added-to-wishlist');
                        this.setAttribute('data-in-wishlist', 'true');
                        showTemporaryNotification(`已加入願望清單: ${productName}, NT$${productPrice}`, productImage);
                        wishlistItems.push(productId); // 添加商品到願望清單
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
            });
        });
    }
    
    function showModal() {
        const modal = document.getElementById('loginModal');
        modal.style.display = 'flex';  // 使用 'flex' 來讓模態框垂直和水平居中
    }

    // 隱藏模態框
    function closeModal() {
        const modal = document.getElementById('loginModal');
        modal.style.display = 'none';
    }

    // 綁定按鈕點擊事件
    document.getElementById('loginButton').addEventListener('click', function() {
        window.location.href = '/Signin.html';  // 跳轉到登入頁面
    });

    document.getElementById('closeModalButton').addEventListener('click', closeModal);


    // 顯示臨時通知
    function showTemporaryNotification(message, productImage = null) {
        const notification = document.createElement('div');
        notification.className = 'temporary-notification';
        notification.innerHTML = `
            <div class="notification-content">
                ${productImage ? `<img src="data:image/jpeg;base64,${productImage}" alt="商品圖片" class="notification-image">` : ''}
                <div class="notification-text">${message}</div>
            </div>
        `;
        document.body.appendChild(notification);

        setTimeout(() => {
            notification.remove();
        }, 1000);
    }
});


    function addItemToWishlist(productId, productName, productPrice, productImage) {
        console.log(`商品已加入願望清單: 名稱: ${productName}, 價格: ${productPrice}`);
    }

    
    function removeItemFromWishlist(productId) {
        console.log(`商品已從願望清單移除: ID: ${productId}`);
    }




const style = document.createElement('style');
style.innerHTML = `
.temporary-notification {
    position: fixed;
    top: 20px;
    right: 20px;
    background-color: #f1f1f1;
    color: #333;
    padding: 15px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    z-index: 1000;
    display: flex;
    align-items: center;
    opacity: 0.95;
}
.notification-content {
    display: flex;
    align-items: center;
}
.notification-image {
    width: 50px;
    height: 50px;
    margin-right: 15px;
    border-radius: 4px;
    object-fit: cover;
}
.notification-text {
    font-size: 14px;
}
`;
document.head.appendChild(style);

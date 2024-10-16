document.addEventListener("DOMContentLoaded", function () {
	    let selectedCategoryId = null;
	    let selectedTypeId = null;
	    let offset = 0; // 僅用于 loadAllProducts
	    const limit = 10; 
	    let totalProducts = 0; // 商品总数（僅用于 loadAllProducts）
	    let isLoading = false; // 是否正在加载中
	    let wishlistItems = []; // 保存當前用戶的願望清單商品 ID
	    
	    const searchInput = document.getElementById('search-input');
	    searchInput.addEventListener('input', function() {
	        const query = searchInput.value;
	        searchProducts(query);
	    });
	    
	    // 搜尋產品的函數
	    function searchProducts(query) {
    fetch(`/products/search?key=${encodeURIComponent(query)}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        const productList = document.getElementById('product-list');
        productList.innerHTML = ''; // 清空現有的商品列表
        document.getElementById('load-more-button').style.display = 'none'; // 隱藏「加載更多」按鈕
        let noProductsMessage = document.getElementById('no-products-message');

        if (data.length > 0) {
            data.forEach(product => {
                let inWishlist = wishlistItems.includes(product.id); // 檢查該商品是否在願望清單中
                let heartClass = inWishlist ? 'added-to-wishlist' : ''; // 設置愛心樣式
				noProductsMessage.style.display = 'none';

                const productHTML = `
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
                                        <a href="#" class="heart d-flex justify-content-center align-items-center ${heartClass}" 
                                           data-product-id="${product.id}" data-product-name="${product.name}"
                                           data-product-price="${product.price}" data-product-image="${product.image}" 
                                           data-in-wishlist="${inWishlist}">
                                            <span><i class="ion-ios-heart"></i></span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;
                productList.innerHTML += productHTML;
            });
            bindWishlistEvents(); // 綁定願望清單按鈕事件
        } else {
        	 noProductsMessage.style.display = 'block';
        }
    })
    .catch(error => console.error('Error fetching products:', error));
}
	    
	    

	    // 獲取 URL 
	    function getQueryParam(param) {
	        let urlParams = new URLSearchParams(window.location.search);
	        return urlParams.get(param);
	    }
		
	    selectedCategoryId = getQueryParam('category');
	    selectedTypeId = getQueryParam('type');

	    // 初始化頁面，載入願望清單和商品
	    initializeWishlist().then(() => {
	        if (selectedCategoryId || selectedTypeId) {
	            loadProducts(selectedCategoryId, selectedTypeId);
	        } else {
	            loadAllProducts(true);
	        }
	    });

	    // 初始化願望清單，保存商品 ID
	    function initializeWishlist() {
	        return fetch('/wishlist/items', {
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

	    // 綁定心形圖標的點擊事件
	    function bindWishlistEvents() {
	        document.querySelectorAll('.heart').forEach(item => {
	            item.removeEventListener('click', toggleWishlist); // 確保不重複綁定事件
	            item.addEventListener('click', toggleWishlist);
	        });
	    }

	    
	   // 心形按鈕點擊處理函數
    function toggleWishlist(event) {
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
        }, 9000);
    }
    

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
	    
	    function loadAllProducts(reset = false) {
	        if (isLoading) return; // 防止重複請求
	        isLoading = true;

	        if (reset) {
	            offset = 0;
	            totalProducts = 0;
	            document.getElementById('product-list').innerHTML = '';
	        }

	        // 構建請求的 URL
	        let url = `/products?offset=${offset}&limit=${limit}`;

	        fetch(url)
	            .then(response => response.json())
	            .then(data => {
	                isLoading = false; // 完成請求後重置標記
	                let productList = document.getElementById('product-list');
	                let noProductsMessage = document.getElementById('no-products-message');

	                if (!productList) {
	                    console.error('Cannot find element with id "product-list".');
	                    return;
	                }

	                if (Array.isArray(data.products) && data.products.length > 0) {
	                    noProductsMessage.style.display = 'none'; // 隱藏「查無此商品」消息

	                    data.products.forEach(product => {
	                        let inWishlist = wishlistItems.includes(product.id);
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

	                    // 呼叫動畫函數並綁定愛心事件
	                    animateProducts();
	                    bindWishlistEvents();

	                    // 更新偏移量
	                    offset += data.products.length;
	                    totalProducts = data.total;

	                    // 檢查是否還有更多商品
	                    if (offset >= totalProducts) {
	                        document.getElementById('load-more-button').style.display = 'none';
	                    } else {
	                        document.getElementById('load-more-button').style.display = 'block';
	                    }
	                } else {
	                    // 如果沒有商品，顯示「查無此商品」消息
	                    if (reset) {
	                        noProductsMessage.style.display = 'block';
	                    }
	                    document.getElementById('load-more-button').style.display = 'none';
	                }
	            })
	            .catch(error => {
	                isLoading = false;
	                console.error('Error fetching products:', error);
	            });
	    }

	    // 根據分類與版型載入商品的函數
	    function loadProducts(categoryId, typeId) {
	        let url = '/products';

	        if (categoryId && typeId) {
	            url = `/products/category/${categoryId}/type/${typeId}`;
	        } else if (categoryId) {
	            url = `/products/category/${categoryId}`;
	        } else if (typeId) {
	            url = `/products/type/${typeId}`;
	        }

	        fetch(url)
	            .then(response => response.json())
	            .then(data => {
	                let productList = document.getElementById('product-list');
	                let noProductsMessage = document.getElementById('no-products-message');
	                productList.innerHTML = "";  // 清空當前的商品列表

	                if (Array.isArray(data) && data.length > 0) {
	                    noProductsMessage.style.display = 'none';
	                    data.forEach(product => {
	                        let inWishlist = wishlistItems.includes(product.id);
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
	                    animateProducts();  // 呼叫動畫函數
	                } else {
	                    noProductsMessage.style.display = 'block';
	                }
	            })
	            .catch(error => console.error('Error fetching products:', error));
	    }

	    // 商品動畫效果的函數
	    function animateProducts() {
	        let animatedElements = document.querySelectorAll('.ftco-animate1');
	        animatedElements.forEach(el => {
	            el.style.opacity = 1;
	            el.style.visibility = 'visible';
	        });
	        bindWishlistEvents();
	    }
	  

	    // 綁定版型按鈕的點擊事件
	    document.querySelectorAll('.product-category li a').forEach(item => {
	        item.addEventListener('click', function (event) {
	            event.preventDefault();

	            // 移除所有 li 的 active 類
	            document.querySelectorAll('.product-category li a').forEach(link => {
	                link.classList.remove('active');
	            });

	            // 為當前點擊的 li 添加 active 類
	            this.classList.add('active');

	            selectedTypeId = this.getAttribute('typeid'); // 取得 typeid

	            // 清空商品列表
	            document.getElementById('product-list').innerHTML = '';
	            // 隐藏“加载更多”按钮
	            document.getElementById('load-more-button').style.display = 'none';

	            if (selectedTypeId) {
	                loadProducts(selectedCategoryId, selectedTypeId); // 根據版型載入商品
	            } else {
	                if (selectedCategoryId) {
	                    loadProducts(selectedCategoryId, null);
	                } else {
	                    loadAllProducts(true); // 如果沒有選擇分類和版型，則顯示所有
	                }
	            }
	        });
	    });

	    // 綁定分類按鈕的點擊事件
	    document.querySelectorAll('.dropdown-item').forEach(item => {
	        item.addEventListener('click', function (event) {
	            event.preventDefault();

	            // 清除所有 active 類
	            document.querySelectorAll('.dropdown-item').forEach(link => {
	                link.classList.remove('active');
	            });
	            this.classList.add('active');

	            selectedCategoryId = this.getAttribute('categoryid'); // 取得 categoryid

	            // 清空商品列表
	            document.getElementById('product-list').innerHTML = '';
	            // 隐藏“加载更多”按钮
	            document.getElementById('load-more-button').style.display = 'none';

	            if (selectedCategoryId) {
	                loadProducts(selectedCategoryId, selectedTypeId); // 根據分類載入商品
	            } else {
	                if (selectedTypeId) {
	                    loadProducts(null, selectedTypeId);
	                } else {
	                    loadAllProducts(true); // 如果沒有選擇分類和版型，則顯示所有
	                }
	            }
	        });
	    });

	    // 綁定「所有商品」按鈕的點擊事件
	    document.getElementById('all-products-category').addEventListener('click', function (event) {
	        event.preventDefault();
	        selectedCategoryId = null;  // 清空分類選擇
	        selectedTypeId = null;      // 清空版型選擇

	        // 移除所有 active 類
	        document.querySelectorAll('.product-category li a, .dropdown-item').forEach(link => {
	            link.classList.remove('active');
	        });
	        this.classList.add('active');

	      
	        document.getElementById('load-more-button').style.display = 'none';

	        loadAllProducts(true);          // 重新載入所有商品
	    });

	    // 綁定“加載更多”按鈕點擊事件
	    document.getElementById('load-more-button').addEventListener('click', function () {
	        loadAllProducts();
	    });

	    // 頁面加載時根據 URL 參數自動載入商品
	    if (selectedCategoryId || selectedTypeId) {
	        // 清空商品列表
	        document.getElementById('product-list').innerHTML = '';
	        // 隐藏“加载更多”按钮
	        document.getElementById('load-more-button').style.display = 'none';

	        loadProducts(selectedCategoryId, selectedTypeId);
	    } else {
	        loadAllProducts(true);
	    }

	   
	    loadAllProducts();
	});

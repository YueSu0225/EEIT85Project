document.addEventListener('DOMContentLoaded', function () {
		    // 更新願望清單的函式
		    function updateWishlist() {
		        // 呼叫 API 取得願望清單
		        fetch(`/wishlist/items`, {
		            method: 'GET',
		            headers: {
		                'Content-Type': 'application/json'
		            }
		        })
		        .then(response => response.json())
		        .then(data => {
		            let wishlistItemsHtml = '';
		            const wishlistContainer = document.getElementById('wishlist-items');
		            const wishlistitemtable = document.getElementById('wishlist-item-table');
		            const emptywishContainer = document.getElementById('empty-wish-container');
		            
		            if (data.length > 0) {
		                // 如果有商品，顯示清單並清空空列表的內容
		                wishlistitemtable.style.display = 'flex';
		                emptywishContainer.innerHTML = '';
		            } else {
		                // 如果沒有商品，隱藏清單並顯示空列表的提示訊息
		                wishlistitemtable.style.display = 'none';
		                emptywishContainer.innerHTML = ''; // 確保先清空內容
		                const emptyWishlist = document.createElement('div');
		                
		                emptyWishlist.innerHTML = `
		                    <div class="container text-center">
		                        <h5>您的喜愛清單沒有商品</h5>
		                        <a href="shop.html">查看更多商品</a>
		                    </div>
		                `;
		                
		                emptywishContainer.appendChild(emptyWishlist);
		            }
		            
		            // 更新願望清單的項目
		            data.forEach(item => {
		                const product = item.product;

		                wishlistItemsHtml += `
		                    <tr class="text-center" data-id="${product.id}">
		                        <td class="product-remove">
		                            <a href="#" class="delete-item"><span class="ion-ios-close"></span></a>
		                        </td>
		                        <td class="image-prod">
		                            <img src="data:image/jpeg;base64,${product.image}" alt="${product.name}" style="width:150px;height:200px;">
		                        </td>
		                        <td class="product-name">
		                            <h3>${product.name}</h3>
		                            <p>${product.description}</p>
		                        </td>
		                        <td class="price">$${product.price}</td>
		                        <td class="product-btn">
		                            <div class="mb-3">
		                                <a href="product-single.html?productId=${product.id}" class="btn btn-primary">查看商品</a>
		                            </div>
		                        </td>
		                    </tr>
		                `;
		            });

		            // 更新心願清單的 HTML
		            const wishlistItemsElement = document.getElementById('wishlist-items');
		            wishlistItemsElement.innerHTML = wishlistItemsHtml;

		            // 綁定刪除按鈕事件
		            wishlistItemsElement.querySelectorAll('.delete-item').forEach(button => {
		                button.addEventListener('click', function (e) {
		                    e.preventDefault();
		                    const itemId = this.closest('tr').getAttribute('data-id');

		                    // 顯示自定義確認模態框
		                    const confirmModal = document.getElementById('confirmModal');
		                    confirmModal.style.display = 'flex';

		                    // 綁定確認和取消按鈕的事件
		                    document.getElementById('confirmDelete').onclick = function () {
		                        // 如果用戶確認，則執行刪除操作
		                        deleteWishlistItem(itemId);
		                        confirmModal.style.display = 'none';
		                    };

		                    document.getElementById('cancelDelete').onclick = function () {
		                        // 如果用戶取消，隱藏模態框
		                        confirmModal.style.display = 'none';
		                    };
		                });
		            });
		        })
		        .catch(error => {
		            console.error('無法取得願望清單資料:', error);
		        });
		    }

		    // 刪除願望清單項目的函式
		    function deleteWishlistItem(productId) {
		        fetch(`/wishlist/delete/${productId}`, {
		            method: 'DELETE',
		            headers: {
		                'Content-Type': 'application/json'
		            }
		        })
		        .then(response => {
		            if (!response.ok) {
		                throw new Error('Network response was not ok');
		            }
		            // 刪除成功後，重新更新願望清單
		            updateWishlist();
		        })
		        .catch(error => {
		            console.error('刪除心願清單項目時發生錯誤:', error);
		        });
		    }

		    // 頁面載入時，首次更新願望清單
		    updateWishlist();
		});

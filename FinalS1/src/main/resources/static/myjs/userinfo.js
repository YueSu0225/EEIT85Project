document.addEventListener('DOMContentLoaded', function (){
    // 使用 fetch get 從後端拿到json物件
    fetch('/final/userinfo',{
	                method: 'GET',
	                headers: {
	                    'Content-Type': 'application/json'
	                }
	            })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
			console.log(data); //顯示後端傳回的json物件


		    document.getElementById('userEmail').innerHTML = data.userEmail; // 用戶帳號
		    document.getElementById('userInfoAddress').innerHTML = data.userInfoAddress;
			document.getElementById('userInfoAddress2').innerHTML = data.userInfoAddress; // 用戶地址
		    document.getElementById('userInfoBirthday').innerHTML = data.userInfoBirthday; // 用戶生日
		    document.getElementById('userInfoName').innerHTML = data.userInfoName;
			document.getElementById('userInfoName2').innerHTML = data.userInfoName; // 用戶名字
		    document.getElementById('userInfoPhone').innerHTML = data.userInfoPhone; // 用戶手機
			
			
			// 判斷是否為 Google 第三方登錄
			      if (data.isGoogleLogin) {
			          // 如果是 Google 第三方登录，用戶不能修改密碼，隱藏按鈕
			          document.getElementById('changePasswordBtn').style.display = 'none';
			      }
		})
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
		
		
		
		
		
		// 使用 fetch GET 从后端拿到 JSON 物件
		fetch('/final/orderdetails', {
		    method: 'GET',
		    headers: {
		        'Content-Type': 'application/json'
		    }
		})
		.then(response => {
		    if (!response.ok) {
		        throw new Error('Network response was not ok');
		    }
		    return response.json();
		})
		.then(data => {
		    // 检查是否有回传数据
		    if (data && data.orders) {
		        console.log('Received data:', data); // 打印回传的 data
		        displayOrderOverview(data.orders); // 调用函数显示最新订单号
		        displayOrderList(data.orders); // 调用函数显示订单列表
		    } else {
		        console.log('No data received.');
		    }
		})
		.catch(error => {
		    console.error('There was a problem with the fetch operation:', error);
		});

		// 显示最新订单总览
		function displayOrderOverview(orders) {
		    const firstOrderElement = document.getElementById('firstorder');
		    if (orders.length > 0) {
		        const latestOrder = orders[orders.length-1];
		        firstOrderElement.innerHTML = `<h6>最新訂單編號: ${latestOrder.orderNumber}</h6>`;
		    } else {
		        firstOrderElement.innerHTML = '<h6>無近期訂單</h6>';
		    }
		}

		// 显示订单列表并创建下拉菜单
		function displayOrderList(orders) {
		    const orderListElement = document.getElementById('order-list');
		    const noOrderMessage = document.getElementById('noorder');

		    // 清空订单列表
		    orderListElement.innerHTML = '';

		    if (orders.length > 0) {
		        noOrderMessage.style.display = 'none'; // 隐藏 "无近期订单" 信息

		        // 创建下拉选择框
		        const selectElement = document.createElement('select');
		        selectElement.className = 'custom-select'; // 可自定义样式
		        selectElement.innerHTML = `
		            <option value="" disabled selected>請選擇訂單編號</option>
		        `;

		        // 将每个订单号作为下拉选项
		        orders.forEach((order, index) => {
		            const optionElement = document.createElement('option');
		            optionElement.value = index; // 使用订单索引来识别
		            optionElement.text = `訂單編號: ${order.orderNumber}`;
		            selectElement.appendChild(optionElement);
		        });

		        // 当用户选择一个订单时显示订单详情
		        selectElement.addEventListener('change', function() {
		            const selectedOrder = orders[this.value];
		            displayOrderDetails(selectedOrder); // 调用函数显示选择的订单详情
		        });

		        orderListElement.appendChild(selectElement);

		        // 初始化第一个订单详情为空
		        const orderDetailsElement = document.createElement('div');
		        orderDetailsElement.id = 'order-details';
		        orderListElement.appendChild(orderDetailsElement);
		    } else {
		        noOrderMessage.style.display = 'block';
		    }
		}

		// 显示订单详情
		function displayOrderDetails(order) {
		    const orderDetailsElement = document.getElementById('order-details');
		    orderDetailsElement.innerHTML = `
		        <hr>
		        <h6>訂單編號: ${order.orderNumber}</h6>
		        <p>訂單狀態: ${order.orderStatus}</p>
		        <p>總金額: ${order.totalPrice} TWD</p>
		        <h6>商品項目:</h6>
		        ${order.orderItems.map(item => `
		            <p>商品名稱: ${item.detailsName}</p>
		            <p>數量: ${item.detailsQuantity}</p>
		            <p>價格: ${item.detailsPrice} TWD</p>
		        `).join('')}
		        <hr>
		    `;
		}



		});
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
		    if (data) {
		        console.log('Received data:', data); // 打印回传的 data
				displayOrderDetails(data); // 调用函数显示数据

		    } else {
		        console.log('No data received.'); // 如果没有数据
		    }
		})
		.catch(error => {
		    console.error('There was a problem with the fetch operation:', error);
		});

		// 函数：显示订单详细信息
		function displayOrderDetails(data) {
		    const orderListElement = document.getElementById('order-list');
		    const noOrderMessage = document.getElementById('noorder');
		    const firstorder = document.getElementById('firstorder');
		    // 确保获取到 noOrderMessage 元素
		//    if (!noOrderMessage) {
		        // 如果有订单数据
		        if (data.orderItems && data.orderItems.length > 0) {
		            noOrderMessage.style.display = 'none'; // 隐藏 "无近期订单" 信息
					firstorder.innerHTML =  `<h6>訂單編號: ${data.orderNumber}</h6>`;
		            // 创建订单总体信息
		            const orderInfoElement = document.createElement('div');
		            orderInfoElement.className = 'order-info'; // 可添加样式
		            orderInfoElement.innerHTML = `
		                <h6>訂單編號: ${data.orderNumber}</h6>
		                <p>訂單狀態: ${data.orderStatus}</p>
		                <p>總金額: ${data.totalPrice} TWD</p>
		                <hr>
		                <h6>商品項目:</h6>
		            `;
		            orderListElement.appendChild(orderInfoElement);

		            // 循环订单项并生成HTML
		            data.orderItems.forEach(orderItem => {
		                const orderItemElement = document.createElement('div');
		                orderItemElement.className = 'order-item'; // 可添加样式

		                // 构建每个订单项的信息
		                orderItemElement.innerHTML = `
		                    <h6>商品名稱: ${orderItem.detailsName}</h6>
		                    <p>數量: ${orderItem.detailsQuantity}</p>
		                    <p>價格: ${orderItem.detailsPrice} TWD</p>
		                    <hr>
		                `;
		                orderListElement.appendChild(orderItemElement); // 将订单项插入页面
		            });
		        } else {
		            // 如果没有订单，显示默认信息
		            noOrderMessage.style.display = 'block';
		        }
		//    } else {
		 //       console.error('Element with id "no-order-message" not found.');
		 //   }
		}

		

		});

document.getElementById('sessionCheck').addEventListener('click', function(event) {
	event.preventDefault(); // 阻止默認連接行為
	// 使用 fetch 檢查 session 是否有效
	fetch('/final/checksession', {
		method: 'GET',
		credentials: 'include' // 確保攜帶 cookies
	})
		.then(response => {
			if (!response.ok) {				
				// 用戶未登錄，重定向到 SignIn.html
				window.location.href = '/SignIn.html';
				console.log("Error1")
				throw new Error('Error: test()');
			}
			// 用户已登錄，重定向到 Account.html
			window.location.href = '/Account.html';
			return response.json();

		})
		.then(data => {//接收session物件
			console.log(data);
		})

		.catch(error => {
			console.error('Error checking session:', error);
			// 處理錯誤,返回SignIn.html
			window.location.href = '/SignIn.html';
		});

});

document.addEventListener('DOMContentLoaded', function() {
    const emailInput = document.getElementById('email');
    const errorMessage = document.getElementById('errormesg');
	const correctMessage = document.getElementById('correctmesg');
    if (!emailInput || !errorMessage) {
        console.error('Email input or error message element not found.');
        return;
    }

    emailInput.addEventListener('blur', function() {
		//清空信息
		errorMessage.textContent = '';
		correctMessage.textContent = '';
		
        const emailInputValue = this.value;

        // 简单的 email 正则表达式检查
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (!emailPattern.test(emailInputValue)) {
            errorMessage.textContent = '請輸入正確的EMAIL格式';
        } else {            
			// 如果格式正确，继续检查邮箱是否已注册    
			fetch('http://localhost:8080/final/checkaccount', {
			    method: 'POST',
			    headers: {
			        'Content-Type': 'application/json'
			    },
			    body: JSON.stringify({ account: emailInputValue })
			})
			.then(response => {
			    if (!response.ok) {
			        throw new Error('Network response was not ok');
			    }
			    return response.json();
			})
			.then(data => {
				if (data) {
				               errorMessage.textContent = '此 EMAIL 已經註冊過了';
				           } else {
				               correctMessage.textContent = '此 EMAIL 可以使用';
				           }
				       })
			.catch(error => {
			    console.error('Error:', error);
				errorMessage.textContent = '檢查 EMAIL 時出錯，請稍後再試。';
			});
        }
    });
});

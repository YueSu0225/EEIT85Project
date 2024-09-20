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
        }
		else{
			    fetch(`/final/checkaccount?account=${encodeURIComponent(emailInputValue)}`, {
			        method: 'GET',
			        headers: {
			            'Content-Type': 'application/json'
			        }
			    })
			    .then(response => response.json())
			    .then(data => {
			        if (data.exists) {
			            errorMessage.textContent = '此 EMAIL 已經註冊過了'; 
			        } else {
			            correctMessage.textContent = '此信箱可以使用';
			        }
			    })
			    .catch(error => {
			        console.log('Error:', error);
			    });
			}
    });
});

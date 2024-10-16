document.addEventListener('DOMContentLoaded', function() {
    const emailInput = document.getElementById('email');
    const errorMessage = document.getElementById('errormesg');
	const correctMessage = document.getElementById('correctmesg');
	const sendEmailCodeBtn = document.getElementById('sendEmailCode'); // 驗證碼按鈕

    if (!emailInput || !errorMessage|| !sendEmailCodeBtn) {
        console.error('Email input or error message element not found.');
        return;
    }

    emailInput.addEventListener('blur', function() {
		//清空信息
		errorMessage.textContent = '';
		correctMessage.textContent = '';
		sendEmailCodeBtn.disabled = true; // 默認進用發送驗證碼按鈕,等信箱驗證過在啟用

		
        const emailInputValue = this.value;

        //email簡化版驗證格式
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
						sendEmailCodeBtn.disabled = true; // 禁用發送驗證碼按鈕
			        } else {
			            correctMessage.textContent = '此信箱可以使用';
						sendEmailCodeBtn.disabled = false; // 啟用發送驗證碼按鈕
			        }
			    })
			    .catch(error => {
			        console.log('Error:', error);
			    });
			}
    });
});

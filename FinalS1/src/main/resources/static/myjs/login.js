document.addEventListener('DOMContentLoaded', function () {
    const emailInput = document.getElementById('loginaccount');
    const errorAccountMsg = document.getElementById('erroaccountmesg');
    const errorPasswordMsg = document.getElementById('erropasswordmesg');
	const passwordInput = document.getElementById('loginpassword');

    emailInput.addEventListener('blur', function () {
        const email = emailInput.value;
        
        if (email) {
            fetch(`/final/checkaccount?account=${encodeURIComponent(email)}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    errorAccountMsg.textContent = ''; // 清空
                } else {
                    errorAccountMsg.textContent = '此信箱未註冊';
                }
            })
            .catch(error => {
                console.log('Error:', error);
            });
        }
    });
	
	passwordInput.addEventListener('input', function () {
	       errorPasswordMsg.textContent = ''; // 當用戶輸入時清空錯誤訊息
	   });
	
    function login() {
        let account = document.getElementById('loginaccount').value;
        let passwd = document.getElementById('loginpassword').value;
        let data = {
            account: account,
            password: passwd
        };
		
        fetch('/final/login', {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
        .then(response => {
            console.log('Response Status:', response.status);
            return response.json(); // 解析 JSON
        })
		.then(data => {
		    if (data.success) {
		        Swal.fire({
		            icon: 'success',
		            title: '登入成功',
		            text: '歡迎回來!',
		            timer: 2300,  // 设置弹窗显示时间，2秒后跳转
		            showConfirmButton: false  // 不显示确认按钮
		        }).then(() => {
		            window.location.href = '/Home.html';  // 弹窗动画结束后跳转
		        });
		    } else {
		        // 如果登入失败，显示密码错误信息
		        errorPasswordMsg.textContent = '密碼錯誤';
		    }
		})

        .catch(error => {
            console.log('err:', error);
        });
    }
    document.getElementById('loginButton').addEventListener('click', login);
});

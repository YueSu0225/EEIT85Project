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
			            errorMessage.textContent = ''; 
						sendEmailCodeBtn.disabled = false; // 啟用發送驗證碼按鈕
						
			        } else {
			            errorMessage.textContent = '此信箱尚未註冊';
						sendEmailCodeBtn.disabled = true; // 禁用發送驗證碼按鈕
			        }
			    })
			    .catch(error => {
			        console.log('Error:', error);
			    });
			}
    });
});

document.getElementById("sendEmailCode").addEventListener("click", function(event) {
    event.preventDefault(); // 阻止表单的默认提交行为

    // 获取输入的邮箱地址
    const email = document.getElementById("email").value;



    // 发送 POST 请求到后端
    fetch("/final/sendCode", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ email: email }), //將輸入的email放入requset
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("網路出錯. not OK");
        }
        return response.json();
    })
    .then(data => {
        if (data.success) {
			// 顯示輸入框和按鈕
			localStorage.setItem("jwtToken", data.jwtToken); // 将JWT存储在localStorage

			document.getElementById("confirmationSection").style.display = "inline-block";
			alert("驗證碼已發送，請檢察您的信箱。");
			
			// 禁用按钮并设置计时器
			          const sendEmailButton = document.getElementById("sendEmailCode");
			          sendEmailButton.disabled = true; // 禁用按钮
			          let countdown = 60; // 倒數計時60秒

			          const countdownInterval = setInterval(() => {
			              sendEmailButton.innerText = `重新發送(${countdown}s)`; // 更新按鈕文字顯示倒數
			              countdown--;

			              if (countdown < 0) {
			                  clearInterval(countdownInterval); // 清除計時器
			                  sendEmailButton.disabled = false; // 一分钟后启用按钮
			                  sendEmailButton.innerText = "發送驗證碼"; // 重置按鈕文字
			              }
			          }, 1000); // 每秒更新一次
        } else {
            alert("發送失敗: " + data.message);
        }
    })
    .catch(error => {
        alert("發生錯誤: " + error.message);
    });
});
let isEmailVerified = false;  // 全局變量，用於追蹤信箱是否已驗證成功

// 信箱驗證代碼
document.getElementById("confirmCode").addEventListener("click", function() {
       const verificationCode = document.getElementById("verificationCode").value;
	   const resultElement = document.getElementById("result");
       // 獲取JWT token（可以通过其他方式获取，比如从后端响应中获取）
       const jwtToken = localStorage.getItem("jwtToken"); // 假設你將tokenh儲存在localStorage中
		
       if (!verificationCode) {
           document.getElementById("result").innerText = "請輸入驗證碼";
           return;
       }

       // 发送AJAX请求到后端验证验证码
       fetch('/final/verifyCode', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json',
          },
           body: JSON.stringify({ 
			token: jwtToken, // 發送儲存的jwt

			code: verificationCode })
       })
       .then(response => response.json())
       .then(data => {
           if (data.success) {
               resultElement.innerText = "驗證成功！";
			   resultElement.style.color = "green";
			   document.getElementById("email").disabled = true; // 鎖定email輸入框
			   document.getElementById("verificationCode").disabled = true; // 鎖定驗證碼輸入框
			   document.getElementById("confirmCode").disabled = true; // 鎖定確認按鈕
			   // 隱藏發送驗證碼按鈕
			   document.getElementById("sendEmailCode").style.display = "none";
			   newPasswordSection.style.display = 'block';
			   isEmailVerified = true;  // 設置信箱已驗證成功
			   
			   
           } else {
			//console.log("erro1");
			resultElement.innerText = "驗證失敗，請檢查驗證碼。";

           }
       })
       .catch(error => {
           console.error('Error:', error);
		   resultElement.innerText = "發生錯誤，請稍後重試。";

       });
   });

	
   document.querySelector('input[type="submit"]').addEventListener('click', function(event) {
       event.preventDefault(); // 阻止默认提交行为

       const email = document.getElementById('email').value; // 从前面已经輸入的 email 
       const password = document.getElementById('password').value;
       const checkPassword = document.getElementById('checkPassword').value;
       const passwordError = document.getElementById('passwordError');
       const checkPasswordError = document.getElementById('checkPasswordError');

       // 清空錯誤信息
       passwordError.textContent = '';
       checkPasswordError.textContent = '';

	   // 檢查密碼長度
	   if (password.length < 6 || password.length > 20) {
	       passwordError.textContent = '密碼長度需在6至20字元之間';
	       return;
	   }
	   
       // 檢查兩組密碼是否一致
       if (password !== checkPassword) {
           checkPasswordError.textContent = '兩次密碼輸入不一致';
           return;
       }

       // 密碼更新後端請求
       fetch("/final/forgetpassword", {
           method: "POST",
           headers: {
               "Content-Type": "application/json",
           },
           body: JSON.stringify({
               account: email,       // 傳送输入的信箱
               password: password    // 傳送输入的新密码
           })
       })
       .then(response => response.json())
       .then(data => {
           if (data.success) {
               alert("密碼更新成功！");
			   window.location.href = '/SignIn.html';
           } else {
               alert("密碼更新失敗: " + data.message);
           }
       })
       .catch(error => {
           alert("發生錯誤: " + error.message);
       });


   });
   
   
   // 清空錯誤信息的事件監聽
   const passwordInput = document.getElementById('password');
   const checkPasswordInput = document.getElementById('checkPassword');

   passwordInput.addEventListener('input', function() {
       document.getElementById('passwordError').textContent = '';
   });

   checkPasswordInput.addEventListener('input', function() {
       document.getElementById('checkPasswordError').textContent = '';
   });
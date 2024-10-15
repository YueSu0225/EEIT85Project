document.getElementById("sendEmailCode").addEventListener("click", function(event) {
    event.preventDefault(); // 阻止表單的默認提交行為

    // 獲取輸入的信箱
    const email = document.getElementById("email").value;

    // 驗證信箱格式
	if (!validateEmail(email)) {
	     Swal.fire({
	         icon: 'error',
	         title: '請輸入有效信箱',
	         timer: 2000,  // 2秒後關閉
	         showConfirmButton: false
	     });
	     return;
	 }
	 Swal.fire({
	                icon: 'success',
	                title: '驗證碼已發送，請檢查您的信箱。',
	                timer: 3000,  // 3秒後關閉
	                showConfirmButton: false  // 顯示確認按鈕，立即可操作
	            });
    // 發送 POST 請求到後端
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
			localStorage.setItem("jwtToken", data.jwtToken); // 將JWT儲存在localStorage

			document.getElementById("confirmationSection").style.display = "inline-block";

			
			// 禁止按鈕並設置計時器
			          const sendEmailButton = document.getElementById("sendEmailCode");
			          sendEmailButton.disabled = true; // 禁止按鈕
			          let countdown = 60; // 倒數計時60秒

			          const countdownInterval = setInterval(() => {
			              sendEmailButton.innerText = `重新發送(${countdown}s)`; // 更新按鈕文字顯示倒數
			              countdown--;

			              if (countdown < 0) {
			                  clearInterval(countdownInterval); // 清除計時器
			                  sendEmailButton.disabled = false; // 一分鐘後啟用按鈕
			                  sendEmailButton.innerText = "發送驗證碼"; // 重製按鈕文字
			              }
			          }, 1000); // 每秒更新一次
        } else {
			Swal.fire({
			               icon: 'error',
			               title: '發送失敗',
			               text: '請聯繫客服',
			               timer: 3000,
			               showConfirmButton: false
			           });
        }
    })
    .catch(error => {
		Swal.fire({
		           icon: 'error',
		           title: '發送錯誤',
		           text: '請聯繫客服中心',
		           timer: 2000,
		           showConfirmButton: false
		       });
    });
});

// 驗證信箱格式簡易版
function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
}


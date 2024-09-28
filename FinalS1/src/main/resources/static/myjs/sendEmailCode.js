document.getElementById("sendEmailCode").addEventListener("click", function(event) {
    event.preventDefault(); // 阻止表单的默认提交行为

    // 获取输入的邮箱地址
    const email = document.getElementById("email").value;

    // 验证邮箱格式（可选）
    if (!validateEmail(email)) {
        alert("請輸入有效的信箱");
        return;
    }

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

// 验证邮箱格式的简单函数
function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
}


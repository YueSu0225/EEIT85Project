document.addEventListener('DOMContentLoaded', function () {
    // 绑定表单提交事件
    document.querySelector('form').addEventListener('submit', function (event) {
        event.preventDefault(); // 防止默认的表单提交行为

        // 获取表单数据
        const account = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const checkPassword = document.getElementById('checkPassword').value;
        const name = document.querySelector('input[placeholder="姓名"]').value;
        const gender = document.querySelector('input[name="gender"]:checked').value;
        const phone = document.querySelector('input[placeholder="手機"]').value;
        const street = document.getElementById('street').value;
        const birthday = document.querySelector('input[type="date"]').value;
		
        // 表单验证
        if (password.length < 6 || password.length > 20) {
            document.getElementById('passwordError').textContent = '密碼長度需為 6-20 字元';
            return;
        }

        if (password !== checkPassword) {
            document.getElementById('checkPasswordError').textContent = '密碼與確認密碼不一致';
            return;
        }

        // 发送数据到后端
        fetch('/final/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
				account: account,                
                password: password,
                name: name,
				email: account,
                gender: gender,
                phone: phone,
                street: street,
                birthday: birthday
            })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success === "true") {
				console.log("註冊成功1");
                // 注册成功
                alert('註冊成功');
                // 重定向到登录页面或其他页面
                window.location.href = '/Home.html';
            } else {
                // 显示错误信息
				console.log("註冊成功2");
                alert(data.message || '註冊失敗');
            }
        })
        .catch(error => {
            console.error('錯誤:', error);
            alert('註冊失敗');
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    // 绑定表单提交事件
    document.querySelector('form').addEventListener('submit', function (event) {
        event.preventDefault(); // 防止默认的表单提交行为

        // 获取表单数据
        const account = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const checkPassword = document.getElementById('checkPassword').value;
        const name = document.querySelector('input[placeholder="姓名"]').value;
        const phone = document.querySelector('input[placeholder="請輸入手機號碼"]').value;
        const street = document.getElementById('street').value;
        const birthday = document.querySelector('input[type="date"]').value;
		const addressPattern = /.+[路街].+號/; // 至少包含“XX路X號”的格式
		const phonePattern = /^09\d{8}$/; // 驗證手機號碼格式：09開頭，後面8個數字
		
        // 表單驗證
        if (password.length < 6 || password.length > 20) {
            document.getElementById('passwordError').textContent = '密碼長度需為 6-20 字元';
            return;
        }

        if (password !== checkPassword) {
            document.getElementById('checkPasswordError').textContent = '密碼與確認密碼不一致';
            return;
        }
		
		// 地址格式驗證
		if (!addressPattern.test(street)) {
			document.getElementById('streeterrormesg').textContent = '請輸入完整的地址，ex: 中山路1號。';
			return;
		} else {
			document.getElementById('streeterrormesg').textContent = ''; // 清除錯誤信息
		}
		
		// 手機號碼格式驗證
		if (!phonePattern.test(phone)) {
		    document.getElementById('phoneerrormesg').textContent = '請輸入有效的手機號碼，格式：09xxxxxxxx';
		    return;
		} else {
		    document.getElementById('phoneerrormesg').textContent = ''; // 清除错误信息
		}
        // 發送數據到後端
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
                phone: phone,
                street: street,
                birthday: birthday
            })
        })
        .then(response => response.json())
        .then(data => {
			if (data.success) {
			        console.log("註冊成功1");
			        alert('註冊成功');
			        window.location.href = '/Home.html';
			    } else {
			        console.log("註冊失敗2");
			        alert(data.message || '註冊失敗');
			    }
        })
        .catch(error => {
            console.error('錯誤:', error);
            alert('註冊失敗1');
        });
    });
});

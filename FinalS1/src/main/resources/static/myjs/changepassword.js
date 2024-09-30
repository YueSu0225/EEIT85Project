function updatePassword(event) {	
	event.preventDefault();
    // 清除错误信息
    document.getElementById("oldPasswordError").textContent = "";
    document.getElementById("newPasswordError").textContent = "";
    document.getElementById("confirmPasswordError").textContent = "";

    // 获取输入框的值
    var oldPassword = document.getElementById("oldPassword").value;
    var newPassword = document.getElementById("newPassword").value;
    var confirmPassword = document.getElementById("confirmPassword").value;

    // 检查前端简单的验证
    if (newPassword !== confirmPassword) {
        document.getElementById("confirmPasswordError").textContent = "新密碼不一致";
        return;
    }

    // 构建请求数据
    var data = {
        oldPassword: oldPassword,
        password: newPassword
    };

    // 发送 POST 请求到后端
    fetch('/final/changepassword', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success == true) {
            alert("密碼修改成功");
            $('#changePasswordModal').modal('hide');
        } else if (data.success == false) {
            document.getElementById("oldPasswordError").textContent = "舊密碼輸入錯誤";
        } else {
            alert("密碼修改失敗: " + result.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('提交时出现问题，请稍后再试');
    });
}
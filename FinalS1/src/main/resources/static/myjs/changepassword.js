function updatePassword(event) {	
	event.preventDefault();
    // 清除錯誤信息
    document.getElementById("oldPasswordError").textContent = "";
    document.getElementById("newPasswordError").textContent = "";
    document.getElementById("confirmPasswordError").textContent = "";

    // 獲取輸入值
    var oldPassword = document.getElementById("oldPassword").value;
    var newPassword = document.getElementById("newPassword").value;
    var confirmPassword = document.getElementById("confirmPassword").value;

    // 檢查前端簡化版驗證
    if (newPassword !== confirmPassword) {
        document.getElementById("confirmPasswordError").textContent = "新密碼不一致";
        return;
    }

   
    var data = {
        oldPassword: oldPassword,
        password: newPassword
    };

    fetch('/final/changepassword', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
	.then(data => {
	    if (data.success === true) {
	        Swal.fire({
	            icon: 'success',
	            title: '密碼修改成功',
	            timer: 2000,  // 2秒後自動關閉
	            showConfirmButton: false  // 不顯示確認按鈕
	        }).then(() => {
	            $('#changePasswordModal').modal('hide');
	        });
	    } else if (data.success === false) {
	        // 舊密碼錯誤
	        Swal.fire({
	            icon: 'error',
	            title: '舊密碼輸入錯誤',
	            timer: 2000,  // 2秒後自動關閉
	            showConfirmButton: false  // 不顯示確認按鈕
	        });
	    } else {
	        // 密碼修改失敗
	        Swal.fire({
	            icon: 'error',
	            title: '密碼修改失敗',
	            text: '錯誤: ' + data.message,
	            timer: 2000,  // 2秒後自動關閉
	            showConfirmButton: false  // 不顯示確認按鈕
	        });
	    }
	})

    .catch(error => {
        console.error('Error:', error);
        alert('提交時出現問題,稍後再試試');
    });
}
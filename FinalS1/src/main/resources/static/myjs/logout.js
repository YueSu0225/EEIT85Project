
document.getElementById('logoutButton').addEventListener('click', function(event) {
    event.preventDefault(); // 防止默認行為
	
	console.log("按了");
    fetch('/final/logout', {
        method: 'POST',
        credentials: 'include' // 確保攜帶 cookies
    })
	.then(response => {
	    if (response.ok) {
	        Swal.fire({
	            icon: 'success',
	            title: '登出成功',
	            text: '您已成功登出。',
	            timer: 2000,  // 顯示2秒
	            showConfirmButton: false  // 不顯示確認按鈕
	        }).then(() => {
	            // 弹窗顯示后重定向到登入頁
	            window.location.href = '/SignIn.html';
	        });
	    } else {
	        console.error('登出失敗');
	    }
	})
    .catch(error => {
        console.error('Error during logout:', error);
    });
});
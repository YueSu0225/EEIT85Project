
document.getElementById('logoutButton').addEventListener('click', function(event) {
    event.preventDefault(); // 防止默認行為
	
	console.log("按了");
    fetch('/final/logout', {
        method: 'POST',
        credentials: 'include' // 確保攜帶 cookies
    })
    .then(response => {
        if (response.ok) {
			alert('您已成功登出');
            // 登出成功，重定向到登陸頁面
            window.location.href = '/SignIn.html';
        } else {
            // 失敗
            console.error('Logout failed');
        }
    })
    .catch(error => {
        console.error('Error during logout:', error);
    });
});
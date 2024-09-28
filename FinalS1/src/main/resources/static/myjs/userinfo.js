document.addEventListener('DOMContentLoaded', function (){
    // 使用 fetch get 從後端拿到json物件
    fetch('/final/userinfo',{
	                method: 'GET',
	                headers: {
	                    'Content-Type': 'application/json'
	                }
	            })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
			//console.log(data); 顯示後端傳回的json物件


		    document.getElementById('userEmail').innerHTML = data.userEmail; // 用戶帳號
		    document.getElementById('userInfoAddress').innerHTML = data.userInfoAddress;
			document.getElementById('userInfoAddress2').innerHTML = data.userInfoAddress; // 用戶地址
		    document.getElementById('userInfoBirthday').innerHTML = data.userInfoBirthday; // 用戶生日
		    document.getElementById('userInfoName').innerHTML = data.userInfoName;
			document.getElementById('userInfoName2').innerHTML = data.userInfoName; // 用戶名字
		    document.getElementById('userInfoPhone').innerHTML = data.userInfoPhone; // 用戶手機
		})
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
		
		

		});
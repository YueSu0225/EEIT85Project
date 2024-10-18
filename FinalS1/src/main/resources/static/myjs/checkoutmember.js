document.addEventListener('DOMContentLoaded', function () {
    // 監聽“同會員資料”勾選框的事件
    document.getElementById('checkboxmember').addEventListener('change', function () {
        if (this.checked) {
            // 如果勾選，則 fetch 到後端拿資料
            fetch('/final/userinfo', {
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
                
                document.getElementById('userEmail').value = data.userEmail; // 用戶信箱
                document.getElementById('userInfoName').value = data.userInfoName; // 用戶名字
                document.getElementById('userInfoPhone').value = data.userInfoPhone; // 用戶手機
                document.getElementById('userInfoAddress').value = data.userInfoAddress; // 用戶地址

                // 取地址的前三字做為城市
                const city = data.userInfoAddress.substring(0, 3);
                document.getElementById('towncity').value = city; // 城市
				// 鎖定輸入框
				lockFields(true);
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
        } else {
            // 如果未勾選，清空表單資料
            document.getElementById('userEmail').value = '';
            document.getElementById('userInfoName').value = '';
            document.getElementById('userInfoPhone').value = '';
            document.getElementById('userInfoAddress').value = '';
            document.getElementById('towncity').value = '';
			// 啟用輸入框
			lockFields(false);
        }
    });
         
			 // 定義鎖定,啟用輸入框的函數
			  function lockFields(lock) {
			      const fields = ['userEmail', 'userInfoName', 'userInfoPhone', 'userInfoAddress', 'towncity', 'country'];
			      fields.forEach(id => {
			          document.getElementById(id).disabled = lock;
					  
			      });
			  }
});

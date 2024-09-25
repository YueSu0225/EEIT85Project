function showUpdateForm() {
    // 抓取資料
    const email = document.getElementById('userEmail').innerText; // 信箱
    const name = document.getElementById('userInfoName2').innerText; // 姓名
    const address = document.getElementById('userInfoAddress2').innerText; // 地址
    const phone = document.getElementById('userInfoPhone').innerText; // 手機
    const birthday = document.getElementById('userInfoBirthday').innerText; // 生日

    // 設置表單的值
    document.getElementById('updateEmail').value = email; // 設置信箱
    document.getElementById('updateName').value = name; // 設置姓名
    document.getElementById('updatePhone').value = phone; // 設置電話
    document.getElementById('updateAddr').value = address; // 設置地址
    document.getElementById('updateBirthday').value = birthday; // 設置生日

    // 顯示模態框
    $('#updateModal').modal('show');
}

function updateMember(event) {
    event.preventDefault(); // 阻止默認提交行為
	
	const userInfo = {       
	    name: document.getElementById('updateName').value,
	    address: document.getElementById('updateAddr').value,
	    phone: document.getElementById('updatePhone').value,
	    birthday: document.getElementById('updateBirthday').value
	};
	const phoneError = document.getElementById('phoneerrormesg');
	const errorMessageSpan = document.getElementById('streeterrormesg');
	phoneError.textContent = '';
	errorMessageSpan.textContent = '';
	
	// 驗證手機號碼
	    
	    
	    
	// 驗證地址
	   const addressPattern = /^(?=.*?[縣市])(?=.*?[區鄉鎮])(?=.*?[路街巷弄]\d+號).+$/;
	if (!addressPattern.test(userInfo.address)) {
	    errorMessageSpan.textContent = '請輸入有效的地址，例如：台北市中正區中山路1號';
	    return; // 結束函數，不提交
	}
		// 驗證手機號碼
		const phonePattern = /^09\d{8}$/;
		if (!phonePattern.test(userInfo.phone)) {
		    phoneError.textContent = '請輸入有效的手機號碼，格式：0912345678';
		    return; // 結束函數，不提交
		}


	


    // 提交更新邏輯
    fetch('/final/updateUserInfo', {
        method: 'PUT', // 使用 PUT 方法
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userInfo)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("資料已提交！");
            console.log('用戶資料更新成功:', data);
            $('#updateModal').modal('hide');
            // 更新顯示的用戶資料（可選）
            document.getElementById('userInfoName2').innerText = userInfo.name;
            document.getElementById('userInfoAddress2').innerText = userInfo.address;
            document.getElementById('userInfoPhone').innerText = userInfo.phone;
            document.getElementById('userInfoBirthday').innerText = userInfo.birthday;
        } else {
            console.error('更新用戶資料時發生錯誤:', data.message);
            alert('更新失敗: ' + data.message);
        }
    })
    .catch(error => {
        console.error('Fetch 錯誤:', error);
        alert('更新失敗: ' + error.message);
    });
}
// 在每個輸入框中添加事件監聽器，當用戶輸入時清除錯誤信息
document.getElementById('updatePhone').addEventListener('input', function() {
    document.getElementById('phoneerrormesg').textContent = '';
});

document.getElementById('updateAddr').addEventListener('input', function() {
    document.getElementById('streeterrormesg').textContent = '';
});

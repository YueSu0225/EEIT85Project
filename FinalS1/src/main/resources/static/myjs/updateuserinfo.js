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
	const nameError = document.getElementById('nameerrormesg');
	const errorMessageSpan = document.getElementById('streeterrormesg');
	const birthdayError = document.getElementById('birthdayerrormesg'); // 確保有這個元素
	birthdayError.textContent = ''; // 清除之前的錯誤信息
	phoneError.textContent = '';
	nameError.textContent = ''; 
	errorMessageSpan.textContent = '';
	
	// 驗證姓名
	if (userInfo.name.length < 3) { 
	    nameError.textContent = '姓名至少三個字！';
	    return; // 結束函數，不提交
	}
    
	// 驗證地址
	//const addressPattern = /^(?:(.+?(?:縣|市|區|鄉|鎮))\s+)?(.+?(路|街|巷|弄))\s*(\d+)?(?:巷\s*(\d+))?\s*(?:弄\s*(\d+))?\s*(\d+(?:之\d+)?)?(?:號(?:之(\d+)?)?)?\s*(\d+(?:樓(?:-\d+)?|\d*)?)?\s*(\d*)?$/;
	const addressPattern = /^(?:(.+?(?:縣|市|區|鄉|鎮))\s+)?(.+?(路|街|巷|弄))\s*(\d+)?(?:巷\s*(\d+))?\s*(?:弄\s*(\d+))?\s*(\d+(?:之\d+)?)號(?:之(\d+)?)?\s*(\d+(?:樓(?:-\d+)?|\d*)?)?\s*(\d*)?$/;

	
	if (!addressPattern.test(userInfo.address)) {
	    errorMessageSpan.textContent = '請輸入有效的地址("-"請使用"之")，例如：台北市中正區中山路1之1號';
		
		    console.log(userInfo.address);
		
	    return; // 結束函數，不提交
	}
		// 驗證手機號碼
		const phonePattern = /^09\d{8}$/;
		if (!phonePattern.test(userInfo.phone)) {
		    phoneError.textContent = '請輸入有效的手機號碼，格式：0912345678';
		    return; // 結束函數，不提交
		}

		if (!userInfo.birthday) {
			birthdayError.textContent = '生日不能為空！';
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
	        Swal.fire({
	            icon: 'success',
	            title: '提交成功！',
	            text: '會員信息更新成功',
	            timer: 2000,  // 2秒鐘後自動關閉
	            showConfirmButton: false  // 不顯示確認按鈕
	        }).then(() => {
	            console.log('用戶資料更新成功:', data);
	            $('#updateModal').modal('hide');
	            // 更新顯示的用戶資料（可選）
	            document.getElementById('userInfoName2').innerText = userInfo.name;
	            document.getElementById('userInfoAddress2').innerText = userInfo.address;
	            document.getElementById('userInfoPhone').innerText = userInfo.phone;
	            document.getElementById('userInfoBirthday').innerText = userInfo.birthday;
	        });
	    } else {
	        console.error('更新用戶資料時發生錯誤:', data.message);
	        Swal.fire({
	            icon: 'error',
	            title: '更新失敗',
	            text: '錯誤: ' + data.message,
	            timer: 2000,  // 2秒鐘後自動關閉
	            showConfirmButton: false  // 不顯示確認按鈕
	        });
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
document.getElementById('updateName').addEventListener('input', function() {
    document.getElementById('nameerrormesg').textContent = ''; 
});
document.getElementById('updateBirthday').addEventListener('input', function() {
    document.getElementById('birthdayerrormesg').textContent = ''; 
});
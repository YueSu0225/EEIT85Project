
let isEmailVerified = false;  // 全局變量，用於追蹤信箱是否已驗證成功

// 信箱驗證代碼
document.getElementById("confirmCode").addEventListener("click", function() {
       const verificationCode = document.getElementById("verificationCode").value;
	   const resultElement = document.getElementById("result");
       // 獲取JWT token 從localStorage中
       const jwtToken = localStorage.getItem("jwtToken"); // 將tokenh儲存在localStorage中
		
       if (!verificationCode) {
           document.getElementById("result").innerText = "請輸入驗證碼";
           return;
       }

       // 發送請求到後端驗證
       fetch('/final/verifyCode', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json',
          },
           body: JSON.stringify({ 
			token: jwtToken, // 發送儲存的jwt

			code: verificationCode })
       })
       .then(response => response.json())
       .then(data => {
           if (data.success) {
               resultElement.innerText = "驗證成功！";
			   resultElement.style.color = "green";
			   document.getElementById("email").disabled = true; // 鎖定email輸入框
			   document.getElementById("verificationCode").disabled = true; // 鎖定驗證碼輸入框
			   document.getElementById("confirmCode").disabled = true; // 鎖定確認按鈕
			   // 隱藏發送驗證碼按鈕
			   document.getElementById("sendEmailCode").style.display = "none";
			   isEmailVerified = true;  // 設置信箱已驗證成功
           } else {
			resultElement.innerText = "驗證失敗，請檢查驗證碼。";

           }
       })
       .catch(error => {
           console.error('Error:', error);
		   resultElement.innerText = "發生錯誤，請稍後重試。";

       });
   });


document.addEventListener('DOMContentLoaded', function () {
    // 保定提交表單事件
    document.querySelector('form').addEventListener('submit', function (event) {
        event.preventDefault(); // 防止默認的表單提交行為
		
		if (!isEmailVerified) { // 檢查信箱是否已驗證
			Swal.fire({
			    icon: 'error',
			    title: '信箱未驗證',
			    text: '請先完成信箱驗證',
			    showConfirmButton: true,  // 顯示確認按鈕
			    timer: 2000  // 2秒後自動關閉
			});
		    return;  // 阻止註冊表單提交
		}
		
        // 獲取表單數據
        const account = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const checkPassword = document.getElementById('checkPassword').value;
        const name = document.getElementById('name').value;
        const phone = document.querySelector('input[placeholder="請輸入手機號碼"]').value;
        const street = document.getElementById('street').value;
        const birthday = document.querySelector('input[type="date"]').value;
		const addressPattern = /^(?:(.+?(?:縣|市|區|鄉|鎮))\s+)?(.+?(路|街|巷|弄|段))\s*(\d+)?(?:巷\s*(\d+))?\s*(?:弄\s*(\d+))?\s*(\d+(?:之\d+)?)號(?:之(\d+)?)?\s*(\d+(?:樓(?:-\d+)?|\d*)?)?\s*(\d*)?$/;

		const phonePattern = /^09\d{8}$/; // 驗證手機號碼格式：09開頭，後面8個數字
		
        // 密碼驗證格式
        if (password.length < 6 || password.length > 20) {
            document.getElementById('passwordError').textContent = '密碼長度需為 6-20 字元';
            return;
        }

        if (password !== checkPassword) {
            document.getElementById('checkPasswordError').textContent = '密碼與確認密碼不一致';
            return;
        }
		//姓名驗證格式
		if (name.length < 3) {
			document.getElementById('nameErrormesg').textContent = '姓名至少三個字'; // 格式不對,顯示錯誤信息
			return;
		} else {
		   document.getElementById('nameErrormesg').textContent = ''; // 長度符合,清空錯誤信息
		}
		
		
		// 地址格式驗證
		if (!addressPattern.test(street)) {
			document.getElementById('streeterrormesg').textContent = '請輸入有效的地址("-"請使用"之")，例如：台北市中正區中山路1之1號';
			return;
		} else {
			document.getElementById('streeterrormesg').textContent = ''; // 清除錯誤信息
		}
		
		// 手機號碼格式驗證
		if (!phonePattern.test(phone)) {
		    document.getElementById('phoneerrormesg').textContent = '請輸入有效的手機號碼，格式：09xxxxxxxx';
		    return;
		} else {
		    document.getElementById('phoneerrormesg').textContent = ''; // 清除錯誤信息
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
		        Swal.fire({
		            icon: 'success',
		            title: '註冊成功',
		            text: '您已成功註冊',
		            timer: 2000,  // 2秒後自動關閉
		            showConfirmButton: false
		        }).then(() => {
		            window.location.href = '/Home.html';  // 確保在提示框後才跳轉
		        });
		    } else {
		        console.log("註冊失敗2");
		        Swal.fire({
		            icon: 'error',
		            title: '註冊失敗',
		            text: data.message || '註冊失敗',
		            timer: 2000,  // 2秒後自動關閉
		            showConfirmButton: false
		        });
		    }
		})
		.catch(error => {
		    console.error('錯誤:', error);
		    Swal.fire({
		        icon: 'error',
		        title: '註冊失敗',
		        text: '發生錯誤，請稍後再試',
		        timer: 2000,  // 2秒後自動關閉
		        showConfirmButton: false
		    });
		});


    });
});

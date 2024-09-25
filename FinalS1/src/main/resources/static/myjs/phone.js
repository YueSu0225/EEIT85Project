const phoneInput = document.getElementById('phone');
const phoneError = document.getElementById('phoneerrormesg');

// 定義手機號碼表達
const phonePattern = /^09\d{8}$/;

phoneInput.addEventListener('blur', function() {
	const phoneValue = phoneInput.value;

	// 验证手机号码格式
	if (phonePattern.test(phoneValue)) {
		phoneError.textContent = ''; // 格式正確,清空錯誤信息
	} else {
		phoneError.textContent = '請輸入有效的手機號碼，格式：09xxxxxxxx'; // 格式不正確,顯示錯誤信息
	}
});
const phoneInput = document.getElementById('phone');
const phoneError = document.getElementById('phoneerrormesg');

// 定义手机号码正则表达式
const phonePattern = /^09\d{8}$/;

phoneInput.addEventListener('blur', function() {
	const phoneValue = phoneInput.value;

	// 验证手机号码格式
	if (phonePattern.test(phoneValue)) {
		phoneError.textContent = ''; // 格式正确，清空错误信息
	} else {
		phoneError.textContent = '請輸入有效的手機號碼，格式：09xxxxxxxx'; // 格式不正确，显示错误信息
	}
});
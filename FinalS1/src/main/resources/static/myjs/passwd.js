document.addEventListener('DOMContentLoaded', function() {
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('checkPassword');
    const passwordError = document.getElementById('passwordError');
    const confirmPasswordError = document.getElementById('checkPasswordError');

    if (!passwordInput || !confirmPasswordInput || !passwordError || !confirmPasswordError) {
        console.error('One or more elements not found.');
        return;
    }

    function validatePassword() {
        const passwordValue = passwordInput.value;
        if (passwordValue.length < 6 || passwordValue.length > 20) {
            passwordError.textContent = '請輸入6至20字元的密碼';
        } else {
            passwordError.textContent = '';
        }
    }

    function validateConfirmPassword() {
        const passwordValue = passwordInput.value;
        const confirmPasswordValue = confirmPasswordInput.value;
        if (passwordValue !== confirmPasswordValue) {
            confirmPasswordError.textContent = '請確認輸入密碼相同';
        } else {
            confirmPasswordError.textContent = '';
        }
    }

    // Validate password length on blur
    passwordInput.addEventListener('blur', validatePassword);

    // Validate confirm password on blur
    confirmPasswordInput.addEventListener('blur', validateConfirmPassword);

    // Validate confirm password when password field changes
    passwordInput.addEventListener('input', validateConfirmPassword);
});

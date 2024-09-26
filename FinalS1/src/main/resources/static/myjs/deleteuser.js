document.getElementById('deleteuser').addEventListener('click', function() {
    // 彈出確認對話框
    const confirmed = confirm('您確定要刪除帳號嗎?');
    
    if (confirmed) {
        // 發送刪除請求
        fetch('/final/deleteUser', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok) {
                alert('帳號已成功刪除');
                window.location.href = '/Home.html'; 
            } else {
                alert('刪除帳號失敗');
            }
        })
        .catch(error => {
            console.error('刪除過程中出錯:', error);
            alert('刪除過程中發生錯誤');
        });
    }
});

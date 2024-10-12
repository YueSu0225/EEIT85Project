document.getElementById('deleteuser').addEventListener('click', function() {
    // 彈出確認對話框
    Swal.fire({
        title: '您確定要刪除帳號嗎?',
        icon: 'warning',
        showCancelButton: true,  // 顯示取消按鈕
        confirmButtonText: '刪除',
        cancelButtonText: '取消',
        reverseButtons: true  // 按鈕順序反轉
    }).then((result) => {
        if (result.isConfirmed) {
            // 用戶確認刪除
            fetch('/final/deleteUser', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => {
                if (response.ok) {
                    Swal.fire({
                        icon: 'success',
                        title: '帳號已成功刪除',
                        timer: 2000,  // 彈窗2秒後自動關閉
                        showConfirmButton: false
                    }).then(() => {
                        window.location.href = '/Home.html';  // 刪除成功後跳轉
                    });
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: '刪除帳號失敗',
                        timer: 2000,  // 彈窗2秒後自動關閉
                        showConfirmButton: false
                    });
                }
            })
            .catch(error => {
                console.error('刪除過程中出錯:', error);
                Swal.fire({
                    icon: 'error',
                    title: '刪除過程中發生錯誤',
                    timer: 2000,
                    showConfirmButton: false
                });
            });
        }
    });
});

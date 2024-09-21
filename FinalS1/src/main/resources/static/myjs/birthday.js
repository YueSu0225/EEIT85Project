
    // 獲取當前日期
    const today = new Date();
    const yyyy = today.getFullYear();
    const mm = String(today.getMonth() + 1).padStart(2, '0'); // 月份從0開始，所以加1
    const dd = String(today.getDate()).padStart(2, '0');

    // 設置預設值為今天的日期
    document.getElementById('birthday').value = `${yyyy}-${mm}-${dd}`;

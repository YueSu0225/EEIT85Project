
// 頁面加載時隱藏下拉菜單
document.getElementById('userDropdown').style.display = 'none';

// 檢查 session 狀態
function checkSession() {
  fetch('/final/checksession', {
    method: 'GET',
    credentials: 'include' // 確保攜帶 cookies
  })
  .then(response => {
    if (!response.ok) {
      // 如果沒有有效 session，隱藏下拉菜單
      document.getElementById('userDropdown').style.display = 'none';
      return;
    }
    // 如果有有效 session，顯示下拉菜單 1 秒後自動隱藏
    showDropdownTemporarily();
  })
  .catch(error => {
    console.error('Error checking session:', error);
    // 出错时也隐藏下拉菜单
    document.getElementById('userDropdown').style.display = 'none';
  });
}

// 顯示下拉菜單 1 秒後自動隱藏
function showDropdownTemporarily() {
  const dropdownMenu = document.getElementById('userDropdown');
  dropdownMenu.style.display = 'block';  // 顯示下拉菜單
  setTimeout(() => {
    // 1 秒後如果鼠標不在下拉菜單或圖標上，則隱藏
    if (!dropdownMenu.matches(':hover') && !document.getElementById('sessionCheck').matches(':hover')) {
      dropdownMenu.style.display = 'none'; // 隱藏下拉菜單
    }
  }, 1000);
}

// 監聽圖標點擊事件
document.getElementById('sessionCheck').addEventListener('click', function(event) {
  event.preventDefault(); // 阻止默認連接行為
  fetch('/final/checksession', {
    method: 'GET',
    credentials: 'include' // 確保攜帶 cookies
  })
  .then(response => {
    if (!response.ok) {
      // 用户未登录，重定向到 SignIn.html
      window.location.href = '/SignIn.html';
      return;
    }
    // 用户已登录，直接跳转到会员中心
    window.location.href = '/Account.html';
  })
  .catch(error => {
    console.error('Error checking session:', error);
    // 出错时，重定向到登录页面
    window.location.href = '/SignIn.html';
  });
});

// 當鼠標懸停在圖標或下拉菜單時保持顯示
document.getElementById('sessionCheck').addEventListener('mouseenter', () => {
  document.getElementById('userDropdown').style.display = 'block';
});

// 當鼠標懸停在下拉菜單上時保持顯示
document.getElementById('userDropdown').addEventListener('mouseenter', () => {
  document.getElementById('userDropdown').style.display = 'block';
});

// 當鼠標移出圖標時隱藏下拉菜單
document.getElementById('sessionCheck').addEventListener('mouseleave', () => {
  const dropdownMenu = document.getElementById('userDropdown');
  setTimeout(() => {
    // 如果鼠標不在下拉菜單上，則隱藏下拉菜單
    if (!dropdownMenu.matches(':hover')) {
      dropdownMenu.style.display = 'none';
    }
  }, 100); // 確保有一定的延遲以便用戶有時間移動到下拉菜單
});

// 當鼠標移出下拉菜單時隱藏下拉菜單
document.getElementById('userDropdown').addEventListener('mouseleave', () => {
  document.getElementById('userDropdown').style.display = 'none';
});

// 頁面加載時自動檢查 session 狀態
document.addEventListener('DOMContentLoaded', checkSession);

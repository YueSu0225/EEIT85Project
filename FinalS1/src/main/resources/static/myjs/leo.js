// 監聽圖標點擊事件
	document.getElementById('cart-icon').addEventListener('click', function(event) {
	  event.preventDefault(); // 阻止默認連接行為
	  fetch('/final/checksession', {
	    method: 'GET',
	    credentials: 'include' // 確保攜帶 cookies
	  })
	  .then(response => {
	    if (response.status === 401) {
		  showModal();
	      return;
	    }
	    window.location.href = '/cart.html';
	  })
	  .catch(error => {
	    console.error('Error checking session:', error);
	    window.location.href = '/Home.html';
	  });
	});
	
	function showModal() {
	        const modal = document.getElementById('loginModal');
	        modal.style.display = 'flex';  
	    }

    
    function closeModal() {
        const modal = document.getElementById('loginModal');
        modal.style.display = 'none';
    }

    
    document.getElementById('loginButton').addEventListener('click', function() {
        window.location.href = '/Signin.html';  // 跳轉到登入頁面
    });

    document.getElementById('closeModalButton').addEventListener('click', closeModal);
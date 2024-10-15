let socket = null; // 全域變量
let isChatOpen = false; // 聊天已關閉
let userUUID = null; 

// 檢查 UUID
function checkSession() {
    if (isChatOpen) {
        console.log("Chat is already open.");
        return; // 如果聊天已打开，直接返回
    }

    console.log('Checking session...');
    fetch('/final/checksession', {
        method: 'GET',
        credentials: 'include' // 確保攜帶cookies
    })
	.then(response => {
	    if (response.status === 401) {
	        showModal();  // 如果是未授權，顯示登入模態框
	        return; // 中斷後續處理
	    }
	    return response.json();  // 只有在 status 不是 401 的情況下繼續處理
	})
    .then(data => {
        userUUID = data.userUUID;
        if (!userUUID) {
			showModal();
            throw new Error('User UUID is undefined');
        }
        openChat(); // 連接 WebSocket
		console.log("Attempting to open chat...");
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
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
// 打開聊天視窗并連接 WebSocket
function openChat() {
    if (isChatOpen) {
        return; // 如果已經打開，直接返回
    }

    $("#chatWindow").show();  // show聊天窗口
    $(".btn-primary").hide(); // 隱藏按鈕
	// 確保只應用於聊天窗口的外層容器
	  $("#chatWindow").draggable().resizable();  // 使窗口可拖動可縮放
	console.log("Connecting WebSocket with UUID:", userUUID);

    // 創建 WebSocket (如果使用ngrok要使用wss://xxxxxxx.ngrok-free.app/)
    socket = new WebSocket("ws://localhost:8080/chat?uuid=" + userUUID);

    // 監控 WebSocket 連接狀態
    socket.onopen = function() {
        console.log("WebSocket connection established.");
        isChatOpen = true; // 聊天視窗已打開
    };

    socket.onmessage = function(event) {
        var chatBox = document.getElementById("chatBox");
		var message = event.data;
console.log(message);

		   // 所有收到的消息都是來自管理員
		   chatBox.innerHTML += "<p class='admin-message'>客服: " + message + "</p>"; // 客服消息靠右

		   chatBox.scrollTop = chatBox.scrollHeight; // 保持聊天內容最底部
    };

    socket.onclose = function() {
        console.log("WebSocket connection closed.");
        isChatOpen = false; // 聊天已關閉
    };

    socket.onerror = function(event) {
        console.error("WebSocket error observed:", event);
    };
}

function sendMessage() {
	var input = document.getElementById("messageInput");
	var message = input.value;
	if (message.trim() !== "") {
	    var formattedMessage = userUUID + ":" + message;
	    console.log("Sending message:", formattedMessage);

	    // 在發送前，直接在前端顯示“我: 消息内容”
	    var chatBox = document.getElementById("chatBox");
	    chatBox.innerHTML += "<p class='user-message'>我: " + message + "</p>"; // 使用者消息靠左
	    chatBox.scrollTop = chatBox.scrollHeight; // 保持聊天内容最底部

	    // 發送消息給後台
	    socket.send(formattedMessage); 
	    input.value = ""; // 清空输入框
	} else {
	    console.log("Chat is not open or message is empty.");
	}
}

function closeChat() {
    $("#chatWindow").hide(); // 隱藏聊天視窗
    $(".btn-primary").show(); // 顯示紐
    if (socket) {
        socket.close(); // 關閉 WebSocket 連接
        isChatOpen = false; // 聊天已關閉

    }
}
// 鍵盤事件：按下 Enter 發送消息
document.getElementById("messageInput").addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        event.preventDefault();  // 防止換行
        sendMessage();  // 發送消息
    }
});
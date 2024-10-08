// 检查会话并获取用户 UUID
function checkSession() {
	console.log('Checking session...');
    fetch('/final/checksession', {
        method: 'GET',
        credentials: 'include' // 确保携带 cookies
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Session check failed');
        }
        return response.json(); // 假设后端返回JSON格式的响应
    })
    .then(data => {
        // 假设 UUID 在 data 中，键名为 userUUID
        var uuid = data.userUUID;
		console.log("User UUID:", uuid);
        // 连接 WebSocket
        openChat(uuid);
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

// 打开聊天窗口并连接 WebSocket
function openChat(uuid) {
    $("#chatWindow").show();  // 显示聊天窗口
    $(".btn-primary").hide(); // 隐藏圆形按钮
    $("#chatWindow").draggable().resizable();  // 使窗口可拖动和可缩放

	var socket = new WebSocket("ws://localhost:8080/chat?uuid=" + uuid);
	// 监控 WebSocket 连接状态
	    socket.onopen = function() {
	        console.log("WebSocket connection established.");
	    };

	    socket.onclose = function() {
	        console.log("WebSocket connection closed.");
	    };
		socket.onerror = function(event) {
		    console.error("WebSocket error observed:", event);
		};

	    socket.onerror = function(error) {
	        console.error("WebSocket error: ", error);
	    };

    socket.onmessage = function(event) {
        var chatBox = document.getElementById("chatBox");
        chatBox.innerHTML += "<p>" + event.data + "</p>";
        chatBox.scrollTop = chatBox.scrollHeight;  // 保持滚动到底部
    };
}

// 关闭聊天窗口
function closeChat() {
    $("#chatWindow").hide();    // 隐藏聊天窗口
    $(".btn-primary").show();   // 显示按钮
}

// 发送消息
function sendMessage() {
    var input = document.getElementById("messageInput");
    var message = input.value;
    if (message.trim() !== "") {
        socket.send(message);
        input.value = "";  // 清空输入框
    }
}

// 例如，在打开聊天时调用 checkSession
document.getElementById("chatButton").onclick = function() {
    checkSession();
};

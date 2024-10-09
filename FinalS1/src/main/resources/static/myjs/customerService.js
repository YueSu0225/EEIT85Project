let socket = null; // 声明全局变量
let isChatOpen = false; // 标记聊天窗口是否打开
let userUUID = null; // 用户 UUID

// 检查会话并获取用户 UUID
function checkSession() {
    if (isChatOpen) {
        console.log("Chat is already open.");
        return; // 如果聊天已打开，直接返回
    }

    console.log('Checking session...');
    fetch('/final/checksession', {
        method: 'GET',
        credentials: 'include' // 确保携带 cookies
    })
    .then(response => response.json())
    .then(data => {
        userUUID = data.userUUID;
        if (!userUUID) {
            throw new Error('User UUID is undefined');
        }
        openChat(); // 连接 WebSocket
		console.log("Attempting to open chat...");
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

// 打开聊天窗口并连接 WebSocket
function openChat() {
    if (isChatOpen) {
        return; // 如果已经打开，直接返回
    }

    $("#chatWindow").show();  // 显示聊天窗口
    $(".btn-primary").hide(); // 隐藏圆形按钮
	console.log("Connecting WebSocket with UUID:", userUUID);

    // 创建 WebSocket 连接
    socket = new WebSocket("ws://localhost:8080/chat?uuid=" + userUUID);

    // 监控 WebSocket 连接状态
    socket.onopen = function() {
        console.log("WebSocket connection established.");
        isChatOpen = true; // 标记聊天已打开
    };

    socket.onmessage = function(event) {
        var chatBox = document.getElementById("chatBox");
        chatBox.innerHTML += "<p>" + event.data + "</p>"; // 显示收到的消息
        chatBox.scrollTop = chatBox.scrollHeight; // 保持滚动到底部
    };

    socket.onclose = function() {
        console.log("WebSocket connection closed.");
        isChatOpen = false; // 标记聊天已关闭
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
		       console.log("Sending message:", formattedMessage); // 打
        socket.send(userUUID + ":" + message); // 发送给管理员
        input.value = ""; // 清空输入框
    }		else {
		        console.log("Chat is not open or message is empty.");
		    }
}

function closeChat() {
    $("#chatWindow").hide(); // 隐藏聊天窗口
    $(".btn-primary").show(); // 显示按钮
    if (socket) {
        socket.close(); // 关闭 WebSocket 连接
        isChatOpen = false; // 标记聊天为关闭

    }
}
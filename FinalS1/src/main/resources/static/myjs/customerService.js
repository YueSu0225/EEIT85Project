let socket = null; // 声明全局变量
let isChatOpen = false; // 用于标记聊天是否已打开

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
    .then(response => {
        if (!response.ok) {
            throw new Error('Session check failed');
        }
        return response.json(); // 假设后端返回JSON格式的响应
    })
    .then(data => {
        var uuid = data.userUUID;
        if (!uuid) {
            throw new Error('User UUID is undefined');
        }
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
    if (!uuid) {
        console.error("Cannot open chat: UUID is undefined");
        return; // 退出函数
    }

    if (isChatOpen) {
        console.log("Chat is already open.");
        return; // 如果已经打开，直接返回
    }

    $("#chatWindow").show();  // 显示聊天窗口
    $(".btn-primary").hide(); // 隐藏圆形按钮
    $("#chatWindow").draggable().resizable();  // 使窗口可拖动和可缩放

    // 创建 WebSocket 连接
    socket = new WebSocket("ws://localhost:8080/chat?uuid="+uuid); // 赋值给全局变量

    // 监控 WebSocket 连接状态
    socket.onopen = function() {
        console.log("WebSocket connection established.");
        isChatOpen = true; // 标记聊天已打开
    };

    socket.onclose = function() {
        console.log("WebSocket connection closed.");
        isChatOpen = false; // 标记聊天已关闭
    };

    socket.onerror = function(event) {
        console.error("WebSocket error observed:", event);
    };

}

socket.onmessage = function(event) {
        var chatBox = document.getElementById("chatBox");
        chatBox.innerHTML += "<p>" + event.data + "</p>"; // 显示收到的消息
        chatBox.scrollTop = chatBox.scrollHeight; // 保持滚动到底部
    };

	function sendMessage() {
	    var input = document.getElementById("messageInput");
	    var message = input.value;
	    if (message.trim() !== "") {
	        socket.send(uuid + ":" + message); // 发送给管理员
	        input.value = ""; // 清空输入框
	    }
	}

    function closeChat() {
        document.getElementById("chatWindow").style.display = "none"; // 隐藏聊天窗口
        socket.close(); // 关闭 WebSocket
    }

// 关闭聊天窗口
function closeChat() {
    $("#chatWindow").hide();    // 隐藏聊天窗口
    $(".btn-primary").show();   // 显示按钮
    if (socket) {
        socket.close(); // 关闭 WebSocket 连接
        isChatOpen = false; // 标记聊天为关闭
    }
}

document.addEventListener("DOMContentLoaded", function() {
    const chatButton = document.getElementById("chatButton");
    chatButton.addEventListener("click", function(event) {
        event.preventDefault(); // 防止默认事件，例如表单提交
        checkSession();
    });
});

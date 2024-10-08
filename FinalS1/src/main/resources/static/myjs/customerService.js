
       var socket;

       // 打開聊天窗口並連接WebSocket
       function openChat() {
           $("#chatWindow").show();  // 顯示聊天窗口
           $(".btn-primary").hide(); // 隱藏圓形按鈕
           $("#chatWindow").draggable().resizable();  // 使窗口可拖動和可縮放

           // 建立 WebSocket 連接
           socket = new WebSocket("ws://localhost:8080/chat?userId=customer1");

           socket.onmessage = function(event) {
               var chatBox = document.getElementById("chatBox");
               chatBox.innerHTML += "<p>" + event.data + "</p>";
               chatBox.scrollTop = chatBox.scrollHeight;  // 保持滾動到底部
           };
       }

       // 關閉聊天窗口
       function closeChat() {
           $("#chatWindow").hide();    // 隱藏聊天窗口
           $(".btn-primary").show();   // 顯示按鈕
       }

       // 發送消息
       function sendMessage() {
           var input = document.getElementById("messageInput");
           var message = input.value;
           if (message.trim() !== "") {
               socket.send(message);
               input.value = "";  // 清空輸入框
           }
       }

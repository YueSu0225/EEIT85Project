window.onload = function(){
	let start = document.getElementById('start');//先宣告
	let mesgDiv = document.getElementById('mesgDiv');//先宣告
	let mesg = document.getElementById('mesg');//先宣告
	let send = document.getElementById('send');//先宣告
	let log = document.getElementById('log');//先宣告
	
	let webSocket;//先宣告
	
	start.addEventListener('click', function(){//使用者觸發事件;
		connect("ws://10.0.100.136:8080/RCWeb/myserver");//建立websocket連線,如果有ssl就用wss
	});
	
	send.addEventListener('click', function(){//使用者觸發事件;
		let message = {
			message: mesg.value
		};
		webSocket.send(JSON.stringify(message));
		mesg.value = '';
	});
	
	start.style.display = 'block';
	mesgDiv.style.display = 'none';
	
	function connect(url){
		webSocket = new WebSocket(url)
		webSocket.onopen = function(event){
			console.log("onOpen");
			
			start.style.display = 'none';
			mesgDiv.style.display = 'block';		
		};

		webSocket.onclose = function(event){
			console.log("onClose");
			start.style.display = 'block';
			mesgDiv.style.display = 'none';
		};		
	
		webSocket.onmessage = function(event){
			console.log(event.data);
			let backMesg = JSON.parse(event.data);
			log.innerHTML += backMesg.message + "<br />";
		};
		
		webSocket.onerror = function(event){
			console.log("onError");
		};		
	}
	
	
	
}
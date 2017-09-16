var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket'); //构建一个SockJS对象
    stompClient = Stomp.over(socket); //用Stomp将SockJS进行协议封装
    stompClient.connect({}, function (frame) { //连接回调
        setConnected(true);
        console.log('Connected: ' + frame);
        /**  订阅了/topic/greetings 发送的消息,这里雨在控制器的 convertAndSendToUser 定义的地址保持一致, 
         *  这里多用了一个/user,并且这个user 是必须的,使用user 才会发送消息到指定的用户。 
         *  */
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    /*消息发送*/
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({'message': $("#mysend").val()}));
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#mybutton").click(function (){
        sendMessage();
    });
});
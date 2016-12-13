<html>
<head>
    <script src="/js/lib/kendoui/jquery.min.js"></script>
</head>
<body>

<div>

    <textarea id="text1">


    </textarea>
    <a href="#" onclick="send1()">send1</a>
    <a href="#" onclick="quit()">quit</a>
    <textarea id="text2">


    </textarea>
    <a href="#" onclick="send2()">send2</a>
    <textarea>


    </textarea>
</div>
<script type="application/javascript" >
    var s1=new WebSocket('wss://www.wana66.com:8443/websocket');
    var s2=new WebSocket('wss://www.wana66.com:8443/websocket');

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function(){
        s1.close();
        s2.close();
    }
    console.log(s1)
    function send1() {
        var text1 = $('#text1').val();
        console.log(text1);
        s1.send(text1);
    }
    function quit() {
        s1.close();
    }
    function send2() {
        var text2 = $('#text2').val();
        console.log(text2);
        s2.send(text2);
    }

</script>

</body>

</html>
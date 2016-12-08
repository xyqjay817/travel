<html>
<head>
    <script src="/js/lib/kendoui/jquery.min.js"></script>
</head>
<body>

<div>

    <textarea id="text1">


    </textarea>
    <a href="#" onclick="send1()">send1</a>
    <textarea id="text2">


    </textarea>
    <a href="#" onclick="send2()">send2</a>
    <textarea>


    </textarea>
</div>
<script type="application/javascript" >
    var s1=new WebSocket('wss://www.wana66.com:8443/websocket');
    var s2=new WebSocket('wss://www.wana66.com:8443/websocket');
    function send1() {
        var text1 = $('#text1').val();
        console.log(text1);
        s1.send(text1);
    }
    function send2() {
        var text2 = $('#text2').val();
        console.log(text2);
        s2.send(text2);
    }
</script>

</body>

</html>
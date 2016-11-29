require(['sm'],
    function($) {
        console.log($('#p').text())
        // 初始化,通常是在业务页面代码的最后执行
        $.init();
    }
)
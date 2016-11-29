'use strict';

define(function() {

    //sui全局配置,具体配置在官网查看
    //http://m.sui.taobao.org/components/#init

    return {
      autoInit: false,
      autoInitSwiper: false,
      router: true,
      showPageLoadingIndicator: true,
      swipePanel: 'left',
      swipePanelOnlyClose: true,
      routerFilter: function($link) {
          if ($link.is('a.router-link')) {
              return true;
          }

          return false;
      }
    }
});

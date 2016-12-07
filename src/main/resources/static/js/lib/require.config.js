var require = {
    paths: {
    },
    shim: {
        'sm': {
            deps: ['zepto']
        },
        'waterfall': {
            deps: ['zepto']
        }
    },
    // urlArgs: "v=1.3.6.5"
    urlArgs: "v=" +  new Date().getMinutes()
};


// function resize() {
//     var deviceWidth = document.documentElement.clientWidth;
//     // if (deviceWidth > 640) deviceWidth = 640;
//     if (deviceWidth < 240) deviceWidth = 240;
//     document.documentElement.style.cssText = 'font-size:' + (deviceWidth / 16) + 'px!important';
// }
// window.addEventListener('resize',resize);
// resize();

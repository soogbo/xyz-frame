/**
 * Created by gary on 15-8-19.
 */
//themes, change CSS with JS
//default theme(CSS) is cerulean, change it if needed
var theme = {
    path: 'systemManage/resources/common/css/',
    selector: '#themes',
    init: function () {
        var currentTheme = $.cookie('currentTheme') == null ? 'cerulean' : $.cookie('currentTheme');
        theme.switchTheme(currentTheme);

        theme.$selector = $(theme.selector);
        theme.$selector.on('click', 'a', function(e){
            e.preventDefault();
            currentTheme = $(this).attr('data-value');
            //解决多级目录问题(如果还是不行,考虑用localstorage)
            $.cookie('currentTheme', currentTheme, {expires: 365, path: $('base').attr('href')||''});
            theme.switchTheme(currentTheme);
        });
    },
    switchTheme: function (themeName) {
        if (themeName == 'classic') {
            $('#bs-css').attr('href', theme.path + 'bootstrap.min.css');
        } else {
            $('#bs-css').attr('href', theme.path + 'bootstrap-' + themeName + '.min.css');
        }

        $( theme.selector + ' i').removeClass('glyphicon glyphicon-ok whitespace').addClass('whitespace');
        $(theme.selector + ' a[data-value=' + themeName + '] i').removeClass('whitespace').addClass('glyphicon glyphicon-ok');

        theme.resetAllIframeTheme(document, themeName);
    },
    resetAllIframeTheme: function (doc, themeName) {
        var frames = doc.getElementsByTagName("iframe");
        if (frames.length <= 0) {
            return;
        }
        for (var i = 0; i < frames.length; i++) {
            var frame = frames[i];
            var cssObj = frame.contentDocument.getElementById("bs-css");
            if (themeName == 'classic') {
                $(cssObj).attr('href', theme.path + 'bootstrap.min.css');
            } else {
                $(cssObj).attr('href', theme.path + 'bootstrap-' + themeName + '.min.css');
            }
            theme.resetAllIframeTheme(frame.contentDocument, themeName);
        }
    }
};
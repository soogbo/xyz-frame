/**
 * Created by gary on 15-8-21.
 */

$(function () {
    initAjaxLoader();
});

var DateFormat = {
    //moment format
    timestamp: 'YYYY-MM-DD HH:mm:ss',
    date: 'YYYY-MM-DD'
};

/**
 * @Deprecated
 * 改用moment插件(已经应用)
 * http://momentjs.cn/docs/#/parsing/
 */
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

var AjaxLoader = {
    opts: {
        msg: '<img id="img_loading" alt="加载中..." src="static/common/images/load.gif">',
        wrap: 'body',
        'class': '',
        css: {}
    },
    init: function () {
        if (!AjaxLoader.$loader) {
            AjaxLoader.$loader = $('<div id="loader-modal"><div class="loader-content"></div></div>');
            AjaxLoader.$loaderContent = AjaxLoader.$loader.find('.loader-content');
        }
    },
    open: function (options) {
        var opts = $.extend({}, AjaxLoader.opts, options);
        AjaxLoader.$loaderContent.html(opts.msg);
        AjaxLoader.$loader.removeAttr('class style')
            .addClass(opts.class || '')
            .css(opts.css)
            .appendTo(opts.wrap);
        AjaxLoader.$loader.show();
    },
    close: function () {
        AjaxLoader.$loader.hide();
    }
};

function initAjaxLoader() {
    AjaxLoader.init();
    $(document).ajaxSend(function (event, request, settings) {
        if (settings.modal) {
            AjaxLoader.open(settings.modal);
        }
    });
    $(document).ajaxStop(function (event, request, settings) {
        AjaxLoader.close();
    });
}

$.extend($, {
    postAjax: function (options) {
        var ops = {
            url: '',
            type: 'POST',
            dataType: 'json',
            cache: false,
            modal: {
                'class': 'fixed'
            },
            success: function (res) {
                if (res.status) {
                    options.handleSuccess && options.handleSuccess(res);
                } else {
                    debug('请求({})失败的真正原因: {}', ops.url, res['actualReasion'] || res.errorReason);
                    var message = StringFormat('请求失败: {}', res.errorReason);
                    if (options.handleError) {
                        options.handleError(res.errorCode, message);
                    } else {
                        n_alert(message, {type: 'error'});
                    }
                }
            },
            error: function (xhr, msg, e) {
                debug('ajax请求失败: ', msg);
                if (options.handleError) {
                    options.handleError('Z999', '请求失败,请稍后重试！');
                } else {
                    n_alert('请求失败,请稍后重试!', {type: 'error'});
                }
            }
        };
        $.extend(ops, options);
        $.ajax(ops);
    },
    getAjax: function (options) {
        options['type'] = 'GET';
        $.postAjax(options);
    },
    tableAjax: function (options) {
        var ops = {
            url: '',
            type: 'POST',
            dataType: 'json',
            cache: false,
//            timeout: 5000,
            beforeSend: function () {
                beforeTableLoading(options.table, options.btnWrap);
            },
            complete: function () {
                afterTableLoading(options.table, options.btnWrap);
            },
            success: function (res) {
                if (res.status) {
                    options.handleSuccess && options.handleSuccess(res);
                } else {
                    debug('请求({})失败的真正原因: {}', ops.url, res['actualReasion']);
                    emptyTableMsg(options.table, StringFormat('请求失败: {}', res.errorReason));
                }
            },
            error: function (xhr, msg, e) {
                debug('ajax请求失败: ', msg);
                emptyTableMsg(options.table, '请求失败,请稍后重试!');
                n_alert('请求失败,请稍后重试!', {type: 'error'});
            }
        };
        $.extend(ops, options);
        $.ajax(ops);
    }
});

function StringFormat(format) {
    var msg = format, i = 0, len = arguments.length, value;
    if (len > 1) {
        for (i = 1; i < len; i++) {
            value = arguments[i];
            if ($.isArray(value) || $.isPlainObject(value)) {
                msg = msg.replace('{}', JSON.stringify(value));
            } else if ($.isFunction(value)) {
                msg = msg.replace('{}', value());
            } else {
                msg = msg.replace('{}', String(value));
            }
        }
    }
    return msg;
}

/* debug fn */
function debug(msg) {
    console.log(StringFormat.apply(this, arguments));
}

function disableButtons(wrap) {
    $(wrap).find('.btn').attr('disabled', true).css({cursor: 'not-allowed'});
}

function enableButtons(wrap) {
    $(wrap).find('.btn').attr('disabled', false).css({cursor: 'default'});
}

function getWindowSize() {
    if (window.innerHeight) {
        return {
            height: window.innerHeight,
            width: window.innerWidth
        }
    } else {
        return {
            height: document.documentElement.clientHeight,
            width: document.documentElement.clientWidth
        }
    }
}

/**
 *
 * @param data 序列号的对象 array 或者 object
 * @param stringty 是否将json object结果转化为json string
 * @param parseNum 是否将string转化为int： 默认true
 * @returns {{}}
 */
$.formJsonData = function (data, stringty, parseNum) {
    var json = {}, value, tmpValue;
    parseNum = parseNum === undefined ? true : parseNum;
    if (data && $.isArray(data)) {
        for (var i = 0; i < data.length; i++) {
            value = $.trim(data[i]['value']);
            if (value) {
                value = parseNum && $.isNumeric(value) ? parseInt(value) : value;
                tmpValue = json[data[i]['name']];
                if (!tmpValue) {
                    json[data[i]['name']] = value;
                } else if ($.isArray(tmpValue)) {
                    tmpValue.push(value);
                } else {
                    tmpValue = [tmpValue];
                    tmpValue.push(value);
                    json[data[i]['name']] = tmpValue;
                }
            }
        }
    } else if (data && $.isPlainObject(data)) {
        json = data;
    }
    return stringty ? JSON.stringify(json) : json;
};

$.fn.serializeJson = function () {
    return $.formJsonData($(this).serializeArray());
};

//手机号码    ///^1[3,5,8]\d{9}$/
function isCellPhone(phoneNum) {
    var reg = /^1[3,5,7,4,8]\d{9}$/;
    if (reg.test(phoneNum)) {
        return true;
    } else {
        return false;
    }
}

// 浏览器兼容Object.keys start--------------
// var DONT_ENUM = "propertyIsEnumerable,isPrototypeOf,hasOwnProperty,toLocaleString,toString,valueOf,constructor".split(","),
//     hasOwn = ({}).hasOwnProperty;
// for (var i in {
//     toString: 1
// }) {
//     DONT_ENUM = false;
// }
// Object.keys = Object.keys || function (obj) {//ecma262v5 15.2.3.14
//         var result = [];
//         for (var key in obj) if (hasOwn.call(obj, key)) {
//             result.push(key);
//         }
//         if (DONT_ENUM && obj) {
//             for (var i = 0; key = DONT_ENUM[i++];) {
//                 if (hasOwn.call(obj, key)) {
//                     result.push(key);
//                 }
//             }
//         }
//         return result;
//     };

if (!Object.keys) Object.keys = function (o) {
    if (o !== Object(o))
        throw new TypeError('Object.keys called on a non-object');
    var k = [], p;
    for (p in o) if (Object.prototype.hasOwnProperty.call(o, p)) k.push(p);
    return k;
}
// 浏览器兼容Object.keys end--------------

// 判断对象是否为空
function isEmptyObj(obj) {
    if (obj == null || obj == "" || obj == undefined) {
        return true;
    }
    if (!isNaN(obj)) { // 是数字
        return false;
    }
    return Object.keys(obj).length === 0;
}

// 数组去重
function arrayUnique(arr) {

    var n = {}, r = []; //n为hash表，r为临时数组
    for (var i = 0; i < arr.length; i++) //遍历当前数组
    {
        if (!n[arr[i]]) //如果hash表中没有当前项
        {
            n[arr[i]] = true; //存入hash表
            r.push(arr[i]); //把当前数组的当前项push到临时数组里面
        }
    }
    return r;
}
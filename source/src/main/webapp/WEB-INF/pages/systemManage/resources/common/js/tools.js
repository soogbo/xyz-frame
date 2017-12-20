function getArrayByString(string) {
    if (string.indexOf(",") < 0) {
        return [ string ];
    }
    var str = string.split(',');
    return str;
}

function getStringByArray(array) {
    var str = '';
    for (var i = 0; i < array.length; i++) {
        str += array[i] + ',';
    }
    if (str.length > 0)
        str = str.substring(0, str.length - 1);
    return str;
}

function trim(str) {
    return str.replace(/[ ]/g, "");
}

function checkStringIsNull(str) {
    if (str == "undefined") {
        return true;
    }
    if (str == null) {
        return true;
    }
    if (str == "") {
        return true;
    }
    if (trim(str) == "") {
        return true;
    }
}

function CheckStr(str) {
    var SpecialCharacters = "!()@/'\"#$%&^。.;'\"*“”：；:<>~·`";
    var i = 0;
    for (i = 0; i < SpecialCharacters.length - 1; i++) {
        if (str.indexOf(SpecialCharacters[i]) != -1) {
            return true;
        }
    }
    return false;
}

Array.prototype.remove = function (dx) {
    if (isNaN(dx) || dx > this.length) {
        return false;
    }
    for (var i = 0, n = 0; i < this.length; i++) {
        if (i != dx) {
            this[n++] = this[i];
        }
    }
    this.length -= 1;
};

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
        "H+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds()
        // 毫秒
    };
    var week = {
        "0": "/u65e5",
        "1": "/u4e00",
        "2": "/u4e8c",
        "3": "/u4e09",
        "4": "/u56db",
        "5": "/u4e94",
        "6": "/u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}
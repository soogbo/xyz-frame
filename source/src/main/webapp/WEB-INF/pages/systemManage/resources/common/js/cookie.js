function SetCookie(sName, sValue) {
    date = new Date();
    s = date.getDate();
    date.setDate(s + 1); // 设置cookie的有效期
    document.cookie = sName + "=" + encodeURI(sValue) + "; expires="
        + date.toGMTString();// 创建cookie
}

function GetCookie(sName) {
    var aCookie = document.cookie.split("; "); // 将cookie中的数据切割成数组，方便遍历
    for (var i = 0; i < aCookie.length; i++) // 遍历cookie中的数据
    {
        var aCrumb = aCookie[i].split("="); // 将键和值分开
        if (sName == aCrumb[0]) { // 判断是否是指定的键
            return decodeURI(aCrumb[1]);
        } // 返回键对应的值
    }
    return null;
}

function clearAllCookie() {
    SetCookie("jump_target_id", null);
}
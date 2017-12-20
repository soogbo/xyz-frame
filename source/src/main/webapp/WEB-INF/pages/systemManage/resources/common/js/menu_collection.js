/**
 * Created by Gary on 2016/6/24.
 * 菜单收藏操作javascript
 */
function collectReport(obj) {
    var toCollect = "static/common/images/star_five1.jpg";
    var deleteCollect = "static/common/images/star_five2.jpg";
    var tempCollect = $(obj).attr("src");
    var tempUrl = $(obj).attr("name1");
    var tempName = $(obj).attr("name2");
    if (tempCollect == toCollect) {
        $(obj).attr("src", deleteCollect);
        $.commonAjax({
            url: 'putUserCollect',
            data: {url: tempUrl, name: tempName},
            success: function () {
                alert("收藏成功！");
            }
        })

    } else if (tempCollect == deleteCollect) {
        $(obj).attr("src", toCollect);
        $.ajax({
            url: "deleteUserCollect",
            data: {"url": tempUrl},
            success: function (data) {
                if (data > 0) {
                    alert("取消收藏成功");
                }
            }
        })
    }
}
//显示我的收藏
function showMyCollect() {
    $.commonAjax({
        url: 'getUserCollect',
        type: 'GET',
        success: function (res) {
            var html = [];
            var obj = res['result'];
            $.each(obj, function (i, v) {
                html.push(StringFormat('<li><a href="javascript:void(0);" onclick="javascript: window.location.href =\'{}\'"><span>{}</span></a></li>', v.url, v.name));
            });
            $("#myCollect").html(html).css("display", "block");
        }
    })
}

var twoMinute = "";
(function () {
    var fullScreenApi = {
            supportsFullScreen: false,
            isFullScreen: function () {
                return false;
            },
            requestFullScreen: function () {
            },
            cancelFullScreen: function () {

            },
            fullScreenEventName: '',
            prefix: ''
        },
        browserPrefixes = 'webkit moz o ms khtml'.split(' ');
    // check for native support
    if (typeof document.cancelFullScreen != 'undefined') {
        fullScreenApi.supportsFullScreen = true;
    } else {
        // check for fullscreen support by vendor prefix
        for (var i = 0, il = browserPrefixes.length; i < il; i++) {
            fullScreenApi.prefix = browserPrefixes[i];
            if (typeof document[fullScreenApi.prefix + 'CancelFullScreen'] != 'undefined') {
                fullScreenApi.supportsFullScreen = true;
                break;
            }
        }
    }
    // update methods to do something useful
    if (fullScreenApi.supportsFullScreen) {
        fullScreenApi.fullScreenEventName = fullScreenApi.prefix + 'fullscreenchange';
        fullScreenApi.isFullScreen = function () {
            switch (this.prefix) {
                case '':
                    return document.fullScreen;
                case 'webkit':
                    return document.webkitIsFullScreen;
                default:
                    return document[this.prefix + 'FullScreen'];
            }
        }
        fullScreenApi.requestFullScreen = function (el) {
            return (this.prefix === '') ? el.requestFullScreen() : el[this.prefix + 'RequestFullScreen']();
        }
        fullScreenApi.cancelFullScreen = function (el) {
            return (this.prefix === '') ? document.cancelFullScreen() : document[this.prefix + 'CancelFullScreen']();
        }
    }
    // jQuery plugin
    if (typeof jQuery != 'undefined') {
        jQuery.fn.requestFullScreen = function () {
            return this.each(function () {
                if (fullScreenApi.supportsFullScreen) {
                    fullScreenApi.requestFullScreen(this);
                    setTimeout(function () {
                        deferredOpen.resolve();
                    }, 100);
                }
            });
        };
        jQuery.fn.cancelFullScreen = function () {
            return this.each(function () {
                if (fullScreenApi.supportsFullScreen) {
                    fullScreenApi.cancelFullScreen(this);
                    setTimeout(function () {
                        deferredClose.resolve();
                    }, 100);
                }
            });
        };
    }
    // export api
    window.fullScreenApi = fullScreenApi;
})();

var deferredOpen = null;
var deferredClose = null;
var player = null;

$(function () {

    $("#fullScreenBtn").click(function () {
        deferredOpen = $.Deferred();
        $.when(deferredOpen).done(function () {
            console.log("deferred fullScreen", $("#fullScreen").height());
            //$("#approve-left").css({height: +($("#fullScreen").height()) - 60 + "px"});
            //$("#approve-right").css({height: +($("#fullScreen").height()) - 60 + "px"});
        });
        $("#fullScreen").requestFullScreen();
    });


    //$("#exit").click(function () {
    //    console.log("deferred fullScreen", $("#fullScreen").height());
    //    deferredClose = $.Deferred();
    //    $.when(deferredClose).done(function () {
    //        console.log("deferred fullScreen", $("#fullScreen").height());
    //        $("#approve-left").css({height: "75vh"});
    //        $("#approve-right").css({height: "75vh"});
    //    });
    //    $("#fullScreen").cancelFullScreen();
    //});

    if (window.top != self) {
        $("#tip").text("iframe里面不能演示该功能！请点击右下角的全屏查看！").show();
    }


});
if (!fullScreenApi.supportsFullScreen) {
    alert("您的破浏览器不支持全屏API哦，请换高版本的chrome或者firebox！");
}
//var isFullScreen = false;


var approve = {
    name: "",
    hadApprove: 0,
    approveData: {},
    checkFlag: true,
    submitClass: {
        pass: true,
        reject: false
    },
    creditCardFirst: false,
    userId: 0,
    isPause: false
};


function stop() {
    return false;
}
var hasArtZoom = false;

function fillData(name, nums, data) {
    approve.name = name;
    approve.hadApprove = nums;
    var approveData = null;
    if (!!data && !!data.result && !!data.other) {
        var applyTime = new Date(data.other.applyTime);
        applyTime = applyTime.getFullYear() + "-" + (applyTime.getMonth() + 1) + "-" + applyTime.getDate()
            + " " + applyTime.getHours() + ":" + applyTime.getMinutes() + ":" + applyTime.getSeconds()

        approveData = {
            person: {
                name: "" + checkNull(data.result.customer_name),
                times: "" + (checkNull(Number(data.other.applyNum))),
                sendTimes: "" + (checkNull(Number(data.other.sendNum))),
                date: "" + checkNull(applyTime),
                mobile: "" + checkNull(data.result.mobile_no),
                //mobile: "" + checkNull(reformatNumber("tes")),
                depositBank: "" + checkNull(data.result.open_bank) + "（" + checkNull(data.result.prov) + "/" + checkNull(data.result.city) + "）"
            },
            personalAddress: {
                homeflag: "" + checkNull(data.result.homeResult),
                location: "" + checkNull(data.result.home_addr1) + "&nbsp;" + checkNull(data.result.home_addr2) + "&nbsp;" + checkNull(data.result.home_district),
                address: "" + checkNull(data.result.home_addr3)
            },
            company: {
                companyflag: "" + checkNull(data.result.unitsResult),
                companyNameflag: "" + checkNull(data.result.companyNameResult),
                name: "" + checkNull(data.result.job_unit),
                Tel: "" + checkNull(data.result.unit_tel_area) + " " + checkNull(data.result.unit_tel_no),
                location: "" + checkNull(data.result.unit_addr1) + "&nbsp;" + checkNull(data.result.unit_addr2) + "&nbsp;" + checkNull(data.result.units_district),
                address: "" + checkNull(data.result.unit_addr3)
            },
            relation: {
                relationFlag: "" + checkNull(data.result.relationResult),
                relativeFlag: "" + checkNull(data.result.relativesResult),
                colleagueFlag: "" + checkNull(data.result.colleagueResult),
                friendFlag: "" + checkNull(data.result.friendResult),
                relativeName: "" + checkNull(data.result.rela_name1),
                relativeMobile: "" + checkNull(reformatNumber(data.result.rela_phone1)),
                colleagueName: "" + checkNull(data.result.rela_name2),
                colleagueMobile: "" + checkNull(reformatNumber(data.result.rela_phone2)),
                friendName: "" + checkNull(data.result.rela_name3),
                friendMobile: "" + checkNull(reformatNumber(data.result.rela_phone3))
            },
            view: {
                videoFlag: "" + checkNull(data.result.videoResult),
                speechflag: "" + checkNull(data.result.speechResult),
                idCardflag: "" + checkNull(data.result.identityResult),
                avatarflag: "" + checkNull(data.result.userPicResult),
                video: "" + checkNull("image/loanftp/show?path=" + data.result.video + "&ftp_marker=" + data.result.ftp_marker + "&customer_id=" + data.result.customer_id), /*${entity.video}${entity.ftp_marker}*/
                videoDownload: "" + checkNull("video/loanftp/download?path=" + data.result.video + "&ftp_marker=" + data.result.ftp_marker + "&customer_id=" + data.result.customer_id), /*${entity.video}${entity.ftp_marker}*/
                avatar: "" + checkNull("image/loanftp/show?path=" + data.result.photo_real_time + "&ftp_marker=" + data.result.ftp_marker + "&customer_id=" + data.result.customer_id),
                idCard: "" + checkNull("image/loanftp/show?path=" + data.result.photo_front + "&ftp_marker=" + data.result.ftp_marker + "&customer_id=" + data.result.customer_id)
                //avatar: "" + checkNull("static/demo/avatar.jpg"),
                //, video: "" + checkNull("static/demo/show.mp4") /*${entity.video}${entity.ftp_marker}*/
                //idCard: "" + checkNull("static/demo/idCard.jpg")
            },
            rejectReason: {
                approveMobile: [],
                approveHome: {},
                approveCompany: [],
                approveRelation: [],
                approveVideo: [],
                approveAvatar: [],
                approveIdCard: []
            }

        };
        $.ajax({
            url: "task_process/getAllRejectReason.do",
            method: "GET",
            data: {},
            beforeSend: function (XMLHttpRequest) {
            },
            success: function (rejectData, textStatus) {
                var rejectReasons = {};
                if (rejectData.status) {
                    for (var i = 0; i < rejectData.result.length; i++) {
                        var type = Number(rejectData.result[i].type);
                        switch (type) {
                            case 1:
                                approveData.rejectReason["approveMobile"].push({
                                    id: rejectData.result[i].id,
                                    type: rejectData.result[i].type,
                                    reason: rejectData.result[i].reason,
                                    message: rejectData.result[i].message
                                });
                                break;
                            case 2:
                                approveData.rejectReason["approveCompany"].push({
                                    id: rejectData.result[i].id,
                                    type: rejectData.result[i].type,
                                    reason: rejectData.result[i].reason,
                                    message: rejectData.result[i].message
                                });
                                break;
                            case 3:
                                approveData.rejectReason["approveCompany"].push({
                                    id: rejectData.result[i].id,
                                    type: rejectData.result[i].type,
                                    reason: rejectData.result[i].reason,
                                    message: rejectData.result[i].message
                                });
                                break;
                            case 4:
                                approveData.rejectReason["approveHome"] = {
                                    id: rejectData.result[i].id,
                                    type: rejectData.result[i].type,
                                    reason: rejectData.result[i].reason,
                                    message: rejectData.result[i].message
                                };
                                break;

                            case 5:
                                approveData.rejectReason["approveRelation"].push({
                                    id: rejectData.result[i].id,
                                    type: rejectData.result[i].type,
                                    reason: rejectData.result[i].reason,
                                    message: rejectData.result[i].message
                                });
                                break;
                            case 6:
                                approveData.rejectReason["approveIdCard"].push({
                                    id: rejectData.result[i].id,
                                    type: rejectData.result[i].type,
                                    reason: rejectData.result[i].reason,
                                    message: rejectData.result[i].message
                                });
                                break;
                            case 7:
                                approveData.rejectReason["approveAvatar"].push({
                                    id: rejectData.result[i].id,
                                    type: rejectData.result[i].type,
                                    reason: rejectData.result[i].reason,
                                    message: rejectData.result[i].message
                                });
                                break;
                            case 8:
                                approveData.rejectReason["approveVideo"].push({
                                    id: rejectData.result[i].id,
                                    type: rejectData.result[i].type,
                                    reason: rejectData.result[i].reason,
                                    message: rejectData.result[i].message
                                });
                                break;
                            case 10:
                                approveData.rejectReason["approveMobile"].push({
                                    id: rejectData.result[i].id,
                                    type: rejectData.result[i].type,
                                    reason: rejectData.result[i].reason,
                                    message: rejectData.result[i].message
                                });
                                break;


                        }
                    }

                    approve.approveData = approveData;

                    clearReject();
                    bindEnter();
                    textHighlight();
                    if (data.result.homeResult == 2
                        || data.result.unitsResult == 2
                        || data.result.companyNameResult == 2
                        || data.result.relationResult == 2) {
                        approve.checkFlag = false;
                        approve.submitClass.pass = false;
                        approve.submitClass.reject = true;
                    }


                    setTimeout(function () {

                        if (!hasArtZoom) {
                            $(".artZoom-fullScreen").artZoom();
                            hasArtZoom = true;
                        }
                        bindVideo();
                    }, 100);

                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                //alertError("网络出现问题，请重试");
            }
        });


    } else {
        approveData = {};
    }


}

function submitApprove(status) {
    //todo 提交审核，然后接受新件
    var url = "";
    var data = null;
    var reasonIds = [];
    var $selectReasons = $("#fullScreen").find('input:checked');
    if (!!$selectReasons.length || $("#feedback").val().length > 0 || status == false) {
        reasonIds = [];
        $selectReasons.each(function () {
            reasonIds.push({name: 'reasonIds', value: $(this).data("rejectId")});
            //reasonIds.push(Number($(this).data("rejectId")));
        });

    }
    var sendTimes = $("#approve-send-times span").html();
    if (status) {
        url = "task_process/agree.do";
        data = {
            productAuditStatusId: approve.userId,
            sendTimes: sendTimes
        }

    } else {
        url = "task_process/reject.do";
        reasonIds.push({
            name: 'productAuditStatusId',
            value: approve.userId
        });
        reasonIds.push({
            name: 'remark',
            value: $("#feedback").val()
        });
        reasonIds.push({
            name: 'sendTimes',
            value: sendTimes
        });
        data = reasonIds

    }
    console.log(data);
    $.ajax({
        url: url,
        method: "POST",
        data: data,
        beforeSend: function (XMLHttpRequest) {
            showError("已经提交请耐心等待")
        },
        success: function (data, textStatus) {
            //data = JSON.parse(data);
            var temp = "" + data.status;
            if (temp == "undefined") {
                data = JSON.parse(data);
            }
            if (data.status) {
                clearInterval(twoMinute);
                postDetail(approve.creditCardFirst);
            } else {
                showError(data.errorReason)
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alertError("网络出现问题，请重试");
            showError("网络出现问题，请重试")
        }
    });

}

function textHighlight() {
    if (approve.approveData.personalAddress.address.indexOf("b") > 0 && approve.approveData.company.address.indexOf("b") > 0) {
        return false;
    }
    var reg = /(号|街道|镇)/ig;
    approve.approveData.personalAddress.address = approve.approveData.personalAddress.address.replace(reg, "<b>$1</b>");
    approve.approveData.company.address = approve.approveData.company.address.replace(reg, "<b>$1</b>");
}

function bindVideo() {
    player = videojs('approve-video', {"language": "zh"}, function () {
        this.play();
        this.on('ended', function () {

        });
    });


    //$("#approve-right > .row > .col-xs-12 > .approve-content #play-pause > span").click(function () {
    //    console.log($(this).data("act"));
    //    if ($(this).data("act") === "play") {
    //        player.play();
    //        $("#pause").show();
    //        $("#play").hide();
    //    } else {
    //        player.pause();
    //        $("#pause").hide();
    //        $("#play").show();
    //    }
    //});

}


function checkNull(obj) {
    if (!obj) {
        return "";
    } else {
        return obj;
    }
}

function postDetail(creditStatus) {
    var data = "";
    if (!!creditStatus) {
        data = {creditStatus: 1};
    } else {
        data = {};
    }

    $.ajax({
        url: "task_process/detail.do",
        method: "POST",
        data: JSON.stringify(data),
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (data, textStatus) {

            if (data.status) {
                approve.userId = checkNull(data.other.productAuditStatusId);
                fillData(data.other.adminName, data.other.finishedTasks, data);
                //取得件后两分钟取消所有认领
                twoMinute = setInterval(function () {
                    cancelApprove();
                }, 120 * 1000);


            } else {
                fillData(null, null, null);
                aMinueLaterPost();
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alertError("网络出现问题，请重试");
            aMinueLaterPost();
        }
    });
}


function aMinueLaterPost() {
    console.log("重新发送");
    approve.approveData = {};
    setTimeout(function () {
        postDetail(approve.creditCardFirst);
    }, 60 * 1000);
}


$(function () {
    $("#fullScreen").find("input:checkbox").prop("checked", false);
    $("#fullScreen").find(".input-checked").prop("checked", true);

    function stop() {
        return false;
    }

    document.oncontextmenu = stop;
    //$("body").keydown(function (event) {
    //    if (event.keyCode == 123 || event.keyCode == 122) {
    //        return false;
    //    }
    //    if (event.ctrlKey && event.keyCode == 13) {
    //        submitApprove(approve.checkFlag === true);
    //    }
    //
    //});


    var approveVM = new Vue({
        el: "#fullScreen",
        data: approve,
        methods: {
            checkApprove: function (name) {
                var $input = $('[data-reject-type="' + $("#" + name).data("rejectType") + '"]');
                /*for (var i = 0; i < $input.length; i++) {
                 if ($($input[i]).prop("checked")) {
                 $input.prop("checked", false);
                 $("#" + name).prop("checked", true);

                 }
                 }*/
                //console.log(approve.checkNum);
                var $inputs = $("#fullScreen").find("input:checkbox");
                console.log("", $inputs.length);

                approve.checkFlag = true;
                for (var i = 0; i < $inputs.length; i++) {
                    if ($($inputs[i]).prop("checked")) {
                        //approve.checkNum++
                        approve.checkFlag = false;
                    }
                }
                if ($.trim($("#feedback").val())) {
                    approve.checkFlag = false;
                }
                console.log("" + approve.checkFlag);
                if (approve.checkFlag === true) {
                    approve.submitClass.pass = true;
                    approve.submitClass.reject = false;
                } else {
                    approve.submitClass.pass = false;
                    approve.submitClass.reject = true;
                }
            },
            clearFullScreen: function () {
                deferredClose = $.Deferred();
                $.when(deferredClose).done(function () {
                    console.log("deferred fullScreen", $("#fullScreen").height());
                    //$("#approve-left").css({height: "100vh"});
                    //$("#approve-right").css({height: "100vh"});
                });
                $("#fullScreen").cancelFullScreen();
            },
            abandonApprove: function () {

                $.ajax({
                    url: "task_process/cancel.do",
                    method: "POST",
                    data: {productAuditStatusId: approve.userId},
                    beforeSend: function (XMLHttpRequest) {
                        showError("已经提交请耐心等待");
                    },
                    success: function (data, textStatus) {
                        var temp = "" + data.status;
                        if (temp == "undefined") {
                            data = JSON.parse(data);
                        }
                        if (data.status) {
                            clearInterval(twoMinute);
                            postDetail(approve.creditCardFirst);
                        } else {
                            showError(data.errorReason);
                        }

                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        //alertError("网络出现问题，请重试");
                        showError("网络出现问题，请重试")
                    }
                });
            },
            cancelApprove: function () {
                $.ajax({
                    url: "task_process/stop.do",
                    method: "POST",
                    data: {},
                    beforeSend: function (XMLHttpRequest) {
                    },
                    success: function (data, textStatus) {
                        var temp = "" + data.status;
                        if (temp == "undefined") {
                            data = JSON.parse(data);
                        }
                        if (data.status) {

                            //cancelText(3, "已取消所有认领，", '秒后跳转到客服认领界面 <a href="task_claim/index.html">直接跳转</a>');
                            approve.approveData = {};
                            approve.isPause = true;
                            showError("已取消所有认领");

                            clearInterval(twoMinute);

                        } else {
                            showError(data.errorReason);
                        }

                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        //alertError("网络出现问题，请重试");
                        showError("网络出现问题，请重试");
                        //approve.approveData = {};
                        //approve.isPause = true;
                        //showError("已取消所有认领");
                    }
                });
            },
            creditCheckbox: function () {
                approve.creditCardFirst = $("#credit-card-first").prop("checked");
                //console.log("approve.creditCardFirst", approve.creditCardFirst);
            },
            feedbackCheck: function () {
                console.log($.trim($("#feedback").val()).length);
                approve.checkFlag = true;
                var $inputs = $("#fullScreen").find("input:checkbox");
                for (var i = 0; i < $inputs.length; i++) {
                    if ($($inputs[i]).prop("checked")) {
                        //approve.checkNum++
                        approve.checkFlag = false;
                    }
                }

                if ($.trim($("#feedback").val()).length == 0 && !!approve.checkFlag) {
                    approve.submitClass.pass = true;
                    approve.submitClass.reject = false;
                    //approve.checkFlag = false;
                } else {
                    approve.submitClass.pass = false;
                    approve.submitClass.reject = true;
                    //approve.checkFlag = true;
                }
                console.log(approve.checkFlag);
            },
            submitApprove: submitApprove,
            startApprove: function () {
                postDetail(approve.creditCardFirst)
            }
        }
    });


});

$(window).load(function () {

    postDetail(approve.creditCardFirst);
    $("#fullScreen").css("visibility", "visible");

    setInterval(function () {
        if ($(".vjs-error-display").css("display") == "block") {
            //console.log($(".vjs-error-display >div").html());
            if ($(".vjs-error-display >div").html().indexOf("视频无法正常播放，请点击右侧超链接下载视频") < 0) {
                //console.log("video error");
                $(".vjs-error-display >div").html("视频无法正常播放，请点击右侧超链接下载视频")
            }
        }
    }, 500);
    //relNum();
    //clickVideo();
});

function showError(text, time) {
    time = time || 3;
    $("#mask").show();
    $("#errorMessage").html("");
    $("#errorMessage").html(text);
    $("#errorMessage").show();
    setTimeout(function () {
        $("#errorMessage").html("");
        $("#errorMessage").hide();
        $("#mask").hide();
    }, time * 1000);
}

function cancelText(wait, text1, text2) {
    if (wait == 0) {
        showError(text1 + wait + text2);
        setTimeout(function () {
            location.href = "task_claim/index.html"
        }, 1000)
    } else {
        showError(text1 + wait + text2);
        wait--;
        setTimeout(function () {
                //showError(text1 + wait + text2);
                cancelText(wait, text1, text2)
            },
            1000)
    }
}

function clearReject() {

    $("#fullScreen").find("input:checkbox").prop("checked", false);
    $("#fullScreen").find("#feedback").val("");
    $("#credit-card-first").prop("checked", approve.creditCardFirst);

    approve.submitClass.pass = true;
    approve.submitClass.reject = false;
    approve.checkFlag = true;
}

function bindEnter() {
    $("body").off("keydown");
    setTimeout(function () {
        $("body").on("keydown", function (event) {
            if (event.ctrlKey && event.keyCode == 13) {
                console.log("keydown");
                $("body").off("keydown");
                submitApprove(approve.checkFlag === true);
            }
        })
    }, 1 * 1000);
}

function clickVideo() {
    $("#bigger-video").on('click', function () {

        //alert(123);
        var video = '<video id="approve-video-big" class="vjs-tech" ' +
            'autoplay loop data-setup="{}" preload="metadata" ' +
            'src="">' +
            '<p class="vjs-no-js">To view this video please enable JavaScript, and consider' +
            'upgrading to a web browser that </p>' +
            '</video>'

        $(video).appendTo($("#video-big"));
        $("#approve-video-big").attr("src", $("#approve-video_html5_api").attr("src"));

        $("#mask").show();
        $("#video-big").show();
        //$("#approve-video_html5_api").pause();


    })
}


function reformatNumber(phone) {
    var prefix = "";
    var number = "";
    var tempNumber = phone;
    //phone = $.trim(phone);
    phone = phone.replace(/(-|\+| )/ig, "");
    var regMobile = /^(0|86|17951)?((13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8})$/;
    if (regMobile.test(phone)) {
        var temp = phone.replace(regMobile, "$1 $2");
        prefix = temp.split(" ").shift();
        number = temp.split(" ").pop();
    } else {
        return tempNumber;
    }
    var num = "" + number;
    var rgx = /(\d+)(\d{4})/;

    while (rgx.test(num)) {
        num = num.replace(rgx, '$1-$2');
    }
    //console.log(num);
    return prefix + " " + num;
}


function cancelApprove() {
    $.ajax({
        url: "task_process/stop.do",
        method: "POST",
        data: {},
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (data, textStatus) {
            var temp = "" + data.status;
            if (temp == "undefined") {
                data = JSON.parse(data);
            }
            if (data.status) {

                //cancelText(3, "已取消所有认领，", '秒后跳转到客服认领界面 <a href="task_claim/index.html">直接跳转</a>');
                approve.approveData = {};
                approve.isPause = true;
                showError("已取消所有认领");
                clearInterval(twoMinute);
            } else {
                showError(data.errorReason);
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alertError("网络出现问题，请重试");
            showError("网络出现问题，请重试");
            //approve.approveData = {};
            //approve.isPause = true;
            //showError("已取消所有认领");
        }
    });
}
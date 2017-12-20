/**
 * Created by x_zhaohh on 2015/11/4.
 */
/**
 * 初始化日期控件
 */
function initDatePicker() {
    $(".form_date").datetimepicker({
        language: 'zh-CN', // 汉化
        weekStart: 0,
        todayBtn: 1,
        autoclose: 1, // 选择日期后自动关闭
        pickerPosition: "bottom-left",
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        minView: "month", // 选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd" // 选择日期后，文本框显示的日期格式
    });
}
/**
 * 初始化日历的默认值
 * @param startId
 * @param endId
 * @param days
 */
function initPickerValue(startId, endId, days) {
    var today = new Date().format("yyyy-MM-dd");
    var s = new Date(new Date().getTime() - days * 24 * 60 * 60 * 1000).format("yyyy-MM-dd");
    var e = new Date(new Date().getTime() - 1 * 24 * 60 * 60 * 1000).format("yyyy-MM-dd");
    $("#" + startId).val(s);
    $("#" + endId).val(e);
    initDatePicker();
}
//初始化单日历的值
function initSingleDatePicker(startId, days) {
    var today = new Date().format("yyyy-MM-dd");
    var s = new Date(new Date().getTime() - days * 24 * 60 * 60 * 1000).format("yyyy-MM-dd");
    $("#" + startId).val(s);
    initDatePicker();
}
function initDataRangePicker() {
    $(".form_date").datetimepicker({
        language: 'zh-CN', // 汉化
        weekStart: 0,
        todayBtn: 1,
        autoclose: 1, // 选择日期后自动关闭
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        minView: "month", // 选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd" // 选择日期后，文本框显示的日期格式
    });
}

function initDataRangePickerValue(startId, endId, days) {
    var today = new Date().getTime();
    var s = new Date(today - days * 24 * 60 * 60 * 1000).format("yyyy-MM-dd");
    var e = new Date(today - 1 * 24 * 60 * 60 * 1000).format("yyyy-MM-dd");
    $("#" + startId).val(s);
    $("#" + endId).val(e);
    initDataRangePicker();
}

/**
 * 计算百分比
 * @param numerator 分子
 * @param denominator 分母
 */
function calPercent(numerator, denominator) {
    if (!numerator || !denominator) {
        return 0 + "%";
    } else {
        return (denominator == 0 ? 0 : numerator * 100 / denominator).toFixed(2) + '%'
    }
}

/**
 * 计算百分比， 如果 分支或者分母为假值 返回空
 * @param numerator
 * @param denominator
 * @returns {*}
 */
function emptyCalcPercent(numerator, denominator) {
    if (!numerator || !denominator) {
        return '';
    } else {
        return (denominator == 0 ? 0 : numerator * 100 / denominator).toFixed(2) + '%'
    }
}

function getInt(number) {
    return number || 0;
}

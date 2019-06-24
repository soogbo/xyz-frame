/**
 * Created by gary on 15-9-1.
 */
$.extend(true, $.fn.dataTable.defaults, {
    'dom': "<'row'<'col-md-6'l><'col-md-6'f>r>t<'row'<'col-md-12'i><'col-md-12 center-block'p>>",
    'defaults': true,
    'bAutoWidth': false,
    'bProcessing': false,
    'bStateSave': false,
    'bJQueryUI': true, // 是否将分页样式应用到表格
    'bPaginate': true, // 是否允许分页
    'iDisplayLength': 10,

    'bLengthChange': false, // 是否显示每页显示条数
    'bFilter': false, // 是否启用条件查询
    'sort': false,
    'info': false, // 是否显示分页信息
    'destroy': true, //默认能够重新二次初始化(如果开启过滤,排序等功能,最好禁止二次初始化)
    'bServerSide': false, // 指定从服务器端获取数据
    'sPaginationType': 'bootstrap',
    'oLanguage': {
        'sSearch': '搜索本次查询:',
        'sEmptyTable': '没有可用的数据',
        'sZeroRecords': '没有找到符合条件的数据',
        'sInfo': '当前第 _START_ - _END_ 条 共计 _TOTAL_ 条 ',
        'sInfoEmpty': '一共 0 条数据，当前页显示0 到 0 条',
        'sInfoFiltered': '(从 _MAX_ 条记录中过滤)',
        "sLengthMenu": "每页显示 _MENU_ 条",
        'oPaginate': {
            'sFirst': '首页',
            'sPrevious': ' 上一页 ',
            'sNext': ' 下一页 ',
            'sLast': '尾页'
        }
    },
    'columnDefs': [
        {
            sDefaultContent: '',
            aTargets: ['_all'],
            'sClass': 'center'
        }
    ]
});

//设置dataTable数据为空时的class
$.fn.dataTable.ext.oStdClasses.sRowEmpty = 'center';
$.fn.dataTable.ext.oJUIClasses.sRowEmpty = 'center';

//additional functions for data table
$.fn.dataTableExt.oApi.fnPagingInfo = function (oSettings) {
    return {
        "iStart": oSettings._iDisplayStart,
        "iEnd": oSettings.fnDisplayEnd(),
        "iLength": oSettings._iDisplayLength,
        "iTotal": oSettings.fnRecordsTotal(),
        "iFilteredTotal": oSettings.fnRecordsDisplay(),
        "iPage": Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
        "iTotalPages": Math.ceil(oSettings.fnRecordsDisplay() / oSettings._iDisplayLength)
    };
};
$.extend($.fn.dataTableExt.oPagination, {
    "bootstrap": {
        "fnInit": function (oSettings, nPaging, fnDraw) {
            var oLang = oSettings.oLanguage.oPaginate;
            var fnClickHandler = function (e) {
                e.preventDefault();
                if (oSettings.oApi._fnPageChange(oSettings, e.data.action)) {
                    fnDraw(oSettings);
                }
            };

            $(nPaging).addClass('pagination').append(
                '<ul class="pagination">' + '<li class="first disabled"><a href="#">' + oLang.sFirst + '</a></li>' +//此处添加
                '<li class="prev disabled"><a href="#">&larr; ' + oLang.sPrevious + '</a></li>' +
                '<li class="next disabled"><a href="#">' + oLang.sNext + ' &rarr; </a></li>' + '<li class="last disabled"><a href="#">' + oLang.sLast + '</a></li>' +//此处添加
                '</ul>'
            );
            var els = $('a', nPaging);
            $(els[0]).bind('click.DT', {action: "first"}, fnClickHandler);//此处添加
            $(els[1]).bind('click.DT', {action: "previous"}, fnClickHandler);
            $(els[2]).bind('click.DT', {action: "next"}, fnClickHandler);
            $(els[3]).bind('click.DT', {action: "last"}, fnClickHandler);//此处添加
        },

        "fnUpdate": function (oSettings, fnDraw) {
            var iListLength = 5;
            var oPaging = oSettings.oInstance.fnPagingInfo();
            var an = oSettings.aanFeatures.p;
            var i, j, sClass, iStart, iEnd, iHalf = Math.floor(iListLength / 2);

            if (oPaging.iTotalPages < iListLength) {
                iStart = 1;
                iEnd = oPaging.iTotalPages;
            }
            else if (oPaging.iPage <= iHalf) {
                iStart = 1;
                iEnd = iListLength;
            } else if (oPaging.iPage >= (oPaging.iTotalPages - iHalf)) {
                iStart = oPaging.iTotalPages - iListLength + 1;
                iEnd = oPaging.iTotalPages;
            } else {
                iStart = oPaging.iPage - iHalf + 1;
                iEnd = iStart + iListLength - 1;
            }

            for (i = 0, iLen = an.length; i < iLen; i++) {
                // remove the middle elements
//                $('li:gt(0)', an[i]).filter(':not(:last)').remove();
                $('li:gt(1)', an[i]).filter(':lt(-2)').remove();//此处修改 $('li:gt(0)', an[i]).filter(':not(:last)').remove();

                // add the new list items and their event handlers
                for (j = iStart; j <= iEnd; j++) {
                    sClass = (j == oPaging.iPage + 1) ? 'class="active"' : '';
                    $('<li ' + sClass + '><a href="#">' + j + '</a></li>')
//                        .insertBefore($('li:last', an[i])[0])
                        .insertBefore($('li:eq(-2)', an[i])[0])//此处修改 .insertBefore( $('li:last', an[i])[0] )
                        .bind('click', function (e) {
                            e.preventDefault();
                            oSettings._iDisplayStart = (parseInt($('a', this).text(), 10) - 1) * oPaging.iLength;
                            fnDraw(oSettings);
                        });
                }

                // add / remove disabled classes from the static elements
                if (oPaging.iPage === 0) {
//                    $('li:first', an[i]).addClass('disabled');
                    $('li:lt(2)', an[i]).addClass('disabled'); //此处修改 $('li:first', an[i]).addClass('disabled');
                } else {
//                    $('li:first', an[i]).removeClass('disabled');
                    $('li:lt(2)', an[i]).removeClass('disabled'); //此处修改$('li:first', an[i]).removeClass('disabled');
                }

                if (oPaging.iPage === oPaging.iTotalPages - 1 || oPaging.iTotalPages === 0) {
//                    $('li:last', an[i]).addClass('disabled');
                    $('li:gt(-3)', an[i]).addClass('disabled'); //此处修改$('li:last', an[i]).addClass('disabled');
                } else {
//                    $('li:last', an[i]).removeClass('disabled');
                    $('li:gt(-3)', an[i]).removeClass('disabled'); //此处修改$('li:last', an[i]).removeClass('disabled');
                }
            }
        }
    }
});

/**
 * 将分页信息放入需要传入服务器的数据对象中
 * @param data
 * @param tableParams ： fnServerData或者ajax对应方法的参数 function (sSource, aoData, fnCallback) 取aoData
 */
function ajaxDataTablePageRequestConvert(data, tableParams) {
    return $.extend({}, data, {
        pageNo: tableParams[3].value / tableParams[4].value + 1, // tableParams[3] 偏移量
        pageSize: tableParams[4].value
    });
}

/**
 * 将ajax page数据转换为datatable 符合的数据格式
 * @param res
 * @param tableParams ： fnServerData或者ajax对应方法的参数 function (sSource, aoData, fnCallback) 取aoData
 * @returns {{iTotalDisplayRecords: *, iTotalRecords: *, aaData: (*|Array), sEcho: *}}
 */
function ajaxDataTablePageResultConvert(res, tableParams) {
    return {
        'iTotalDisplayRecords': res['totalRecords'],
        'iTotalRecords': res['totalRecords'],
        'aaData': res['entityList'] || [],
        'sEcho': (res['sEcho'] || (tableParams || [])[0] || 0) + 1
    }
}
function ajaxDataTableListResultConvert(res) {
    return {'data': res.result};
}

function setTableColumnIndex($table, columnIndex) {
    $table.DataTable().column(columnIndex || 0).nodes().each(function (cell, i) {
        cell.innerHTML = i + 1;
    });
}

/* datatable common fn */
function initDataTableBody($table, empty) {
    var colspan = $table.find('thead th').length,
        listBody = '<tbody id="" class="list-body text-center"><tr><td colspan="' + colspan + '" class="text-center">没有找到符合条件的数据！</td></tr></tbody>',
        listLoading = '<tbody class="list-loading"><tr><td colspan="' + colspan + '"><div id="loading" class="text-center" style="margin-top: 0px">Loading...<div class="text-center"></div></div></td></tr></tbody>';
    $table.data('colspan', colspan);
    $table.find('thead').after(listLoading)
        .after(listBody);
    if (empty) {
        $table.find('tbody.list-loading').hide();
    } else {
        $table.find('tbody.list-body').hide();
    }
}

/* datatable common fn */
function overDataTableBody($table, empty) {
    var colspan = $table.find('thead th').length,
        listBody = '<tbody id="" class="list-body text-center"><tr><td colspan="' + colspan + '" class="text-center">请查询！</td></tr></tbody>',
        listLoading = '<tbody class="list-loading"><tr><td colspan="' + colspan + '"><div id="loading" class="text-center" style="margin-top: 0px">Loading...<div class="text-center"></div></div></td></tr></tbody>';
    $table.data('colspan', colspan);
    $table.find('thead').after(listLoading)
        .after(listBody);
    if (empty) {
        $table.find('tbody.list-loading').hide();
    } else {
        $table.find('tbody.list-body').hide();
    }
}

function beforeTableLoading($table, $wrap) {
    disableButtons($wrap);
    $table.find('tbody.list-body').hide();
    $table.find('tbody.list-loading').show();
}

function afterTableLoading($table, $wrap) {
    enableButtons($wrap);
    $table.find('tbody.list-body').show();
    $table.find('tbody.list-loading').hide();
}

function emptyTableMsg($table, msg) {
    msg = msg || '没有找到符合条件的数据！';
    var colspan = $table.data('colspan');
    $table.find('tbody.list-body').html('<tr><td colspan="' + colspan + '" class="text-center">' + msg + '</td></tr>')
}

/**
 * 获取DataTable的行数据（非现实出来的数据）
 * @param $table jQuery Object 操作的表
 * @param $tr  jQuery Object  处理的行
 * @returns {*}
 */
function getTableRowData($table, $tr) {
    return $table.DataTable().row($tr).data()
}

/**
 * 更新DataTable的行数据，（最后会重新刷新当前列表）
 * @param $table jQuery Object 操作的表
 * @param $tr  jQuery Object  处理的行
 * @param data 需要更新的数据
 */
function updateTableRowData($table, $tr, data) {
    $table.DataTable().row($tr).data(data).draw(false);
}

/**
 * 删除DataTable中的一行
 * @param $table jQuery Object 操作的表
 * @param $tr 需要删除的行
 */
function removeTableRow($table, $tr) {
    $table.DataTable().row($tr).remove().draw(false)
}

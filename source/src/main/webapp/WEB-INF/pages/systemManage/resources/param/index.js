customDataTable = null;
currentSelectData = null;
is_showAddView = false;
is_showEditView = false;
selectedCheckbox = null;

$(document).ready(function () {
    getTableData();
});

function closeButtonClick(button) {
    if (document.getElementById("add_dialog_div").style.display == "block") {
        $("#add_dialog_div").fadeOut("fast");
    }
    if (document.getElementById("modify_dialog_div").style.display == "block") {
        $("#modify_dialog_div").fadeOut("fast");
    }
    if (document.getElementById("delete_dialog_div").style.display == "block") {
        $("#delete_dialog_div").fadeOut("fast");
    }
    is_showAddView = false;
    is_showEditView = false;
    currentSelectData = null;
    selectedCheckbox = null;
}
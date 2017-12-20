function selectCheckBox(checkBox) {
    var checkVBoxs = document.getElementsByName("checkInfo");
    if (checkBox.id == "checkall") {
        setCheckBoxState(checkVBoxs, checkBox.checked);
    } else {
        var isAllSelect = true;
        for (var i = 0; i < checkVBoxs.length; i++) {
            if (checkVBoxs[i].checked == false) {
                document.getElementById("checkall").checked = false;
                isAllSelect = false;
                break;
            }
        }

        if (isAllSelect == true) {
            document.getElementById("checkall").checked = true;
        }
    }
}

function setCheckBoxState(checkVBoxs, isChecked) {
    for (var i = 0; i < checkVBoxs.length; i++) {
        checkVBoxs[i].checked = isChecked;
    }
}
function selectCheckBox2(checkBox) {
	var checkVBoxs = document.getElementsByName("checkInfo2");
	if (checkBox.id == "checkall2") {
		setCheckBoxState(checkVBoxs, checkBox.checked);
	} else {
		var isAllSelect = true;
		for (var i = 0; i < checkVBoxs.length; i++) {
			if (checkVBoxs[i].checked == false) {
				document.getElementById("checkall2").checked = false;
				isAllSelect = false;
				break;
			}
		}
		
		if (isAllSelect == true) {
			document.getElementById("checkall2").checked = true;
		}
	}
}

function setCheckBoxState(checkVBoxs, isChecked) {
	for (var i = 0; i < checkVBoxs.length; i++) {
		checkVBoxs[i].checked = isChecked;
	}
}
//自定义字典对象
function Dictionary() {
    this.data = new Array();

    this.put = function (key, value) {
        this.data[key] = value;
    };

    this.getValue = function (key) {
        return this.data[key];
    };

    this.remove = function (key) {
        this.data[key] = null;
    };

    this.isEmpty = function () {
        return this.data.length == 0;
    };

    this.size = function () {
        return this.data.length;
    };
}

function Map() {
    this.keys = new Array();
    this.data = new Array();

    this.put = function (key, value) {
        if (this.data[key] == null) {
            this.keys.push(value);
        }
        this.data[key] = value;
    };

    this.getValue = function (key) {
        return this.data[key];
    };

    this.getValueString = function () {
        var valuses = "";
        for (var key in this.data) {
            if (!isNaN(key)) {
                valuses += this.data[key] + ","
            }
        }
        return valuses.substring(0, valuses.length);
    };

    this.getKeyString = function () {
        var keys = "";
        for (var key in this.data) {
            if (!isNaN(key)) {
                keys += key + ","
            }
        }
        return keys.substring(0, keys.length);
    };

    this.remove = function (key) {
        this.keys.remove(key);
        this.data[key] = null;
    };

    this.isEmpty = function () {
        return this.keys.length == 0;
    };

    this.size = function () {
        return this.keys.length;
    };
}
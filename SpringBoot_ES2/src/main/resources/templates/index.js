const CONTENT_PATH = "/ES/product/";
//新增员工
$("#add").click(function () {
    var title = $("#title").val();
    var author = $("#author").val();
    var category = $("#category").val();
    var price = $("#price").val();
    $.ajax({
        async: false,
        type: "post",
        url: CONTENT_PATH + "add",
        data: JSON.stringify({
            title: title,
            author: author,
            category: category,
            price: price
        }),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            alert("新增商品成功！");
        }
    });
});

//es 查询
$("#esSearch").click(function () {
    $("#result").empty();
    $.ajax({
        async: false,
        type: "post",
        url: CONTENT_PATH + "esSearch",
        data: JSON.stringify({
            keyword: $("#keyword").val()
        }),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            //获取信息
            $("#totalTimeMillis").val(data.totalTimeMillis);
            //获取信息.
            let dataList = data.data;
            for (let i = 0; i < dataList.length; i++) {
                $("#result").append(
                    "<div>"
                    + "书名:" + dataList[i].showTitle + "&nbsp;&nbsp;&nbsp;"
                    + "作者名:" + dataList[i].author + "&nbsp;&nbsp;&nbsp;"
                    + "类别:" + dataList[i].category + "&nbsp;&nbsp;&nbsp;"
                    + "价格:" + dataList[i].price + "<br/>"
                    + "</div>"
                )
            }
        }
    });
});

//es 查询
$("#dbSearch").click(function () {
    $("#result").empty();
    $.ajax({
        async: false,
        type: "post",
        url: CONTENT_PATH + "dbSearch",
        data: JSON.stringify({
            keyword: $("#keyword").val()
        }),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            //获取信息
            $("#totalTimeMillis").val(data.totalTimeMillis);
            //获取信息.
            let dataList = data.data;
            for (let i = 0; i < dataList.length; i++) {
                $("#result").append(
                    "<div>"
                    + "书名:" + dataList[i].showTitle + "&nbsp;&nbsp;&nbsp;"
                    + "作者名:" + dataList[i].author + "&nbsp;&nbsp;&nbsp;"
                    + "类别:" + dataList[i].category + "&nbsp;&nbsp;&nbsp;"
                    + "价格:" + dataList[i].price + "<br/>"
                    + "</div>"
                )
            }
        }
    });
});
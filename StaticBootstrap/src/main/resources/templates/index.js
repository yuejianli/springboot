//员工列表
function queryParams(params){
    return params;
}
//员工列表
function handleClientData(res){
    console.log(res);
    return (res==undefined)?[]:res.data.result;
}
//给每行添加修改的操作按钮
function operateFormatter(value,row,index){
    return [
        '<a class="edit text-primary" href="javascript:void(0)" data-toggle="tooltip" title="修改">',
        '<i class="fa fa-pencil"></i>&nbsp;&nbsp修改&emsp;</a>',
        '<a class="delete text-danger" href="javascript:void(0)" data-toggle="tooltip" title="撤销">',
        '<i class="fa fa-minus"></i>&nbsp;&nbsp撤销&emsp;</a>'].join('');
}

var old_info;
//操作事件
window.operateEvents={
    'click .edit':function(e,value,row,index){
        old_info = row;
        $("#edit_name").val(row.name);
        $("#edit_age").val(row.age);
        $("#edit_description").val(row.description);
        $("#edit_popup").modal('show');
    },
    'click .delete':function(e,value,row,index){
        old_info = row;
        $("#prompt").html("您确定要删除员工：【<span class='text-danger'>"+row.name+"</span>】吗？");
        $("#delete_popup").modal('show');
    },
    'mouseover .edit':function(e,value,row,index){
        $(this).tooltip();
    },
    'mouseover .delete':function(e,value,row,index){
        $(this).tooltip();
    }
}

//新增按钮
$("#add").click(function (){
    $("#add_name").val("");
    $("#add_sex").val("");
    $("#add_sex").selectpicker('refresh');
    $("#add_age").val("");
    $("#add_description").val("");
    $("#add_popup").modal('show');
})

//新增员工
$("#add_submit").click(function(){
    var name = $("#add_name").val();
    var sex = $("#add_sex").val();
    var age = $("#add_age").val();
    var description = $("#add_description").val();
    $.ajax({
        async:false,
        type:"post",
        url:"user/addUser",
        data:JSON.stringify({
            name:name,
            sex:sex,
            age:age,
            description:description
        }),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function(data){
            Flavr.fprompt("新增员工成功！");
            $("#user_table").bootstrapTable("refresh","{silent:false}");
            $("#add_popup").modal('hide');
        }
    });
});

//修改员工
$("#edit_submit").click(function(){
    var name = $("#edit_name").val();
    var sex = $("#edit_sex").val();
    var age = $("#edit_age").val();
    var description = $("#edit_description").val();
    $.ajax({
        async:false,
        type:"post",
        url:"user/updateUser",
        data:JSON.stringify({
            id: old_info.id,
            name:name,
            sex:sex,
            age:age,
            description:description
        }),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function(data){
            Flavr.fprompt("修改员工成功！");
            $("#user_table").bootstrapTable("refresh","{silent:false}");
            $("#edit_popup").modal('hide');
        }
    });
});

$("#delete_submit").click(function(){
    $.ajax({
        async:false,
        type:"post",
        url:"user/deleteUser",
        data:JSON.stringify({
            id:old_info.id
        }),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function(data){
            Flavr.fprompt("删除员工成功！");
            $("#user_table").bootstrapTable("refresh","{silent:false}");
            $("#delete_popup").modal('hide');
        }
    });
});

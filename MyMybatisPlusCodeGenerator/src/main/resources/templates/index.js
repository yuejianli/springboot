$(function(){
    $("#downFile").hide();
})

//当点击自动生成时,进行处理
$("#autoCode").click(function(){
    let info=getInfo();
    console.log("输出值:"+JSON.stringify(info));
    $.ajax({
        async:false,
        type:"post",
        url:  '/mp/mp/codeGenerator',
        data:JSON.stringify(info),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function(result){
            //
            console.log("文件下载成功:"+"文件路径是:"+result.data.result);
            $("#downFile").attr("href"," /mp/mp/download?fileName="+result.data.result);
            $("#downFile").show();
        }
    });
})

//处理信息
function getInfo(){
    //1. 获取信息,进行组装
   let projectName=convertStr($("#projectName").val());
   let packageName=convertStr($("#packageName").val());

   //数据库的相关配置
   let dbTypeName=convertStr($("#dbTypeName").val());
   let databaseSource=convertStr($("#databaseSource").val());
   let host=convertStr($("#host").val());
   let port=convertStr($("#port").val());
   let dbName=convertStr($("#dbName").val());
   let username=convertStr($("#username").val());
   let password=convertStr($("#password").val());

   //项目配置

   let author=convertStr($("#author").val());
   //表配置

   let tableNames=convertStr($("#tableNames").val());
   let tablePrefixes=convertStr($("#tablePrefixes").val());
   let fieldPrefixes=convertStr($("#fieldPrefixes").val());
   let excludeTableNames=convertStr($("#excludeTableNames").val());
   let ignoreColumns=convertStr($("#ignoreColumns").val());

   //包配置
   let packageEntity=convertStr($("#packageEntity").val());
   let packageMapper=convertStr($("#packageMapper").val());
   let packageMapperXml=convertStr($("#packageMapperXml").val());
   let packageService=convertStr($("#packageService").val());
   let packageServiceImpl=convertStr($("#packageServiceImpl").val());
   let packageController=convertStr($("#packageController").val());

   //文件配置
   let fileNamePatternEntity=convertStr($("#fileNamePatternEntity").val());
   let fileNamePatternMapper=convertStr($("#fileNamePatternMapper").val());
   let fileNamePatternMapperXml=convertStr($("#fileNamePatternMapperXml").val());
   let fileNamePatternService=convertStr($("#fileNamePatternService").val());
   let fileNamePatternServiceImpl=convertStr($("#fileNamePatternServiceImpl").val());
   let fileNamePatternController=convertStr($("#fileNamePatternController").val());

   //xml 配置
   let baseMapper=convertStr($("#baseMapper").val());
   let baseColumn=convertStr($("#baseColumn").val());

    return {
        "author":author,
        "dbConfigRo":{
            "dbTypeName":dbTypeName,
            "databaseSource":databaseSource,
            "host":host,
            "port":port,
            "username":username,
            "password":password,
            "dbName":dbName
        },
        "dbTableRo":{
            "tableNames":tableNames,
            "tablePrefixes":tablePrefixes,
            "fieldPrefixes":fieldPrefixes,
            "excludeTableNames":excludeTableNames,
            "ignoreColumns":ignoreColumns
        },
        "fileConfigRo":{
            "projectName":projectName,
            "packageName":packageName,
            "packageEntity":packageEntity,
            "packageMapper":packageMapper,
            "packageMapperXml":packageMapperXml,
            "packageService":packageService,
            "packageServiceImpl":packageServiceImpl,
            "packageController":packageController,
            "fileNamePatternEntity":fileNamePatternEntity,
            "fileNamePatternMapper":fileNamePatternMapper,
            "fileNamePatternMapperXml":fileNamePatternMapperXml,
            "fileNamePatternService":fileNamePatternService,
            "fileNamePatternServiceImpl":fileNamePatternServiceImpl,
            "fileNamePatternController":fileNamePatternController
        },
        "projectConfigRo":{
            "baseMapper":baseMapper,
            "baseColumn":baseColumn
        },
    }

}

//测试数据库连接
$("#sqlConnection").click(function(){
    let info=getInfo();
    console.log("输出值:"+JSON.stringify(info));
    $.ajax({
        async:false,
        type:"post",
        url:  '/mp/mp/sqlConnection',
        data:JSON.stringify(info),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function(result){
           Flavr.fpromptpon(result.message);
        }
    });

})

$("#sqlCode").click(function(){
    let info={
        "sql":$("#selfSql").val()
    }
    console.log
    ("输出值:"+JSON.stringify(info));
    $.ajax({
        async:false,
        type:"post",
        url:  '/mp/mp/execSql',
        data:JSON.stringify(info),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function(result){
            Flavr.fpromptpon(result.message);
        }
    });

})

function convertStr(content){
    if(typeof content === "undefined" || content === null || content.trim() === "") {
       return "";
    }
    return content;
}
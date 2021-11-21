<html>
<head>
    <title>Welcome ${web} </title>
</head>
<body>
<h2> list 的相关展示</h2>
<ul>
<#list hobbys as hobby>
    <li>${hobby}</li>
</#list>
</ul>

<h2> list展示一起,</h2>
<#list hobbys>
    <ul>
        <#-- 用items 表示内部的元素， items -->
        <#items as hobby>
            <li>${hobby}
        </#items>
    </ul>
</#list>
<h2>分隔符展示</h2>
<p> hobbys: <#list hobbys as hobby>${hobby}<#sep>, </#list> </p>
<h2>展示表格的数据信息</h2>
<table>
    <th>
        <td>id编号</td>
        <td>名称</td>
        <td>年龄</td>
        <td>性别</td>
        <td>描述</td>
    </th>
    <#if uers??>
       <#list users as u>
           <tr>
               <td>${u.id}</td>
               <td>${u.name}</td>
               <td>${u.age}</td>
               <td><#if u.sex==1>男<#else>女</#if></td>
               <td>${u.description}</td>
           </tr>
       </#list>
        <#-- 如果为空的话， #else 表示为空的意义-->
        <#else>
        <tr>
            没有数据
        </tr>
        </#if>
</table>
</body>
</html>

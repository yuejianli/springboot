<html>
<head>
    <title>Welcome ${web} </title>
</head>
<body>
<!--单个if展示-->
<h1>Welcome ${user}! <#if user='yjl'>,领导牛逼</#if></h1>
<p>Our latest product:
    <a href="${info.url}">${info.name}</a>!
<h2>性别是:</h2>
<!--if else 展示-->
<#if sex=='男'>
     男
<#else>
     女
</#if>
<h2>成绩是:</h2>
<!--if elseif else-->
<#if score &gt;90 >
    优
<#elseif score &gt;80>
    良
<#elseif score &gt;70>
    中
<#elseif score &gt;60>
    及
<#else>
    差
</#if>>
</body>
</html>

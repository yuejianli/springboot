<html>
<head>
    <title>这是放置freemark的其他的相关的用法</title>
</head>
<body>
<#--字符串嵌套输出-->
<h3>字符串嵌套输出</h3>
${"hello ${name}"}
<h3>定义变量输出</h3>
<#assign number1 = 10>
<#assign number2 = 5>
<#-- 支持"+"、"－"、"*"、"/"、"%"运算符 -->
"+" : ${number1 + number2}
"－" : ${number1 - number2}
"*" : ${number1 * number2}
"/" : ${number1 / number2}
"%" : ${number1 % number2}
<h3>内建函数</h3>
第一个字母大写: ${str?cap_first}
所有字母小写: ${str?lower_case}
所有字母大写: ${str?upper_case}
<h3>将数据取整</h3>
<#assign floatData = 12.34>
数值取整数：${floatData?int}
<h3>获取集合的长度:</h3>
获取集合的长度：${users?size}
<h3>日期进行格式化</h3>
时间格式化：${dateTime?string("yyyy-MM-dd")}
<#--循环遍历map-->
Map集合：
<#assign mapData={"name":"程序员", "salary":10000}>
直接通过Key获取 Value值：${mapData["name"]}
通过Key遍历Map：
<#list mapData?keys as key>
    Key: ${key} - Value: ${mapData[key]}
</#list>
通过Value遍历Map：
<#list mapData?values as value>
    Value: ${value}
</#list>
</body>
</html>

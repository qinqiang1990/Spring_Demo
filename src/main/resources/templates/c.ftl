<#macro m1>   <#--定义指令m1 -->
	<b>aaabbbccc</b>
	<b>dddeeefff</b>
</#macro>

<@m1 /><@m1 />  <#--调用上面的宏指令 -->
------------------------------------------------------
<#macro m2 a b c >
	${a}--${b}--${c}
</#macro>
<@m2 a="老高" b="老张" c="老马" />
----------------------------------------------------
<#macro border> 
  <table border=4 cellspacing=0 cellpadding=4><tr><td> 
    <#nested> 
  </td></tr></table> 
</#macro> 
<@border >表格中的内容！</@border>
-------------------------------------------------------
测试命名空间：
<#import "m.ftl" as mm  />
<@mm.hello date="2010-2011" />

<#assign count = [1,23,45,67,89]>
<#assign num = 3..100>
<#list count as temp>
	${temp}
</#list>

<#list num as temp>
	${temp}
</#list>

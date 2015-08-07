<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

${doc.fields.field[0].@name}
----------------
<#list doc.fields.field[0].data as data>
${data.key}----${data.value}
</#list>
----------------
<#list doc.fields.field.data as data>
${data.key}----${data.value}
</#list>
-----------------
<#list doc["fields/field[@id='sex']/data"] as data>
${data.key}----${data.value}
</#list>
-----------------
<#import "m.ftl" as m  />
<#list ls as data>
<@m.select id="${data.id}" name="${data.name}" />
</#list>


</html>
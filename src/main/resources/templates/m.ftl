<#macro hello date>
  Hello ${date}
</#macro>

<#macro select id name>
<#local datas=doc["fields/field[@id='${id}']"]/>
${datas.@name}
<select id="${datas.@id}" name="${datas.@name}">
<option>${datas.@name}</option>
<#list datas.data as data>
<option value='${data.key}'>${data.value}</option>
</#list>
</select>
</#macro>

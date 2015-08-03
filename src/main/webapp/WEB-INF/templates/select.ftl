<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>了解点菜100网</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="/public/css/base.css">
    <link rel="stylesheet" href="/public/vendor/popup/jquery.popup.css">
    <link rel="stylesheet" href="/public/css/common.css">
    <link rel="stylesheet" href="/public/css/bottom.css">
    <!--[if lt IE 9]>
    <script src="/public/vendor/html5shiv-printshiv.js"></script>
    <![endif]-->
</head>

<body>
    		 

<#macro select id data>
	<select id="${id}" name="${id}">
		<#if data?is_hash_ex>
		  <#local keys=data?keys />
		  <#list keys as key>
          
          		<option value="${key}">${data[key]}</option>
            
          </#list>
                
		<#else>
		
			<#list data as one >
				<option>${one}</option>
			</#list>
			
		</#if>
	</select>
 
</#macro> 
<@select id="${id}"  data=data />

<@select id="${id}"  data={"1":"A","2":"B","3":"C"} />

						<div class="cooper treatment">
                            <h3 class="text-title ff-yahei">合作待遇</h3>
                            <div class="items">
                                <div class="item">
                                    <div class="des">
                                        <h3 class="ff-yahei">图片美工处理</h3>
                                        <p class="fs-14 fc-666">可根据商家所提供的产品图文及广告需求，由本司专业设计师进行图片美工处理，然后投放。</p>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="des">
                                        <h3 class="ff-yahei">广告文案撰写</h3>
                                        <p class="fs-14 fc-666">可根据商家需要，由本司资深问文案完成广告文案撰写，然后投放。</p>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="des">
                                        <h3 class="ff-yahei">站内焦点推送</h3>
                                        <p class="fs-14 fc-666">可根据商家需要，将由本司网站技术人员进行广告的置顶推送。</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="friendly-link" class="friendly link black-center-box cont">
                    <div class="title">
                        <h2 class="ff-yahei">友情链接</h2>
                        <h3>Friendly Link</h3>
                    </div>
                    <div class="content">
                        <div class="link imgs clearfix">
                         
                        </div>
                    </div>
                </div>
                <div id="disclaimer" class="disclaimer black-center-box cont">
                    <div class="title">
                        <h2 class="ff-yahei">免责申明</h2>
                        <h3>Disclaimer</h3>
                    </div>
                    <div class="content ff-yahei">
                        <h3>本公司在此特别声明对如下事宜不承担任何法律责任：</h3>
                        <ul class="fs-14">
                            <li>对您使用网站、与本网站相关的任何内容、服务或其它链接至本网站的站点、内容均不作直接、间接、法定、约定的保证。</li>
                            <li>无论在任何原因下（包括但不限于疏忽原因），对您或任何人通过使用本网站上的信息或由本网站链接的信息，或其他与本网站链接的网站信息所导致的损失或损害（包括直接、间接、特别或后果性的损失或损害），责任均由使用者自行承担（包括但不限于疏忽责任），使用者对本网站的使用即表明同意承担浏览本网站的全部风险。</li>
                            <li>点菜100平台上关于点菜100平台用户或其发布的相关信息（包括但不限于餐饮服务、酒店信息、图片、文字描述）均由用户自行提供，用户依法应对其提供的任何信息 承担全部责任。如果遇到诸如酒店服务质量、服务瑕疵、信息虚假等争议和纠纷，您可以依照相关法律法规直接与信息提供者和信息提供商解决，点菜100对该信息不负任何直接的、间接的法律责任。</li>
                            <li>任何单位或个人认为点菜100平台网页内容（包括但不限于点菜100平台用户发布的餐饮信息）可能涉嫌侵犯其合法权益，应该及时向点菜100提出书面权利通知，并提供身份证明、权属证明、具体链接（URL）及详细侵权情况证明。点菜100在收到上述法律文件后，将会依法尽快移除相关涉嫌侵权的内容。</li>
                        </ul>
                    </div>
                </div>
                <div id="contact-us" class="contact us black-center-box cont clearfix">
                    <div class="title">
                        <h2 class="ff-yahei">联系我们</h2>
                        <h3>Contact us</h3>
                    </div>
                    <div class="content">
                        <div class="map-wrap fl">
                            <div id="map" class="map"></div>
                        </div>
                        <div class="methods fr fs-14 ff-yahei fc-666">
                            <div class="item method">0512-62798998&nbsp;&nbsp;&nbsp;&nbsp;0512-62781189</div>
                            <div class="item method">天天一百</div>
                            <div class="item method">jtb@tt100.cn</div>
                            <div class="item method">www.tt100.cn</div>
                            <div class="item method">天天一百</div>
                            <div class="item method">江苏省苏州工业园星湖街328号创意产业园10栋401单元</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 
</body>

</html>

<html>
<#include "../common/head.ftl">
<body>

<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">

<div id="page-content-wrapper">

<div class="container-fluid">
	<div class="row clearfix">
		<div class="col-md-12 column">
		<div class="page-header">
			<h1>
				卖家客户端<small>商品订单</small>
			</h1>
		</div>
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th>订单id</th>
                        <th>姓名</th>
                        <th>手机号</th>
                        <th>地址</th>
                        <th>金额</th>
                        <th>订单状态</th>
                        <th>支付状态</th>
                        <th>创建时间</th>
                        <th colspan="2">操作</th>
					</tr>
				</thead>
				<tbody>
				<#list orderMasterPage.content as orderMasterDTO>
					<tr>
					    <td>${orderMasterDTO.orderId}</td>
	                    <td>${orderMasterDTO.buyerName}</td>
	                    <td>${orderMasterDTO.buyerPhone}</td>
	                    <td>${orderMasterDTO.buyerAddress}</td>
	                    <td>${orderMasterDTO.orderAmount}</td>
	                    <td>${orderMasterDTO.getOrderStatusEnum().message}</td>
	                    <td>${orderMasterDTO.getPayStatus().message}</td>
	                    <td>${orderMasterDTO.createTime}</td>
						<td>
						  <a href="/sell/seller/order/detail?orderId=${orderMasterDTO.orderId}">详情</a>
						</td>
						<td>
						  <#if orderMasterDTO.getOrderStatusEnum().message == "新订单">
                            <a href="/sell/seller/order/cancel?orderId=${orderMasterDTO.orderId}">取消</a>
                          </#if>
						</td>
					</tr>
					 </#list>
				</tbody>
				
			</table>
		</div>
		<!-- 分页 -->
	<div class="row clearfix">
		<div class="col-md-12 column">
		    <ul class="pagination pull-right" >
		    <#if page lte 1>
		        <li class="disabled"><a href="#">上一页</a></li>
			<#else>
			    <li><a href="/sell/seller/order/list?page=${page - 1}&size=${size}">上一页</a></li>
		    </#if>
				<#list 1..orderMasterPage.getTotalPages() as index>
				  <#if index == page>
				      <li class="disabled"><a href="#">${index}</a></li>
				  <#else>
				      <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
				  </#if>
				</#list>
				<#if page gte orderMasterPage.getTotalPages()>
				    <li class="disabled"><a href="#">下一页</a></li>
				<#else>
					<li><a href="/sell/seller/order/list?page=${page + 1}&size=${size}">下一页</a></li>
				</#if>
			</ul>
		</div>
	</div>
	
</div>
	</div>
</div>
</div>
</div>

<!--WebSocket新订单播放音乐 -->
<audio id="orderMusic" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg" />
</audio>


<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<!-- WebSocket新订单消息推送 -->
	<div class="modal fade" id="Mymodal" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								卖家客户端
							</h4>
						</div>
						<div class="modal-body">
							您有新订单，请注意查收。
						</div>
						<div class="modal-footer">
							 <button onclick="javascript:document.getElementById('orderMusic').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
							 <button onclick="location.reload()" type="button" class="btn btn-primary">查看</button>
						</div>
					</div>
				</div>
	</div>

<!-- WebSocket: https://www.runoob.com/html/html5-websocket.html -->
<script type="text/javascript">
	var webSocket = null;
	if("WebSocket" in window) {
	console.log("浏览器支持WebSocket");
	} else {
	 alert("浏览器不支持WebSocket!");
	 }
    webSocket = new WebSocket("ws://127.0.0.1:8080/sell/webSocket");

	webSocket.onopen = function(event) {
	console.log("WebSocket建立连接");
	
	}
	webSocket.onclose = function(event) {
	console.log("WebSocket关闭连接");
	}
	webSocket.onerror = function() {
	alert("WebSocket通信错误");
	}
	
	webSocket.onmessage = function(event) {
	console.log("接收数据:" + event.data);
	$("#Mymodal").modal('show');
        document.getElementById('orderMusic').play();
	}
    <!-- 关闭窗口通信停止 -->
	window.onbeforeunload = function() {
	webSocket.close();
	}


</script>
</body>

</html>






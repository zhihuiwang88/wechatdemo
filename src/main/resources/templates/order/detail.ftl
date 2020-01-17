<html>
<#include "../common/head.ftl">
<body>

<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">

<div id="page-content-wrapper">

<div class="container">
	<div class="row clearfix">
		<div class="col-md-4 column">
			<table class="table">
				<thead>
					<tr>
						<th>订单ID</th>
						<th>订单总金额</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${orderMasterDto.orderId}</td>
						<td>${orderMasterDto.orderAmount}</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<!--订单详情 -->
		<div class="col-md-12 column">
			<table class="table">
				<thead>
					<tr>
						<th>商品ID</th>
					    <th>商品名称</th>
						<th>价格</th>
						<th>数量</th>
						<th>总额</th>
					</tr>
				</thead>
				<tbody>
				<#list orderMasterDto.orderDetail as orderDetails>
				  <tr>
						<td>${orderDetails.productId}</td>
						<td>${orderDetails.productName}</td>	
						<td>${orderDetails.productPrice}</td>
						<td>${orderDetails.productQuantity}</td>
						<td>${orderDetails.productPrice * orderDetails.productQuantity}</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div>
		<!-- 按钮-->
		<div class="col-md-12 column">
		  <#if orderMasterDto.getOrderStatusEnum().getMessage() == "新订单">
		     <a href="/sell/seller/order/cancel?orderId=${orderMasterDto.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
             <a href="/sell/seller/order/finish?orderId=${orderMasterDto.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
          </#if>
        </div>
		
	</div>
</div>
	</div>
</div>
</body>
</html>

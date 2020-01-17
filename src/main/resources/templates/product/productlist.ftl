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
				卖家客户端<small>商品列表</small>
			</h1>
		</div>
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th>商品id</th>
                        <th>名称</th>
                        <th>图片</th>
                        <th>单价</th>
                        <th>库存</th>
                        <th>描述</th>
                        <th>类目</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
                        <th colspan="2">操作</th>
					</tr>
				</thead>
				<tbody>
				<#list productInfoPage.content as productInfo>
					<tr>
					    <td>${productInfo.productId}</td>
	                    <td>${productInfo.productName}</td>
	                    <td><img src="${productInfo.productIcon}" height="90" width="90"></td>
	                    <td>${productInfo.productPrice}</td>
	                    <td>${productInfo.productStock}</td>
	                    <td>${productInfo.productDescription}</td>
	                    <td>${productInfo.categoryType}</td>
	                    <td>${productInfo.createTime}</td>
	                    <td>${productInfo.updateTime}</td>
	                    <td><a href="/sell/seller/product/productview?productId=${productInfo.productId}">修改</td>
						<td>
						  <#if productInfo.getProductStatusEnum().message == "产品在架">
                            <a href="/sell/seller/product/soldout?productId=${productInfo.productId}">下架</a>
                          <#else>
                          	<a href="/sell/seller/product/putaway?productId=${productInfo.productId}">上架</a>
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
			    <li><a href="/sell/seller/product/list?page=${page - 1}&size=${size}">上一页</a></li>
		    </#if>
				<#list 1..productInfoPage.getTotalPages() as index>
				  <#if index == page>
				      <li class="disabled"><a href="#">${index}</a></li>
				  <#else>
				      <li><a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a></li>
				  </#if>
				</#list>
				<#if page gte productInfoPage.getTotalPages()>
				    <li class="disabled"><a href="#">下一页</a></li>
				<#else>
					<li><a href="/sell/seller/product/list?page=${page + 1}&size=${size}">下一页</a></li>
				</#if>
			</ul>
		</div>
	</div>
	
</div>
	</div>
</div>
</div>
</div>
</body>

</html>






<html>
<#include "../common/head.ftl">
<body>
<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">
<div id="page-content-wrapper">

  <div class="container">
	<div class="row clearfix">
		<div class="col-md-10 column">
			<table class="table table-bordered table-condensed">
				<thead>
					<tr>
						<th>类目ID</th>
						<th>名字</th>
						<th>type</th>
						<th>创建时间</th>
						<th>修改时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<#list productCategoryList as categoryList>
					<tr>
						<td>${categoryList.categoryId}</td>
						<td>${categoryList.categoryName}</td>
						<td>${categoryList.categoryType}</td>
						<td>${categoryList.createTime}</td>
						<td>${categoryList.updateTime}</td>
						<td><a href="/sell/seller/category/categoryview?categoryId=${categoryList.categoryId}" >修改</td>
					</tr>
				</#list>	
				</tbody>
			</table>
		</div>
		
	</div>
</div>



</div>
</div>
</body>
</html>
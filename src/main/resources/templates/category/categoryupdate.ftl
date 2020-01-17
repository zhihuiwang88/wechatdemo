<html>
<#include "../common/head.ftl">
<body>
<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">
<div id="page-content-wrapper">

<div class="container">
	<div class="row clearfix">
		<div class="col-md-6 column">
			<form role="form" action="/sell/seller/category/save" method="post">
				<div class="form-group">
					 <label>类目名字</label>
					 <input type="text" name="categoryName" class="form-control" value="${(productCategory.categoryName)!''}"/>
				</div>
				<div class="form-group">
					 <label>类目类型</label>
					 <input type="text" name="categoryType" class="form-control" value="${(productCategory.categoryType)!''}" />
				</div>
				<input  name="categoryId" type="hidden" value="${(productCategory.categoryId)!''}" />
				<button type="submit" class="btn btn-default btn-primary pull-right">提交</button>
			</form>
		</div>
	</div>
</div>


</div>
</div>
</body>
</html>

<html>
<#include "../common/head.ftl">
<body>
<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">

<div id="page-content-wrapper">

<div class="container">
	<div class="row clearfix">
		<div class="col-md-8 column">
			<form role="form" action="/sell/seller/product/save" method="post">
				<div class="form-group">
					 <label>商品名称</label>
					 <input type="text" name="productName" value="${(productInfo.productName)!''}" class="form-control"/>
				</div>
				<div class="form-group">
					 <label>单价</label>
					 <input type="text" name="productPrice" value="${(productInfo.productPrice)!''}" class="form-control"/>
				</div>
			    <div class="form-group">
					 <label>库存</label>
					 <input type="text" name="productStock" value="${(productInfo.productStock)!''}" class="form-control"/>
				</div>
			    <div class="form-group">
					 <label>描述</label>
					 <input type="text" name="productDescription" value="${(productInfo.productDescription)!''}" class="form-control"/>
				</div>
			   <div class="form-group">
	                <label>图片</label>
	                 <img src="${(productInfo.productIcon)!''}" height="90" width="90">
	                <input id="productIcon" name="productIcon" type="text" hidden="hidden" value="${(productInfo.productIcon)!''}"/>
	                <div class="file-loading">
	                    <input id="input-id" type="file">
	                    <p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>
	                </div>
                 </div>
			    <div class="form-group">
					 <label>类目</label>
					 <select name="categoryType" class="form-control">
					 <#list productCategoryList as productCategory>
					   <option value="${productCategory.categoryType}"
					       <#if  ((productInfo.categoryType)??) && productCategory.categoryType == productInfo.categoryType>
					      selected="selected"
					       </#if>
					     >${productCategory.categoryName}
					   </option>
					 </#list>
					  </select>
				</div>
				 <input hidden type="text" name="productId" value="${(productInfo.productId)!''}" />
				 <button  type="submit" class="btn btn-default btn-primary pull-right">提交</button>
			</form>
		</div>
	</div>
</div>
	</div>
</div>
</body>

</html>






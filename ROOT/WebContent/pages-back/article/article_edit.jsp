<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type="text/javascript"></script>

<!-- test -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta
	content="Preview page of Metronic Admin Theme #1 for bootstrap markdown & wysiwyg editors"
	name="description" />
<meta content="" name="author" />

<!-- MULTI SELECT -->
<link href="path/to/multiselect.css" media="screen" rel="stylesheet"
	type="text/css">
<script src="path/to/jquery.multi-select.js" type="text/javascript"></script>
<!-- END MULTI SELECT -->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<link
	href="../assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-summernote/summernote.css"
	rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL PLUGINS -->


<link rel="shortcut icon" href="favicon.ico" />
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/js.cookie.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/jquery.blockui.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
	type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script
	src="../assets/global/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-markdown/lib/markdown.js"
	type="text/javascript"></script>
<script
	src="./../assets/global/plugins/bootstrap-markdown/js/bootstrap-markdown.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-summernote/summernote.min.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/pages/scripts/components-editors.min.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<!-- test -->

<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />


<script src="../assets/global/plugins/jquery.blockui.min.js"
	type="text/javascript"></script>


<script src="../assets/layouts/global/scripts/quick-sidebar.min.js"
	type="text/javascript"></script>
<script src="../assets/layouts/global/scripts/quick-nav.min.js"
	type="text/javascript"></script>
<link href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"
	rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script
	src="../assets/global/plugins/bootstrap-tagsinput/bootstrap-tagsinput.min.js"
	type="text/javascript"></script>
<script
	src="../assets/pages/scripts/components-bootstrap-tagsinput.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-tagsinput/bootstrap-tagsinput-typeahead.css"
	rel="stylesheet" type="text/css" />

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"> <i
				class="fa fa-edit"></i> Article Edit
			</span>
		</div>
		<div class="actions right">
			<button class="btn btn-danger"
				style="float: left; border-radius: 8px !important;"
				onclick="deleteFunc()"><i class="fa fa-trash"></i> Delete Article</button>

			<script type="text/javascript">
				function deleteFunc() {
					var r = confirm("ยืนยันการลบบทความ");
					if (r == true) {
						document.location = "article_delete?id=${article.articleId}";
					} else {
					}
				}
			</script>
		</div>
	</div>
	<div class="portlet-body form">
		<div class="row">
			<div class="col-md-1"></div>
			<form action="article_update" class="form-horizontal col-md-10"
				method="post" autocomplete="off" enctype="multipart/form-data">
				<div class="form-body">
					<div class="row">
						<div class="form-group col-md-9">
							<input name="articleId" type="hidden" class="form-control"
								value="${article.articleId}"> <input type="hidden"
								class="form-control" value="${article.fileId}" name="fileId">
							<label for="default" class="control-label">Topic Name</label> <input
								name="topic" type="text" class="form-control"
								value="${article.topic}">
						</div>
						<div class="col-md-3">
							<label for="default" class="control-label">Author</label> <select
								class="form-control select2me" name="author" disabled>
								<option value="${article.userId}">${article.userId}</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 text-center">
						<div class="form-group last">
							<div class="fileinput fileinput-new" data-provides="fileinput">
								<div class="fileinput-new thumbnail"
									style="width: 200px; height: 150px;">
									<c:forEach var="arList" items="${arList}" varStatus="Count">
										<c:if test="${arList.article_id == article.articleId}">
											<c:choose>
												<c:when
													test="${arList.type == '.png' || arList.type == '.jpg' || arList.type == '.gif' || arList.type == '.jpeg' }">
													<img src="${arList.path}" alt="" name="path"
														style="width: 200px; height: 150px;">
												</c:when>
											</c:choose>
											<!-- <img
									src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=cover+image"
									alt="" /> -->
										</c:if>
										<%-- <c:if test="${News.path != Null}">
								<img
									src="${News.path}"
									alt="" />
								</c:if> --%>
									</c:forEach>
								</div>
								<div class="fileinput-preview fileinput-exists thumbnail"
									style="max-width: 200px; max-height: 150px;"></div>
								<div class="text-center">
									<span class="btn default btn-file"> <span
										class="fileinput-new"><i class="fa fa-picture-o"></i> Select image </span> <span
										class="fileinput-exists"> Change </span> <input type="file"
										name="fileUpload">
									</span> <a href="javascript:;" class="btn red fileinput-exists"
										data-dismiss="fileinput"> Remove </a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="single" class="control-label"> Article Type </label>
							<select id="single" class="form-control select2" name="type">
								<c:forEach var="articleType" items="${articleType}"
									varStatus="Count">
									<c:choose>
										<c:when test="${Count.count eq article.articleTypeId}">
											<option value="${articleType.article_type_id}" selected>${articleType.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${articleType.article_type_id}">${articleType.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<br>
						<div class="form-group">
							<label for="single" class="control-label">Tags </label>
							<div>
								<input name="tags" type="text" class="form-control input-large"
									value="<c:forEach var="ArticleInTag" items="${ArticleInTag}"
									varStatus="Count">
									<c:if test="${ArticleInTag.article_id eq article.articleId}">
								${ArticleInTag.name}
								</c:if> ,
								</c:forEach>" data-role="tagsinput">
								
							</div>
						</div>
					</div>
				</div>


				<div class="form-group">
					<textarea name="detail" data-provide="markdown" rows="10">${article.detail}</textarea>
				</div>

				<c:forEach var="News" items="${News}" varStatus="Count">
								${News.topic}<br>

				</c:forEach>

				<br>
				<div class="">
					<div class="row">
						<div class="col-xs-12" style="text-align: center;">
							<button type="submit" class="btn sbold blue" value="Submit">
								<i class="fa fa-save"></i>&nbsp;Save
							</button>
							<button type="reset" class="btn  red">
								<i class="fa fa-close"></i>&nbsp;Cancel
							</button>
						</div>
					</div>
				</div>
			</form>
			<div class="col-md-1"></div>
		</div>
	</div>
</div>


<%@page import="model.Category"%>
<%@page import="model.Products"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.*"%>
<%@ page import="java.text.*"%>

<!DOCTYPE html>
<html class="no-js" lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Admin -Sản Phẩm</title>
<meta name="description" content="Admin - Bán hàng">
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="../include/css.jsp"></jsp:include>
</head>
<body>


	<!-- Right Panel -->

	<div id="right-panel" class="right-panel">

		<jsp:include page="../include/header.jsp"></jsp:include>

		

		<div class="content">
			<div class="animated fadeIn">
				<div class="row">

					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<strong class="card-title">Danh sách</strong> <a
									class="btn btn-primary float-right" href="AdminAddProducts"><span><i
										class="fa fa-plus-square"></i></span> Thêm</a>
							</div>
							<div class="card-body">
								<table id="bootstrap-data-table"
									class="table table-striped table-bordered" style="width: 100%;">
									<thead>
										<tr>
											<th>ID</th>
											<th>Tên</th>
											<th>Loại</th>
											<th>Mô tả</th>
											<th>Giá</th>
											<th>KM</th>
											<th>Nổi bật</th>
											<th>Lượt thích</th>
											<th>Ảnh</th>
											<th>Chi tiết</th>
											<th>Sửa</th>
											<th>Xóa</th>
											
										</tr>
									</thead>
									<tbody>
										<%
											DecimalFormat myFormatter = new DecimalFormat("###,###.###");

											String realRootPath = request.getServletContext().getRealPath("");
											String folderUrl = "assets/img/shop/product/";

											ArrayList<Products> list = (ArrayList<Products>) request.getAttribute("lstProducts");
											for (Products l : list) {
										%>
										<tr>
											<td><%=l.getId()%></td>
											<td><%=l.getTenSanPham()%></td>
											<td>
												<%
													for (Category category : (ArrayList<Category>) request.getAttribute("lstCategorys")) {
															long a = category.getId();
															long b = l.getId_loaiSanPham();
															if (a == b) {
																out.print(category.getTenLoaiSanPham());
															}
														}
												%>
											</td>
											<td style="min-width: 150px;"><%=l.getMoTa()%></td>
											<td><%=myFormatter.format(l.getGiaGoc())%>đ</td>
											<td><%=l.getKhuyenMai()%>%</td>
											<td><%=l.getTinhTrang()%></td>
											<td><%=l.getLuotThich()%></td>
											<td style="max-width: 50px;">
												<%
													if (l.getAnhChinh() != "") {
												%> <a
												href="admin/../assets/img/shop/product/<%=l.getAnhChinh()%>">
													<img alt=""
													src="admin/../assets/img/shop/product/<%=l.getAnhChinh()%>"
													style="width: 100%">
											</a> <%
 	} else {
 %> <img alt="" src="https://placehold.it/270x270" style="width: 100%">
												<%
													}
												%>
											</td>
											<td><a href="AdminListDetailProduct?id=<%=l.getId()%>"
												class="btn btn-warning btn-block">Xem</a></td>
											<td><a class="btn btn-success  btn-block"
											
												href="AdminEditProducts?id=<%=l.getId()%>"><span><i
														class="fa fa-edit"></i></span> Sửa</a>
														</td>
														<td>
												<a class="btn btn-secondary btn-block"
												href="AdminDeleteProducts?id=<%=l.getId()%>"	>
													<span><i class="fa fa-trash-o"></i></span> Xóa
												</a></td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- .animated -->
		</div>
		<!-- .content -->
		<div class="clearfix"></div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#bootstrap-data-table-export').DataTable();
		});
	</script>

	<%
		if (session.getAttribute("Edit") == "Success") {
	%>
	<script>
		swal('Sửa dữ liệu thành công');
	</script>
	<%
		session.removeAttribute("Edit");
		}
	%>

	<%
		if ((String) session.getAttribute("Add") == "Success") {
	%>
	<script>
		document.write("haha");
		swal('Thêm dữ liệu thành công');
	</script>
	<%
		session.removeAttribute("Add");
		}
	%>

	<%
		if ((String) session.getAttribute("Delete") == "Success") {
	%>
	<script>
		swal('Xóa dữ liệu thành công');
	</script>
	<%
		session.removeAttribute("Delete");
		}
	%>

	<script>
    function Delete(id) {
    	swal({
  		  title: "Bạn có chắc chắn muốn xóa dữ liệu?",
  		  text: "Sau khi xóa, bạn sẽ không thể phục hồi dữ liệu này!",
  		  type: 'warning',
  		  showCancelButton: true,
  		  confirmButtonColor: '#3085d6',
  		  cancelButtonColor: '#d33',
  		  confirmButtonText: 'Yes'
  		}).then((result) => {
  		  if (result.value) {
  			  window.location.href = "AdminDeleteProducts?id=" + id;
  		  } else {
  			  swal("Dữ liệu của bạn không thay đổi!");
  		  }
  		});
    }
  </script>
	<!-- /#right-panel -->

	<!-- Right Panel -->

	<!-- Scripts -->

	<jsp:include page="../include/js.jsp"></jsp:include>

	<script src="admin/lib/assets/js/lib/data-table/datatables.min.js"></script>

	
</body>
</html>

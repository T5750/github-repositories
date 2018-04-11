var prefix = "/manage/ocrbd";
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						search : true, // 是否显示搜索框
						showColumns : true, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
						// "server"
						// queryParams : queryParams,
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{ // 列配置项
									// 数据类型，详细参数配置参见文档http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
									checkbox : true
								// 列表中显示复选框
								},
								{
									field : 'ocrId', // 列字段名
									title : '主键ID' // 列标题
								},
								{
									field : 'wordsResultNum',
									title : '文字识别个数'
								},
								{
									field : 'bankName',
									title : '银行卡名称'
								},
								{
									field : 'bankCardNumber',
									title : '银行卡卡号'
								},
								{
									field : 'bankCardType',
									title : '银行卡类型'
								},
								{
									field : 'words',
									title : '识别的文字集'
								},
								{
									field : 'address',
									title : '住址'
								},
								{
									field : 'birth', // 列字段名
									title : '出生' // 列标题
								},
								{
									field : 'sex',
									title : '性别',
                                    formatter:function (value,row,index) {
										var gender = "";
                                        if (value=='male'){
											gender="男性";
										}else{
                                            gender="女性";
										}
                                        return gender;
                                    }
								},
								{
									field : 'name',
									title : '姓名'
								},
								{
									field : 'idCardNum',
									title : '公民身份号码'
								},
								{
									field : 'nation',
									title : '民族'
								},
								{
									field : 'issueDate',
									title : '签发日期'
								},
								{
									field : 'authority',
									title : '签发机关'
								},
								{
									field : 'expiryDate',
									title : '失效日期'
								},
								{
									field : 'imagePath',
									title : '识别图像',
									formatter:function (value,row,index) {
										var a = '<img class="faceimage" src="'+value+'" width="66px" height="66px" onerror="this.src=\'/img/loadfail.png\'">';
										return a;
                                    }
								},
								{
									title : '操作',
									field : 'roleId',
									align : 'center',
									formatter : function(value, row, index) {
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.ocrId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										return d;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code === 0) {
					layer.msg("删除成功");
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})

}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	}, function() {
		var ids = new Array();
		$.each(rows, function(i, row) {
			ids[i] = row['ocrId'];
		});
		console.log(ids);
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}
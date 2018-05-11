﻿<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>商户通知</title>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/global.css" media="all">
		<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/table.css" />
	</head>

	<body>
		<div class="admin-main">

            <blockquote class="layui-elem-quote">
                <div class="layui-btn layui-btn-small" id=""></div>
                <div class="layui-form" style="float:right;">
                    <div class="layui-form-item" style="margin:0;">
                        <label class="layui-form-label">订单号</label>
                        <div class="layui-input-inline">
                            <input type="text" name="orderId" id="orderId" autocomplete="off" class="layui-input">
                        </div>
						<label class="layui-form-label">商户单号</label>
                        <div class="layui-input-inline">
                            <input type="text" name="mchOrderNo" id="mchOrderNo" autocomplete="off" class="layui-input">
                        </div>
                        <label class="layui-form-label">通知状态</label>
                        <div class="layui-input-inline">
                                <select name="status" id="status" lay-search="">
                                <option value="-99">所有状态</option>
                                <option value="1" >通知中</option>
                                <option value="2" >通知成功</option>
                                <option value="3" >通知失败</option>
                            </select>
                        </div>
                        <div class="layui-form-mid layui-word-aux" style="padding:0;">
                            <button id="search" lay-filter="search" class="layui-btn" lay-submit><i class="fa fa-search" aria-hidden="true"></i> 查询</button>
                        </div>
                    </div>
                </div>
            </blockquote>

			<fieldset class="layui-elem-field">
				<legend>通知列表</legend>
				<div class="layui-field-box layui-form">
					<table class="layui-table admin-table">
						<thead>
							<tr>
								<th style="width: 30px;"><input type="checkbox" lay-filter="allselector" lay-skin="primary"></th>
                                <th>订单号</th>
                                <th>商户ID</th>
                                <th>订单类型</th>
                                <th>通知次数</th>
                                <th>状态</th>
                                <th>最后通知</th>
                                <th>操作</th>
							</tr>
						</thead>
						<tbody id="content">
						</tbody>
					</table>
				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="paged" class="page">
				</div>
			</div>
		</div>
		<!--模板-->
		<script type="text/html" id="tpl">
			{{# layui.each(d.list, function(index, item){ }}
			<tr>
				<td><input type="checkbox" lay-skin="primary"></td>
                <td>{{ item.orderId }}</td>
                <td>{{ item.mchId }}</td>
                <td>
                    {{# if(item.orderType === '1'){ }} <span>支付</span> {{#  } }}
                    {{# if(item.orderType === '2'){ }} <span>转账</span> {{#  } }}
                    {{# if(item.orderType === '3'){ }} <span>退款</span> {{#  } }}
				</td>
                <td>{{ item.notifyCount }}</td>
                <td>
                    {{# if(item.status === 1){ }} <span style="color: blue">通知中</span> {{#  } }}
                    {{# if(item.status === 2){ }} <span style="color: green">通知成功</span> {{#  } }}
                    {{# if(item.status === 3){ }} <span style="color: red">通知失败</span> {{#  } }}
                </td>
                <td>{{ item.lastNotifyTime }}</td>
				<td>
					<a href="javascript:;" data-id="{{ item.orderId }}" data-opt="view" class="layui-btn layui-btn-normal layui-btn-mini">查看详情</a>
					<a href="javascript:;" data-id="{{ item.orderId }}" data-opt="edit" class="layui-btn layui-btn-mini">发送通知</a>
					<!--<a href="javascript:;" data-id="{{ item.mchId }}" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>-->
				</td>
			</tr>
			{{# }); }}
		</script>
		<script type="text/javascript" src="../plugins/layui/layui.js"></script>
		<script>
			layui.config({
				base: '../js/'
			});

			layui.use(['paging', 'form'], function() {
				var $ = layui.jquery,
					paging = layui.paging(),
					layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
					layer = layui.layer, //获取当前窗口的layer对象
					form = layui.form();

                paging.init({
                    openWait: true,
					url: '/mch_notify/list?v=' + new Date().getTime(), //地址
					elem: '#content', //内容容器
					params: { //发送到服务端的参数
					},
					type: 'GET',
					tempElem: '#tpl', //模块容器
					pageConfig: { //分页参数配置
						elem: '#paged', //分页容器
						pageSize: 10 //分页大小
					},
					success: function() { //渲染成功的回调
						//alert('渲染成功');
					},
					fail: function(msg) { //获取数据失败的回调
						//alert('获取数据失败')
					},
					complate: function() { //完成的回调
						//alert('处理完成');
						//重新渲染复选框
						form.render('checkbox');
						form.on('checkbox(allselector)', function(data) {
							var elem = data.elem;

							$('#content').children('tr').each(function() {
								var $that = $(this);
								//全选或反选
								$that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
								form.render('checkbox');
							});
						});

                        //绑定所有预览按钮事件
                        $('#content').children('tr').each(function() {
                            var $that = $(this);
                            $that.children('td:last-child').children('a[data-opt=view]').on('click', function() {
                                viewForm($(this).data('id'));
                            });
                        });

						//绑定所有编辑按钮事件						
						$('#content').children('tr').each(function() {
							var $that = $(this);
							$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
                                layer.confirm('确定重新发送通知么？', {
                                    btn: ['发送','不发送'] //按钮
                                }, function(){
                                    layer.msg('功能还再开发中...', {icon: 1});
                                }, function(){
                                });
							});
						});

                        //绑定所有删除按钮事件
                        $('#content').children('tr').each(function() {
                            var $that = $(this);
                            $that.children('td:last-child').children('a[data-opt=del]').on('click', function() {
                                layer.msg($(this).data('id'));
                            });
                        });

					},
				});
				//获取所有选择的列
				$('#getSelected').on('click', function() {
					var names = '';
					$('#content').children('tr').each(function() {
						var $that = $(this);
						var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
						if($cbx) {
							var n = $that.children('td:last-child').children('a[data-opt=edit]').data('name');
							names += n + ',';
						}
					});
					layer.msg('你选择的名称有：' + names);
				});

				$('#search').on('click', function() {
					var mchOrderNo = $("#mchOrderNo").val();
					var orderId = $("#orderId").val();
                    var status = $("#status").val();
                    paging.get({
                        "mchOrderNo": mchOrderNo,
						"orderId":orderId,
						"status":status,
                        "v":new Date().getTime()
                    });
				});

				var addBoxIndex = -1;

				$('#import').on('click', function() {
					var that = this;
					var index = layer.tips('只想提示地精准些', that, { tips: [1, 'white'] });
					$('#layui-layer' + index).children('div.layui-layer-content').css('color', '#000000');
				});

				function viewForm(id) {
                    //本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
                    $.get('/mch_notify/view.html?orderId=' + id, null, function(form) {
                        addBoxIndex = layer.open({
                            type: 1,
                            title: '订单详情',
                            content: form,

                            shade: false,
                            offset: ['100px', '30%'],
                            area: ['600px', '550px'],
                            zIndex: 19950924,
                            maxmin: false,

                            full: function(elem) {
                                var win = window.top === window.self ? window : parent.window;
                                $(win).on('resize', function() {
                                    var $this = $(this);
                                    elem.width($this.width()).height($this.height()).css({
                                        top: 0,
                                        left: 0
                                    });
                                    elem.children('div.layui-layer-content').height($this.height() - 95);
                                });
                            },
                            end: function() {
                                addBoxIndex = -1;
                            }
                        });
                        layer.full(addBoxIndex);
                    });
				}
			});
		</script>
	</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>公寓管理系统</title>
    <%--<script src="/js/admin/ajax.js" type="text/javascript"></script>--%>
    <%--<script src="/js/admin/aIndex.js" type="text/javascript"></script>--%>

    <jsp:include page="/pages/common/admin.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/base.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inner_page.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu_comm.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sf_actived.css"/>
    <script type="application/javascript">
        var params = {};
        $.post("checkAdminLogin.action",params,function(data){
            var result = data.replace(/[\ \"?]/g, "");
            if ("0" == result){
                window.location = "login.action";
            }
        });
        $(function () {
            $('#tree').tree({
                url: 'MenuList.action',
                onClick: function (node) {
                    menuClick(node.text, node.url);
                },
                loadFilter: function (rows) {
                    return convert(rows);
                }
            });

            $('#poral').portal({
                border:false,
                fit:true
            });

            var p = $('<div></div>').appendTo('body');
            p.panel({
                title: '用户数量',
                height: 250,

                collapsible: true
            });

            $('#poral').portal('add', {
                panel: p,
                columnIndex: 0
            });
        });

        function menuClick(title, url) {

            if ($('#tabs').tabs('exists', title)) {
                $('#tabs').tabs('select', title);
            } else {
                if (url != '') {
                    var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
                    $('#tabs').tabs('add', {
                        title: title,
                        content: content,
                        closable: true
                    });
                }
            }
        }

        function convert(rows) {
            function exists(rows, parentId) {
                for (var i = 0; i < rows.length; i++) {
                    if (rows[i].id == parentId) return true;
                }
                return false;
            }

            var nodes = [];
            // get the top level nodes
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                if (!exists(rows, row.pid)) {
                    nodes.push({
                        id: row.id,
                        text: row.name,
                        url: row.url
                    });
                }
            }

            var toDo = [];
            for (var i = 0; i < nodes.length; i++) {
                toDo.push(nodes[i]);
            }
            while (toDo.length) {
                var node = toDo.shift();	// the parent node
                // get the children nodes
                for (var i = 0; i < rows.length; i++) {
                    var row = rows[i];
                    if (row.pid == node.id) {
                        var child = {id: row.id, text: row.name, url: row.url};
                        if (node.children) {
                            node.children.push(child);
                        } else {
                            node.children = [child];
                        }
                        toDo.push(child);
                    }
                }
            }
            return nodes;
        }
    </script>
</head>

<body class="easyui-layout">

<%--头部--%>
<div data-options="region:'north',split:false,collapsible:false" style="height:95px;">
    <div id="header" style="height: 85px;">
        <div class="top-line"></div>
        <div class="header-logo">
            <div class="logo-div" style="width: auto">

                <p style="float: right;height: 77px;line-height: 77px;margin-top: -9px;">
                    <!-- 
                    <a href="javascript:;" class="top-help">
                        <span class="bor-l"></span><span>帮助</span><span class="bor-r"></span>
                    </a>
                     -->
                    <span>${admin_user.loginName}</span>
                    <a href="adminLogout.action" class="top-exit">退出</a>
                </p>
            </div>
        </div>
    </div>
</div>
<%--左边面板--%>
<div data-options="region:'west',title:'菜单',split:true" style="width:200px;">
    <ul id="tree"></ul>
</div>
<%--左边面板结束--%>
<%--中心内容--%>
<div data-options="region:'center'" style="padding:5px;background:#eee;">
    <div id="tabs" class="easyui-tabs" data-options="fit:true">
        <div title="工作台" style="padding:20px;">
            <%--<div id="poral">--%>
                <%--<div style="width:33%"></div>--%>
                <%--<div style="width:33%"></div>--%>
                <%--<div style="width:33%"></div>--%>
            <%--</div>--%>
        </div>
    </div>

</div>
<div data-options="region:'south',split:false,collapsible:false"
     style="height: 30px;">
    <div style="text-align:center; font-size: 12px;">
        &copy; 2019 软易IT 版权所有
    </div>
</div>

</body>
</html>

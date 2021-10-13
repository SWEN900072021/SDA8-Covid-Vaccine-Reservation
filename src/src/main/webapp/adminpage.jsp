<%--
  Created by IntelliJ IDEA.
  User: 白咕咕
  Date: 2021/9/14
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title>Admin Homepage</title>
    <style type="text/css">
        ui{
            line-height: 30px;
        }
        *{
            text-decoration:none;
        }
        a:link {color: #1e2c09
        }
        a:visited {color: rgba(255, 65, 41, 0.98);
        }
        a:hover {color: #9fecff
        }
        a:active {color: #9fecff
        }
    </style>

</head>
<body class="easyui-layout">
<!--Navbar-->
<div data-options="region:'north',split:true" style="height:95px;">
    <div style="margin: 30px 50px;" >
        <h2 style="font-size:30px;font-weight: bolder;color:#a2a2a2">Admin Homepage</h2>
    </div>
    <div style="position:absolute; left: 35%;top: 10px">
        <p ><font color="#4ac9ff" size="7px">COVID-19 Booking </font><font size="7px" color="#ff508b">System</font><font color="#" size="4px">&nbsp;Group SDA8</font></p>
    </div>
    <div style="position:absolute; right: 30px;top: 5px">
        Hello!&nbsp;Admin&nbsp;
            <form id="logout_form" name="logout_form"
                  action="${pageContext.request.contextPath}/logout" method="post">
                <input type="submit" value="Logout"></br>
            </form>
    </div>
    <div style="position: absolute;right:30px; top:50px;">
        <a href="javascript:void(0)" id="mb" class="easyui-menubutton"
           data-options="menu:'#changeTheme',iconCls:'icon-edit'">change style</a>
        <div id="changeTheme" style="width:150px;">
            <div >default</div>
            <div >bootstrap</div>
            <div >gray</div>
            <div >black</div>
            <div >metro</div>
            <div >material</div>
        </div>
    </div>
</div>
<!--foot-->
<div data-options="region:'south',title:'copyright',split:true" style="height:80px;">
    <div id = "copyrightDiv" style = "text-align: center">
        copyright@Group SDA8
        <br>
    </div>
</div>
<!--left menu-->
<div data-options="region:'west',title:'system menu',split:true" style="width:200px;">
    <div id="aa" class="easyui-accordion" style="width:193px" data-options="multiple:true,border:0">
        <div title="Add Vaccine" data-options="iconCls:'icon-edit'" style="overflow:auto;padding:10px;">
            <ui>
                <li><a href="javascript:void(0);" pageUrl="add_vaccine" >Add new vaccine type</a></li>
                <li><a href="javascript:void(0);" pageUrl="add_question">Add a new question</a></li>
            </ui>
        </div>


        <div title="Create Users" data-options="iconCls:'icon-edit',selected:true" style="padding:10px;">
            <ui>
                <li><a href="javascript:void(0);" pageUrl="signuprecipient.jsp"  >Create new recipient account</a></li>
                <li><a href="javascript:void(0);" pageUrl="signupprovider.jsp"  >Create new provider account</a></li>
            </ui>
        </div>
        <div title="View" data-options="iconCls:'icon-search' " style="padding:10px;">
            <ui>
                <li><a href="javascript:void(0);" pageUrl="get_timeslot" id = "rePassword">View all time slots</a></li>
                <li><a href="javascript:void(0);" pageUrl="get_user" id = "">View all users</a></li>
            </ui>
        </div>

    </div>
</div>
<!--content-->
<div data-options="region:'center',title:'content'" style="padding:5px;background:#eee;">
    <div id="tt" class="easyui-tabs"  data-options="fit:true">
        <div title="welcome page" style="padding:20px;display:none;">
            <h1 style="font-weight: bolder">Welcome！</h1>
            You're logged in as admin
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $("a[pageUrl]").click(function () {
            var pageUrl = $(this).attr("pageUrl");
            var title = $(this).html();
            var isExist = $('#tt').tabs("exists",title);
            if(!isExist){
                $('#tt').tabs('add',{
                    title:title,
                    content:"<iframe  frameborder ='0' width = '100%' height= '99%' src='"+pageUrl+"'></iframe>",
                    closable:true,
                    fit:true,
                    tools:[{
                        iconCls:'icon-mini-refresh',
                        handler:function(){
                            alert('refresh');
                        }
                    }]
                });
            }else{
                $("#tt").tabs("select",title);
            }
        });

        $("#changeTheme").menu({
            onClick:function(item){
                var themeName = item.text;
                var href = $("#styleId").attr("href");
                href =  href.substring(0,href.indexOf("themes"))+"themes/"+themeName+"/easyui.css";
                $("#styleId").attr("href",href);
            }

        });

    });


</script>


</html>

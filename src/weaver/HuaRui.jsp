<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/systeminfo/init_wev8.jsp" %>
<%@ page import="weaver.general.*" %>
<%@ page import="weaver.conn.RecordSet" %>
<%@ taglib uri="/WEB-INF/weaver.tld" prefix="wea" %>
<%@ taglib uri="/browserTag" prefix="brow" %>
<jsp:useBean id="DepartmentComInfo" class="weaver.hrm.company.DepartmentComInfo" scope="page"/>
<script type="text/javascript">

    //var appUri = "900000592@hangzhou.zg";
    //var appUri = "62001003@gd.zg";
    var appUri = "000000";
    function getUserInfo()
    {
//     var ret ="";
        var ret = initshare.GetUserInfo(appUri);
        if (ret == "")
        {
            var error = initshare.ErrorCode;
            alert(error);
            return "";
        }
        return ret;
    }


    function entrWin() {
        var userInfoXml= getUserInfo();
        //var userInfoXml= "<uri>qtqx</uri><nam>qtqx</nam><utp></utp>";
        if(userInfoXml=="undefined"){

        } else {
            if(userInfoXml == "<uri /><nam /><utp />"){

            }else if ( userInfoXml == null || userInfoXml == "") {

            } else{
                var submitUrl = '<%=basePath+"Homepage?reqOption="+pageBean.getRegistryKey()%>';
                $.ajax({
                    url:submitUrl,
                    data:{"<%= pageBean.ACTION_NAME %>" : "<%=pageBean.SINGLE_POINT_LOGIN_ACTION_HTML_VALUE%>",
                        "MESS" : userInfoXml},
                    type:'post',
                    cache:false,
                    async : false,
                    dataType:'json',
                    contentType: "application/x-www-form-urlencoded; charset=gbk",
                    success:function(data) {
                        $('#userNumber').val(data.msgCode);
                        $("#ssss").val(data.msgInfo);
                        $("#form1").submit();


                    },
                    error : function() {
                        alert('error');
                    }
                });

            };
        }
    }
</script>


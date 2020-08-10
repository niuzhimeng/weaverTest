var jkye='oTable1';//明细表table的id
var fieldId='field9060'//借款余额明细表id主键名
var jkjemx='detail1_3_8 td_etype_3';
var cjkjemx='detail1_3_9 td_etype_3';
jQuery(document).ready(function () {
    checkCustomize();
});
function checkCustomize() {
    var flag=true;
    /*********************************
     * 获取所有的借款金额行信息
     *********************************/
    var jkdetailData=document.getElementsByClassName("detail1_3_8 td_etype_3");//获取借款余额的明细信息数据
    alert('借款数据信息------>'+jkdetailData.value);
    alert('借款数据信息长度为------>'+jkdetailData.length);
    for (var i = 0; i < jkdetailData.length; i++) {
        var jkye=$("#field9060_"+i).val();
        alert('jkye---->'+jkye);
    }
    /*********************************
     * 获取所有的冲借款金额行信息
     *********************************/
    var cjkdetailData=document.getElementsByClassName("detail1_3_9 td_etype_3");//冲借款金额明细
    alert('冲销数据信息------>'+cjkdetailData.value);
    alert('冲销数据信息长度为------>'+cjkdetailData.length);
    for (var j = 0; j <cjkdetailData.length; j++) {
        var cjkje=$("#field9061_"+j).val();
        alert("冲借款金额为----->"+cjkje);
    }
    /**************************************************************
     * 比较jkye和cjkje两者关系(明细表--------->冲销金额 < 借款金额(JS做校验))
     ***************************************************************/
    var jkyeAll=parseFloat(jkye);//借款金额比较jkye和cjkje两者关系
    var cjkjeAll=parseFloat(cjkje);//冲销金额
    //执行方法体
    if(parseFloat(jkye)<parseFloat(cjkje)){
        flag=false;
        alert('冲借款金额不可大于借款金额')
    }else{
        flag=true;
    }
    return flag;
}

function checkCustomize(){
    var jkdetailData=document.getElementsByClassName("detail1_3_8 td_etype_3");//获取借款余额的明细信息数据
    alert('借款数据信息长度为------>'+jkdetailData.length);
    for (var i = 0; i < jkdetailData.length; i++) {
        var ll=$("#field9060_"+i).val();
        alert('ll---->'+ll);
    }
}




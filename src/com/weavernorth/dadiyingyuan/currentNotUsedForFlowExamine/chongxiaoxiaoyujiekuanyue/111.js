
    $(document).ready(function (){
        $('#field12642').change(function (){//绑定流程类型的change事件

            var sqzylx = jQuery("#field12642").val();
            alert('值'+sqzylx);
            if (sqzylx === null || sqzylx === undefined || sqzylx === '') {

            }
            if (sqzylx==0 )//虚拟机
            {
                $('.fy').show();//显示class“fy”
                $('.ry').hide();//隐藏class“ry”
            }
            else if(sqzylx =='1')//物理机
            {
                $('.fy').hide();//隐藏class“fy”
                $('.ry').show();//显示class“ry”
            }
            else if(sqzylx ==2)//公有云
            {
                $('.fy').hide();//隐藏class“fy”
                $('.ry').show();//显示class“ry”
            }

            else if(sqzylx ==3)//办公电脑
            {
                $('.fy').hide();//隐藏class“fy”
                $('.ry').show();//显示class“ry”
            }

            else if(sqzylx ==4)//配置调整
            {
                $('.fy').hide();//隐藏class“fy”
                $('.ry').show();//显示class“ry”
            }
            else//全部显示
            {
                $('.fy').show();
                $('.ry').show();
            }
        });
    })


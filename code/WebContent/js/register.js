function input_text_onfocus(that)
{
    that.style.borderColor='powderblue';
}
function input_text_onblur(that)
{
    that.style.borderColor='black';
}
function getQueryString(name) 
{  
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");  
    var r = window.location.search.substr(1).match(reg);  
    if (r != null) 
    {
        return unescape(r[2]);
    } 
    else
    {
        return null;  
    } 
    
}  
function lookerr()
{
    var err=getQueryString("err");
    if(err!=null)
    {
        switch(err)
        {
            case '0':alert('未知错误！');break;
            case '1':alert('用户名不能为空！');break;
            case '2':alert('密码不能为空！');break;
            case '3':alert('两次密码不一致！');break;
            case '4':alert('用户名不能超过19个字符！');break;
            case '5':alert('密码不能超过19个字符！');break;
            case '6':alert('电子邮箱格式错误！');break;
            case '7':alert('用户名已存在！');break;
        }
    }
}
function check(form)
{
    var username=form.username.value;
    var pas=form.pas.value;
    var pasconfirm=form.pasconfirm.value;
    var email=form.email.value;
    var re=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if(username==null||username=='')
    {
        alert('用户名不能为空！');
        form.username.focus();
        return false;
    }
    else if(pas==null||pas=='')
    {
        alert('密码不能为空！');
        form.pas.focus();
        return false;
    }
    else if(pasconfirm!=pas)
    {
        alert('两次密码不一致！');
        form.pasconfirm.focus();
        return false;
    }
    else if(username.length>=19)
    {
        alert('用户名不能超过19个字符！');
        form.username.focus();
        return false;
    }
    else if(pas.length>=19)
    {
        alert('密码不能超过19个字符！');
        form.pas.focus();
        return false;
    }
    else if(!re.test(email))
    {
    	alert('电子邮箱格式错误！');
        form.email.focus();
        return false;
    }
    else
    {
        return true;
    }
}
$(document).ready(function()
{
	$("#photo").change(function()
	{
		var hasfile=$("#hasfile");
		hasfile.val("1");
	});
});
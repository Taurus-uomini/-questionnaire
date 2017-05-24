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
            case '1':alert('用户名或密码不能为空！');break;
            case '2':alert('用户名或密码错误！');break;
            case '3':alert('你的帐号未激活，请登陆邮箱激活帐号！');break;
        }
    }
    var act=getQueryString("act");
    if(act!=null)
    {
        switch(act)
        {
            case '0':alert('帐号激活失败！');break;
            case '1':alert('帐号激活成功！');break;
            case '2':alert('你的帐号已激活，不要重复激活！');break;
        }
    }
}
function check(form)
{
    var username=form.username.value;
    var pas=form.pas.value;
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
    else
    {
        return true;
    }
}

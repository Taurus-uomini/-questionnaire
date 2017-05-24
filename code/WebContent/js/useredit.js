$(document).ready(function()
{
	$("#userphoto").click(function()
	{
		$("#inputfile").trigger("click");
	});
	$("#inputfile").change(function()
	{
		var file=$(this);
		var fileObj=file[0];
		var windowURL=window.URL||window.webkitURL;
		var dataURL;
		var img=$("#userphoto");
		var hasfile=$("#hasfile");
		if(fileObj&&fileObj.files&&fileObj.files[0])
		{	
			hasfile.val("1");
			dataURL=windowURL.createObjectURL(fileObj.files[0]);
			img.attr('src',dataURL);	
		}
		else
		{
			alert("图片获取错误！");
		}
	});
});
function lookerr(err)
{
    if(err!=null)
    {
        switch(err)
        {
            case 0:alert('未知错误！');break;
            case 1:alert('用户名不能为空！');break;
            case 2:alert('密码不能为空！');break;
            case 3:alert('两次密码不一致！');break;
            case 4:alert('用户名不能超过19个字符！');break;
            case 5:alert('密码不能超过19个字符！');break;
            case 6:alert('电子邮箱格式错误！');break;
        }
    }
}
function check(form)
{
    var email=form.email.value;
    var re=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if(!re.test(email))
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
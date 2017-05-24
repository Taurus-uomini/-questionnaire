$(document).ready(function()
{
	$("#userphoto").click(function()
	{
		$("#inputfile").trigger("click");
	});
	$("#addsurvey").click(function()
	{
		window.location.href="../survey/toadd.do";
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
	$("#editpasssave").click(function(){
		var oldpassword=$("#oldpassword").val();
		var newpassword=$("#newpassword").val();
		var renewpassword=$("#renewpassword").val();
	  $.post("editpassword.do",
	  {
		 oldpassword:oldpassword,
		 newpassword:newpassword,
		 renewpassword:renewpassword
	  },
	  function(data,status){
	    if(status=="success")
	    {
	    	actiondo(data);
	    }
	    else
	    {
	    	actiondo("500");
	    }
	  });
	});
	$("#editinfosave").click(function(){
		var psignature=$("#psignature").val();
		var hasfile=$("#hasfile").val();
		$.ajaxFileUpload
        ({
        	url: 'editinfo.do', 
         type: 'post',
         data: { psignature: psignature,hasfile: hasfile }, 
         secureuri: false, 
         fileElementId: 'inputfile', 
         dataType: 'json', 
         success: function (data, status)  
            {
        	 	if(data.ret==true)
        	 	{
        	 		actiondo("2");
        	 	}
        	 	else
        	 	{
        	 		actiondo("3");
        	 	}
            },
         error: function (data, status, e)
            {
        	 	actiondo("2");
            }
        });
	});
});
function actiondo(act)
{
	act=parseInt(act);
	switch(act)
	{
		case -1:alert("旧密码不能为空！");break;
		case -2:alert("新密码不能为空！");break;
		case -3:alert("重复新密码不能为空！");break;
		case -4:alert("新密码和重复新密码不一致！");break;
		case -5:alert("旧密码错误！");break;
		case 0:alert("修改密码失败！");break;
		case 3:alert("修改信息失败！");break;
		case 500:alert("连接服务器错误！");break;
		case 1:alert("修改密码成功！");window.location.href="../logout.do";break;
		case 2:alert("修改信息成功！");window.location.href="../my/index.do";break;
	}
}

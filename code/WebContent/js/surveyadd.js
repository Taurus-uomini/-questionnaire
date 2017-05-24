	var qn=0;
	var ic=0;
	var ir=0;
	var icheckcolor="blue";
	var ichecktype="square";
	var divpanel="<div class='panel panel-primary'>";
	var divpanelheading="<div class='panel-heading'>";
	var divpanelbody="<div class='panel-body'>";
	function changeichicktype(type)
	{
		ichecktype=type;
		$('input').iCheck({
		    checkboxClass: 'icheckbox_'+ichecktype+'-'+icheckcolor+'',
		    radioClass: 'iradio_'+ichecktype+'-'+icheckcolor+'',
		    increaseArea: '20%' // optional
		 });
	}
	function changeichickcolor(color)
	{
		icheckcolor=color;
		$('input').iCheck({
		    checkboxClass: 'icheckbox_'+ichecktype+'-'+icheckcolor+'',
		    radioClass: 'iradio_'+ichecktype+'-'+icheckcolor+'',
		    increaseArea: '20%' // optional
		 });
	}
$(document).ready(function()
{
	$("#addquestion").attr("disabled","disabled");
	$("#addradio").attr("disabled","disabled");
	$("#addcheckbox").attr("disabled","disabled");
	$("#title").change(function()
	{
		$("#addquestion").removeAttr("disabled");
	});
	$("#addquestion").click(function()
	{
		ic=0;
		ir=0;
		++qn;
		$(".center").append(divpanel+"<div id='questiond"+qn+"'>"+divpanelheading+"<textarea class='form-control' rows='2' id='question"+qn+"' name='question"+qn+"'></textarea></div>"+divpanelbody+"</div></div></div>");
		$("#question"+qn).trigger("focus");
		$("#addquestion").attr("disabled","disabled");
		$("#addradio").attr("disabled","disabled");
		$("#addcheckbox").attr("disabled","disabled");
		$("#question"+qn).change(function()
		{
			$("#addradio").removeAttr("disabled");
			$("#addcheckbox").removeAttr("disabled");
		});
	});
	$("#addcheckbox").click(function()
	{
		++ic;
		$("#questiond"+qn+" .panel-body").append("<div class='chose'><input type='checkbox' name='qu"+qn+"checkbox' vlaue='"+ic+"' /><input type='text' class='form-control icheckinput q"+qn+"checkbox' name='q"+qn+"checkbox"+ic+"' id='q"+qn+"checkbox"+ic+"'/></div>");
		$("#q"+qn+"checkbox"+ic).trigger("focus");
		$("#addradio").attr("disabled","disabled");
		$("#addcheckbox").attr("disabled","disabled");
		$("#addquestion").removeAttr("disabled");
		$('input').iCheck({
		    checkboxClass: 'icheckbox_'+ichecktype+'-'+icheckcolor+'',
		    radioClass: 'iradio_'+ichecktype+'-'+icheckcolor+'',
		    increaseArea: '20%' // optional
		 });
		$("#q"+qn+"checkbox"+ic).change(function()
		{
			$("#addcheckbox").removeAttr("disabled");
		});
	});
	$("#addradio").click(function()
	{
		++ir;
		$("#questiond"+qn+" .panel-body").append("<div class='chose'><input type='radio' name='qu"+qn+"radio' vlaue='"+ir+"' /><input type='text' class='form-control icheckinput q"+qn+"radio' name='q"+qn+"radio"+ir+"' id='q"+qn+"radio"+ir+"' /></div>");
		$("#q"+qn+"radio"+ir).trigger("focus");
		$("#addcheckbox").attr("disabled","disabled");
		$("#addradio").attr("disabled","disabled");
		$("#addquestion").removeAttr("disabled");
		$('input').iCheck({
		    checkboxClass: 'icheckbox_'+ichecktype+'-'+icheckcolor+'',
		    radioClass: 'iradio_'+ichecktype+'-'+icheckcolor+'',
		    increaseArea: '20%' // optional
		 });
		$("#q"+qn+"radio"+ir).change(function()
		{
			$("#addradio").removeAttr("disabled");
		});
	});
	$("#save").click(function()
	{
		var i=1;
		for(i=1;i<=qn;++i)
		{
			var cb=$("#questiond"+i).find(".q"+i+"checkbox");
			var rd=$("#questiond"+i).find(".q"+i+"radio");
			$("#form").append("<input type='hidden' name='q"+i+"checkbox' value='"+cb.length+"' />");
			$("#form").append("<input type='hidden' name='q"+i+"radio' value='"+rd.length+"' />");
		}
		$("#form").append("<input type='hidden' name='qn' value='"+qn+"' />");
		$("#form").append("<input type='hidden' name='icheckcolor' value='"+icheckcolor+"' />");
		$("#form").append("<input type='hidden' name='ichecktype' value='"+ichecktype+"' />");
		$("#form").submit();
	});
});
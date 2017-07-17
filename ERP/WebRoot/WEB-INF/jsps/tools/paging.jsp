<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	$(function(){
		var pageNum=${pageNum};
		var maxPageNum=${maxPageNum};
		if(pageNum==1){
			$("#fir").css("display","none");
			$("#pre").css("display","none");
			$("last").css("display","block");
			$("#next").css("display","block");
		}else if(pageNum==maxPageNum){
			$("#last").css("display","none");
			$("#next").css("display","none");
			$("#fir").css("display","block");
			$("#pre").css("display","block");
		}else if(maxPageNum==1){
			$("#fir").css("display","none");
			$("#last").css("display","none");
			$("#pre").css("display","none");
			$("#next").css("display","none");
		}else{
			$("#fir").css("display","block");
			$("#pre").css("display","block");
			$("last").css("display","block");
			$("#next").css("display","block");
		}
		//首页
		$("#fir").click(function(){
			//收集页码值设置为指定值，提交表单
			//获取原始源码值，然后设置为1，设置回去
			$("[name=pageNum]").val(1);
			$("form:first").submit();
		});
		//下一页
		$("#next").click(function(){
			//收集页码值设置为指定值，提交表单
			//获取原始源码值，然后+1，设置回去
			$("[name=pageNum]").val($("[name=pageNum]").val()*1+1);
			$("form:first").submit();
		});
		//上一页
		$("#pre").click(function(){
			//收集页码值设置为指定值，提交表单
			//获取原始源码值，然后-1，设置回去
				$("[name=pageNum]").val($("[name=pageNum]").val()-1);
			$("form:first").submit();
		});
		//末页
		$("#last").click(function(){
			//收集页码值设置为指定值，提交表单
			//获取原始源码值，然后-1，设置回去
			$("[name=pageNum]").val(maxPageNum);
			$("form:first").submit();
		});
		
	});
</script>
<br/>
<s:hidden name="pageNum"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="51%">&nbsp;</td>
		<td width="13%">共 ${dataTotal}条记录
		<td width="6%">
			<a id="fir" class="sye">首&nbsp;&nbsp;页</a>
		</td>
		<td width="6%">
			<a id="pre" class="sye">上一页</a>
		</td>
		<td width="6%">
			<a id="next" class="sye">下一页</a>
		</td>
		<td width="6%">
			<a id="last" class="sye">末&nbsp;&nbsp;页</a>
		</td>
		<td width="12%">当前第<span style="color:red;">${pageNum}</span>/${maxPageNum}页</td>
	</tr>
</table>

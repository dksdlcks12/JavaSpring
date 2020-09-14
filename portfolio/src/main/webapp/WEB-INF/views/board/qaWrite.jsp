<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-boardWrite-box">
	<div class="common-boardWrite-writeBox">
		<table class="common-boardWrite-titleBox" border="1">
			<tr>
				<td class="common-boardWrite-rowTitle">제 목</td>
				<td class="common-boardWrite-rowContent"><input type="text" class="common-boardWrite-title" maxlength="30"></td>
			</tr>
			<tr>
				<td class="common-boardWrite-rowTitle">작성자</td>
				<td class="common-boardWrite-rowContent"><input type="text" class="common-boardWrite-reviewWriter" maxlength="16"> 16자 이내로 입력해 주세요.</td>
			</tr>
			<tr>
				<td class="common-boardWrite-rowTitle">공개여부</td>
				<td class="common-boardWrite-rowContent"><div class="common-boardWrite-isOpen"><label><input class="common-boardWrite-isOpenButton" type="radio" name="qaIsOpen" value="Y" checked>공개</label><label><input class="common-boardWrite-isOpenButton" type="radio" name="qaIsOpen" value="N">비공개</label></div></td>
			</tr>
			<tr class="common-boardWrite-passWordBox" style="display: none">
				<td class="common-boardWrite-rowTitle">비밀번호</td>
				<td class="common-boardWrite-rowContent"><input type="text" class="common-boardWrite-password noneMemberPassword" maxlength="8"> 숫자 8자 이내로 입력해 주세요.</td>
			</tr>
		</table>
		<div class="common-boardWrite-Content">
			<div id="qaWrite"></div>
		</div>
		<div class="transImg" hidden></div>
		<textarea class="sandNote" hidden></textarea>
		<button class="common-boardWrite-button">등 록</button>
	</div>
</div>
<script>
	var imgList = [];
	$('#qaWrite').summernote({
		tabsize: 2,
		height: 300,
		minHeight: 300,
		maxHeight: 300,
		lang : 'ko-KR',
		toolbar: [
			['style', ['style']],
			['font', ['bold', 'underline', 'clear']],
			['color', ['color']],
			['insert', ['picture']]
		],
		callbacks: {
			onImageUpload: function(files, editor, welEditable) {
	            for (var i = files.length - 1; i >= 0; i--) {
	            	sendFile(files[i], this);
	            }
	        }
		}
	});
	function sendFile(file, el) {
		var form_data = new FormData();
		form_data.append('file', file);
		$.ajax({
			data: form_data,
			type: "POST",
			url: '<%=request.getContextPath()%>/qaimg',
			cache: false,
			contentType: false,
			enctype: 'multipart/form-data',
			processData: false,
			success: function(data) {
				$(el).summernote('editor.insertImage', '<%=request.getContextPath()%>/resources/image/photo6.JPG');
				imgList.push(data.img);
			}
		});
	}
	$('.common-boardWrite-button').click(function(){
    	var index = 0;
    	var arr = [];
    	$('.transImg').html($('#qaWrite').summernote('code'));
    	$('.transImg img').each(function(){
    		$(this).attr('src', '<%=request.getContextPath()%>/resources/image/qa'+imgList[index]);
    		index++;
    	})
    	$('.sandNote').html($('.transImg').html());
    	var qaTitle = $('.common-boardWrite-title').val();
    	var qaWriter = $('.common-boardWrite-reviewWriter').val();
    	var qaIsOpen = $('.common-boardWrite-isOpenButton:checked').val();
    	var qaPw = $('.common-boardWrite-password').val();
		var qaContent = $('.sandNote').html();
    	if(qaTitle != ""){
   			if(qaWriter != ""){
   				if(qaIsOpen == "Y" || qaPw!=""){
		    		if(qaContent != '&lt;p&gt;&lt;br&gt;&lt;/p&gt;'){
		    			arr.push({"qaTitle":qaTitle, "qaWriter":qaWriter, "qaIsOpen":qaIsOpen, "qaPw":qaPw ,"qaContent":qaContent})
		    			$.ajax({
							async:false,
							type:'POST',
							data: JSON.stringify(arr),
							url:"<%=request.getContextPath()%>/qaadd",
							dataType:"json",
							contentType:"application/json; charset=UTF-8",
							success : function(data){
								alert('Q&A가 성공적으로 등록되었습니다.')
								location.replace('<%=request.getContextPath()%>/qalist');
							}
						});
		    		}else{
		        		alert('내용을 입력하여 주세요.')
		        	}
   				}else{
		    		alert("비밀번호를 입력하여 주세요.")
	    		}
   			}else{
       			alert('작성자를 입력하여 주세요.')
   			}
    	}else{
        	alert('제목을 입력하여 주세요.')
       	}
	})
	$('.common-boardWrite-isOpenButton').change(function(){
		if($(this).val()=="N"){
			$('.common-boardWrite-passWordBox').css("display", "table-row");
		}else{
			$('.common-boardWrite-passWordBox').css("display", "none");
			$('.common-boardWrite-password').val("");
		}
	})
</script>
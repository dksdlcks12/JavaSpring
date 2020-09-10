<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="user-asApply-box">
	<div class="user-asApply-asApplyBox">
		<h2>A/S신청</h2>
		<div class="user-asApply-asApplyInfoBox">
			<div class="user-asApply-asOptionBox">
				<div class="user-asApply-selectGoodsBox">
					<div class="user-asApply-selectGoodsTitle">제목(24자이내)</div><input class="user-asApply-selectGoods" maxlength="24">
				</div>
				<div class="user-asApply-selectGoodsBox">
					<div class="user-asApply-selectGoodsTitle">이름</div><input class="user-asApply-name" maxlength="24">
				</div>
				<div class="user-asApply-selectGoodsBox">
					<div class="user-asApply-selectGoodsTitle">전화번호</div><input type="tel" class="user-asApply-tel" maxlength="12">
				</div>
			</div>
			<h2>상세설명</h2>
			<div id="asApply" class="user-asApply-board"></div>
			<div class="transImg" hidden></div>
			<textarea class="sandNote" hidden></textarea>
			<button class="user-asApply-button">A/S신청</button>
		</div>
	</div>
</div>
<script>
	var imgList = [];
	$('#asApply').summernote({
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
			url: '<%=request.getContextPath()%>/asapplyimg',
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
    $('.user-asApply-button').click(function(){
    	var index = 0;
    	var arr = [];
    	$('.transImg').html($('#asApply').summernote('code'));
    	$('.transImg img').each(function(){
    		$(this).attr('src', '<%=request.getContextPath()%>/resources/image/asApply'+imgList[index]);
    		index++;
    	})
    	$('.sandNote').html($('.transImg').html());
    	var title = $('.user-asApply-selectGoods').val();
    	var name = $('.user-asApply-name').val();
    	var tel = $('.user-asApply-tel').val();
		var sandNote = $('.sandNote').html();
    	if(title != ""){
    		if(sandNote != '&lt;p&gt;&lt;br&gt;&lt;/p&gt;'){
    			if(name != ""){
    				if(tel != ""){
        				arr.push({"title":title, "name":name, "tel":tel, "sandNote":sandNote})
		    			$.ajax({
							async:false,
							type:'POST',
							data: JSON.stringify(arr),
							url:"<%=request.getContextPath()%>/asAdd",
							dataType:"json",
							contentType:"application/json; charset=UTF-8",
							success : function(data){
								alert('A/S신청이 성공적으로 이루어졌습니다.')
								location.replace('<%=request.getContextPath()%>/asviewlist');
							}
						});
    				}else{
        				alert('연락가능한 전화번호를 입력하여 주세요.')
       				}
   				}else{
        			alert('이름을 입력하여 주세요.')
       			}
    		}else{
        		alert('상세설명을 입력하여 주세요.')
        	}
    	}else{
        	alert('제목을 입력하여 주세요.')
       	}
	})
</script>
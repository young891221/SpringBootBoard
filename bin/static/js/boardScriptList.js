	
	var objectList = {
		selectOption: $('#selectOption'), //Title, 서비스 분류 항목
		optionContent: $('#optionContent'), //서비스 분류 선택시의 항목
		selectContent: $('#selectContent'), //Title 선택시 검색 항목
		subListExistFlag: false //검색결과 페이지시 기존 결과항목 리로딩용 flag값
	};
	
	$(document).ready(function() { 
		objectList.selectOption = $('#selectOption');
		objectList.optionContent = $('#optionContent');
		objectList.selectContent = $('#selectContent');
		
		objectList.selectOption.on('change', function(){
			if(objectList.selectOption.val() === 'board_group') {
				objectList.selectContent.hide();
				objectList.optionContent.val("zum").attr("selected", "selected");
				objectList.optionContent.show();
				selectOptionList(); //파라미터값 넣기
			} else if(objectList.selectOption.val() === 'board_title' || objectList.selectOption.val() === '') {
				objectList.optionContent.hide();
				objectList.selectContent.val('');
				objectList.selectContent.show();
			} else {
				selectOptionList();
			}
		});
		
		objectList.optionContent.on('change', function(){
			selectOptionList();
		});
	});
	
	function selectOptionList() {

		if(!objectList.selectOption.val()){
			alert('옵션을 선택하세요');
			return;
		}
			
		var selectContent;
		if(objectList.selectOption.val() === 'board_group'){
			selectContent = objectList.optionContent.val();
		} else if(objectList.selectOption.val() === 'board_title'){
			selectContent = objectList.selectContent.val();
		}

		var params = {
				selectOption: objectList.selectOption.val(),
				selectContent: selectContent
		};
		var jsonParams = JSON.stringify(params);
		$.ajax({
		    url: "/selectOptionBoard",
		    type: "POST",
		    data: jsonParams,
		    headers: { 
		        "Accept": "application/json; charset=UTF-8",
		        "Content-Type": "application/json; charset=UTF-8" 
		    },
			dataType: "json", //서버로 부터 반환되는 데이터 형식
		    success: function (data) {
				var selectOptionBoardList = data.selectOptionBoardList;
				$('#mainHide').hide();
				$('#subHide').show();
				
				if(objectList.subListExistFlag) {
					$('#subList').remove();
					$('#subFlag').append($('<tbody id="subList"></tbody>'));
				}
				
				if(selectOptionBoardList.length != 0) {
					$(selectOptionBoardList).each(function(index){
						
						var tr = $('<tr></tr>');
						var board_id = $('<td>'+selectOptionBoardList[index].board_id+'</td>');
						var board_group = $('<td>'+selectOptionBoardList[index].board_group+'</td>');
						
						if(selectOptionBoardList[index].board_status == 0){ //'0'일 경우 임시, '1'일 경우 공지
							var board_title = $('<td><a href="javascript:window.location.href=\'/selectOneBoard?board_id='+selectOptionBoardList[index].board_id+'\'">'+'[임시]'+selectOptionBoardList[index].board_title+'</a></td>');
						} else {
							var board_title = $('<td><a href="javascript:window.location.href=\'/selectOneBoard?board_id='+selectOptionBoardList[index].board_id+'\'">'+'[공지]'+selectOptionBoardList[index].board_title+'</a></td>');
						}
						
						var insert_date = new Date(selectOptionBoardList[index].board_insert_date);
						var update_date = new Date(selectOptionBoardList[index].board_update_date);
						
						var board_insert_date = $('<td>'+myDateFormat(insert_date)+'</td>');
						if(selectOptionBoardList[index].board_update_date != null) {
							var board_update_date = $('<td>'+myDateFormat(update_date)+'</td>');
						} else {
							var board_update_date = $('<td></td>');
						}
						
						var board_view_count = $('<td>'+selectOptionBoardList[index].board_view_count+'</td>');
						
						$('#subList').append(tr).append(board_id).append(board_group).append(board_title).append(board_insert_date).append(board_update_date).append(board_view_count);
						
					});
					objectList.subListExistFlag = true;
				} else {
					var hr = $('<hr>유효한 데이터가 없습니다</hr>');
					$('#subList').append(hr);
					objectList.subListExistFlag = true;
				}
				
		    },
		    error: function (data) {
				$(location).attr('href', '/boardError');
		    }
		});
	}

	function myDateFormat(d) {
		var myDate = d.getFullYear()+". "+(d.getMonth()+1)+". "+d.getDate()+" "+(d.getHours() < 12 ? "오전" : "오후")+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
		return myDate;
	}

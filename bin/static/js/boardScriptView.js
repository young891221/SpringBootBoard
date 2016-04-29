
	var objectList = {
		board_image: null
	};

	
	$(document).ready(function() {
		objectList.board_image = $('#board_image').val();
		$('#board_status').val($('#status').val()).attr("selected");
		$('#board_group').val($('#group').val()).attr("selected");
		
	});
	
	function popupOpen(){
		var popUrl = "/boardFormPop?board_image="+objectList.board_image;	//팝업창에 출력될 페이지 URL
		var w = 500;    //팝업창의 너비
		var h = 500;    //팝업창의 높이
		var LeftPosition=(screen.width-w)/2;
		var TopPosition=(screen.height-h)/2;
		var popOption = "width="+w+", height="+h+", top="+TopPosition+", left="+LeftPosition+"," +
				" resizable=no, scrollbars=no, status=no, menubar=no;";    //팝업창 옵션(optoin)
		window.open(popUrl,"",popOption);
	}
	
	function setChildValue(value){
		objectList.board_image = value;
	}
	
	function memberCheck(check) {

		if ($('#member_email').val().trim() == "") {
			alert("이메일을 입력하세요(공백 불가)");
			$('input#member_email').focus();
			return false;
		} else {
			// 정규식 - 이메일 유효성 검사
	        var regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
			
			if(!regEmail.test($('#member_email').val())) {
                alert('이메일 주소가 유효하지 않습니다');
                $('#member_email').focus();
                return false;
            }
		}
		if ($('#member_pw').val().trim() == "") {
			alert("비밀번호를 입력하세요(공백 불가)");
			$('input#member_pw').focus();
			return false;
		}
		
		var params = {
				board_id: $('#board_id').val(),
				member_email: $('#member_email').val(),
				member_pw: $('#member_pw').val()
		};
		
		$.ajax({
		    url: "/memberCheck",
		    type: "GET",
		    data: params,
		    success: function (data, status, result) {
		    	if(data){
			    	if(check == "update"){
			    		boardUpdate();
			    	} else {
			    		boardDelete();
			    	}
		    	} else {
		    		alert("이메일과 비밀번호를 다시 확인하세요");
		    	}
		    },
		    error: function () {
		    	alert('이메일/비밀번호 확인 중 문제가 발생하였습니다.');
		    	$(location).attr('href', '/boardError');
		    }
		});
	}

	function boardUpdate() {
		
		if (confirm("정말 수정 하시겠습니까?") == true) {
		
			if ($('#board_status').val().trim() == "") {
				alert("게시판상태를 선택하세요(공백 불가)");
				$('select#board_status').focus();
				return false;
			}
			if ($('#board_group').val().trim() == "") {
				alert("서비스분류를 선택하세요(공백 불가)");
				$('select#board_group').focus();
				return false;
			}
			if ($('#board_title').val().trim() == "") {
				alert("제목를 입력하세요(공백 불가)");
				$('input#board_title').focus();
				return false;
			}
			if ($('#board_sub_title').val().trim() == "") {
				alert("부제목을 입력하세요(공백 불가)");
				$('input#board_sub_title').focus();
				return false;
			}
			if ($('#board_content').val().trim() == "") {
				alert("내욜을 입력하세요(공백 불가)");
				$('input#board_content').focus();
				return false;
			}
			

		var params = {
				board_id: $('#board_id').val(),
				board_group: $('#board_group').val(),
				board_title: $('#board_title').val(),
				board_sub_title: $('#board_sub_title').val(),
				board_content: $('#board_content').val(),
				board_image: objectList.board_image,
				board_status: $('#board_status').val(),
				member_email: $('#member_email').val(),
				member_pw: $('#member_pw').val()
		};
		var jsonParams = JSON.stringify(params);

		$.ajax({
		    url: "/updateBoardForm",
		    type: "POST",
		    data: jsonParams,
		    headers: { 
		        "Accept": "application/json",
		        "Content-Type": "application/json" 
		    },
			dataType: "text",
		    success: function (data, status, result) {
		    	if(data === 'fail') {
		    		alert(data);
		    	} else {
		    		alert(data);
		    		$(location).attr('href', '/boardList');
		    	}
		    },
		    error: function () {
		    	alert('수정 프로세스 중 문제가 발생하였습니다.');
				$(location).attr('href', '/boardError');
		    }
		});
		} else {
			return;
		}
	}

	function boardDelete() {
		
		if (confirm("정말 삭제 하시겠습니까?") == true) {
		
		var params = {
				board_id: $('#board_id').val()
		};
		
		$.ajax({
		    url: "/deleteBoardForm",
		    type: "GET",
		    data: params,
		    dataType: "text",
		    success: function (data) {
		    	alert(data);
				$(location).attr('href', '/boardList');
		    },
		    error: function (data) {
		    	alert(data);
				$(location).attr('href', '/boardError');
		    }
		});
		} else {
			return;
		}
	}
	

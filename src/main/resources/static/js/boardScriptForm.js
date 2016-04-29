
	var objectList = {
		board_image: null
	};

	function popupOpen(){
		var popUrl = "/boardFormPop";	//팝업창에 출력될 페이지 URL
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
	
	function boardInsert() {
		
		if (confirm("정말 작성 하시겠습니까?") == true) {
			
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
				board_group: $('#board_group').val(),
				board_title: $('#board_title').val(),
				board_sub_title: $('#board_sub_title').val(),
				board_content: $('#board_content').val(),
				board_image: objectList.board_image,
				board_status: $('#board_status').val(),
				member_pw: $('#member_pw').val()
		};
		var jsonParams = JSON.stringify(params);
		
		$.ajax({
		    url: "/insertBoardForm",
		    type: "POST",
		    data: jsonParams,
		    headers: { 
		        "Accept": "application/json",
		        "Content-Type": "application/json" 
		    },
			dataType: "text", //서버로 부터 반환되는 데이터 형식
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
	
	

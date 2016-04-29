	
	var objectList = {
		fileNameCheck: null,
		checkFileType: null
	};
	
	$(document).ready(function() {
		var board_image = opener.board_image;
		if(board_image != null) {
			$("#getImage").attr("src", "/resources/imageFile/"+board_image); 
		}
		
		$("#upload-file-input").on("change", fileCheck);
		
		$("#complete").on("click", function(){
		    if(objectList.fileNameCheck === null && objectList.checkFileType === true){
		    	$('div span', opener.document).text(null);//이미지값 부모뷰에 전달
			    opener.setChildValue(null);//이미지값 부모스크립트에 전달
		    }
		    $('div span', opener.document).text(objectList.fileNameCheck);//이미지값 부모뷰에 전달
		    opener.setChildValue(objectList.fileNameCheck);//이미지값 부모스크립트에 전달
		    window.open('about:blank', '_self').close();
		 });
	});

	function uploadFile() {
	  $.ajax({
	    url: "/uploadFile",
	    type: "POST",
	    data: new FormData($("#upload-file-form")[0]),
	    enctype: 'multipart/form-data',
	    processData: false,
	    contentType: false,
	    cache: false,
	    success: function (result) {
	    	board_image = result.filename;
	    	$("#getImage").attr("src", result.resultPath); 
	    },
	    error: function () {
	    	alert('이미지 업로드 오류!');
	    }
	  });
	}
	
	//업로드 파일 체크
	function fileCheck(){
		
	    var filePath    = $("input[type=file]").val();
	    var idx         = filePath.lastIndexOf(".")+1;  //확장자 제외한 경로+파일명
	    var idx2        = filePath.lastIndexOf("\\")+1; //파일경로를 제외한 파일명+확장자
	    var extension = (filePath.substr(idx, filePath.length)).toLowerCase();  //확장자명
	    objectList.fileNameCheck = filePath.substring(idx2, filePath.length).toLowerCase();  //파일명.확장자
		var fileNameLength = objectList.fileNameCheck.length;
		objectList.checkFileType = false;    
	   
	    if (fileNameLength != 0) //파일명+확장자 길이가 0이 아니라면(즉,파일이 선택되었다면)
	    {
	        if( extension === 'gif' || extension === 'jpg' || extension === 'png' )
	        {
				objectList.checkFileType = true;
	            for(i=0; i < fileNameLength; i++)
	            {
	                var chk = objectList.fileNameCheck.charCodeAt(i);
	                if (chk > 128) 
	                {
	                    alert('선택한 이미지 : [' + objectList.fileNameCheck + ']' + '\n' + '파일명이 한글로 된 이미지 첨부 불가');
	                    return;
	                }
	            }
	            //위 조건 모두 이상없으면 submit
	            uploadFile();
	        }
	        else
	        {
				$("#uploadFile").val("");
	            alert("jpg / png / gif 파일만 올릴수 있습니다.");
	            return;  
	        }
	    }
	}

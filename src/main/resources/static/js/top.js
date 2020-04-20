$(function() {
	$("#submit").prop("disabled",true);
	$("#submit").css("background-color", "silver");
	var visible_comment = false;
	var visible_name = false;
	
	  $("#submit").hover(function() {
		    $(this).css("background-color", " lightgreen");
		  }, function() {
		    $(this).css("background-color", " green");
		  });
	
	$("textarea").on("keyup", function(){
		var comment = $(this).val().length
		if(comment<3 || comment>250 || $(this).val().match(/^[ 　\r\n\t]*$/) ){
			$("#comment_error").css({"color":"green", "font-size":"11px"});
			$("#comment_error").text("コメントは3-250文字で入力してね");
			 visible_comment = false;
		}else{
			$("#comment_error").text("");
			 visible_comment = true;
		}
		button_status();
	});
	
	$("input[type='text']").on("keyup", function(){
		var name = $(this).val().length
		if(name<2 || name>20 || $(this).val().match(/^[ 　\r\n\t]*$/)){
			$("#name_error").css({"color":"green", "font-size":"11px"});
			$("#name_error").text("名前は2-20文字で入力してね");
			 visible_name = false;
		}else{
			$("#name_error").text("");
			 visible_name = true;
		}
		button_status();
	});
	
	function button_status() {
		if(visible_comment && visible_name){
			$("#submit").css("background-color","green");
			$("#submit").prop("disabled",false);
		}else{
			$("#submit").css("background-color","silver");
			$("#submit").prop("disabled",true);
		}
	}
});

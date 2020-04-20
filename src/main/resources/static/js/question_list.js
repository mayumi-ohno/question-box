$(function() {
	
	 $("a[name$=mark]").hover(function() {
			var id = $(this).attr("name").replace("mark", "");
			var markStatus = $("input[id="+id+"mark-status]");
  				if(markStatus.val() == '★'){
  					$(this).text("☆");
  				}else{
  					$(this).text("★");
  				}
		  }, function() {
			var id = $(this).attr("name").replace("mark", "");
			var markStatus = $("input[id="+id+"mark-status]");
  			$(this).text(markStatus.val());
		});
	  
	  $("a[name$=mark]").on("click", function(){
 			var hostUrl = "http://tsuchiya-box.work/questions/tsuchiya/mark";
//			var hostUrl = "http://localhost:8080/tsuchiya/mark";
			var id = $(this).attr("name").replace("mark", "");
			var markStatus = $("input[id="+id+"mark-status]");
			
				$.ajax({
					url: hostUrl,
					type: "GET",
					dataType: "json",
					data:{
						id: id ,
						mark: markStatus.val(),
					},
					async: true
				});
  				if(markStatus.val() == '★'){
  					markStatus.val("☆");
  				}else{
  					markStatus.val("★");
  				}
	  });
});
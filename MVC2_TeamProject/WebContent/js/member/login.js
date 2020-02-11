	function formSubmit() {
		$("form").submit();
	}
	$(function() {
		if("${msg}" !=null){
			$("#msg").attr('style','color : red;');
		}
		$("form").submit(
				function() {
					var id = $("input[name='id']").val();
					if (id == '') {
						$("input[name='id']").val(
								$("input[name='id']").attr("placeholder"));
					}
				});
	});
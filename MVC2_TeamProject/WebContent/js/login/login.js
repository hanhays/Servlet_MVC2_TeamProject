	function formSubmit() {
		$("form").submit();
	}
	$(function() {
		$("form").submit(
				function() {
					var id = $("input[name='id']").val();
					if (id == '') {
						$("input[name='id']").val(
								$("input[name='id']").attr("placeholder"));
					}
				});
	});
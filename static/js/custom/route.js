$(document).ready(function () {
	$("#about").click(function () {
		$.ajax({
			type: 'GET',
			url: '/user/rated',
			success: function (data) {
				$('.panel panel-default').text = "ABC";
			}
		});
	});
});
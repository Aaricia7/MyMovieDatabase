$(function () {

	// Router
	function init() {

		// Routie is a routing library, more info: https://github.com/jgallen23/routie
		routie({
			'': movie,
		});
	}

	function movie() {
		console.debug('movie view');
		setView('Movie');
	}

	function setView(view) {
		$(".nav-link").removeClass("active");
		$("#" + view).addClass("active");

		$.ajax({
			url: "templates/" + view + ".html",
			cashe: false
		}).done(function (template) {
			$("#content").html(template);
		});
	}
	init();
});
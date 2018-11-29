$(document).ready(function() {
	$(".btn-pref .btn").click(function () {
		if(this.id == "beginner") {
			$("#intermediate").removeClass("btn-warning").addClass("btn-default").addClass("btn-int");;
			$("#advanced").removeClass("btn-danger").addClass("btn-default").addClass("btn-adv");;
	    	$(this).removeClass("btn-default").removeClass("btn-beg").addClass("btn-success");  
		} else if (this.id == "intermediate") {
			$("#beginner").removeClass("btn-success").addClass("btn-default").addClass("btn-beg");
			$("#advanced").removeClass("btn-danger").addClass("btn-default").addClass("btn-adv");;
			$(this).removeClass("btn-outline-warning").removeClass("btn-int").addClass("btn-warning");  
		} else if (this.id == "advanced") {
			$("#beginner").removeClass("btn-success").addClass("btn-default").addClass("btn-beg");
			$("#intermediate").removeClass("btn-warning").addClass("btn-default").addClass("btn-int");
			$(this).removeClass("btn-outline-danger").removeClass("btn-adv").addClass("btn-danger");  
		}
	});
});
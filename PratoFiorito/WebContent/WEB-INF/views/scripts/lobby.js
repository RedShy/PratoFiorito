function getEventsFromServer() {
	console.log("DOCUMENTO PRONTO!");
	$.ajax({
		url : "getEvents",
		success : function(result) {
			// 				window.location.href = 'game';
			location.reload(true);
			getEventsFromServer();
		},
		error : function(xhr, status, error) {
			console.log("ERRORE");
			console.log(xhr.responseText);
			console.log(JSON.parse(xhr.responseText));
			//call events again after some time
			setTimeout(function() {
				getEventsFromServer();
			}, 500);
		}
	});
}
$(document).ready(getEventsFromServer());
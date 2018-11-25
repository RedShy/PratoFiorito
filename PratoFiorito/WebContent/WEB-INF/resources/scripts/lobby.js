function getEventsFromServer() {
	console.log("CHIAMO EVENTI DAL SERVER");
	$.ajax({
		url : "getEvents",
		success : function(event) {
			console.log("AJAX SUCCESSO!");
			console.log(event);
			if (event == "gameStarted") {
				// gioco iniziato, vai alla pagina del gioco
				window.location.replace('game');
			} else if (event == "hostLeaved") {
				// L'host ha abbandonato la partita
				// TODO: inviare popup e andare alla mainpage
				window.location.replace('mainPage');
			} else 
			{
				location.reload(true);
			}

		},
		error : function(xhr, status, error) {
			console.log("ERRORE");
			console.log(xhr.responseText);
			console.log(status);
			console.log(error);
			setTimeout(function() {
				getEventsFromServer();
			}, 5000);
		}
	});
}
$(document).ready(getEventsFromServer());
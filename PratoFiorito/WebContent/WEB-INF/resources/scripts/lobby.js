function getEventsFromServer() {
	console.log("CHIAMO EVENTI DAL SERVER");
	$.ajax({
		url : "getEvents",
		success : function(result) {
			console.log("AJAX SUCCESSO!");
			console.log(result);
			event = JSON.parse(result);
			if (event.name == "gameStarted") {
				// gioco iniziato, vai alla pagina del gioco
				window.location.replace('game');
			} else if (event.name == "hostLeaved") {
				// L'host ha abbandonato la partita
				alert("L'host ha abbandonato la lobby, sarai inviato alla pagina principale");
				window.location.replace('mainPage');
			} else if (event.name == "guestLeaved") {
				// Il guest ha abbandonato la partita
				alert("Il guest ha abbandonato la lobby!");
				
				$("#guestName").html("GUEST: EMPTY");
				getEventsFromServer();
			} else if (event.name == "guestJoined") {
				
				$("#guestName").html("GUEST: "+JSON.parse(event.data));
				getEventsFromServer();
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
			}, 2000);
		}
	});
}
$(document).ready(getEventsFromServer());
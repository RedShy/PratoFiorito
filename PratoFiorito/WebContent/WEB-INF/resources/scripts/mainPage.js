function getEventsFromServer() {
	$.ajax({
		url : "getLobbies",
		success : function(result) {
			event = JSON.parse(result);
			console.log(event.data);
			if (event.name == "createdLobby") {
				
				lobby = JSON.parse(event.data);

				lobbyElement = $("<h3></h3>").append(
						lobby.title + " (" + lobby.numberPlayersInside + "/"
								+ lobby.capacity + ")");
				
				//aggiungo id e titolo alla lobby
				lobbyElement.attr("title", lobby.title);
				lobbyElement.attr("id", lobby.title.replace(/ /g, "_"));

				// rende il nome della lobby cliccabile
				lobbyElement.attr("onclick", "joinLobby($(this).attr('title'))")
						.css("cursor", "pointer");

				$("#lobbies").append(lobbyElement);
			} else if (event.name == "removedLobby") {
				lobbyTitle = JSON.parse(event.data);
				$("#" + lobbyTitle.replace(/ /g, "_")).remove();
			}

			getEventsFromServer();
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

function joinLobby(title) {
	console.log("CLICK");
	$.ajax({
		url : "joinLobby",
		data : {
			'lobbyTitle' : title
		},
		success : function(result) {
			console.log("AJAX SUCCESSO");
			if (result === "noLobby") {
				alert("C'è stato un errore con la lobby selezionata");
			} else if (result === "fullLobby") {
				alert("La lobby e' piena, non e' possibile unirsi");
			} else {
				window.location = "lobby";
			}
		},
		error : function(xhr, status, error) {
			console.log("ERRORE");
			console.log(xhr.responseText);
			console.log(status);
			console.log(error);
		}
	});
}

$(document).ready(getEventsFromServer());
function getEventsFromServer()
{
	$.ajax({
		url : "getLobbies",
		success : function(result)
		{
			event = JSON.parse(result);
			console.log(event.data);
			if (event.name == "createdLobby")
			{

				lobby = JSON.parse(event.data);

				lobbyElement = $("<tr></tr>").append(
						"<td>" + lobby.title + "</td>" + "<td>"
								+ lobby.numberPlayersInside + "</td>" + "<td>"
								+ lobby.capacity + "</td>");

				// aggiungo id e titolo alla lobby
				lobbyElement.attr("title", lobby.title);
				lobbyElement.attr("id", lobby.title.replace(/ /g, "_"));

				// rende il nome della lobby cliccabile
				lobbyElement
						.attr("onclick", "joinLobby($(this).attr('title'))")
						.css("cursor", "pointer");

				$("#lobbies").append(lobbyElement);
			} else if (event.name == "removedLobby")
			{
				lobbyTitle = JSON.parse(event.data);
				$("#" + lobbyTitle.replace(/ /g, "_")).remove();
			}

			getEventsFromServer();
		},
		error : function(xhr, status, error)
		{
			console.log("ERRORE");
			console.log(xhr.responseText);
			console.log(status);
			console.log(error);
			setTimeout(function()
			{
				getEventsFromServer();
			}, 5000);
		}

	});
}

function joinLobby(title)
{
	console.log("CLICK");
	$.ajax({
		url : "joinLobby",
		data : {
			'lobbyTitle' : title
		},
		success : function(result)
		{
			console.log("AJAX SUCCESSO");
			if (result === "noLobby")
			{
				$("#alertError").show();
				$("#alertFull").hide();
			} else if (result === "fullLobby")
			{
				$("#alertError").hide();
				$("#alertFull").show();
			} else
			{
				window.location = "lobby";
			}
		},
		error : function(xhr, status, error)
		{
			console.log("ERRORE");
			console.log(xhr.responseText);
			console.log(status);
			console.log(error);
		}
	});
}

$(document).ready(getEventsFromServer());
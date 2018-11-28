function won() {
	alert("HAI VINTO!");
	$("#status").append("<h2 style=\"color: Green;\">HAI VINTO!</h2><audio autoplay><source src=\"resources/sounds/youWin.mp3\" type=\"audio/mp3\" ></audio>");
}

function lost() {
	alert("HAI PERSO");
	$("#status").append("<h2 style=\"color: Red;\">HAI PERSO!</h2><audio autoplay><source src=\"resources/sounds/youLose.mp3\" type=\"audio/mp3\" ></audio>");
}


function updateCells(cells) {
	// data una lista di celle, apporta le modifiche a queste
	for (i = 0; i < cells.length; i++) {
		// seleziona la cella da modificare: #x_y img
		selector = "#" + cells[i].x + "_" + cells[i].y + " img";
		$(selector).attr("src", "resources/images/" + cells[i].image + ".JPG");
	}
}

function getEventsFromServer() {
	$.ajax({
		url : "getEvents",
		success : function(result) {
			event = JSON.parse(result);
			if (event.name === "won") {
				won();
			} else if (event.name === "lost") {
				lost();
			} else if (event.name === "guestLeaved") {
				alert("ATTENZIONE! L'altro giocatore ha abbandonato la partita, ritornerai alla lobby");
				window.location = "lobby";
			} else if (event.name === "hostLeaved") {
				alert("ATTENZIONE! L'host e' tornato alla lobby, sarai inviato anche tu alla lobby");
				window.location = "lobby";
			} else {
				updateCells(JSON.parse(result));
				getEventsFromServer();
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

function sendClick(x, y, click, player) {
	console.log("Invio " + click + " :" + x + ":" + y);
	$.ajax({
		url : click,
		data : {
			'x' : x,
			'y' : y,
			'player' : player
		},
		success : function(response) {
			if (response === "notYourTurn") {
				// non è il tuo turno: non fare nulla
			} else {
				// la risposta del server è fatta di 2 pezzi
				result = JSON.parse(response);

				//1. le celle modificate
				updateCells(result.cells);

				//2. se ho vinto o perso o il gioco continua
				if (result.gameStatus === "won") {
					won();
				} else if (result.gameStatus === "lost") {
					lost();
				}
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

function captureAndSendClick() {
	console.log("CATTURO CLICK");
	document.oncontextmenu = function() {
		return false;
	};

	$(document).mousedown(function(e) {
		console.log("CLICK")
		cell = $(e.target)[0].closest("td");
		x = cell.getAttribute("x");
		y = cell.getAttribute("y");
		player = $("#playing").attr("player");

		if (e.button == 0) {
			sendClick(x, y, "clickLeft", player);
			return false;
		} else if (e.button == 2) {
			sendClick(x, y, "clickRight", player);
			return false;
		}
		return true;
	});
}

function main() {
	console.log("main");
	getEventsFromServer();
	captureAndSendClick();
}

$(document).ready(main());
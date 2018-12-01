function won()
{
	alert("HAI VINTO!");
	$("#status")
			.append(
					"<h2 style=\"color: Green;\">HAI VINTO!</h2><audio autoplay><source src=\"resources/sounds/youWin.mp3\" type=\"audio/mp3\" ></audio>");
}

function lost()
{
	alert("HAI PERSO");
	$("#status")
			.append(
					"<h2 style=\"color: Red;\">HAI PERSO!</h2><audio autoplay><source src=\"resources/sounds/youLose.mp3\" type=\"audio/mp3\" ></audio>");
}

function updateCells(cells)
{
	// data una lista di celle, apporta le modifiche a queste
	for (let i = 0; i < cells.length; i++)
	{
		// seleziona la cella da modificare: #x_y img
		let selector = "#" + cells[i].x + "_" + cells[i].y + " img";
		$(selector).attr("src", "resources/images/" + cells[i].image + ".JPG");
	}
}

function showChangeTurn()
{
	let tmp=$("#playingTurn").attr("src");
	$("#playingTurn").attr("src",$("#notPlayingTurn").attr("src"));
	$("#notPlayingTurn").attr("src",tmp);
}

function getEventsFromServer()
{
	$
			.ajax({
				url : "getEvents",
				success : function(result)
				{
					// indico la data dell'ultimo ping ricevuto dall'altro giocatore
					lastPingReceived = new Date();
					
					event = JSON.parse(result);
					if (event.name === "won")
					{
						won();
					} else if (event.name === "lost")
					{
						lost();
					} else if (event.name === "guestLeaved")
					{
						alert("ATTENZIONE! L'altro giocatore ha abbandonato la partita, ritornerai alla lobby");
						window.location = "lobby";
					} else if (event.name === "hostLeaved")
					{
						alert("ATTENZIONE! L'host e' tornato alla lobby, sarai inviato anche tu alla lobby");
						window.location = "lobby";
					} else if (event.name === "clickLeft"
							|| event.name === "clickRight")
					{
						updateCells(JSON.parse(event.data));
						showChangeTurn();
						getEventsFromServer();
					} else
					{
						getEventsFromServer();
					}
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
					}, 2000);
				}
			});
}

function sendClick(x, y, click, player)
{
	console.log("Invio " + click + " :" + x + ":" + y);
	$.ajax({
		url : "click",
		data : {
			'x' : x,
			'y' : y,
			'player' : player,
			'click' : click
		},
		success : function(response)
		{
			if (response === "notYourTurn" || response === "cannotOpenCell"
					|| response === "cannotPlaceFlag")
			{
				// non è il tuo turno o non puoi aprire la cella o non puoi
				// piazzare bandiera: non fare nulla
			} else
			{
				
				// la risposta del server è fatta di 2 pezzi
				result = JSON.parse(response);

				// 1. le celle modificate
				updateCells(result.cells);
				
				//cambio il turno
				showChangeTurn();

				// 2. se ho vinto o perso o il gioco continua
				if (result.gameStatus === "won")
				{
					won();
				} else if (result.gameStatus === "lost")
				{
					lost();
				}
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

function captureAndSendClick()
{
	console.log("CATTURO CLICK");
	document.oncontextmenu = function()
	{
		return false;
	};

	$(document).mousedown(function(e)
	{
		console.log("CLICK")
		let cell = $(e.target)[0].closest("td");
		let x = cell.getAttribute("x");
		let y = cell.getAttribute("y");
		let player = $("#playing").attr("player");

		if (e.button == 0)
		{
			sendClick(x, y, "clickLeft", player);
			return false;
		} else if (e.button == 2)
		{
			sendClick(x, y, "clickRight", player);
			return false;
		}
		return true;
	});
}

// Invio ogni 30 secondi un ping per dire che sono ancora presente
function sendPing()
{
	$.ajax({
		url : "ping",
		success : function(response)
		{
			setTimeout(function()
			{
				sendPing();
			}, 30000);
		},
		error : function(xhr, status, error)
		{
			console.log("ERRORE");
			console.log(xhr.responseText);
			console.log(status);
			console.log(error);
			setTimeout(function()
			{
				sendPing();
			}, 2000);
		}
	});
}

var lastPingReceived = new Date();

function main()
{
	console.log("main");
	getEventsFromServer();
	captureAndSendClick();
	sendPing();

	// ogni 60 secondi controlla da quanto tempo hai ricevuto l'ultimo evento
	// dall'altro giocatore
	setInterval(function()
	{
		let currentDate = new Date();
		let millisecondsFromLastPing = currentDate - lastPingReceived;

		// Se l'ultimo evento l'ho ricevuto dopo oltre 60 secondi, assumo che
		// l'altro giocatore ha perso la connessione con il server
		if (millisecondsFromLastPing > 35000)
		{
			// comunico al server che l'altro giocatore ha perso la connessione
			$.ajax({
				url : "otherPlayerLostConnection",
				success : function(response)
				{
					alert("L'altro giocatore ha perso la connessione!")
					// esco dal gioco corrente
					window.location = response;
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

	}, 60000);
}

$(document).ready(main());
function getEventsFromServer() {
	console.log("CHIAMO EVENTI DAL SERVER");
	$.ajax({
		url : "getEvents",
		success : function(result) {
			console.log("AJAX SUCCESSO!");
			location.reload(true);
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
		success : function(result) {

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
$(document).ready(function()
{
	$("#username").focusout(function()
	{
		console.log($("#username").val());
		if ($("#username").val())
		{
			$.ajax({
				url : "check",
				data : {
					'username' : $("#username").val(),
				},
				success : function(result)
				{
					if (result === "KO")
					{
						$("#alert").show();
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

	});
});
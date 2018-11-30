package pratofiorito.components.services;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Event
{
	public static final String USER_JOINED = "userJoined";
	public static final String USER_EXITED = "userExited";
	public static final String GAME_STARTED = "gameStarted";
	public static final String CLICK_LEFT = "clickLeft";
	public static final String CLICK_RIGHT = "clickRight";
	public static final String HOST_LEAVED = "hostLeaved";
	public static final String GUEST_LEAVED = "guestLeaved";
	public static final String GUEST_JOINED = "guestJoined";
	public static final String UPDATE_LOBBY = "updateLobby";
	public static final String CREATED_LOBBY = "createdLobby";
	public static final String REMOVED_LOBBY = "removedLobby";
	public static final String WON = "won";
	public static final String LOST = "lost";

	private String name;
	private String data;
	private static ObjectMapper mapper = new ObjectMapper();

	public Event(String name, Object JSONData)
	{
		this.name = name;

		setJSONData(JSONData);
	}

	public Event(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getData()
	{
		return data;
	}

	public void setJSONData(Object data)
	{
		try
		{
			// Controlla se è già in formato JSON
			mapper.readTree((String) data);
			
			// Nessuna eccezione: inseriscilo direttamente
			this.data = (String) data;
		} catch (IOException | ClassCastException e1)
		{
			//non è in formato JSON: trasformalo in JSON
			try
			{
				this.data = mapper.writeValueAsString(data);
			} catch (JsonProcessingException e)
			{
				e.printStackTrace();
			}
		}
	}

	public String toJSON()
	{
		try
		{
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}

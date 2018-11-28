package pratofiorito.components.services;

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
	public static final String WON = "won";
	public static final String LOST = "lost";
	
	private String name;
	private String data;
	private static ObjectMapper mapper = new ObjectMapper();
	
	
	public Event(String name, Object JSONData)
	{
		super();
		this.name = name;
		
		setJSONData(JSONData);
	}
	
	public Event(String name)
	{
		this(name,null);
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
			this.data = mapper.writeValueAsString(data);
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
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

package pratofiorito.components.services;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Service;

@Service
public class EventsService
{
	private Map<String, BlockingQueue<String>> events = new HashMap<>();

	public String nextEvent(String id) throws InterruptedException
	{
		if (!events.containsKey(id))
		{
			events.put(id, new LinkedBlockingQueue<>());
		}
		return events.get(id).take();
	}
	
	public void insertEvent(String id, String event) throws InterruptedException
	{
		System.out.println("EVENTO INSERITO ID="+id +" EVENTO: "+event);
		if (!events.containsKey(id))
		{
			events.put(id, new LinkedBlockingQueue<>());
		}
		
		events.get(id).put(event);
		
	}

}

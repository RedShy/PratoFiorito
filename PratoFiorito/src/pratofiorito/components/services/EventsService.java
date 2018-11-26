package pratofiorito.components.services;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Service;

@Service
public class EventsService
{
	//abbiamo una lista di eventi per ogni utente
	private Map<String, BlockingQueue<String>> events = new HashMap<>();
	
	// indica per ogni utente se esiste già un thread in ascolto sui suoi eventi
	private Map<String, Thread> threadsAlreadyListening = new HashMap<>();

	public String nextEvent(String username) throws InterruptedException
	{
		if (!events.containsKey(username))
		{
			events.put(username, new LinkedBlockingQueue<>());
			
			threadsAlreadyListening.put(username, null);
		}
		
		// esiste già un thread in ascolto sugli eventi dell'utente?
		if(threadsAlreadyListening.get(username) != null)
		{
			System.out.println("SONO IL THREAD "+ Thread.currentThread()+" E INTERROMPO IL THREAD "+threadsAlreadyListening.get(username));
			
			// C'è già un altro thread in ascolto, interrompilo per sostituirlo
			threadsAlreadyListening.get(username).interrupt();
		}
		
		//Indica che ci sei tu ad ascoltare gli eventi
		threadsAlreadyListening.put(username, Thread.currentThread());
		
		String event = events.get(username).take();
	
		//Indica che il posto è libero per ascoltare eventi
		threadsAlreadyListening.put(username, null);
		
		if(event==null) {
			return "NOTHING";
		}
		
		return event;
	}
	
	public void insertEvent(String username, String event) throws InterruptedException
	{
		System.out.println("EVENTO INSERITO PER "+username +" EVENTO: "+event);
		if (!events.containsKey(username))
		{
			events.put(username, new LinkedBlockingQueue<>());
		}
		
		events.get(username).put(event);
	}

}

package soa.utwente.nl.AvailabilityUpdate;

import org.springframework.stereotype.Service;
import soa.utwente.nl.AvailabilityUpdate.Exceptions.NotFoundException;

import javax.websocket.Session;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AvailabilityService {
    private static final Map<Integer, Availability> availabilities = Collections.synchronizedMap(new HashMap<>());
    private static int idCounter = 1;

    public Availability createAvailability(Availability availability) {
        availability.setSessionId(idCounter++);
        availabilities.put(availability.getSessionId(), availability);
        return availability;
    }

    public Availability updateAvailability(Availability availability){
        availabilities.put(availability.getSessionId(), availability);
        return availability;
    }

    public void deleteAvailability(Integer id){
        if(!availabilities.containsKey(id)) throw new NotFoundException("Could not find user with id " + id);
        availabilities.entrySet().removeIf(x-> x.getKey().equals(id));
    }

    public Availability getAvailability(Integer id){
        if(!availabilities.containsKey(id)) throw new NotFoundException("Could not find user with id " + id);
        return availabilities.get(id);
    }

    public List<Integer> getAvailable(Integer id){
        if(!availabilities.containsKey(id)) throw new NotFoundException("Could not find user with id " + id);
        return availabilities.get(id).getAvailable();
    }

    public List<Integer> getMaybe(Integer id){
        if(!availabilities.containsKey(id)) throw new NotFoundException("Could not find user with id " + id);
        return availabilities.get(id).getMaybe();
    }

    public List<Integer> getNotAvailable(Integer id){
        if(!availabilities.containsKey(id)) throw new NotFoundException("Could not find user with id " + id);
        return availabilities.get(id).getNotAvailable();
    }
}

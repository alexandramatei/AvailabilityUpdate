package soa.utwente.nl.AvailabilityUpdate;

import org.springframework.stereotype.Service;
import soa.utwente.nl.AvailabilityUpdate.Classes.Availability;
import soa.utwente.nl.AvailabilityUpdate.Exceptions.NotFoundException;

import java.util.*;

@Service
public class AvailabilityService {
    private static final List<Availability> availabilities = new ArrayList<>();


    public Availability createAvailability(Availability availability) {
        availabilities.add(availability);
        return availability;
    }

    public Availability updateAvailability(Availability availability){
        availabilities.set(availability.getSessionId(), availability);
        return availability;
    }

    public void deleteAvailability(Integer id){
       availabilities.remove(getAvailability(id));
    }

    public Availability getAvailability(Integer id){
        return availabilities.stream().filter(availability -> availability.getSessionId() == id).findFirst().orElseThrow(NotFoundException::new);
    }

    public List<Integer> getAvailable(Integer id){
        return getAvailability(id).getAvailable();
    }

    public List<Integer> getMaybe(Integer id){
        return getAvailability(id).getMaybe();
    }

    public List<Integer> getNotAvailable(Integer id){
        return getAvailability(id).getNotAvailable();
    }

    public boolean updateAvailableUser(int sessionId, int userId){
       Availability userAvailability = getAvailability(sessionId);
       if(userAvailability.getAvailable().contains(userId)){return true;}
       if(userAvailability.getMaybe().contains(userId)){userAvailability.getMaybe().remove(userId);}
       if(userAvailability.getNotAvailable().contains(userId)){userAvailability.getNotAvailable().remove(userId);}
       userAvailability.getAvailable().add(userId);
       return true;
    }

    public boolean updateMaybeUser(int sessionId, int userId){
        Availability userAvailability = getAvailability(sessionId);
        if(userAvailability.getMaybe().contains(userId)){return true;}
        if(userAvailability.getAvailable().contains(userId)){userAvailability.getAvailable().remove(userId);}
        if(userAvailability.getNotAvailable().contains(userId)){userAvailability.getNotAvailable().remove(userId);}
        userAvailability.getMaybe().add(userId);
        return true;
    }

    public boolean updateNotAvailableUser(int sessionId, int userId){
        Availability userAvailability = getAvailability(sessionId);
        if(userAvailability.getNotAvailable().contains(userId)){return true;}
        if(userAvailability.getMaybe().contains(userId)){userAvailability.getMaybe().remove(userId);}
        if(userAvailability.getAvailable().contains(userId)){userAvailability.getAvailable().remove(userId);}
        userAvailability.getNotAvailable().add(userId);
        return true;
    }


}

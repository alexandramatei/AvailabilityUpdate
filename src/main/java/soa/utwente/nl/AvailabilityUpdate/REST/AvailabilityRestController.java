package soa.utwente.nl.AvailabilityUpdate.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soa.utwente.nl.AvailabilityUpdate.Classes.Availability;
import soa.utwente.nl.AvailabilityUpdate.AvailabilityService;

import java.util.List;

@RestController
public class AvailabilityRestController {

    @Autowired private AvailabilityService service;

    @GetMapping(value = "availability/{sessionId}")
    public Availability getAvailability(@PathVariable Integer sessionId){return service.getAvailability(sessionId);}

    @GetMapping(value = "availability/available/{sessionId}")
    public List<Integer> getAvailable(@PathVariable Integer sessionId){return service.getAvailable(sessionId);}

    @GetMapping(value = "availability/maybeAvailable/{sessionId}")
    public List<Integer> getMaybe(@PathVariable Integer sessionId){return service.getMaybe(sessionId);}

    @GetMapping(value = "availability/notAvailable/{sessionId}")
    public List<Integer> getNotAvailable(@PathVariable Integer sessionId){return service.getNotAvailable(sessionId);}

    @PostMapping
            (path="/addAvailability", consumes = "application/json", produces = "application/json")
    public Availability createAvailability(@RequestBody Availability availability){return service.createAvailability(availability);}

    @PutMapping(
            path="/updateAvailability", consumes = "application/json", produces = "application/json")
    public Availability updateSession(@RequestBody Availability availability){return service.updateAvailability(availability);}


    @DeleteMapping(value = "/deleteAvailability/{sessionId}")
    public void deleteAvailability(@PathVariable Integer sessionId){service.deleteAvailability(sessionId);}



    @GetMapping(value = "/updateAvailability/{sessionId}/{userId}/available")
    public boolean updateAvailableUser(@PathVariable int sessionId, @PathVariable int userId){
        return service.updateAvailableUser(sessionId,userId);
    }

    @GetMapping(value = "/updateAvailability/{sessionId}/{userId}/maybe")
    public boolean updateMaybeUser(@PathVariable int sessionId, @PathVariable int userId){
        return service.updateMaybeUser(sessionId,userId);
    }

    @GetMapping(value = "/updateAvailability/{sessionId}/{userId}/notAvailable")
    public boolean updateNotAvailableUser(@PathVariable int sessionId, @PathVariable int userId){
        return service.updateNotAvailableUser(sessionId,userId);
    }

    @GetMapping(value = "/healthCheck")
    public Boolean healthCheck(){return true;}
}

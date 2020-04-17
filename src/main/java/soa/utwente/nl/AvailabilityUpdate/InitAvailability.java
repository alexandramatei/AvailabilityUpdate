package soa.utwente.nl.AvailabilityUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import soa.utwente.nl.AvailabilityUpdate.Classes.Availability;
import soa.utwente.nl.AvailabilityUpdate.Classes.Subscription;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

@Component
public class InitAvailability {
    @Autowired private AvailabilityService service;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @PostConstruct
    public void init(){
        service.createAvailability(new Availability(
                1,
                new ArrayList<Integer>(){{
                     add(201934);
                     add(202134); }},
                new ArrayList<Integer>(){{
                    add(208934);
                    add(212134);
                    add(202564); }},
                 new ArrayList<Integer>(){{
                    add(205864);
                    add(202590);
                    add(207864); }}

                ));
        service.createAvailability(new Availability(
                2,
                new ArrayList<Integer>(){{
                    add(201934);
                    add(202134); }},
                new ArrayList<Integer>(){{
                    add(208934);
                    add(212134);
                    add(202564); }},
                new ArrayList<Integer>(){{
                    add(205864);
                    add(202590);
                    add(207864); }}

        ));

        String subscriptionUrl = "http://localhost:8087/subscribe";
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForObject(subscriptionUrl, new Subscription("sessions", "http://localhost:8083/" ), Subscription.class);
    }
}

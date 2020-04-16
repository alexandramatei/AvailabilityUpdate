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
                     add(2);
                     add(4);
                     add(3); }},
                new ArrayList<Integer>(){{
                    add(10);
                    add(122);
                    add(35); }},
                 new ArrayList<Integer>(){{
                    add(156);
                    add(2345);
                    add(3457); }}

                ));
        service.createAvailability(new Availability(
                2,
                new ArrayList<Integer>(){{
                    add(2);
                    add(4);
                    add(3); }},
                new ArrayList<Integer>(){{
                    add(10);
                    add(122);
                    add(35); }},
                new ArrayList<Integer>(){{
                    add(156);
                    add(2345);
                    add(3457); }}

        ));

        String subscriptionUrl = "http://localhost:8087/subscribe";
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForObject(subscriptionUrl, new Subscription("sessions", "http://localhost:8083/" ), Subscription.class);
    }
}

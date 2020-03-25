package soa.utwente.nl.AvailabilityUpdate;

import java.util.List;

public class Availability {
    private Integer sessionId;
    private List<Integer> available;
    private List<Integer> maybe;
    private List<Integer> notAvailable;

    public Availability(Integer sessionId, List<Integer> available, List<Integer> maybe, List<Integer> notAvailable) {
        this.sessionId = sessionId;
        this.available = available;
        this.maybe = maybe;
        this.notAvailable = notAvailable;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public List<Integer> getAvailable() {
        return available;
    }

    public void setAvailable(List<Integer> available) {
        this.available = available;
    }

    public List<Integer> getMaybe() {
        return maybe;
    }

    public void setMaybe(List<Integer> maybe) {
        this.maybe = maybe;
    }

    public List<Integer> getNotAvailable() {
        return notAvailable;
    }

    public void setNotAvailable(List<Integer> notAvailable) {
        this.notAvailable = notAvailable;
    }
}

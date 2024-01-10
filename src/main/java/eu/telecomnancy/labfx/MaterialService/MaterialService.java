package eu.telecomnancy.labfx.MaterialService;

import eu.telecomnancy.labfx.utils.Reservation;

public interface MaterialService {
    public void activate();
    public void deactivate();
    public void addReservation(Reservation reservation);
    public void removeReservation(Reservation reservation);
}

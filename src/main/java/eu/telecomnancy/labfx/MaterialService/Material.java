package eu.telecomnancy.labfx.MaterialService;

import eu.telecomnancy.labfx.utils.ReservationDelay;

import java.util.ArrayList;

public class Material implements MaterialService{
    private int id;
    private String name;
    private String type;
    private int owner;
    private String cost;
    private String description;
    private String createdAt;
    private String updatedAt;
    private String startTime;
    private String endTime;
    private ArrayList<ReservationDelay> reservationDelays;
    private String image;
    private boolean isActive;

    public Material(int id, String name, int owner, String cost, String description, String createdAt, String updatedAt, String startTime, String endTime, ArrayList<ReservationDelay> reservationDelays, String image, boolean isActive) {
        this.id = id;
        this.name = name;
        this.type = "materiel";
        this.owner = owner;
        this.cost = cost;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reservationDelays = reservationDelays;
        this.image = image;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ArrayList<ReservationDelay> getReservationDelays() {
        return reservationDelays;
    }

    public void setReservationDelays(ArrayList<ReservationDelay> reservationDelays) {
        this.reservationDelays = reservationDelays;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

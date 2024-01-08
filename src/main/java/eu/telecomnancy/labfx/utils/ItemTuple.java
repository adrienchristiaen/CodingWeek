package eu.telecomnancy.labfx.utils;

import java.time.LocalDateTime;

public class ItemTuple {
    int id;
    LocalDateTime tansactionTime;

    public ItemTuple(int id, LocalDateTime tansactionTime) {
        this.id = id;
        this.tansactionTime = tansactionTime;
    }

    public int getId() {
        return id;
    }
    public LocalDateTime getTansactionTime() {
        return tansactionTime;
    }
}

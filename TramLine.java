package org.example;
import java.util.List;

public class TramLine {
    private String name;
    private List<TramStation> stations;

    public TramLine(String name, List<TramStation> stations) {
        this.name = name;
        this.stations = stations;
    }

    public String getName() {
        return name;
    }

    public List<TramStation> getStations() {
        return stations;
    }
}
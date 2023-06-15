package org.example;

import java.util.ArrayList;
import java.util.List;

public class TramNetworkSimulation {

    public static TramNetwork createTramNetwork() {
        List<TramLine> lines = new ArrayList<>();
        lines.add(createLineA());
        lines.add(createLineB());
        lines.add(createLineC());
        return new TramNetwork(lines);

    }

    /**
     *
     * @return
     */
    private static TramLine createLineA() {
        List<TramStation> stations = new ArrayList<>();
        stations.add(new TramStation("Station 1", 100, 400));
        stations.add(new TramStation("Station 2", 190, 400));
        stations.add(new TramStation("Station 3", 100, 300));
        stations.add(new TramStation("Station 4", 100, 200));
        stations.add(new TramStation("Station 5", 200, 200));
        stations.add(new TramStation("Station 6", 300, 300));
        stations.add(new TramStation("Station 7", 210, 400));
        stations.add(new TramStation("Station 8", 400, 400));

        return new TramLine("Line A", stations);
    }

    private static TramLine createLineB() {
        List<TramStation> stations = new ArrayList<>();
        stations.add(new TramStation("Station X", 150, 150));
        stations.add(new TramStation("Station Y", 250, 250));
        stations.add(new TramStation("Station Z", 350, 350));

        return new TramLine("Line B", stations);
    }
    private static TramLine createLineC() {
        List<TramStation> stations = new ArrayList<>();
        stations.add(new TramStation("Station 5", 300, 100));
        stations.add(new TramStation("Station 6", 400, 100));
        stations.add(new TramStation("Station 7", 500, 200));
        stations.add(new TramStation("Station 8", 350, 250));

        return new TramLine("Line C", stations);
    }
}


class TramNetwork {
    private List<TramLine> lines;

    public TramNetwork(List<TramLine> lines) {
        this.lines = lines;
    }

    public List<TramLine> getLines() {
        return lines;
    }
}

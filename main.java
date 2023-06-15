package org.example;

import javax.swing.JFrame;

public class main {
    public static void main(String[] args) {

        TramNetwork tramNetwork = TramNetworkSimulation.createTramNetwork();

        JFrame frame = new JFrame("Tram Network Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);

        TramSimulationPanel simulationPanel = new TramSimulationPanel(tramNetwork);
        frame.add(simulationPanel);

        frame.setVisible(true);
    }


}
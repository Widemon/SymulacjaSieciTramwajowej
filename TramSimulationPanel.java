package org.example;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//klasa TramSimulationPanel dziedziczy po JPanel
class TramSimulationPanel extends JPanel {

    public static int population = Integer.parseInt(JOptionPane.showInputDialog(null,"Podaj liczbe populacji"));
    public static int speed1=Integer.parseInt(JOptionPane.showInputDialog(null,"Podaj predkosc linii 1[ms]"));
    public static int speed2=Integer.parseInt(JOptionPane.showInputDialog(null,"Podaj predkosc linii 2[ms]"));
    private TramNetwork tramNetwork;
    private int tramPeople=0 ;
    private int tramPeople2=0 ;
    private int tramIndex;
    private int tramIndex2;
    private int lineIndex;
    private int lineIndex2;
    private int direction;
    private int direction2;
    private int lastx,lasty;
    private int lastx2,lasty2;
    public int stop=0;
    public List<String[]> output1 = new ArrayList<>();
    public List<String[]> output2 = new ArrayList<>();
    private int allStationsAmount;

    public void save() {

            // Specify the output file path
        String outputPath = "output.csv";

        try {
            FileWriter fileWriter = new FileWriter(outputPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // Write header line to the CSV file
            String header = "Tramwaj1,Tramwaj2";
            bufferedWriter.write(header);
            bufferedWriter.newLine(); // Move to the next line
            for(int i=0;i<output1.size();i++){
                String dataLine = String.join(",", output1.get(i));
                bufferedWriter.write(dataLine);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("CSV file created successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error occurred while writing to the CSV file.");
        }
    }

    public TramSimulationPanel(TramNetwork tramNetwork) {
        this.tramNetwork = tramNetwork;
        this.tramIndex = 0;
        this.tramIndex2=0;
        this.lineIndex = 2;
        this.lineIndex2 = 0;
        allStationsAmount = tramNetwork.getLines().get(2).getStations().size()+tramNetwork.getLines().get(0).getStations().size() ;

        if(stop==0){
            setupTram();
            stop=1;
        }


        Timer timer = new Timer(speed1, e -> moveTram());
        Timer timer2 = new Timer(speed2, e -> moveTram2());
        timer.start();
        timer2.start();


    }
    public int distribution(){
        return population /allStationsAmount;
    }
    public void setupTram(){
        TramLine currentLine = tramNetwork.getLines().get(lineIndex2);
        List<TramStation> stations2 = currentLine.getStations(); //metoda getStations() jest wywoływana przez obiekt currentLine

        for(TramStation station : stations2) {
            station.updateNumPeople(distribution());
        }

        TramLine currentLine2 = tramNetwork.getLines().get(lineIndex);
        List<TramStation> stations = currentLine2.getStations(); //metoda getStations() jest wywoływana przez obiekt currentLine2
                                                                 //ten i przypadek powyżej ilustuje nam przykład polimorfizmu w kodzie

        for (TramStation station : stations) {
            station.updateNumPeople(distribution());
        }
    }
    private void moveTram2() {
        TramLine currentLine = tramNetwork.getLines().get(lineIndex2);
        List<TramStation> stations2 = currentLine.getStations();
        if(tramIndex2 >= stations2.size()-1){
            direction2=0;
        }
        if(tramIndex2 == 0){
            direction2=1;
        }
        if(direction2==1){
            tramIndex2++;
        }
        else{
            tramIndex2--;
        }
        int i=0;

        TramStation currentStation = stations2.get(tramIndex2);
        tramPeople2 = currentStation.exchangePeople(tramPeople2);

        output1.add(new String[]{Integer.toString(tramPeople2),Integer.toString(tramPeople)});
        if(tramPeople2>50){
            JOptionPane.showMessageDialog(null,"Tramwaj 2 sie wywroclawil");
            save();
            System.exit(2);
        }
        repaint();
    }
    private void moveTram() {
        TramLine currentLine = tramNetwork.getLines().get(lineIndex);
        List<TramStation> stations = currentLine.getStations();
        if(tramIndex >= stations.size()-1){
            direction=0;
        }
        if(tramIndex == 0){
            direction=1;
        }
        if(direction==1){
            tramIndex++;
        }
        else{
            tramIndex--;
        }

        TramStation currentStation = stations.get(tramIndex);
        tramPeople= currentStation.exchangePeople(tramPeople);
        output2.add(new String[]{String.valueOf(tramPeople)});
        if(tramPeople>50){
            save();
            JOptionPane.showMessageDialog(null,"Tramwaj 1 sie wywroclawil");
            System.exit(1);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        TramLine currentLine = tramNetwork.getLines().get(lineIndex);
        List<TramStation> stations = currentLine.getStations();
        TramStation currentStation = stations.get(tramIndex);
        g.setColor(Color.RED);
        g.fillRect(currentStation.getX(), currentStation.getY(), 20, 10);


        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(tramPeople),currentStation.getX()+15, currentStation.getY());

        for (TramStation station : stations) {
            int numPeople = station.getNumPeople();

            System.out.println(stations.get(2).getNumPeople());
            int x = station.getX();
            int y = station.getY();
            g.drawString(Integer.toString(numPeople), x, y - 10);
            g.setColor(Color.BLACK);
            g.drawLine(x,y,lastx,lasty);
            g.setColor(Color.BLUE);
            g.fillOval(x - 10, y - 10, 20, 20);
            lastx=x;
            lasty=y;

        }
        TramLine currentLine2 = tramNetwork.getLines().get(lineIndex2);
        List<TramStation> stations2 = currentLine2.getStations();
        TramStation currentStation2 = stations2.get(tramIndex2);
        g.setColor(Color.RED);
        g.fillRect(currentStation2.getX(), currentStation2.getY(), 20, 10);


        g.drawString(String.valueOf(tramPeople2),currentStation2.getX()+15, currentStation2.getY());

        for (TramStation station : stations2) {

            int x = station.getX();
            int y = station.getY();

            g.setColor(Color.BLACK);
            g.drawLine(x,y,lastx2,lasty2);
            g.setColor(Color.BLACK);
            int numPeople = station.getNumPeople();
            g.drawString(Integer.toString(numPeople), x,y - 10);

            g.setColor(Color.BLUE);
            g.fillOval(x - 10, y - 10, 20, 20);
            lastx2=x;
            lasty2=y;
        }
    }
}

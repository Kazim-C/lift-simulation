import Lift.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;


import javax.swing.*;

public class Frame extends JComponent{

    private static class Line{
        final int x1;
        final int y1;
        final int x2;
        final int y2;
        final Color color;

        public Line(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
    }

    private static class Rectangle{
        final int rectX;
        final int rectY;
        final int width;
        final int height;
        final Color color;


        public Rectangle(int rectX, int rectY, int width, int height, Color color) {
            this.rectX = rectX;
            this.rectY = rectY;
            this.width = width;
            this.height = height;
            this.color = color;
        }
    }

    private final LinkedList<Line> lines = new LinkedList<Line>();
    private final LinkedList<Rectangle> rectangles = new LinkedList<Rectangle>();

    public void addLine(int x1, int x2, int x3, int x4) {
        addLine(x1, x2, x3, x4, Color.black);
    }

    public void addRectangle(int rectX, int rectY, int width, int height, Color color) {

        rectangles.add(new Rectangle(rectX, rectY, width, height, color));
        repaint();


    }

    public void addLine(int x1, int x2, int x3, int x4, Color color) {
        lines.add(new Line(x1,x2,x3,x4, color));
        repaint();
    }

    public void clearLines() {
        lines.clear();
        repaint();
    }

    public void clearRect() {
        rectangles.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        for (Line line : lines) {
            g.setColor(line.color);
            g.drawLine(line.x1, line.y1, line.x2, line.y2);
        }

        for (int i = 0; i < rectangles.size(); i++) {
            g.setColor(Color.blue);



            g.fillRect(rectangles.get(i).rectX, rectangles.get(i).rectY, rectangles.get(i).width, rectangles.get(i).height);
            g.fillRect(rectangles.get(i + 1).rectX, rectangles.get(i + 1).rectY, rectangles.get(i + 1).width, rectangles.get(i + 1).height);

            i++;


        }


    }

    public static void main(String[] args) throws CloneNotSupportedException{






        //THIS IS FOR THE FRAME :
        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final Frame comp = new Frame();
        comp.setPreferredSize(new Dimension(900, 960));
        testFrame.getContentPane().add(comp, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();
        JButton newLineButton = new JButton("Launch Simulation");

        //text field and text label for PEOPLE input
        final JTextField fieldPeople = new JTextField();
        fieldPeople.setBounds(180,-8, 100,30);
        JLabel people =new JLabel("Number Of People:");
        people.setBounds(50,-8, 120,30);

        //text field and text label for FLOORS input
        final JTextField fieldFloors = new JTextField();
        fieldFloors.setBounds(780,-8, 100,30);
        JLabel floors =new JLabel("Number Of Floors:");
        floors.setBounds(650,-8, 120,30);

        //label for current lift floor
        JLabel currentFloorLift =new JLabel("0");
        currentFloorLift.setBounds(270,850, 120,30);
        JLabel currentFloorLiftText =new JLabel("Current Lift Floor:");
        currentFloorLiftText.setBounds(150,850, 120,30);


        //label for current lift floor Base Case
        JLabel currentFloorLiftBC =new JLabel("0");
        currentFloorLiftBC.setBounds(760,850, 120,30);
        JLabel currentFloorLiftBCText =new JLabel("Current Lift Floor Base Case:");
        currentFloorLiftBCText.setBounds(570,850, 190,30);

        //label for travelled floors Base Case
        JLabel totalFloorsTraveledBC =new JLabel("0");
        totalFloorsTraveledBC.setBounds(760,865, 120,30);
        JLabel totalFloorsTraveledTextBC =new JLabel("Travelled floors:");
        totalFloorsTraveledTextBC.setBounds(570,865, 190,30);

        //label for travelled floors Base Case
        JLabel totalFloorsTraveled =new JLabel("0");
        totalFloorsTraveled.setBounds(270,865, 120,30);
        JLabel totalFloorsTraveledText =new JLabel("Travelled floors:");
        totalFloorsTraveledText.setBounds(150,865, 190,30);



        comp.add(fieldFloors);comp.add(fieldPeople);comp.add(floors);comp.add(people);
        //adding to the panel the current lift floors UI
        comp.add(currentFloorLift);comp.add(currentFloorLiftText);
        //adding to the panel the current lift floor Base Case UI
        comp.add(currentFloorLiftBC);comp.add(currentFloorLiftBCText);

        //adding to the panel the travelled floors Base Case
        comp.add(totalFloorsTraveledBC);comp.add(totalFloorsTraveledTextBC);
        //adding to the panel the travelled floors
        comp.add(totalFloorsTraveled);comp.add(totalFloorsTraveledText);


        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("Improved || Base Case");



        panel.add(label1);




        buttonsPanel.add(newLineButton);

        testFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        testFrame.getContentPane().add(panel, BorderLayout.NORTH);




        newLineButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                comp.clearLines();





                AlgoWithoutNames Algo = new AlgoWithoutNames();
                int numberOfPeople;
                int numberOfFloors;



                try {
                    numberOfFloors = Integer.parseInt(fieldFloors.getText());
                }
                catch (NumberFormatException exc)
                {
                    numberOfFloors = 5;
                }

                try {
                    numberOfPeople = Integer.parseInt(fieldPeople.getText());
                }
                catch (NumberFormatException excp)
                {
                    numberOfPeople = 10;
                }







                try {
                    Algo.listCreationForFloors(numberOfFloors, numberOfPeople);
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }

                Algo.baseCase(numberOfFloors, numberOfPeople);
                Algo.baseCaseImproved(numberOfFloors, numberOfPeople);






                comp.addLine(45, 20, 850, 20, Color.black);
                comp.addLine(450, 20, 450, 860, Color.black);
                comp.addLine(400, 20, 400, 860, Color.black);
                comp.addLine(500, 20, 500, 860, Color.black);


                int y1 = 0;
                for (int i = 0; i < numberOfFloors; i++) {

                    y1 += (840 / numberOfFloors) ;
                    int x1 = 45;
                    int x2 = 855;

                    comp.addLine(x1, y1 +20, x2, y1 +20, Color.black);

                }


                final int[] i = {0};
                final int[] x = {0};

                final int[] y = new int[1];
                final int[] y2 = new int[1];

                final int[] finish = new int[1];

                int finalNumberOfFloors = numberOfFloors;
                Timer timer = new Timer(500/(1+Integer.valueOf(numberOfFloors/5)), new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        comp.clearRect();

                        int valueOfFloor = Algo.getListsFloors().get(i[0]);
                        int valueOfFloorBaseCase = Algo.getListsFloorsBaseCase().get(x[0]);

                        //setting the label to the lift floor
                        currentFloorLift.setText(String.valueOf(valueOfFloor));
                        //setting the label to the lift floor Base Case
                        currentFloorLiftBC.setText(String.valueOf(valueOfFloorBaseCase));

                        //setting the label to travelled floors
                        totalFloorsTraveledBC.setText(String.valueOf(x[0]));
                        totalFloorsTraveled.setText(String.valueOf(i[0]));



                        y[0] = (((840 / finalNumberOfFloors) * Math.abs((valueOfFloor - finalNumberOfFloors)+1)) + ((840 / finalNumberOfFloors) / 4))+ 20;
                        y2[0] = ((840 / finalNumberOfFloors) * Math.abs((valueOfFloorBaseCase - finalNumberOfFloors)+1) + ((840 / finalNumberOfFloors) / 4)) +20;


                        comp.addRectangle(415, y[0], 20, (840 / finalNumberOfFloors / 2), Color.red);
                        comp.addRectangle(465, y2[0], 20, (840 / finalNumberOfFloors / 2), Color.red);

                        if (i[0] < Algo.getListsFloors().size() - 1) {

                            i[0]++;

                        }


                        if (x[0] < Algo.getListsFloorsBaseCase().size() - 1) {

                            x[0]++;

                        } else {
                            finish[0]++;
                        }

                        if (finish[0] == 1) {

                            ((Timer) ae.getSource()).stop();
                            //setting the label to travelled floors
                            totalFloorsTraveledBC.setText(String.valueOf(x[0]+1));
                            totalFloorsTraveled.setText(String.valueOf(i[0]+1));
                        }

                    }
                });
                timer.setRepeats(true);
                timer.setCoalesce(true);
                timer.setInitialDelay(0);
                timer.start();


            }
        });


        testFrame.pack();
        testFrame.setVisible(true);
    }
}
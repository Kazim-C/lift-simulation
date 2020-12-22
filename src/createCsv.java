import Lift.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.PrintWriter;

public class createCsv {

    public static void main(String[] args) throws CloneNotSupportedException, FileNotFoundException {


        //creating the csv file
        PrintWriter pw = new PrintWriter(new File ("/Users/williamdroin/Desktop/csvFileForJava/First.csv"));

        StringBuilder sb = new StringBuilder();


        int numberOfRep = 500;

        sb.append("number of people");
        sb.append(",");

        for (int i = 2; i<100; i++){

            sb.append(i);
            sb.append(",");

        }

        sb.append("\r\n");


        //first for loop to reach the max of people we want
        for (int people = 1; people<=250; people++){

            sb.append(people);
            sb.append(",");

            for (int floors = 2; floors<90; floors++){

                float totalToBeAveraged = 0;

                //creation of the list that will store all the values of how many floors were travelled per rep
                //to later average those values.
                //List<Integer> travelledAveraged = new ArrayList<>();

                for (int rep = 0; rep<numberOfRep; rep++){
                    AlgoWithoutNames Algo = new AlgoWithoutNames();

                    //running the algorithm


                    Algo.listCreationForFloors(floors, people);
                    Algo.baseCaseImproved(floors, people);

                    float sizeOfCurrent = Algo.getListsFloors().size();

                    totalToBeAveraged += sizeOfCurrent;






                }

                float finalAveragedValue = totalToBeAveraged / numberOfRep;

                sb.append(finalAveragedValue);
                sb.append(",");


            }

            sb.append("\r\n");


        }

        pw.write(sb.toString());
        pw.close();

        System.out.println(" finished ");
    }




}
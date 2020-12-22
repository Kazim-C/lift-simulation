package Lift;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AlgoWithoutNames {



    List<Object> liftList = new ArrayList<Object>();





    //initialize the list that will contain all the floors
    List<List<Object>> listsFloors = new ArrayList<List<Object>>();
    List<List<Object>> listsFloorsBaseCase = new ArrayList<List<Object>>();


    //initialize the list that will contain all the floors for the people who went out of the elevator
    //this is to check that everybody is at the right floor
    List<List<Object>> listsFloorsOut = new ArrayList<List<Object>>();
    List<List<Object>> listsFloorsOutBaseCase = new ArrayList<List<Object>>();





    //lists to store all of the floors that the lift stopped to
    List<Integer> listsOfStops= new ArrayList<>();
    //lists to store all of the floors that the lift stopped to
    List<Integer> listsOfStopsBC= new ArrayList<>();

    //lists to store all of the floors that the lift went by (this will be used by the UI to represent the lift going
    // through the building)
    List<Integer> listsOfFloorsTravelled= new ArrayList<>();

    //lists to store all of the floors that the lift went by on base case (this will be used by the UI to represent
    // the lift going through the building)
    List<Integer> listsOfFloorsTravelledBaseCase= new ArrayList<>();


    int peopleOut = 0;
    int peopleoutBaseCase = 0;














    public void listCreationForFloors(int numberOfFloors, int numberOfPeople) throws CloneNotSupportedException {

        //initialize random
        Random rand = new Random();


        //this for loop will create a list (which represents one floor), for every iteration
        // and store those lists in listsFloors.
        for (int i = 0; i <= numberOfFloors; i++) {

            //creating a list that will represent one floor
            List<Object> listIndividual = new ArrayList<>();

            //adding that list to listsFloors which will in the end contain all of the floors as a list of lists.
            listsFloors.add(listIndividual);



        }

        //same as the previous one
        //this for loop will create a list (which represents one floor), for every iteration
        // and store those lists in listsFloors.
        for (int i = 0; i <= numberOfFloors; i++) {

            //creating a list that will represent one floor
            List<Object> listIndividual = new ArrayList<>();

            //adding that list to listsFloors which will in the end contain all of the floors as a list of lists.
            listsFloorsBaseCase.add(listIndividual);



        }


        //this for loop will create a list (which represents one floor), for every iteration
        // and store those lists in listsFloorsOut.
        for (int i = 0; i <= numberOfFloors; i++) {

            //creating a list that will represent one floor
            List<Object> listIndividualOut = new ArrayList<>();

            //adding that list to listsFloors which will in the end contain all of the floors as a list of lists.
            listsFloorsOut.add(listIndividualOut);


        }

        //this for loop will create a list (which represents one floor), for every iteration
        // and store those lists in listsFloorsOut.
        for (int i = 0; i <= numberOfFloors; i++) {

            //creating a list that will represent one floor
            List<Object> listIndividualOut = new ArrayList<>();

            //adding that list to listsFloors which will in the end contain all of the floors as a list of lists.
            listsFloorsOutBaseCase.add(listIndividualOut);


        }


        //for loop to create random values of people object
        for (int i = 0; i < numberOfPeople; i++) {

            //creating a variable that will randomly generate a new number in the range 0 to numberOfFloors
            int floorForIndividualPeople = rand.nextInt((numberOfFloors));



            //Create a new object with the randomly generated variables.
            //This is to have a "random" distribution of people across floors.
            peopleObject obj = new peopleObject(floorForIndividualPeople);



            //creating a variable that will randomly generate a new number in the range 0 to numberOfFloors
            //It has to be different to the one previously generated
            // to avoid people being on second floor and going to the second floor to be generated
            int randomDifferentFloor = rand.nextInt((numberOfFloors));


            //Checking that the number generated for the floor that the person wants to go to is not the same
            //if it not the same add it to the list
            if (randomDifferentFloor != floorForIndividualPeople) {
                (listsFloors.get(randomDifferentFloor)).add(obj);
                (listsFloorsBaseCase.get(randomDifferentFloor)).add(obj);

            }


            //if it is not the same don't add to the list but to make sure that we have all th people requested
            //we need to substract 1 to i.
            //this is basically making sure that the iterations where "randomDifferentFloor = floorForIndividualPeople"
            //have no impact on the number of people generated.
            else {
                i = i - 1;
            }
        }



    }















    public void pathFindingAlgorithm(int numberOfFloors, int numberOfPeople){







        int currentClosest = 0;
        int currentLiftFloor = 0;
        int differenceBetweenFloorsClosest = 0;

        //This variable will be updated every time an person gets out of the elevator.
        //It will be used to know if everybody got to the floor they wanted.









        while (peopleOut < numberOfPeople) {
            //for loop to find the closest floor with a call for the current floor of the elevator.

            if (liftList.size() == 0) {
                for (int y = 0; y < listsFloors.size(); y++) {

                    //gettting the number of calls on each floors.
                    int Calls = (listsFloors.get(y).size());

                    //this is the int is equal to the difference between
                    int differenceBetweenFloors = Math.abs(currentLiftFloor - y);


                    // This if statement is called when the first floor with people waiting for the lift is found
                    // It finds the difference between those two floors and puts it into current closest
                    if (Calls != 0 && differenceBetweenFloors != 0 && differenceBetweenFloorsClosest == 0) {

                        differenceBetweenFloorsClosest = differenceBetweenFloors;

                        currentClosest = y;


                    }


                    // this if statement is called when the first floor with people waiting has already been found
                    // it will update if differenceBetweenFloors < differenceBetweenFloorsClosest
                    // which would mean that the current iteration of the for loop has a value of y (floor) closer than the previous one.
                    if (Calls != 0 && differenceBetweenFloors != 0 && differenceBetweenFloors < differenceBetweenFloorsClosest) {

                        currentClosest = y;
                        differenceBetweenFloorsClosest = differenceBetweenFloors;


                    }

                }
                //resetting the the value of the difference between closest floor to 0 reset the research for a new closest.
                differenceBetweenFloorsClosest = 0;
            }




            //this is for finding where people, in the lift, want to go.
            else {

                //those two for loops are used to find the closest call for people inside the lift.
                for (int r = 0; r < liftList.size(); r++) {

                    for (int w = 0; w < liftList.size(); w++) {




                        int x = Math.abs(currentLiftFloor - ((peopleObject) liftList.get(r)).getFloor());
                        int q = Math.abs(currentLiftFloor - ((peopleObject) liftList.get(w)).getFloor());





                        if (q < x) {

                            currentClosest = ((peopleObject) liftList.get(w)).getFloor();
                        }
                        else{
                            currentClosest = ((peopleObject) liftList.get(r)).getFloor();
                        }

                    }





                }


                //the following series of if statements are used to make sure that the lift stops at every floor if needed
                //Meaning that they don't jump from floor 0 to 5 and don't stop at floor 4 even if they could
                //they avoid that by adding 1 (or subtract 1) to current floor to reach the one he wants to go to without
                //"jumping" to it straight and skipping floors.

            }



            int diffCheck = currentClosest - currentLiftFloor;
            int differenceAbs =  Math.abs(currentClosest - currentLiftFloor);

            if( diffCheck == 1 || diffCheck == 0){
                listsOfFloorsTravelled.add(currentLiftFloor);
                currentLiftFloor = currentClosest;
                getPeopleInAndOut(currentLiftFloor,0);




            }



            else if (diffCheck > 1){

                for (int i = 0; i < differenceAbs; i++){
                    listsOfFloorsTravelled.add(currentLiftFloor);
                    currentLiftFloor += 1;
                    getPeopleInAndOut(currentLiftFloor,0);






                }

            }

            else {


                for (int i = 0; i < differenceAbs; i++){

                    listsOfFloorsTravelled.add(currentLiftFloor);
                    currentLiftFloor = currentLiftFloor - 1;
                    getPeopleInAndOut(currentLiftFloor,0);

                    listsOfFloorsTravelled.add(currentLiftFloor);




                }

            }


        }
        listsOfFloorsTravelled.add(currentLiftFloor);




    }





    public void baseCase(int numberOfFloors, int numberOfPeople){


        int addingFloors = 1;
        int baseCaseCurrentFloors = 0;
        int x = 0;






        while (peopleoutBaseCase < numberOfPeople){



            if(baseCaseCurrentFloors == numberOfFloors-1){

                addingFloors = -1;

            }

            if(baseCaseCurrentFloors == 0){

                addingFloors = 1;
                getPeopleInAndOut(baseCaseCurrentFloors,1);

            }


            listsOfFloorsTravelledBaseCase.add(baseCaseCurrentFloors);
            baseCaseCurrentFloors += addingFloors;
            getPeopleInAndOut(baseCaseCurrentFloors,1);



            x++;





        }

        listsOfFloorsTravelledBaseCase.add(baseCaseCurrentFloors);

    }








    public void baseCaseImproved(int numberOfFloors, int numberOfPeople){


        int addingFloors = 1;
        int baseCaseCurrentFloors = 0;


        int SomeoneUpStairs = 0;
        int SomeoneDownStairs = 0;






        while (peopleOut < numberOfPeople){





            //if there are still some people upstairs or if people in the lift want to go above the current lift floor
            //if not the lift goes back down.
            for (int i = baseCaseCurrentFloors; i < numberOfFloors; i++) {

                if (listsFloors.get(i).size() != 0) {

                    SomeoneUpStairs = 1;

                }

            }

            //if there are still some people upstairs or if people in the lift want to go above the current lift floor
            //if not the lift goes back down.
            for (int i = baseCaseCurrentFloors; i == 0; i--) {

                if (listsFloors.get(i).size() != 0) {

                    SomeoneDownStairs = 1;

                }

            }

            int currentBiggest = 0;
            //this is to get the highest floor of the lift
            for (Object o : liftList) {


                int t = ((peopleObject) o).getFloor();


                if (t >= currentBiggest) {

                    currentBiggest = ((peopleObject) o).getFloor();
                }





            }

            //this loops find the smallest required floor of people inside the lift.
            int currentSmallestUpdated = 0;
            int currentSmallest = numberOfFloors + 1;
            for (Object o : liftList) {
                int t = ((peopleObject) o).getFloor();


                if (t <= currentSmallest) {

                    currentSmallest = ((peopleObject) o).getFloor();
                }

                currentSmallestUpdated = 1;


            }






            if (liftList.size() == 10){

                if(currentBiggest < baseCaseCurrentFloors && addingFloors == 1){addingFloors = -1;}
                if(currentSmallest > baseCaseCurrentFloors && addingFloors == -1){addingFloors = 1;}


            }


            else {

                if (currentBiggest < baseCaseCurrentFloors && addingFloors == 1 && SomeoneUpStairs == 0) {
                    addingFloors = -1;
                }

                //this if statement basically checks that if
                if (currentSmallest > baseCaseCurrentFloors && addingFloors == -1 && SomeoneDownStairs == 0 && currentSmallestUpdated != 0) {
                    addingFloors = 1;


                }

            }

            //setting the bounds for the floors that the lift can go too
            if(baseCaseCurrentFloors == listsFloors.size() -1){

                addingFloors = -1;

            }
            //setting the bounds for the floors that the lift can go too
            if(baseCaseCurrentFloors == 0){

                addingFloors = 1;

            }










            getPeopleInAndOut(baseCaseCurrentFloors,0);


            listsOfFloorsTravelled.add(baseCaseCurrentFloors);
            baseCaseCurrentFloors += addingFloors;


            getPeopleInAndOut(baseCaseCurrentFloors,0);






            SomeoneUpStairs = 0;




        }

        listsOfFloorsTravelled.add(baseCaseCurrentFloors);

    }


































    public void getPeopleInAndOut(int currentLiftFloor, int BaseCase){

        //this list is used to store the items to remove from the list
        List<Object> toRemoveFromLiftList = new ArrayList<Object>();



        //to get people off the lift with matching floors to the one currently on.
        if (liftList.size() != 0){


            //this variable will be updated if somebody gets out of the Lift
            int stopped = 0;

            //iterate through the list of objects to find a matching floor
            for (Object o : liftList) {

                int x = ((peopleObject) o).getFloor();

                if ( x == currentLiftFloor){


                    toRemoveFromLiftList.add(o);




                    if (BaseCase == 1){
                        peopleoutBaseCase += 1;
                        listsFloorsOutBaseCase.get(currentLiftFloor).add(o);


                    }

                    else if (BaseCase == 0) {
                        peopleOut += 1;
                        listsFloorsOut.get(currentLiftFloor).add(o);
                    }






                    stopped = 1;


                }


            }

            //if the variable as been updated it means that somebody went off, so it also means that the lift stopped
            //We had the floor at which it stopped to the lift of stops for the UI
            if (stopped == 1){listsOfStops.add(currentLiftFloor);}

            //removing all the elements the matching elements between the list
            // i Used this method to avoid the "ConcurrentModificationException” i was getting
            liftList.removeAll(toRemoveFromLiftList);






        }

        //to get people on the lift if there is any body on the floor.
        if (listsFloors.size() != 0 && BaseCase == 0) {





            int stopped = 0;

            int currentFloorSize = listsFloors.get(currentLiftFloor).size();
            //this for loop is responsible to get the people on the floor (that the lift is on), inside the lift.
            for (int i = 0; i < (currentFloorSize); i++) {




                if (liftList.size() >= 10){
                    break;


                }




                //getting the first person from the floor list (for every iteration)
                //We get the first one because in the scope we will delete it after adding it to the lift
                // the second person will then become the first that is why we get the first one.
                liftList.add(listsFloors.get(currentLiftFloor).get(0));


                listsFloors.get(currentLiftFloor).remove(0);
                stopped = 1;









            }

            if (stopped == 1){listsOfStops.add(currentLiftFloor);}

        }



        else if (listsFloorsBaseCase.size() != 0 && BaseCase == 1){

            int stoppedBC = 0;
            int currentFloorSizeBC = listsFloorsBaseCase.get(currentLiftFloor).size();

            for (int i = 0; i < (currentFloorSizeBC); i++) {

                if (liftList.size() >= 10){
                    break;


                }


                //getting the first person from the floor list (for every iteration)
                //We get the first one because in the scope we will delete it after adding it to the lift
                // the second person will then become the first that is why we get the first one.
                liftList.add(listsFloorsBaseCase.get(currentLiftFloor).get(0));


                listsFloorsBaseCase.get(currentLiftFloor).remove(0);
                stoppedBC = 1;



            }

        }




    }

    public void clearList(){

        listsOfFloorsTravelledBaseCase = null;


    }

    public List<Integer> getListsFloorsBaseCase() {


        return listsOfFloorsTravelledBaseCase;
    }

    public List<Integer> getListsFloors() {


        return listsOfFloorsTravelled;


    }









}

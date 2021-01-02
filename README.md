# lift-simulation
A java app that compares two different kind of lift algorithm

## Purpose

The goal for this project was to improve on a mechanical design of a lift. The mechanical lift only goes one direction (up or down) 
Once it reaches either end it changes direction.

### Problem

This seemed very very impractical because if you are on the second floor, you want to go the ground floor and the lift is at ground level:

    1. The lift needs to go up 
    2. Stops at 2nd floor 
    3. Since it was going up it needs to go to the very last floor of the building to go back down
    4. Reaches last floor
    5. Changes direction and goes back down to ground level
    
This was the way lift used to work. It can really be a problem if the building has a lot of floors.

### Solution

Improving on this design seemed extremely easy, but the mechanical lift is actually hard or impossible to improve on some scenarios:
    
    1. You are at ground floor, the lift is at ground floor and you want to go to the second floor.
    2. You are on the second floor, the lift is on third floor and you want to go to the gorund floor.

One solution was to write an algorithm that cuts the unecessary travel to the top and bottom to change direction.

Improved Algorithms:
    
    IF lift not full AND nobody called lift on higher/lower (depending on direction up or down) floor
    
        IF people in lift want to go in the same direction the lift is going (up or down)
            don't change direction
            
        IF people in lift DON'T want to go in the same direction the lift is going (up or down)
            change direction
    
    IF lift full AND nobody wants to go to a higher/lower (depending on direction up or down) floor 
    than the current floor:
        go in the opposite direction
        
## Implementation

This Algorithm is implemented in Java with a UI to interact and test the algorithm.

    To launch the App double click on the Frame.class 
    OR 
    Compile and run Frame.java

This window should appear, it lets you choose the number of people and the number of floors.
Improved case is on the left and mechanical (or base case) is on the right.


![liftInitial](https://user-images.githubusercontent.com/72973649/102917073-d7b0eb80-4484-11eb-9518-7ee16e2651b3.png)

After clicking on "launch simulation" there is a live count of the number of floors travelled and a live indicator of the current floor.

The simulation can be ran and re-ran as much as you want with different values or the same values.
It generates a new pseudo random building every time (random dispatching of people amongst the floor):

![sideBySideLift](https://user-images.githubusercontent.com/72973649/102917062-d41d6480-4484-11eb-9e98-ecee40d2583d.png)

## Data analysis

There is a java class that runs the algorithm over and over with different values and generates a CSV file (Excel file).
It can be used to analyse the performance of the algorithms compared to each others.

This java class runs the program 150 times per scenario and averages the results, example: take 2 people and 5 floor, 
generate a pseudo random building based on those predicates, repeat this 150 times to get 150 different pseudo random 
representation of the same predicates. Average the result and write them to a csv file. Take 3 people and 5 floor etc...
Repeat this to for every value between 1-249 people and 2-89 floors to get a good represantation of the algorithm' performance.
(This could be done in parrallel but I did not know how to parallelize an algorithm at the time so depending on the hardware, 
it can take a long time).

The table has the following form:

| people/floors  | 2 | 3 | [...] | 88 | 89 |
| ---- | ---- | ---- | ---- | ---- |
| 1  | 1.3 | 1.6 | ∅ | 65 | 78 |
| 2  | 1.7  | 1.9 | ∅ | 101 | 107 |
| [...]  | ∅  | ∅ | ∅ | ∅ | ∅ |
| 248  | 27.32  | 54.01 | ∅ | 4747 | 4936 |
| 249 | 27.74  | 54.2 | ∅ | 4844 | 4940 |


# lift-simulation
A java app that compares two different kind of lift algorithm

## Purpose

The goal for this project was to improve on a mechanical design of a lift. The mechanical lift only goes one direction (up or down) 
Once it reaches either end it changes direction.

### Problem

This seemed very very impractical because if you are on the second floor, you want to go the ground floor and the lift is at ground level:
    The lift needs to go up 
    Stops at 2nd floor 
    Since it was going up it needs to go to the very last floor of the building to go back down
    Reaches last floor
    Changes direction and goes back down to ground level
    
This was the way lift used to work. It can really be a problem if the building has a lot of floors.

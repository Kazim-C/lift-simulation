package Lift;

public class peopleObject {

    public int floor;
    public String id;



    public peopleObject(int floor, String id){

        this.floor = floor;
        this.id = id;

    }

    public peopleObject(int floor){

        this.floor = floor;
        this.id = id;

    }




    //get method
    public int getFloor(){return this.floor;}
    public String getId(){return this.id;}



    //set method
    public void setFloor(int floor){
        this.floor = floor;
    }
    public void setId(String id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "peopleObject{" +
                "floor=" + floor +
                ", id='" + id + '\'' +
                '}';
    }
}

import java.util.ArrayList;
/** Wayne Rudnick + Ian Kahn
 * This Code is for our CS 351 project 4 - Disease Simulation
 * World Class Description:
 * This class looks at the board // world // containment
 * It returns a list of neighbors that are nearby each person
 *
 *    */
public class World {
    /** Wayne Rudnick + Ian Kahn
     *  returnNeighbors(int x, int y) method Description:
     *  parameters : int x, int y
     *  returns ArrayList<Person>
     * This method looks at the board // world // containment
     * It returns an arraylist of type People that are nearby each person
     **/
    public static ArrayList<Person> returnNeighbors(int x, int y) {

        ArrayList<Person> neighbors = new ArrayList<>();

        for (Person p : Main.people) {

            if (p.getXCoordinate() <= x + Main.exposureDistance && p.getXCoordinate() >= x - Main.exposureDistance &&
                    p.getYCoordinate() <= y + Main.exposureDistance &&
                    p.getYCoordinate() >= y - Main.exposureDistance) {

                neighbors.add(p);
            }
        }

        return neighbors;

    }
}



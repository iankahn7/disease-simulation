import java.util.ArrayList;
import java.util.Random;
/** Wayne Rudnick + Ian Kahn
 * This Code is for our CS 351 project 4 - Disease Simulation
 * Person Class Description:
 *  This class is the main parent class of our Person type which extends thread.
 *  This class utilizes the other classes; Main and word. This class has several
 *  parameters which dictate the positioning of the people
 *  and behavior of disease spreading.
 *  This class also contains a lot of helper methods to return various values from the object as well
 *  when called.
 *
 *    */
public class Person extends Thread{
    private boolean isVulnerable;
    private boolean isImmune;
    private boolean isDead;
    private int sicknessLevel;
    private int x;
    private int y;
    private double movementFactor = Main.moveDistance;
    /**  public Person(boolean isVulnerable, boolean isImmune, boolean isDead,int sicknessLevel,
     int positionX, int positionY)
     *
     * this is the person object.
     * paramaters: boolean isVulnerable, boolean isImmune, boolean isDead,int sicknessLevel,
     *      int positionX, int positionY
     *    */
    public Person(boolean isVulnerable, boolean isImmune, boolean isDead,int sicknessLevel,
                  int positionX, int positionY) {
        this.isVulnerable = isVulnerable;
        this.isImmune = isImmune;
        this.isDead = isDead;
        this.x = positionX;
        this.y = positionY;
        this.sicknessLevel = sicknessLevel;
    }

    public boolean returnIsVulnerable(){
        return isVulnerable;
    }
    public boolean returnIsImmune(){
        return isImmune;
    }
    public boolean returnIsDead(){
        return isDead;
    }
    public int getSicknessLevel() {return sicknessLevel;}
    public int getXCoordinate(){
        return x;
    }
    public int getYCoordinate(){
        return y;
    }
    /** sets new X coordinate, takes int parameter */
    public int setXCoordinate(int newX){
        return this.x=newX;
    }
    /** sets new Y coordinate, takes int parameter */
    public int setYCoordinate(int newY){
        return this.y=newY;
    }
    /** sets new sicknessLevel value, takes int parameter */
    public void setSicknessLevel(int newSicknessLevel){
        this.sicknessLevel = newSicknessLevel;
    }
    /** sets person to dead state value, takes boolean parameter if it is dead or alive */
    public void setDead(boolean isDead){
        this.isDead = isDead;
    }
    public void setImmune(boolean isImmune){
        this.isImmune = isImmune;
    }
    /** run() method description:
     * paramaters : none
     * returns: void
     * updates and implements the multithreading aspects of the individual dots / people*/
    public void run() {
        while (true) {
            movementFactor = Main.moveDistance;

            try {
                Thread.sleep(Main.moveDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Random random = new Random();


            if (isDead) {

            } else {
                int direction = random.nextInt(4);
                if (random.nextInt(12) > sicknessLevel) {
                    if (direction == 0 && x > 0) {
                        setXCoordinate((int) (x - movementFactor));
                    } else if (direction == 1 && x < Main.dimensionWidth) {
                        setXCoordinate((int) (x + movementFactor));
                    } else if (direction == 2 && y > 0) {
                        setYCoordinate((int) (y - movementFactor));
                    } else if (direction == 3 && y < Main.dimensionHeight) {
                        setYCoordinate((int) (y + movementFactor));
                    }
                }
            }

            //Probability of somebody recovering from the disease
            if(isImmune){
                if (random.nextInt(10001) > 9998){
                    isImmune = false;
                }
            }

            //Chance of the person becoming more sick
            if (sicknessLevel > 0) {
                if (random.nextInt(101) > 99) {
                    if (sicknessLevel < 10) {
                        sicknessLevel++;
                    }
                }

                //Chance of recovering from the disease or dying as a result
                float recoverOrDie = random.nextFloat(0, 1);
                if (random.nextInt(1001) > 999) {
                    if (recoverOrDie > Main.recoveryProbability) {
                        isDead = true;
                    } else if (recoverOrDie < Main.recoveryProbability) {
                        sicknessLevel = 0;
                        isImmune = true;
                    }
                }
            }


            //Calls the world to see who the neighbors are
            //Then might infect the person based off probability, the sicker the person, the higher the chance of spread
            ArrayList<Person> neighbors = World.returnNeighbors(x, y);

            for (Person p : neighbors) {

                if (p != this) {
                    if (p.sicknessLevel > 0 && this.sicknessLevel < 1) {

                        if(random.nextInt(11) > 8 && !isImmune) {
                            sicknessLevel = 1;
                        }
                    }
                }
            }
        }
    }
}






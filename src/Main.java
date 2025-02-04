import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import static javafx.application.Platform.exit;

/** Wayne Rudnick + Ian Kahn
 * This Code is for our CS 351 project 4 - Disease Simulation
 * Main Class Description:
 *  This class is the main parent branch/ controller of our GUI Program version.
 *  This class utilizes the other classes; person and word, and various objects to
 *   read in a text file input which configures the layout / behavior of the simulation
 *    Once you have the associated Text File with desired parameters and specifications as input,
 *    you can simply run the program. The program also takes keyboard inputs to change the positioning
 *    of the people on the board as well as the color of the window.
 *
 *    */
public class Main extends Application {
    //public static final int SCREEN_SIZE = 1000;
    public static int CIRCLE_SIZE = 7;
    public static Pane pane = new Pane();
    public static ArrayList<Person> people = new ArrayList();
    public static Random random = new Random();
    /**
     * This section contains a lot of the various values and parameters of the program. Most are
     * already pre-initialised to values specified in the program description as asked. The respective values
     * are then later replaced if a different value is found in the text file.
     *    */
    public static String initialConfiguration = "random";
    public static int dimensionWidth = 200;
    public static int dimensionHeight = 200;
    public static int exposureDistance = 20;
    public static int incubationPeriod = 5;
    public static int sicknessTime = 10;
    public static double recoveryProbability = 0.95;
    //public static int randomAgentCount;
    public static int randomGridRows;
    public static int randomGridColumns;
    public static int randomGridAgentsN = 100;
    public static int gridRows;
    public static int initialSick = 1;
    public static int gridColumns;
    public static int moveDistance = 0;
    public static int moveDelay = 1000;
    public static int initialImmune = 0;

    /** Here is the main method  of the program.
     * It first looks through the text file for the respective key-words of the various parameters.
     * Then, when it finds one, it changes the default value to the new value associated with the
     * keyword in the text file.
     *
     *    */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("testingFiles/basicTest.txt")));
        /*

        System.out.println("Please enter the file path of your input configuration file: ");
        Scanner scnr = new Scanner(System.in);
        String configFile = scnr.nextLine();
        System.out.println("You entered: "+configFile);
        System.out.println("Running Program with: "+configFile);
        Scanner sc = new Scanner(new BufferedReader(new FileReader(configFile)));

         */



        while(sc.hasNextLine()) {
            String[] stringInput;
            stringInput = sc.nextLine().split(" ");
            switch(stringInput[0]) {
                case "dimensions":
                    dimensionWidth = Integer.parseInt(stringInput[1]);
                    dimensionHeight = Integer.parseInt(stringInput[2]);
                    System.out.println("TESTER: ");
                    System.out.println(dimensionWidth);
                    System.out.println(dimensionHeight);
                    break;
                case "exposuredistance":
                    exposureDistance = Integer.parseInt(stringInput[1]);
                    break;
                case "incubation":
                    incubationPeriod = Integer.parseInt(stringInput[1]);
                    break;
                case "sickness":
                    sicknessTime = Integer.parseInt(stringInput[1]);
                    break;
                case "recover":
                    recoveryProbability = Float.parseFloat(stringInput[1]);
                    break;
                case "randomgrid":
                    initialConfiguration = "randomgrid";
                    randomGridRows = Integer.parseInt(stringInput[1]);
                    randomGridColumns = Integer.parseInt(stringInput[2]);
                    randomGridAgentsN = Integer.parseInt(stringInput[3]);
                    break;
                case "random":
                    initialConfiguration = "random";
                    randomGridAgentsN = Integer.parseInt(stringInput[1]);
                    break;
                case "grid":
                    initialConfiguration = "grid";
                    gridRows = Integer.parseInt(stringInput[1]);
                    gridColumns = Integer.parseInt(stringInput[2]);
                    break;
                case "initialsick":
                    initialSick = Integer.parseInt(stringInput[1]);
                    break;
                case "move":
                    moveDistance = Integer.parseInt(stringInput[1]);
                    moveDelay = Integer.parseInt(stringInput[2]);
                    break;
                case "initialimmune":
                    initialImmune = Integer.parseInt(stringInput[1]);
                    break;
                default:

            }
        }


        int sickPlaced = 0;
        int immunePlaced = 0;
        if(initialConfiguration.equals("random")) {

            for (int i = 0; i < randomGridAgentsN; i++) {
                Person p = new Person(false, false, false, 0,
                        random.nextInt(dimensionWidth), random.nextInt(dimensionHeight));
                people.add(p);
            }
            for (int x = 0; x < initialSick; x++) {
                Person p = new Person(false, false, false, 1,
                        random.nextInt(dimensionWidth), random.nextInt(dimensionHeight));
                people.add(p);
            }
            for (int x = 0; x < initialImmune; x++) {
                Person p = new Person(false, true, false, 0,
                        random.nextInt(dimensionWidth), random.nextInt(dimensionHeight));
                people.add(p);
            }
        }
        else if(initialConfiguration.equals("grid")) {
            for (int x = 0; x < gridColumns; x++) {
                for (int y = 0; y < gridRows; y++) {
                    if (immunePlaced < initialImmune) {
                        Person p = new Person(false, true, false, 0,
                                (dimensionWidth / gridColumns) * y,
                                (dimensionHeight / gridColumns) * x);
                        people.add(p);
                        immunePlaced++;
                    }
                    else if (sickPlaced < initialSick) {
                        Person p = new Person(false, false, false, 1,
                                (dimensionWidth / gridColumns) * y,
                                (dimensionHeight / gridColumns) * x);
                        people.add(p);
                        sickPlaced++;
                    }
                    else {
                        Person p = new Person(false, false, false, 0,
                                (dimensionWidth / gridColumns) * y,
                                (dimensionHeight / gridColumns) * x);
                        people.add(p);
                    }
                }
            }
        }else if(initialConfiguration.equals("randomgrid")) {
            for (int z = 0; z < initialSick; z++) {
                Person p = new Person(false, false, false, 1,
                        0,
                        0);
                people.add(p);
            }
            for (int x = 0; x < initialImmune; x++) {
                Person p = new Person(false, true, false, 0,
                        random.nextInt(dimensionWidth), random.nextInt(dimensionHeight));
                people.add(p);
            }
            for (int z = 0; z < randomGridAgentsN - initialSick - initialImmune; z++) {
                Person p = new Person(false, false, false, 0,
                        0,
                        0);
                people.add(p);
            }

            int index = 0;

            for (Person p: people){
                int scalarX = random.nextInt(randomGridColumns);
                int scalarY = random.nextInt(randomGridColumns);
                p.setXCoordinate((dimensionWidth/randomGridColumns) * scalarX);
                p.setYCoordinate((dimensionHeight/randomGridRows) * scalarY);

            }


        }


        for(Person startPeople: people) {
            startPeople.start();

        }
        Application.launch(args);
    }

    /** Here is the start method  of the program.
     * This method initialises the GUI window and tells all the other corresponding GUI elements
     * to activate.
     *
     *    */
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent(), dimensionWidth, dimensionHeight);
        primaryStage.setTitle("Ian and Wayne's Disease Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();



        /**
         * The code below allows the user to press keys and have it change the world in some way
         * There are features from changing the background to randomizing the placement of people
         */
        scene.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            char keyPressed = keyEvent.getCharacter().charAt(0);
            if(keyPressed == 'r') { //randomize positions
                pane.getChildren().clear();
                for (Person p : people) {
                    p.setXCoordinate(random.nextInt(dimensionWidth));
                    p.setYCoordinate(random.nextInt(dimensionHeight));

                }
            }
            else if(keyPressed == 'c'){ //change background color
                scene.setFill(Color.color(random.nextDouble(0, 1),
                        random.nextDouble(0, 1), random.nextDouble(0, 1)));
            }
            else if(keyPressed == 'k'){ //eliminate a random person
                Person tempRandomPerson = people.get(random.nextInt(people.size()));
                tempRandomPerson.setDead(true);
            }
            else if(keyPressed == 's'){ //speed up the people
                moveDistance++;
            }
            else if(keyPressed == 'w'){ //slow down the people
                if(moveDistance > 0) {
                    moveDistance--;
                }
            }
            else if(keyPressed == 'z'){ //cure all people and remove immunity
                for(Person p: people){
                    p.setSicknessLevel(0);
                    p.setDead(false);
                    p.setImmune(false);
                }
            }

            else if(keyPressed == 'i'){ //infect a random person
                Person tempRandomPerson = people.get(random.nextInt(people.size()));
                tempRandomPerson.setSicknessLevel(1);
            }

            else if(keyPressed == 'b'){ //speed up the people
                CIRCLE_SIZE++;
            }
            else if(keyPressed == 'n'){ //speed up the people
                if(CIRCLE_SIZE > 0) {
                    CIRCLE_SIZE--;
                }
            }
            else if(keyPressed == 'm'){ //speed up the people
                Media media = new Media("Want A Break From The Ads Sound Effect.mp3");
                MediaPlayer player = new MediaPlayer(media);
                player.play();
            }

            else if(keyPressed == ' '){ //Merge the people into a conglomerate
                int x = 1;
                int size = people.size();
                for(Person p: people){
                    p.setXCoordinate(dimensionWidth/2);
                    p.setYCoordinate((dimensionHeight/size) * x);
                    x++;
                }
            }

            else if(keyPressed == 'u'){ //Displays how many people are alive
                int alive = 0;
                for(Person p: people){
                    if(!p.returnIsDead()){
                        alive++;
                    }
                }
                primaryStage.setTitle("NUMBER OF PEOPLE ALIVE: " + alive);
            }

            else if(keyPressed == 'o'){ //Display an image

                InputStream stream = null;
                try {
                    stream = new FileInputStream("download.jpg");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image = new Image(stream);
                ImageView imageView = new ImageView();
                //Setting image to the image view
                imageView.setImage(image);
                //Setting the image view parameters
                imageView.setX(10);
                imageView.setY(10);
                imageView.setFitWidth(575);
                imageView.setPreserveRatio(true);
                Group root = new Group(imageView);
                Scene scene1 = new Scene(root, dimensionWidth, dimensionHeight);

                for(int y = 0; y < 10; y++) {

                    Stage secondaryStage = new Stage();
                    secondaryStage.setTitle("Want a break from the ads?");
                    secondaryStage.setX(random.nextInt(dimensionWidth));
                    secondaryStage.setY(random.nextInt(dimensionHeight));
                    secondaryStage.setScene(scene1);
                    secondaryStage.show();
                }


            }

            else if(keyPressed == 'e'){ //exit the program
                System.exit(0);
            }




        });
        new AnimationTimer() {
            @Override
            /** handle(long now) method description:
             * paramaters: (long now)
             * returns: void
             * handle updates the gui / visual elements*/
            public void handle(long now) {

                pane.getChildren().clear();
                for (Person p : people) {
                    ColorAdjust colorAdjust = new ColorAdjust();
                    colorAdjust.setHue(p.getSicknessLevel() * -0.05);
                    Circle circle = new Circle();
                    circle.setCenterX(p.getXCoordinate());
                    circle.setCenterY(p.getYCoordinate());
                    if (p.returnIsDead()) {
                        circle.setFill(Color.BLACK);
                    }
                    else if(p.returnIsImmune()){
                        circle.setFill(Color.YELLOW);
                    }
                    else if(p.getSicknessLevel() > 0){
                        circle.setFill(Color.RED);
                        circle.setEffect(colorAdjust);

                    }else {
                        circle.setFill(Color.GREEN);
                    }
                    circle.setRadius(CIRCLE_SIZE);
                    pane.getChildren().add(circle);
                }

            }

        }.start();

    }

    private Parent createContent() {
        for(Person p: people){
            Circle circle = new Circle();
            circle.setCenterX(p.getXCoordinate());
            circle.setCenterY(p.getYCoordinate());
            if(p.returnIsDead()){
                circle.setFill(Color.BLACK);
            }
            else if(p.getSicknessLevel() > 0){
                circle.setFill(Color.RED);
            }
            else {
                circle.setFill(Color.GREEN);
            }
            circle.setRadius(CIRCLE_SIZE);
            pane.getChildren().add(circle);

        }
        return pane;
    }



    }



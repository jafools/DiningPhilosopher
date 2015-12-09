package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller extends AnimationTimer implements Initializable{
    private static final int NumberOfPhilosophers = 5;  // How many to test with.
    ExecutorService executorService = null;

    Philosopher[] philosophers = null;
    public Boolean isFull = true;
    public Boolean restart = false;

    public StringBuilder build = new StringBuilder();

    Circle[] circles = new Circle[NumberOfPhilosophers];

    Rectangle[] rectangle = new Rectangle[NumberOfPhilosophers];

    ChopStick[] chopSticks = new ChopStick[NumberOfPhilosophers];

    final ObservableList<String> listItems = FXCollections.observableArrayList("AVERAGE VALUES");














    @FXML
    private Circle Philosopher3;

    @FXML
    private Circle Philosopher4;

    @FXML
    private Button StopButton;

    @FXML
    private Circle Philosopher2;

    @FXML
    private Circle Philpsopher1;

    @FXML
    private Button StartButton;

    @FXML
    private Label ConsoleOutputLabel;

    @FXML
    private Rectangle ChopStick3;

    @FXML
    private TextField ConsoleOutput;

    @FXML
    private Rectangle ChopStick4;

    @FXML
    private Rectangle ChopStick5;

    @FXML
    private Rectangle Chopstick2;

    @FXML
    private Label Number1;

    @FXML
    private Label Number2;

    @FXML
    private Label Number3;

    @FXML
    private Label Number4;
    @FXML
    private Label Number5;

    @FXML
    private Circle ExampleCirlce1;

    @FXML
    private Label HungryState;

    @FXML
    private Label EatingState;

    @FXML
    private Label ThinkingState;

    @FXML
    private Circle ExampleCirlce11;

    @FXML
    private Circle ExampleCirlce12;

    @FXML
    private Circle TableCirlce;

    @FXML
    private ListView<String> AverageOuput;


    @FXML
    private Circle Philosopher5;

    @FXML
    private Button RestartButton;

    @FXML
    private Rectangle ChopStick1;


   // AnimationTimer

    @FXML
    void Start(ActionEvent event) throws InterruptedException, FileNotFoundException {



        if(isFull) {
                    philosophers = new Philosopher[NumberOfPhilosophers];
            Platform.runLater(new Runnable() {
                    @Override
                    public void run() {


                        for (int j = 0; j < NumberOfPhilosophers; j++){
                            circles[0] = Philpsopher1;
                            circles[1] = Philosopher2;
                            circles[2] = Philosopher3;
                            circles[3] = Philosopher4;
                            circles[4] = Philosopher5;

                        }

                        for (int k = 0; k < NumberOfPhilosophers; k++){
                            rectangle[0] = ChopStick1;
                            rectangle[1] = Chopstick2;
                            rectangle[2] = ChopStick3;
                            rectangle[3] = ChopStick4;
                            rectangle[4] = ChopStick5;

                        }

                        for (int i = 0; i < NumberOfPhilosophers; i++) {    // Need a different chopstick for each Philosopher

                            chopSticks[i] = new ChopStick(i, rectangle[i]);
                        }

                        executorService = Executors.newFixedThreadPool(NumberOfPhilosophers);



                        for (int i = 0; i < NumberOfPhilosophers; i++) {
                            philosophers[i] = new Philosopher(i, chopSticks[i], chopSticks[(i + 1) % NumberOfPhilosophers], circles[i]);



                            executorService.execute(philosophers[i]);




                        }



                    }
            });









        } else {

            executorService.shutdown(); //stop thread

            // Wait for all thread to finish
            while (!executorService.isTerminated()) {
                Thread.sleep(1000);

            }

            //Average thinking time
            for (Philosopher philosopher : philosophers) {
                listItems.add(philosopher + " => Average Time units spent thinking = "
                        + philosopher.TimeSpentThinking());
                System.out.println(philosopher + " => Average Time units spent thinking = "
                        + philosopher.TimeSpentThinking());
            }

            //Average Eating time
            for (Philosopher philosopher : philosophers) {
                listItems.add(philosopher + " => Average Time units spent eating = "
                        + philosopher.TimeSpentEating());
                System.out.println(philosopher + " => Average Time units spent eating = "
                        + philosopher.TimeSpentEating());
            }

            //Average Eating time
            for (Philosopher philosopher : philosophers) {
                listItems.add(philosopher + " => Average Time units spent being Hungry = "
                        + philosopher.TimeSpentHungry());
                System.out.println(philosopher + " => Average Time units spent being Hungry = "
                        + philosopher.TimeSpentHungry());

            }

            // How many times each philosopher had eaten
            for (Philosopher philosopher : philosophers) {
                listItems.add(philosopher + " => Number of times The Philosopher ate = "
                        + philosopher.TimesEaten());
                System.out.println(philosopher + " => Number of times The Philosopher ate = "
                        + philosopher.TimesEaten());
            }
        }

    }


        public void ChangeColor(Philosopher philosopher) {
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    if(philosopher.isEatingYellow())

                    {
                        philosopher.setFill(Color.YELLOW);
                    }

                    else if(philosopher.isHungryGreen())

                    {
                        philosopher.setFill(Color.GREEN);
                    }

                    else if(philosopher.isThinkingBlue())

                    {
                        philosopher.setFill(Color.BLUE);
                    }
                      }



            }
            );
        }

            public void ChangeChopStickColor(Philosopher philosopher, ChopStick chopstick){





    }






    @FXML
    void Stop(ActionEvent event) throws InterruptedException {
        restart = true;
        isFull = false;
        for (Philosopher philosopher : philosophers) {
            philosopher.isFull = true;
        }

        StartButton.fire();



        for (Philosopher philosopher : philosophers) {
            philosopher.isFull = false;
        }
        System.out.println("***********************************************************************");

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        AverageOuput.setItems(listItems);
    }

    @Override
    public void handle(long now) {


    }
}

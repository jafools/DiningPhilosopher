package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


public class Main extends Application {


    public static Controller controller;
    public static Scene scene;




    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("sample.fxml").openStream());
        controller = fxmlLoader.getController();
        primaryStage.setTitle("Dining Philosopher");
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        PrintStream stream = new PrintStream(new FileOutputStream("C:\\Users\\ajace_000\\Documents\\Linneaus Uni\\DiningPhilosophers.txt")); // THIS SEND THE CONSOLE OUTPUT TO AN EXTERNAL TEXT FILE
        System.setOut(stream);
        launch(args);







    }
    }


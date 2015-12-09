package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
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
        File file = new File("TraceFile.txt");
        PrintStream printStream = new PrintStream(new FileOutputStream(file));
        System.setOut(printStream); // THIS SEND THE CONSOLE OUTPUT TO AN EXTERNAL TEXT FILE

        launch(args);







    }
    }


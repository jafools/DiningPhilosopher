package sample;

/**
 * Created by ajace_000 on 11/30/2015.
 */

import javafx.scene.shape.Rectangle;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick extends Rectangle {
    public final Rectangle rectangle;
    // Only one philosopher can have one chopstick
    Lock on = new ReentrantLock();
    // Who I am.
    private final int id;



    public ChopStick(int id, Rectangle rectangle) {
        this.rectangle = rectangle;
        this.id = id;
    }





    public boolean pickUpChopStick(Philosopher number, String whichChopStick) throws InterruptedException {
        if (on.tryLock(10, TimeUnit.MILLISECONDS)) {


            System.out.println(number + " picked up " + whichChopStick + " " + this);
            return true;
        }
        return false;
    }

    public void putDownChopStick(Philosopher who, String name) {
        on.unlock();

        System.out.println(who + " put down " + name + " " + this);
    }

    @Override
    public String toString() {
        return "Chopstick Number " + id;
    }
}


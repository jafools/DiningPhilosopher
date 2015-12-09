package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

/**
 * Created by ajace_000 on 11/30/2015.
 */



    public class Philosopher extends Circle implements Runnable{

        private final int id;
        private final Circle circle;




        private final ChopStick leftChopStick; // Two seperate chopsticks for each user
        private final ChopStick rightChopStick;


        public boolean isFull = false; //to create a random thinking and eating time

        public boolean NeedsToEat = true;

        private Random random = new Random();

        private int TimesEaten = 0; // Number of times the philosopher will eat
        private int TimeSpentEating = 0;
        private int TimeThinking = 0; //How long the Philosopher spent Thinking
        private int TimeSpentThinking = 0; //How many times the philosopher was thinking
        private int bignum = 100;
        private int threadNum = 500;

        private int TimesHungry = 0;
        private long TimeSpentHungry = 0;
        private boolean ThinkingBlue = false;
        private boolean HungryGreen = false;
        private boolean EatingYellow = false;


        public Philosopher(int id, ChopStick leftChopStick, ChopStick rightChopStick, Circle circle) {
            this.id = id;
            this.leftChopStick = leftChopStick;
            this.rightChopStick = rightChopStick;
            this.circle = circle;
        }




    public void run() {

            try {
                while (!isFull) {

                    // Think for a bit.
                    think();

                    NeedsToEat = true;


                    long start = System.nanoTime(); //Start the timer for being hungry


                   // while (NeedsToEat = true) {


                        this.circle.setFill(Color.GREEN);
                        Thread.sleep(threadNum);

                        if (leftChopStick.pickUpChopStick(this, "left")) {
                            leftChopStick.rectangle.setFill(Color.RED);

                            if (rightChopStick.pickUpChopStick(this, "right")) {
                                rightChopStick.rectangle.setFill(Color.RED);
                                TimeSpentHungry = (System.nanoTime() - start) + TimeSpentHungry;


                                TimesHungry++;
                                eat();

                                // Finished.
                                rightChopStick.putDownChopStick(this, "right");
                                rightChopStick.rectangle.setFill(Color.BLACK);
                            }
                            // Finished.
                            leftChopStick.putDownChopStick(this, "left");
                            leftChopStick.rectangle.setFill(Color.BLACK);
                        }



                }



            } catch (Exception e) {
                // Catch the exception outside the loop.
                e.printStackTrace();
            }
        }




    private void think() throws InterruptedException {


            this.circle.setFill(Color.BLUE);

            System.out.println(this + " is thinking");
            int randomTime = random.nextInt((30) + 1) * bignum;
            Thread.sleep(randomTime);
            TimeSpentThinking = randomTime + TimeThinking;
            TimeThinking++;

        }

        private void eat() throws InterruptedException {

            this.circle.setFill(Color.YELLOW);


            System.out.println(this + " is eating");
            TimesEaten++;
            int randomTime = random.nextInt((30) +1) *bignum;
            Thread.sleep(randomTime);
            TimeSpentEating = randomTime + TimeSpentEating;
        }

        // Getters and setters.
        public int TimesEaten() {
            return TimesEaten;
        }

        public int TimeSpentThinking(){
            return TimeSpentThinking/TimeThinking;
        }

        public int TimeSpentEating(){return TimeSpentEating/TimesEaten;
        }

        public long TimeSpentHungry(){
        return ((TimeSpentHungry/1000000)*bignum)/(TimesHungry*threadNum);
        }

        public boolean isThinkingBlue(){
            return ThinkingBlue;
        }

    public boolean isEatingYellow() {
        return EatingYellow;
    }

    public boolean isHungryGreen() {
        return HungryGreen;
    }



    @Override
        public String toString() {
            return "Philosopher Number: " + id;
        }
    }


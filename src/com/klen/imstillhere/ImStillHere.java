package com.klen.imstillhere;

import com.klen.robot.RobotUtils;
import java.awt.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class ImStillHere {

    private static int MIN_DELAY_TIME = 5000;
    private static int MAX_RESTART_ATTEMPTS = 3;

    private boolean running = false;

    private Robot robot;
    private Random random;
    private Scanner scanner;

    //configuration;
    private int width;
    private int height;
    private int delayTime = 60000;
    private boolean moveMouse = false;
    private boolean moveCharacter = false;
    private int restartAttempts = 0;

    public ImStillHere(String[] args){
        initialize();
    }

    private void initialize() {
        System.out.println("Initializing...");

        try{
            robot = RobotUtils.getRobot();
            random = new Random();
            scanner = new Scanner(System.in);
            configureResolution();
            configureDelayTime();
            configureMoveMouse();
            configureMoveCharacter();
            printConfiguration();
        } catch (Exception e){
            System.out.println("Caught exception during initialization:");
            restart();
        }

    }

    private void configureResolution() {
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) Math.round(resolution.getWidth());
        height = (int) Math.round(resolution.getHeight());
    }

    private void configureDelayTime(){
        System.out.println("Set Delay Time(ms):");
        int input = scanner.nextInt();
        if(input >= MIN_DELAY_TIME){
            delayTime = input;
        } else {
            System.out.println("Delay time must be greater than " + MIN_DELAY_TIME + "ms, defaulted to " + delayTime + "ms");
        }
        scanner.nextLine();
    }

    private void configureMoveMouse(){
        System.out.println("Move mouse (Y/N):");
        moveMouse = getBooleanFromInput();
    }

    private void configureMoveCharacter(){
        System.out.println("Move Character (Y/N):");
        moveCharacter = getBooleanFromInput();
    }

    private void printConfiguration() {
        System.out.println("\n***Configuraton***");
        System.out.println("ScreenWidth: " + width + "px");
        System.out.println("ScreenHeight: " + height + "px");
        System.out.println("DelayTime: " + delayTime + "ms");
        System.out.println("MoveMouse: " + moveMouse);
        System.out.println("MoveCharacter: " + moveCharacter);
        System.out.println("******************\n");
    }

    public void start(){
        System.out.println("Starting...");

        running = true;

        do{
            doActions();
        } while(running);

        shutDown();
    }

    private void doActions(){
        try {
            System.out.println("Delaying for " + delayTime + "ms");
            robot.delay(delayTime);
            System.out.println("Executing Actions...");
            moveMouse();
            moveCharacter();
        } catch (Exception e){
            System.out.println("Caught exception during action execution:");
            e.printStackTrace();
            running = false;
        }
    }

    private void moveMouse(){
        if(moveMouse){
            robot.mouseMove(random.nextInt(width), random.nextInt(height));
        }
    }


    private void moveCharacter() {
        if(moveCharacter){
            robot.keyPress('w');
            robot.keyRelease('w');
        }
    }

    private void restart(){
        System.out.println("Restarting...");
        restartAttempts++;
        if(restartAttempts >= MAX_RESTART_ATTEMPTS){
            System.out.println("Restart attempts exceeds max!");
            shutDown();
        }
        initialize();
    }

    private void shutDown(){
        System.out.println("Shutting Down...");
        if (scanner != null){
            scanner.close();
        }
        System.exit(0);
    }

    private boolean getBooleanFromInput(){
        String input = scanner.nextLine();
        if(input.toUpperCase().equals("Y")){
            return true;
        } else if(input.toUpperCase().equals("N")){
            return false;
        } else {
            System.out.println("Invalid input, defaulting false!");
            throw new InputMismatchException();
        }
    }
}

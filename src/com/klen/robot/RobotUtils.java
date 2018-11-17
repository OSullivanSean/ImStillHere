package com.klen.robot;

import java.awt.*;

public class RobotUtils {

    private static Robot robot = null;

    public static Robot getRobot(){
        if(robot == null){
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
        return robot;
    }
}

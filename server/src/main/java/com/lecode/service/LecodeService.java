package com.lecode.service;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.springframework.stereotype.Service;

@Service
public class LecodeService {

    public String typingCode(String code) {
        try {
            Robot robot = new Robot();
            int x;
            Character y;
            for (int i = 0; i < code.length(); i++) {
                y = code.charAt(i);
                x = y.hashCode();
                robot.keyPress(x);
                robot.keyRelease(x);
//                robot.delay(10);
            }
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (AWTException e) {
            e.printStackTrace();
        }
        return "Sucesso";
    }

}

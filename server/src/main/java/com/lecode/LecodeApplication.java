package com.lecode;

import com.lecode.view.LecodeView;
import java.net.UnknownHostException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LecodeApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(LecodeApplication.class);
        builder.headless(false);
        builder.run(args);
        LecodeView view = new LecodeView();
        view.setVisible(true);
    }
}

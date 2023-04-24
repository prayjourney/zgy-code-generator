package ${cfg.packageParent};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ${cfg.projectName}Application {
    public static void main(String[] args) {
        SpringApplication.run(${cfg.projectName}Application.class, args);
    }

}
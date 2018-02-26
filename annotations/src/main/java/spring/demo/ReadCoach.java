package spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReadCoach implements Coach {

    private ReadFortuneService readFortuneService;

    @Autowired
    public ReadCoach(ReadFortuneService readFortuneService) {
        this.readFortuneService = readFortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Daily workout from Random coach";
    }

    @Override
    public String getDailyFortune() {
        System.out.println(">> ReadCoach: daily fortune from ReadCoach");
        return readFortuneService.getFortune();
    }
}

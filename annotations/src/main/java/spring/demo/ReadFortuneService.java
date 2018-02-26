package spring.demo;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Component
public class ReadFortuneService implements FortuneService {
    private Random random = new Random();
    private List<String> list = new ArrayList<>();

    public ReadFortuneService() {
        try {
            String filename = "fortunes.txt";
            ClassLoader classLoader = ReadFortuneService.class.getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String tempLine;
            while ((tempLine = bufferedReader.readLine()) != null) {
                list.add(tempLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFortune() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}

package it.plansoft.codekombat;

import com.google.gson.Gson;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class Starter implements ApplicationListener<ApplicationReadyEvent> {

    private final Gson gson = new Gson();
    private final Solver solver;

    public Starter(Solver solver) {
        this.solver = solver;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

            Stream.of(1, 2, 3, 4, 5, 6)
                    .forEach(testNumber -> {
                        try {
                            String input1 = new String(Files.readAllBytes(ResourceUtils.getFile("classpath:input"+testNumber+".json").toPath()));
                            String flights1 = new String(Files.readAllBytes(ResourceUtils.getFile("classpath:flights"+testNumber+".json").toPath()));
                            List<Integer> solve = solver.solve(
                                    gson.fromJson(input1, Input.class),
                                    Arrays.asList(gson.fromJson(flights1, Flight[].class))
                            );
                            System.out.println("Solution "+testNumber+" = " + solve.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

    }


}

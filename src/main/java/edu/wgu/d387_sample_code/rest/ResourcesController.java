package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.locale.GreetingMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/resources")
@CrossOrigin
public class ResourcesController {

    private Executor ex = Executors.newFixedThreadPool(2);

    @GetMapping("welcome")
    public ResponseEntity<List<String>>getWelcomeMessage() {
        List<String> l = new ArrayList<String>();

        ex.execute(() -> {
            GreetingMessage rrEN = new GreetingMessage("en", "US");
            l.add(rrEN.getWelcomeMessage());
        });

        ex.execute(() -> {
            GreetingMessage rrFR = new GreetingMessage("fr", "CA");
            l.add(rrFR.getWelcomeMessage());
        });

        return ResponseEntity.ok(l);

    }
}

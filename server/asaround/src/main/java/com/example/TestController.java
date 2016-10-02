package com.example;



import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sibi on 10/1/16.
 */

@RestController
public class TestController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public TestModel greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new TestModel(counter.incrementAndGet(),
                String.format(template, name));
    }
}

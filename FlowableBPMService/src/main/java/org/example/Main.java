package org.example;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableFeignClients
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner init(final RuntimeService runtimeService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                Map inputVar = new HashMap();
                inputVar.put("account1", 1L);
                inputVar.put("account2", 4L);
                inputVar.put("amount", "300");
                ProcessInstance pr = runtimeService.startProcessInstanceByKey("SampleSagaOrchestration", inputVar);
                log.info("Got Processing Instance Id "+pr.getId());
            }
        };
    }
}
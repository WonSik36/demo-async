package com.example.demoasync;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class SomeService {
    private final ApplicationEventPublisher publisher;
    private final SomeRepository repository;

    public void syncEvent() {
        repository.save(new Some("name"));

        log.info("sync Event");

        publisher.publishEvent(1);
    }

    public void asyncEvent() {
        repository.save(new Some("name"));

        log.info("async Event");

        publisher.publishEvent(true);
    }
}

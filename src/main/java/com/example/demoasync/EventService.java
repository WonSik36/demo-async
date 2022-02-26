package com.example.demoasync;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Log4j2
public class EventService {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void syncEventAfterCommit(Integer num) throws InterruptedException {
        log.info("after commit num: "+num);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void syncEventAfterCompletion(Integer num) throws InterruptedException {
        log.info("after complete num: "+num);
    }

    @Async
    @TransactionalEventListener
    public void asyncEvent(Boolean bool) throws InterruptedException {
        Thread.sleep(100000);
        log.info("bool: "+bool);
    }
}

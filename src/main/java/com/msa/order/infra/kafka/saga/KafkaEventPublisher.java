package com.msa.order.infra.kafka.saga;

import com.msa.order.infra.event.Event;
import com.msa.order.infra.event.EventType;
import com.msa.order.infra.event.payload.EventPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Async("kafkaEventPublishingExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publish(Event event) { this.publish(event.getEventTopic(), event);}

    public void publish(String topic, Event event) {
        try{
            kafkaTemplate.send(
                topic,
                event
            ).get(1, TimeUnit.SECONDS);
        } catch (Exception e){
            log.error("[ERROR][KafkaEventPublisher] Publishing Event Failed", e);
        }

    }
}

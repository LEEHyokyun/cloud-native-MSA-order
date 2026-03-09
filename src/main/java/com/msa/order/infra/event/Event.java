package com.msa.order.infra.event;

import com.msa.order.infra.event.payload.EventPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event<T extends EventPayload> {
    private String eventTopic;
    private EventType eventType;
    private T payload;

}

package com.ryan.rnd.amq.controller;

import com.ryan.rnd.amq.domain.SampleReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class Receiver {

    @JmsListener(destination = "sub1",containerFactory = "jmsListenerContainerFactory", subscription = "sub1")
    public void receiveMessage(SampleReq msg){
        log.info(LocalDateTime.now() + "message : {}", msg);
    }
}

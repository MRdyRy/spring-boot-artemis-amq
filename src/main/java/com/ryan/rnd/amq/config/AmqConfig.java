package com.ryan.rnd.amq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

@Configuration
@EnableJms
public class AmqConfig {

    @Value("${CLIENT_ID}")
    private String clientId;

    @Value("${DURABLE}")
    private boolean durable;

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(CachingConnectionFactory connectionFactory,
                                                                      DefaultJmsListenerContainerFactoryConfigurer configurer){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory,connectionFactory);
        factory.setPubSubDomain(true);
        factory.setSubscriptionDurable(durable);
        factory.setClientId(clientId);
        connectionFactory.setClientId(clientId);

        return factory;
    }
}

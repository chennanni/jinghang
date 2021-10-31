package com.max.msg;

import org.apache.activemq.artemis.core.config.impl.SecurityConfiguration;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.apache.activemq.artemis.spi.core.security.ActiveMQJAASSecurityManager;
import org.apache.activemq.artemis.spi.core.security.jaas.InVMLoginModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerManager {

    @Bean
    public ActiveMQJAASSecurityManager securityManager(@Value("${amq.broker.username}") String user,
                                                       @Value("${amq.broker.password}") String password,
                                                       @Value("${amq.broker.role}") String role) {
        final SecurityConfiguration configuration = new SecurityConfiguration();
        final ActiveMQJAASSecurityManager securityManager =
                new ActiveMQJAASSecurityManager(InVMLoginModule.class.getName(), configuration);
        configuration.addUser(user, password);
        configuration.addRole(user, role);
        configuration.setDefaultUser(user);
        return securityManager;
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public EmbeddedActiveMQ embeddedActiveMQ(ActiveMQJAASSecurityManager securityManager) {
        final EmbeddedActiveMQ embeddedActiveMQ = new EmbeddedActiveMQ();
        embeddedActiveMQ.setSecurityManager(securityManager);
        return embeddedActiveMQ;
    }

}

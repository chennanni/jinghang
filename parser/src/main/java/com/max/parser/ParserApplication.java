package com.max.parser;

import com.max.parser.config.ParserConfig;
import com.max.parser.zk.RegistryProvider;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParserApplication implements ApplicationRunner {

    @Autowired
    ParserConfig parserConfig;

    private static final Logger logger = LoggerFactory.getLogger(ParserApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // parse args
        ArgumentParser parser = ArgumentParsers.newFor("ParserApplication").build();
        parser.addArgument("--host")
                .dest("host")
                .help("host")
                .setDefault("localhost");
        parser.addArgument("--id")
                .dest("id")
                .help("id")
                .type(Integer.class)
                .setDefault(0);
        Namespace res = parser.parseArgs(args.getSourceArgs());
        String hostname = res.get("host");
        int instanceId = res.get("id");
        logger.info("param hostname: " + hostname);
        logger.info("param id: " + instanceId);

        //register parser service
        RegistryProvider registryProvider = new RegistryProvider(hostname,parserConfig.getServiceName(),String.valueOf(instanceId), parserConfig.getMaxInstance());
        int registeredInstanceId = registryProvider.register();
        if (registeredInstanceId == -1) System.exit(0);
        System.setProperty("instance.id", String.valueOf(registeredInstanceId));

    }
}

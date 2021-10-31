package com.max.ftp;

import com.max.core.db.DatasetDAO;
import com.max.core.db.DbTemplate;
import com.max.core.db.FeedDAO;
import com.max.core.msg.MessageSender;
import com.max.ftp.db.FtpDbManager;
import com.max.ftp.file.FtpFileManager;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.Namespace;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class FtpApplication implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(FtpApplication.class);

    @Autowired
    MessageSender messageSender;

    @Autowired
    FtpDbManager ftpDbManager;

    @Autowired
    DbTemplate dbTemplate;

    @Autowired
    FtpFileManager ftpFileManager;

    public static void main(String[] args) {
        SpringApplication.run(FtpApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("*********************** NEW BATCH START *********************** ");

        // parse args
        ArgumentParser parser = ArgumentParsers.newFor("FtpApplication").build();
        parser.addArgument("--feed", "-f")
                .dest("feedId")
                .type(Integer.class)
                .setDefault(1)
                .help("feedId");
        parser.addArgument("--date", "-d")
                .dest("cobDate")
                .type(Integer.class)
                .setDefault(20210930)
                .help("cobDate");
        Namespace res = parser.parseArgs(args.getSourceArgs());
        int cobDate = res.get("cobDate");
        int feedId = res.get("feedId");
        logger.info("param cobDate: " + cobDate);
        logger.info("param feedId: " + feedId);

        // generate file
        String feedName = dbTemplate.getFeedNameFromFeedId(feedId);
        Map<String, String> filePath = ftpFileManager.generateFile(cobDate, feedId, feedName);

        // insert to com.max.loader.db
        String datasetId = ftpDbManager.updateDb(cobDate, feedId);
        if (StringUtils.equalsIgnoreCase(datasetId, "-1")) {
            logger.error("Something is wrong with the com.max.loader.db operation, can not continue to process.");
            return;
        }

        // send msg
        messageSender.sendCompleteMessage(cobDate, feedId, datasetId, filePath);
        logger.info("*********************** DONE BATCH GENERATION *********************** ");
    }

}

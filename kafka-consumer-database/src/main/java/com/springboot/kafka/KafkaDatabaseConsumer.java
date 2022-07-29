package com.springboot.kafka;

import com.springboot.kafka.entity.WikimediaData;
import com.springboot.kafka.repository.WikimediaDataRrepositry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private WikimediaDataRrepositry wikimediaDataRrepositry;

    public KafkaDatabaseConsumer(WikimediaDataRrepositry wikimediaDataRrepositry) {
        this.wikimediaDataRrepositry = wikimediaDataRrepositry;
    }

    @KafkaListener(topics = "wikimedia_recentchange",groupId = "myGroup")
    public void consume(String eventMessage){
        LOGGER.info(String.format("Event Message Received -> %s",eventMessage));

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);

        wikimediaDataRrepositry.save(wikimediaData);



    }
}

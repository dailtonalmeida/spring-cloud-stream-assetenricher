package com.gft.assetstream.assetenricher;

import com.gft.assetstream.assetenricher.business.domain.Asset;
import java.math.BigDecimal;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@Slf4j
public class AssetEnricherApplication {
    private static final int HINT_GENEVA = 1; //(1<<0)
    private static final int HINT_MAUI   = 2; //(1<<2)

    public static void main(String[] args) {
        SpringApplication.run(AssetEnricherApplication.class, args);
    }

    @Bean
    public Function<Message<Asset>, Message<Asset>> enrichAsset() {
        return (Message<Asset> message) -> {
            Asset asset = message.getPayload();
            asset.setValue(BigDecimal.valueOf(1000L)); //enrich asset
            log.info("enriched asset {}", asset);
            
//            MessageHeaders headers = message.getHeaders();
//            headers.put("x-route-hint", 3); 
            
            return MessageBuilder.withPayload(asset)
                    .copyHeaders(message.getHeaders())
                    .setHeader("x-route-hint", HINT_GENEVA | HINT_MAUI) //3=2^1+2^0  / byte 0 = hint to route to geneva / byte 1 = hint to route to maui
                    .build();
        };
    } 
}

package de.chaintracker.kafka.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class UserConsumerConfig {

  @Value("${spring.kafka.servers}")
  private String bootstrapServers;

  private final String groupId = "infectionchain_backend";

  @Bean
  public Map<String, Object> userConsumerConfig() {
    final Map<String, Object> props = new HashMap<>();
    props.put(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        this.bootstrapServers);
    props.put(
        ConsumerConfig.GROUP_ID_CONFIG,
        this.groupId);
    props.put(
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class);
    props.put(
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class);
    return props;
  }

}

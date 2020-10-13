import org.apache.kafka.common.serialization.StringDeserializer;
import utils.KafkaUtils;

import java.util.Properties;

public class TestConsumer {
    public static void main(String[] args){
        //配置信息
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", "localhost:9092");
        //必须指定消费者组
        props.put("group.id", "test-consumer-group");
        //设置数据key和value的序列化处理类
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", StringDeserializer.class);
        String topic = "test";

        KafkaUtils.consume(topic,props);
    }

}

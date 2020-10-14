import kafka.Kafka;
import org.apache.kafka.common.serialization.StringDeserializer;
import utils.KafkaUtils;

import java.util.Properties;

public class TestConsumer {
    public static void main(String[] args){

        String ipAddress = "localhost:9092";
        //配置信息
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", ipAddress);
        //必须指定消费者组
//        props.put("group.id", "test-consumer-group");
        props.put("group.id", "wkyj_dataclean");
        //设置数据key和value的序列化处理类
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", StringDeserializer.class);
        String topic = "tailingsStdOffline";
//        new Thread(()->{
//            System.out.println("topic: test");
//            KafkaUtils.consume("test",props);
//        }).start();

//        new Thread(() -> {
//            System.out.println("topic: "+ topic);
//            KafkaUtils.consume(topic,props);
//        }).start();

//        new Thread(() -> {
//            System.out.println("topic: "+ "stdTailingsMonitorDry");
//            KafkaUtils.consume("stdTailingsMonitorDry",props);
//        }).start();

        new Thread(() -> {
            System.out.println("topic: "+ "tailingsStdOfflineErr");
            KafkaUtils.consume("tailingsStdOfflineErr",props);
        }).start();

    }

}

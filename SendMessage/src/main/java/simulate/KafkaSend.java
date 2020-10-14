package simulate;

import org.apache.kafka.common.serialization.StringSerializer;
import utils.KafkaUtils;
import utils.RandomUtils;

import java.util.Properties;

public class KafkaSend {
    public static void main(String[] args) throws InterruptedException {
        //配置信息
        String ip = "localhost:9092";
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", ip);

        //设置数据key和value的序列化处理类
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);

        String topic = "test";

        for (int i=0; ;i++) {
            System.out.println("product: msg: "+i);
            KafkaUtils.produce(topic,props,"msg: " + i);
            Thread.sleep(2000);
        }
    }
}

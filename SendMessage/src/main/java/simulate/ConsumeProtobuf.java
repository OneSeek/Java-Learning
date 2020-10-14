package simulate;

import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.BytesDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.KafkaUtils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class ConsumeProtobuf {
    Logger logger = LoggerFactory.getLogger(ConsumeProtobuf.class);
    public static void main(String[] args) throws InvalidProtocolBufferException, UnsupportedEncodingException {
        String ipAddress = "localhost:9092";
        //配置信息
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", ipAddress);
        //必须指定消费者组
        props.put("group.id", "test-consumer-group");
        //设置数据key和value的序列化处理类
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", ByteArrayDeserializer.class);
        String topic = "chuang_std";


        System.out.println("start consume topic: " + topic);
        KafkaUtils.consume(topic, props);



    }
}

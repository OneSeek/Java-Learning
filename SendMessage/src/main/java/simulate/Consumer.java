package simulate;

import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.common.serialization.BytesDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import utils.KafkaUtils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        String ipAddress = "localhost:9092";
        //������Ϣ
        Properties props = new Properties();
        //kafka��������ַ
        props.put("bootstrap.servers", ipAddress);
        //����ָ����������
        props.put("group.id", "test-consumer-group");
        //��������key��value�����л�������
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", StringDeserializer.class);
        String topic = "chuang_std";


        System.out.println("start consume topic: " + topic);
        KafkaUtils.consume(topic, props);



    }
}

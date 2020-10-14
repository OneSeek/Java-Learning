package test;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.StringSerializer;
import utils.KafkaUtils;

import java.util.Properties;

public class Sender {
    public static void main(String[] args) {
        String jsonData = KafkaUtils.readJsonFile("src\\main\\java\\data\\data.json");

        String ip = "localhost:9092";
        //������Ϣ
//        String ip = "localhost:9092";
        Properties props = new Properties();
        //kafka��������ַ
        props.put("bootstrap.servers", ip);

        //��������key��value�����л�������
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);

        KafkaUtils.produce("chuang_ods", props, jsonData);

        System.out.println(jsonData);

    }
}

package test;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.StringSerializer;
import utils.KafkaUtils;

import java.util.Properties;

public class Sender {
    public static void main(String[] args) {
        String jsonData = KafkaUtils.readJsonFile("src\\main\\java\\data\\data.json");

        String ip = "localhost:9092";
        //配置信息
//        String ip = "localhost:9092";
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", ip);

        //设置数据key和value的序列化处理类
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);

        KafkaUtils.produce("chuang_ods", props, jsonData);

        System.out.println(jsonData);

    }
}

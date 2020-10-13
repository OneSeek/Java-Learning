package utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Properties;

public class KafkaUtils {
    private static Logger logger = LoggerFactory.getLogger(KafkaUtils.class); //log.info() 调用

    public static Properties consumerProperties;

    public static Properties producerProperties;

    public static Properties databasesProperties;

    public static Properties configProperties;

    public static JSONObject configJson;

    public static boolean isRunningKafkaConsumer = true;

    public static void produce(String topic,Properties props,String json){
        //创建生产者实例
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, json);
        producer.send(record);
        producer.close();
    }

    public static void consume(String topic,Properties props){

        //创建消息者实例
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(props);
        //订阅topic1的消息
        consumer.subscribe(Collections.singletonList(topic));
        //到服务器中读取记录
        while (isRunningKafkaConsumer){
            ConsumerRecords<String,String> records = consumer.poll(100);
            for(ConsumerRecord<String,String> record : records){
                System.out.println(record.value());
            }
        }
    }

    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),StandardCharsets.UTF_8);
            int ch = 0;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}

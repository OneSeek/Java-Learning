import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import utils.KafkaUtils;

import java.util.Collections;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        /*模拟参数*/
        args = new String[]{"--configPath",
                "C:\\MyProgram\\Project\\KafkaStudy\\src\\main\\java\\config",
                "--parallelism", "3",
                "--enableCheckpoint", "false"
        };
//        //加载kafka生产者配置、消费者配置
//        if (!utils.KafkaUtils.loadDataCleanConfig(args[1])) {
//            return;
//        }


        run("test");
//        run("topic-std");
    }
    public static void run(String topic){
        String bootstrap_servers = KafkaUtils.consumerProperties.getProperty("bootstrap.servers");
        String group_id = KafkaUtils.consumerProperties.getProperty("group.id");
        //配置信息
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", bootstrap_servers);
        //必须指定消费者组
        props.put("group.id", group_id);
        //设置数据key和value的序列化处理类
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", StringDeserializer.class);
        //创建消息者实例
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(props);
        //订阅topic1的消息
        consumer.subscribe(Collections.singletonList(topic));
        //到服务器中读取记录
        while (true){
            ConsumerRecords<String,String> records = consumer.poll(100);
            for(ConsumerRecord<String,String> record : records){
                System.out.println(record);
            }
        }
    }
}
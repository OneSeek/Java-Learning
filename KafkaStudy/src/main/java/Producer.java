import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import utils.KafkaUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Producer {
    public static void main(String[] args) {
//        /*模拟参数*/
//        args = new String[]{"--configPath",
//                "C:\\MyProgram\\Project\\KafkaStudy\\src\\main\\java\\config",
//                "--parallelism", "3",
//                "--enableCheckpoint", "false"
//        };
//        //加载kafka生产者配置、消费者配置
//        if (!utils.KafkaUtils.loadDataCleanConfig(args[1])) {
//            return;
//        }
        run("test");
//        run("topic-ods");
    }
    public static void run(String topic){
//        String bootstrap_servers = KafkaUtils.producerProperties.getProperty("bootstrap.servers");
        //配置信息
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", "localhost:9092");

        //设置数据key和value的序列化处理类
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);
        //创建生产者实例
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);



        Map<String,String> map = new HashMap<>();

        map.put("单体Json",JsonData.单体json);
        map.put("嵌套单体Json",JsonData.嵌套单体json);
        map.put("数组Json",JsonData.数组json);
        map.put("嵌套json数组",JsonData.嵌套json数组);
        map.put("变体Json(子Json为单体Json)",JsonData.变体json_子json为单体);
        map.put("变体json为Json数组",JsonData.变体json_子json为Json数组);
        for (Map.Entry<String,String> entry:map.entrySet()){
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, entry.getValue());
            //发送记录
            System.out.println("发送");
            producer.send(record);
        }

        producer.close();
    }
}
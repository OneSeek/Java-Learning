import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import utils.KafkaUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Producer {
    public static void main(String[] args) {
//        /*ģ�����*/
//        args = new String[]{"--configPath",
//                "C:\\MyProgram\\Project\\KafkaStudy\\src\\main\\java\\config",
//                "--parallelism", "3",
//                "--enableCheckpoint", "false"
//        };
//        //����kafka���������á�����������
//        if (!utils.KafkaUtils.loadDataCleanConfig(args[1])) {
//            return;
//        }
        run("test");
//        run("topic-ods");
    }
    public static void run(String topic){
//        String bootstrap_servers = KafkaUtils.producerProperties.getProperty("bootstrap.servers");
        //������Ϣ
        Properties props = new Properties();
        //kafka��������ַ
        props.put("bootstrap.servers", "localhost:9092");

        //��������key��value�����л�������
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);
        //����������ʵ��
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);



        Map<String,String> map = new HashMap<>();

        map.put("����Json",JsonData.����json);
        map.put("Ƕ�׵���Json",JsonData.Ƕ�׵���json);
        map.put("����Json",JsonData.����json);
        map.put("Ƕ��json����",JsonData.Ƕ��json����);
        map.put("����Json(��JsonΪ����Json)",JsonData.����json_��jsonΪ����);
        map.put("����jsonΪJson����",JsonData.����json_��jsonΪJson����);
        for (Map.Entry<String,String> entry:map.entrySet()){
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, entry.getValue());
            //���ͼ�¼
            System.out.println("����");
            producer.send(record);
        }

        producer.close();
    }
}
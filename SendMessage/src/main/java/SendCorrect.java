import org.apache.kafka.common.serialization.StringSerializer;
import utils.KafkaUtils;

import java.util.Properties;

public class SendCorrect {
    public static void main(String[] args) throws InterruptedException {
        //������Ϣ
        String ip = "localhost:9092";
        Properties props = new Properties();
        //kafka��������ַ
        props.put("bootstrap.servers", ip);

        //��������key��value�����л�������
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);


        String msg = "{\n" +
                "    \"cata_id\":\"CC20200325104919.00\",\n" +
                "    \"o_time\":\"2020-03-25 10:49:19\",\n" +
                "    \"m\":7.5,\n" +
                "    \"epi_lat\":48.93,\n" +
                "    \"epi_lon\":157.74,\n" +
                "    \"epi_depth\":30,\n" +
                "    \"auto_flag\":\"M\",\n" +
                "    \"location_c\":\"ǧ��Ⱥ��\",\n" +
                "    \"grade_level\":\"1\",\n" +
                "    \"load_time\":\"2020-03-30 14:01:04\"\n" +
                "}\n";

        KafkaUtils.produce("kafkatest", props, msg);
//            KafkaUtils.produce("test",props,msg);
        System.out.println(msg);


    }

//    }

}


import Sink.GaussDBSink;
import Sink.KafkaSink;
import Sink.KafkaSource;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;

import java.util.Properties;

public class RealTimeCleaning {
    public static void main(String[] args) throws Exception {
// set up the streaming execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //默认情况下，检查点被禁用。要启用检查点，请在StreamExecutionEnvironment上调用enableCheckpointing(n)方法，
        // 其中n是以毫秒为单位的检查点间隔。每隔5000 ms进行启动一个检查点,则下一个检查点将在上一个检查点完成后5秒钟内启动
        env.enableCheckpointing(5000);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "192.168.144.95:9092");//kafka的节点的IP或者hostName，多个使用逗号分隔
        properties.setProperty("zookeeper.connect", "192.168.144.95:2181");//zookeeper的节点的IP或者hostName，多个使用逗号进行分隔
        properties.setProperty("group.id", "test-consumer-group");//flink consumer flink的消费者的group.id

        FlinkKafkaConsumer010<String> myConsumer = new FlinkKafkaConsumer010<String>(
                "topic-ods",
                new SimpleStringSchema(),
                properties);// topic-ods是kafka中开启的topic

        myConsumer.setStartFromLatest();
        String dataSourceName = "";
//        myConsumer.assignTimestampsAndWatermarks(new CustomWatermarkEmitter());

        DataStream<String> keyedStream = env.addSource(myConsumer);//将kafka生产者发来的数据进行处理，本例子未进行任何处理

//        DataStream<String> keyedStream = env.addSource(new KafkaSource(dataSourceName));//将kafka生产者发来的数据进行处理，本例子未进行任何处理


//        logger.info("将kafka生产者发来的数据进行处理，本例子未进行任何处理");
//        keyedStream.print();//直接将从生产者接收到的数据在控制台上进行打印


        DataStream<String> CleanData =
                keyedStream
                        .map(new MapFunction<String, String>() {
                            @Override
                            public String map(String s) throws Exception {
                                System.out.println(s);
                                return s;
                            }
                        });

        CleanData.print();

        FlinkKafkaProducer010<String> myProducer = new FlinkKafkaProducer010<>(
                "192.168.144.95:9092",
                "topic-std",
                new SimpleStringSchema());

        myProducer.setWriteTimestampToKafka(true);

        keyedStream.addSink(new KafkaSink());

        // execute program
        env.execute("Flink Streaming Java API Skeleton");
    }
}

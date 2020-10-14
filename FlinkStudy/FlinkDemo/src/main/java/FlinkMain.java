import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class FlinkMain {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> input = env.addSource(new DemoSource());

        DataStream<String> parsed = input.map(new MapFunction<String, String>() {
            @Override
            public String map(String value) {
                return value;
            }
        }).flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String s, Collector<String> collector) throws Exception {

            }
        });

        parsed.addSink(new DemoSink());
        parsed.print();
        env.execute();
    }
}

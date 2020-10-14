import org.apache.flink.streaming.api.functions.sink.SinkFunction;

public class DemoSink implements SinkFunction<String> {
    @Override
    public void invoke(String value, Context context) throws Exception {
        System.out.println("Sink value: "+value);
    }
}

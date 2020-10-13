package Sink;


import org.apache.flink.streaming.api.functions.sink.SinkFunction;

public class KafkaSink implements SinkFunction<String> {

    @Override
    public void invoke(String value, Context context) throws Exception {

    }
}

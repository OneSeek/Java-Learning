package Sink;

import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

public class GaussDBSink extends RichSinkFunction {
    @Override
    public void invoke(Object value, Context context) throws Exception {
        System.out.println("value: "+value);
        System.out.println("context: "+context);
    }
}

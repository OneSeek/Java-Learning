package aggregations;

import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WindowTest {
    private static final Logger LOG = LoggerFactory.getLogger(FoldTest.class);
    private static final String[] TYPE = {"苹果", "梨", "西瓜", "葡萄", "火龙果"};

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //添加自定义数据源,每秒发出一笔订单信息{商品名称,商品数量}
        DataStreamSource<Tuple2<String, Integer>> orderSource = env.addSource(new SourceFunction<Tuple2<String, Integer>>() {
            private volatile boolean isRunning = true;
            private final Random random = new Random();

            @Override
            public void run(SourceContext<Tuple2<String, Integer>> ctx) throws Exception {
                while (isRunning) {
                    TimeUnit.SECONDS.sleep(1);
                    String type = TYPE[random.nextInt(TYPE.length)];
                    System.out.println("---------------");
                    System.out.println(type);
                    ctx.collect(Tuple2.of(type, 1));
//                    ctx.collect(Tuple2.of(TYPE[random.nextInt(TYPE.length)], 1));
                }
            }

            @Override
            public void cancel() {
                isRunning = false;
            }

        }, "order-info");
        //这里只为将DataStream → KeyedStream,用空字符串做分区键。所有数据为相同分区
        orderSource
                .keyBy(0).timeWindow(Time.seconds(5))

                .apply(new MyTimeWindowFunction()).print();

        env.execute("Flink Streaming Java API Skeleton");
    }
}
class MyTimeWindowFunction implements WindowFunction<Tuple2<String,Integer>, String, Tuple, TimeWindow> {

    @Override
    public void apply(Tuple tuple, TimeWindow window, Iterable<Tuple2<String, Integer>> input, Collector<String> out) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        int sum = 0;

        for(Tuple2<String,Integer> tuple2 : input){
            sum +=tuple2.f1;
        }

        long start = window.getStart();
        long end = window.getEnd();

        out.collect(tuple.getField(0) + "  " + sum + "\nwindow_start :"
                + format.format(start) + "\nwindow_end :" + format.format(end)
        );

    }
}

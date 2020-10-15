package aggregations;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AggregationsTest {
    private static final Logger LOG = LoggerFactory.getLogger(AggregationsTest.class);
    private static final String[] TYPE = { "苹果", "梨", "西瓜", "葡萄", "火龙果" };

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
                    ctx.collect(Tuple2.of(TYPE[random.nextInt(TYPE.length)], 1));
                }
            }
            @Override
            public void cancel() {
                isRunning = false;
            }

        }, "order-info");

        orderSource
//                //将订单流按Tuple的第一个字段分区--商品名称
                .keyBy(0)
//                //累加订单流Tuple的第二个字段--商品数量
//                //将流中的每个元素按标准输出打印
//                .min(0) //找最小值，返回一个tuple元素（可能是不存在的元素），属性0是窗口内所有元素的属性0的最小值，属性1是窗口内第一个tuple的属性1值
//                .min(1) //找最小值，返回一个tuple元素（可能是不存在的元素），属性0是窗口内第一个tuple的属性0值，属性1是窗口内所有元素的属性1的最小值
//                .minBy(0)//找属性0值最小的元素，返回一个tuple元素，属性0、属性1均是窗口内属性0值最小的元素值
//                .minBy(1)//找属性1值最小的元素，返回一个tuple元素，属性0、属性1均是窗口内属性1值最小的元素值
                .sum(1)//求和，返回一个tuple元素（可能是不存在的元素），属性0是窗口内第一个tuple的属性0值，属性1是窗口内所有元素的属性1值的和
//                .sumBy(1) // 无sumBy方法，程序报错
                .print();

        env.execute("Flink Streaming Java API Skeleton");
    }
}
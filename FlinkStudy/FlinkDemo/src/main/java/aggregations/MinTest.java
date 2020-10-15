package aggregations;

import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

public class MinTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //获取数据源
        List<Tuple3<Integer,Integer,Integer>> data = new ArrayList<>();
        data.add(new Tuple3<>(0,2,2));
        data.add(new Tuple3<>(0,1,1));
        data.add(new Tuple3<>(0,5,6));
        data.add(new Tuple3<>(0,3,5));
        data.add(new Tuple3<>(1,1,9));
        data.add(new Tuple3<>(1,2,8));
        data.add(new Tuple3<>(1,3,10));
        data.add(new Tuple3<>(1,2,9));

        DataStreamSource<Tuple3<Integer,Integer,Integer>> items = env.fromCollection(data);
        items.keyBy(0).min(2).print();

        env.execute("defined streaming source");
    }

}

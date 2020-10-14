package lambda;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class POJOsTest {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Person> personStream = env.fromElements(new Person("fangpc", 24), new Person("fangpengcheng", 25));

        personStream.filter((FilterFunction<Person>) person -> person.age >= 18);
        personStream.print();

        env.execute();

    }
}

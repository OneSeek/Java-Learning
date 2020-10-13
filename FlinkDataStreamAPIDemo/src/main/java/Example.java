import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.api.common.functions.FilterFunction;

public class Example {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Person> flintstones = env.fromElements(
                new Person("Fred", 35),
                new Person("Wilma", 35),
                new Person("Pebbles", 2));
/* fromCollection(Collection) 方法
List<Person> people = new ArrayList<Person>();

people.add(new Person("Fred", 35));
people.add(new Person("Wilma", 35));
people.add(new Person("Pebbles", 2));

DataStream<Person> flintstones = env.fromCollection(people);
 */
        /*
        另一个获取数据到流中的便捷方法是用 socket
            DataStream<String> lines = env.socketTextStream("localhost", 9999)
或读取文件
            DataStream<String> lines = env.readTextFile("file:///path");
         */
        DataStream<Person> adults = flintstones.filter(
                (FilterFunction<Person>) person -> person.age >= 18);

        adults.print();

        env.execute();
    }

    public static class Person {
        public String name;
        public Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return this.name + ": age " + this.age.toString();
        }
    }
}
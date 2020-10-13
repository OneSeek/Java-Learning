import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
    static Logger logger = LoggerFactory.getLogger("Test.class");
    public static void main(String[] args) {

//        double curr = 1596076000899L;
//        System.out.println();
//
//        //方法 一
//        long t1 = System.currentTimeMillis();
//        //方法 二
//        long timeInMillis = Calendar.getInstance().getTimeInMillis();
//        //方法 三
//        long time = new Date().getTime();
//
//        System.out.println(t1);
//        System.out.println(timeInMillis);
//        System.out.println(time-curr);
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        String date = df.format(curr);// new Date()为获取当前系统时间，也可使用当前时间戳
//        System.out.println(date);
//
//        System.out.println(df.format(0));

//        Test1.t = 10;
//        Test2 test2 = new Test2();
//        test2.out();
//        System.out.println();


        /*模拟参数*/
        args = new String[]{"--configPath",
                "C:\\MyProgram\\Project\\KafkaStudy\\src\\main\\java\\config",
                "--parallelism", "3",
                "--enableCheckpoint", "false"
        };
//        //加载kafka生产者配置、消费者配置
//        if (!utils.KafkaUtils.loadDataCleanConfig(args[1])) {
//            return;
//        }

        Thread consumerThread = new Thread(()->{
            Consumer.run("test");
        });
        consumerThread.start();

        Producer.run("test");

    }
}

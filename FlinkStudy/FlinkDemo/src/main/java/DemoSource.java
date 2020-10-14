import akka.stream.impl.fusing.Scan;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Scanner;


public class DemoSource implements SourceFunction<String> {
    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        int count = 0;
        while (count < 10) {
            Scanner sc = new Scanner(System.in);
            String in = sc.next();
            synchronized (ctx.getCheckpointLock()) {
                ctx.collect(count+in);
                count++;
            }

        }
    }

    @Override
    public void cancel() {

    }
}

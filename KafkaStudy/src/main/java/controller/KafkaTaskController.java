//package controller;
//
//import com.dtdream.datariverstreaming.util.KafkaUtils;
//import com.dtdream.datariverstreaming.vo.KafkaTaskConfigVO;
//import io.swagger.annotations.ApiOperation;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//
//import java.util.Properties;
//
//public class KafkaTaskController {
//
//    /**
//     * 保存Kafka任务
//     *
//     * @param kafkaTaskConfigVo
//     * @return 是否创建成功
//     */
//    @ApiOperation(value = "保存保存Kafka任务", notes = "")
//    @PostMapping("/createTask")
//    public String createTask(@RequestHeader(value = "tenant") String tenant,
//                                           @RequestHeader(value = "user") String user,
//                                           @RequestBody KafkaTaskConfigVO kafkaTaskConfigVo) {
//
//
//        String topic = kafkaTaskConfigVo.getTopic();
//        //配置信息
//        Properties props = new Properties();
//        //kafka服务器地址
//        props.put("bootstrap.", kafkaTaskConfigVo.getServersIP());
//        //必须指定消费者组
//        props.put("group.id", kafkaTaskConfigVo.getGroupID());
//        //设置数据key和value的序列化处理类
//        props.put("key.deserializer", StringDeserializer.class);
//        props.put("value.deserializer", StringDeserializer.class);
//
//        // 请求元数据获得 kafka属性
//
//        KafkaUtils.consume(topic,props);
//
//        return "success";
//    }
//
//}

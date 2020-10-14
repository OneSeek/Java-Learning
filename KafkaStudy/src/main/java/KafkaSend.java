import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.StringSerializer;
import utils.KafkaUtils;
import utils.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class KafkaSend {
    public static void main(String[] args) throws InterruptedException {
        //配置信息
        String ip = "localhost:9092";
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", ip);

        //设置数据key和value的序列化处理类
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);

        String jsonData = KafkaUtils.readJsonFile("C:\\MyProgram\\Project\\Java-Learning\\KafkaStudy\\src\\main\\java\\json\\data.json");

//        System.out.println(jsonData);
        JSONArray jsonArray = JSON.parseArray(jsonData);
//        System.out.println(jsonArray.get(0));
//        System.out.println(jsonArray.get(5));
        assert jsonArray != null;
        JSONObject jsonObject = jsonArray.getJSONObject(1);
//        System.out.println(jsonObject);

        int x = 10;
        while(x-->0){
//            int type = new Random().nextInt(6);
            Thread.sleep(1000);
            sendRandomDataFormat2(jsonObject);

            utils.KafkaUtils.produce("test",props,jsonObject.toJSONString());
            System.out.println("json type:"+2+"\n"+jsonObject);
        }

    }
    public static void sendRandomDataFormat1(JSONObject jsonObject){
        Date date = randomDate("2020-01-01","2022-01-31");
        String time_value = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        String cata_id_value = RandomUtils.getRandomUppercaseString(2)+time_value+".00";
        String o_time_value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        jsonObject.put("cata_id",cata_id_value);
        jsonObject.put("o_time",o_time_value);

    }
    public static void sendRandomDataFormat2(JSONObject jsonObject){
        String dataId_value = RandomUtils.getRandomNumbersAndLowercaseString(28);
        Date date = randomDate("2020-01-01","2022-01-31");
        String sendTime_value = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        String departmentId_value = RandomUtils.getRandomNumString(17)+RandomUtils.getRandomUppercaseString(1);
        String provinceId_value = RandomUtils.getRandomNumString(2);
        String type_value = RandomUtils.getRandomNumString(2);
        jsonObject.put("dataId",dataId_value);
        jsonObject.put("sendTime",sendTime_value);
        jsonObject.put("departmentId",departmentId_value);
        jsonObject.put("provinceId",provinceId_value);
        jsonObject.put("type",type_value);

        JSONObject datas = jsonObject.getJSONObject("datas");
        datas.put("id",RandomUtils.getRandomRange(10000,1));
        //传感器编号 420106NBWY0412
        datas.put("sensorno",provinceId_value+RandomUtils.getRandomNumbersAndUppercaseString(12));

        String collectdate_value = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
        datas.put("collectdate",collectdate_value);
//            0.0527435594662907
        Random random = new Random();
        String xvalue = String.valueOf(random.nextDouble());
        String yvalue = String.valueOf(random.nextDouble());
        datas.put("xvalue",xvalue);
        datas.put("yvalue",yvalue);

        jsonObject.put("datas",datas);
    }
    public static void sendRandomDataFormat3(JSONArray jsonArray){
        Date date = randomDate("2020-01-01","2022-01-31");
        String time_value = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        String cata_id_value = RandomUtils.getRandomUppercaseString(2)+time_value+".00";
        String o_time_value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

//        jsonObject.put("cata_id",cata_id_value);
//        jsonObject.put("o_time",o_time_value);

    }

    private static Date randomDate(String beginDate,String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }
}


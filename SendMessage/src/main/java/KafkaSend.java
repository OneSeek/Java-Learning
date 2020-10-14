import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.StringSerializer;
import utils.KafkaUtils;
import utils.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import static utils.RandomUtils.randomDate;

public class KafkaSend {
    static String jsonData = KafkaUtils.readJsonFile("C:\\MyProgram\\Project\\KafkaStudy\\src\\main\\java\\json\\data.json");
    static JSONObject jsonObject = new JSONObject();
    static JSONArray jsonArray = new JSONArray();
    static JSONObject datasJSONObject = new JSONObject();
    static JSONArray datasJSONArray = new JSONArray(10);//new Random().nextInt(5));


    static Date date = randomDate("2020-01-01","2022-01-31");
    static String sendTime_value = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    static String dataId_value = RandomUtils.getRandomNumbersAndLowercaseString(28);
    static String departmentId_value = RandomUtils.getRandomNumString(17)+RandomUtils.getRandomUppercaseString(1);
    static String provinceId_value = RandomUtils.getRandomNumString(2);
    static String type_value = RandomUtils.getRandomNumString(2);
    static String time_value = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    static String cata_id_value = RandomUtils.getRandomUppercaseString(2)+time_value+".00";
    static String o_time_value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    static String collectdate_value = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
    static Random random = new Random();
    static String xvalue = String.valueOf(random.nextDouble());
    static String yvalue = String.valueOf(random.nextDouble());
    static Double randomDouble = new Random().nextDouble();
    static Integer randomInteger = new Random().nextInt();
    static String local_c_value = "千岛群岛";
    static String auto_flag_value = RandomUtils.getRandomUppercaseString(1);



    public static void main(String[] args) throws InterruptedException {

        //配置信息
        String ip = "localhost:9092";
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", ip);

        //设置数据key和value的序列化处理类
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);



//        while(true){
            int type = RandomUtils.getRandomRange(6,1);
            Thread.sleep(1000);
            setData(); // 赋值
//            setRandomValue(); // 随机赋值
            send(type);
            String msg;
            if(jsonArray.isEmpty()){
                msg = jsonObject.toJSONString();
            }else{
                msg = jsonArray.toJSONString();
            }
            utils.KafkaUtils.produce("tailingsOffline",props,msg);
//            KafkaUtils.produce("test",props,msg);
            System.out.println("json type:"+type+"\n"+msg);
        }

//    }

    public static void send(int i){
        jsonArray = new JSONArray();
        jsonObject = new JSONObject();
        datasJSONArray = new JSONArray();
        datasJSONObject = new JSONObject();
        switch (i){
            case 1 :sendRandomDataFormat1();
                break;
            case 2:sendRandomDataFormat2();
                break;
            case 3 :sendRandomDataFormat3();
                break;
            case 4:sendRandomDataFormat4();
                break;
            case 5 :sendRandomDataFormat5();
                break;
            case 6:sendRandomDataFormat6();
                break;

        }


    }

    /**
     * 格式一：单体JSON
     * {
     *     "cata_id":"CC20200325104919.00",
     *     "o_time":"2020-03-25 10:49:19",
     *     "m":7.5,
     *     "epi_lat":48.93,
     *     "epi_lon":157.74,
     *     "epi_depth":30,
     *     "auto_flag":"M",
     *     "location_c":"千岛群岛",
     *     "grade_level":"1",
     *     "load_time":"2020-03-30 14:01:04"
     * }
     */
    public static JSONObject sendRandomDataFormat1(){
        jsonObject.put("cata_id",cata_id_value);
        jsonObject.put("o_time",o_time_value);
        jsonObject.put("m",randomDouble);
        jsonObject.put("epi_lat",randomDouble);
        jsonObject.put("epi_lon",randomDouble);
        jsonObject.put("epi_depth",randomInteger);
        jsonObject.put("auto_flag",auto_flag_value);
        jsonObject.put("location_c",local_c_value);
        jsonObject.put("grade_level",randomInteger);
        jsonObject.put("load_time",o_time_value);
        return jsonObject;
    }

    /**
     * 格式二：嵌套单体JSON
     */
    public static void sendRandomDataFormat2(){
        jsonObject.put("dataId",dataId_value);
        jsonObject.put("sendTime",sendTime_value);
        jsonObject.put("departmentId",departmentId_value);
        jsonObject.put("provinceId",provinceId_value);
        jsonObject.put("type",type_value);
        JSONObject datas = getJsonObjectData();

        jsonObject.put("datas",datas);
    }

    /**
     * 处理格式三，数组json
     * [{
     *     "cata_id":"CC20200325104919.00",
     *     "o_time":"2020-03-25 10:49:19",
     *     "m":7.5,
     *     "epi_lat":48.93,
     *     "epi_lon":157.74,
     *     "epi_depth":30,
     *     "auto_flag":"M",
     *     "location_c":"千岛群岛",
     *     "grade_level":"1",
     *     "load_time":"2020-03-30 14:01:04"
     * },
     * {
     *     "cata_id":"CC20200325104919.00",
     *     "o_time":"2020-03-25 10:49:19",
     *     "m":7.5,
     *     "epi_lat":48.93,
     *     "epi_lon":157.74,
     *     "epi_depth":30,
     *     "auto_flag":"M",
     *     "location_c":"千岛群岛",
     *     "grade_level":"1",
     *     "load_time":"2020-03-30 14:01:04"
     * }]
     * @param
     */
    public static void sendRandomDataFormat3(){
        int n = new Random().nextInt(10);
        for(int i=0; i<n; i++){
            setData();
            jsonObject = new JSONObject();
            jsonObject = sendRandomDataFormat1();
            jsonArray.add(jsonObject);
        }

    }

    /**
     * 格式4: 嵌套Json数组
     * {
     *   "dataId":"43f6c732a24c2182cddc1db3eff7",
     *   "sendTime":"20200106140908",
     *   "departmentId":"91420500673656549K",
     *   "provinceId":"42",
     *   "type":"04",
     *   "datas":[
     *     {
     *       "id":"1",
     *       "sensorno":"420106NBWY0412",
     *       "collectdate":"2020/01/06 14:08:00",
     *       "xvalue":"0.0527435594662907",
     *       "yvalue":"-0.837524458416799"
     *      },
     *      {
     *        "id":"1",
     *        "sensorno":"420106NBWY0412",
     *        "collectdate":"2020/01/06 14:08:30",
     *        "xvalue":"0.0524235109610677",
     *        "yvalue":"-0.837342156395159"
     *       }
     *     ]
     * }
     */
    public static void sendRandomDataFormat4(){
        datasJSONObject = getJsonObjectData();
        datasJSONArray = getJsonArrayData();

        jsonObject.put("dataId",dataId_value);
        jsonObject.put("sendTime",sendTime_value);
        jsonObject.put("departmentId",departmentId_value);
        jsonObject.put("provinceId",provinceId_value);
        jsonObject.put("type",type_value);
        datasJSONArray = new JSONArray();
        datasJSONArray = getJsonArrayData();
        jsonObject.put("datas",datasJSONArray);
    }

    /**
     * 格式5:变体Json(子Json为单体Json
     *{
     *   "dataId":"43f6c732a24c2182cddc1db3eff7",
     *   "sendTime":"20200106140908",
     *   "departmentId":"91420500673656549K",
     *   "provinceId":"42",
     *   "type":"01",
     *   "datas":
     *     {
     *       "id":"1",
     *       "sensorno":"420106NBWY0412",
     *       "collectdate":"2020/01/06 14:08:00",
     *       "xvalue":"0.0527435594662907"
     *      }
     *
     * }
     */
    public static void sendRandomDataFormat5(){
        jsonObject.put("dataId",dataId_value);
        jsonObject.put("sendTime",sendTime_value);
        jsonObject.put("departmentId",departmentId_value);
        jsonObject.put("provinceId",provinceId_value);
        jsonObject.put("type",type_value);
        JSONObject datas = getJsonObjectData();
        datas.fluentRemove("yvalue");
        jsonObject.put("datas",datas);
    }

    /**
     * 格式5:变体Json(子Json为Json数组)
     * {
     *   "dataId":"43f6c732a24c2182cddc1db3eff7",
     *   "sendTime":"20200106140908",
     *   "departmentId":"91420500673656549K",
     *   "provinceId":"42",
     *   "type":"02",
     *   "datas":[
     *     {
     *       "id":"1",
     *       "sensorno":"420106NBWY0412",
     *       "collectdate":"2020/01/06 14:08:00",
     *       "xvalue":"0.0527435594662907",
     *       "yvalue":"-0.837524458416799"，
     *       "zvalue":"-0.837524458416799"
     *      },
     *      {
     *        "id":"1",
     *        "sensorno":"420106NBWY0412",
     *        "collectdate":"2020/01/06 14:08:30",
     *        "xvalue":"0.0524235109610677",
     *        "yvalue":"-0.837342156395159"
     *        "zvalue":"-0.837524458416799"
     *       }
     *     ]
     * }
     */
    public static void sendRandomDataFormat6(){
        jsonObject.put("dataId",dataId_value);
        jsonObject.put("sendTime",sendTime_value);
        jsonObject.put("departmentId",departmentId_value);
        jsonObject.put("provinceId",provinceId_value);
        jsonObject.put("type",type_value);
        datasJSONArray = new JSONArray();
        datasJSONArray = getJsonArrayDataR();
        jsonObject.put("datas",datasJSONArray);
    }

    /**
     * 得到一个一定规范的json对象
     * @return
     */
    public static JSONObject getJsonObjectData(){
        JSONObject datas = new JSONObject();
        datas.put("id",RandomUtils.getRandomRange(10000,1));
        //传感器编号 420106NBWY0412
        datas.put("sensorno",provinceId_value+RandomUtils.getRandomNumbersAndUppercaseString(12));

        datas.put("collectdate",collectdate_value);
//            0.0527435594662907

        datas.put("xvalue",xvalue);
        datas.put("yvalue",yvalue);

        return datas;
    }

    public static JSONArray getJsonArrayData(){
        datasJSONArray.clear();
        datasJSONObject.clear();
        //int n = new Random().nextInt(99);
        int n=10;
        for(int i=0;i<n;i++){
            datasJSONObject = getJsonObjectData();
            datasJSONArray.add(datasJSONObject);
        }
        return datasJSONArray;
    }

    // 变体json数组
    public static JSONArray getJsonArrayDataR(){
        datasJSONArray.clear();
        datasJSONObject.clear();
        //int n = new Random().nextInt(99);
        int n=10;
        for(int i=0;i<n;i++){
            datasJSONObject = getJsonObjectData();
            datasJSONObject.put("yvalue",randomDouble);
            datasJSONArray.add(datasJSONObject);
        }
        return datasJSONArray;
    }

    public static void setData(){
        date = randomDate("2020-01-01","2022-01-31");
        sendTime_value = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        dataId_value = RandomUtils.getRandomNumbersAndLowercaseString(28);
        departmentId_value = RandomUtils.getRandomNumString(17)+RandomUtils.getRandomUppercaseString(1);
        provinceId_value = RandomUtils.getRandomNumString(2);
        type_value = RandomUtils.getRandomNumString(2);
        time_value = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        cata_id_value = RandomUtils.getRandomUppercaseString(2)+time_value+".00";
        o_time_value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        collectdate_value = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);

        xvalue = String.valueOf(random.nextDouble());
        yvalue = String.valueOf(random.nextDouble());
        randomDouble = new Random().nextDouble();
        randomInteger = new Random().nextInt();
        local_c_value = "千岛群岛";
        auto_flag_value = RandomUtils.getRandomUppercaseString(1);
    }

    static String randomString = RandomUtils.getRandomNumbersAndString(new Random().nextInt(10));
    public static void setRandomValue(){
        sendTime_value = randomString;
        dataId_value = randomString;
        departmentId_value = randomString;
        provinceId_value = randomString;
        type_value = randomString;
        time_value = randomString;
        cata_id_value = randomString;
        o_time_value = randomString;
        collectdate_value = randomString;
        xvalue = randomString;
        yvalue = randomString;
        randomDouble = new Random().nextDouble();
        randomInteger = new Random().nextInt();
        local_c_value = randomString;
        auto_flag_value = randomString;
    }

}


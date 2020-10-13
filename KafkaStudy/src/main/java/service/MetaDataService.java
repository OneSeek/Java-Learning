//package service;
//
//import com.alibaba.fastjson.JSONObject;
//import com.dtdream.datariver.util.httpclient.IMetadataManageRestfulService;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
///**
// * 调用元数据服务接口类
// */
//@Component
//@Slf4j
//public class MetaDataService {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger("business");
//
//    @Value("${global.dtexplorer.metadata:/metadataManage/api/restful/v1/dataView/tableColumnForDs}")
//    private String metaDataUrl;
//
//
//    @Autowired
//    private IMetadataManageRestfulService iMetadataManageRestfulService;
//
//
//    /**
//     * 调用元数据接口获取表详情
//     *
//     * @param tableGuid
//     * @return
//     */
//    public JSONObject getTableDb(String tableGuid) {
//        // 调用元数据接口，获取表的字段名和类型
//        JSONObject tableResult;
//        String json = "";
//        try {
//            String requestPath = metaDataUrl + tableGuid;
//            json = iMetadataManageRestfulService.get(requestPath);
//
//            if (json == null) {
//                log.warn("getMetaData[][]metaData is null[]guid={}", tableGuid);
//                return null;
//            }
//            JSONObject result = JSONObject.parseObject(json);
//            Integer code = result.getInteger("code");
//            String message = (String) (result.get("message"));
//            if (0 != code) {
//                log.error("getMetaData[]fail[]guid={},cause={}", tableGuid, message);
//                return null;
//            }
//            JSONObject data = result.getJSONObject("data");
//            tableResult = data.getJSONObject("table");
//        } catch (Exception e) {
//            log.error("getMetaData[]fail[]guid={},data={}", tableGuid, json, e);
//            return null;
//        }
//        return tableResult;
//    }
//
//}
//

//package pers.lqresier.dis.demo.es;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.ElasticsearchException;
//import org.elasticsearch.action.ActionListener;
//import org.elasticsearch.action.DocWriteResponse;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.support.replication.ReplicationResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentBuilder;
//import org.elasticsearch.common.xcontent.XContentFactory;
//import org.elasticsearch.common.xcontent.XContentType;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//
///**
// * Created with IDEA
// *
// * @author qiujiajin
// * @date 2020/7/19 19:09
// * Description
// */
//public class RestHighLevelClientDemoMain {
//
//    public static void main(String[] args) throws IOException {
//        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
//        IndexRequest request = new IndexRequest("posts", "doc", "1");
//        String jsonString = "{\"user\":\"mike\",\"age\":12,\"id\":15,\"time\":\"2019-02-15\"}";
//        request.source(jsonString, XContentType.JSON);
////        // 同步执行
//        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
//        // 异步执行
////        client.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
////            @Override
////            public void onResponse(IndexResponse indexResponse) {
////
////            }
////
////            @Override
////            public void onFailure(Exception e) {
////
////            }
////        });
//        if(indexResponse.getResult()== DocWriteResponse.Result.CREATED){
//            // 文档第一次被创建
//        }else if(indexResponse.getResult()== DocWriteResponse.Result.UPDATED){
//            // 文档被修改
//        }
//        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
//        if(shardInfo.getTotal()!=shardInfo.getSuccessful()){
//            // 总分片数和成功的分片数不一致，说明有失败的
//        }
//        if(shardInfo.getFailed()>0){
//            for(ReplicationResponse.ShardInfo.Failure failure:shardInfo.getFailures()){
//                //获取失败原因
//                String reason = failure.reason();
//            }
//        }
//        GetRequest getRequest = new GetRequest("posts", "doc", "1");
//        // 同步执行
//        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
//        // 异步执行
//        client.getAsync(getRequest, RequestOptions.DEFAULT, new ActionListener<GetResponse>() {
//            @Override
//            public void onResponse(GetResponse documentFields) {
//
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//
//            }
//        });
//        client.close();
//    }
//
//    /**
//     * 通过Map方式提供source
//     */
//    public static void sourceByMap() {
//        HashMap<String, Object> jsonMap = new HashMap<>();
//        jsonMap.put("user", "mike");
//        jsonMap.put("age", 12);
//        jsonMap.put("id", 15);
//        jsonMap.put("time", new Date());
//        IndexRequest indexRequest = new IndexRequest("posts", "doc", "1").source(jsonMap);
//    }
//
//    /**
//     * 通过XContentBuilder方式提供source
//     */
//    public static void sourceByXContentBuilder() throws IOException {
//        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
//        xContentBuilder.startObject();
//        xContentBuilder.field("user", "mike");
//        xContentBuilder.field("age", 12);
//        xContentBuilder.field("id", 15);
//        xContentBuilder.timeField("time", new Date());
//        xContentBuilder.endObject();
//        IndexRequest indexRequest = new IndexRequest("posts", "doc", "1").source(xContentBuilder);
//    }
//
//    /**
//     * 通过Object方式提供source
//     */
//    public static void sourceByObject() {
//        IndexRequest indexRequest = new IndexRequest("posts", "doc", "1").source("user", "mike", "age", 12, "id", 15, "time", new Date());
//    }
//}

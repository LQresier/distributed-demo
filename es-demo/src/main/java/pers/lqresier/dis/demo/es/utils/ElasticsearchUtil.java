package pers.lqresier.dis.demo.es.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/23 13:15
 * Description es操作工具包
 */
@Component
public class ElasticsearchUtil {
    @Resource
    private RestHighLevelClient client;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 创建索引
     */
    public boolean createIndex(String index) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
        logger.info("判断索引是否存在: {}", index);
        try {
            // 先判断索引是否存在，然后在执行添加指令
            if (!indexExits(index)) {
                CreateIndexResponse response = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
                return response.isAcknowledged();
            }
        } catch (IOException e) {
            logger.warn("添加索引失败 {}", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断索引库是否存在
     */
    public boolean indexExits(String index) {
        GetIndexRequest getIndexRequest = new GetIndexRequest(index);
        boolean exists = false;
        logger.info("判断索引是否存在: {}", index);
        try {
            exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            logger.warn("判断索引是否存在异常  {}", e.getMessage());
            e.printStackTrace();
        }
        return exists;
    }

    /**
     * 删除索引
     */
    public boolean deleteIndex(String index) {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        try {
            AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            if (delete.isAcknowledged()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建文档
     *
     * @param indexName 索引名
     * @param object    对象
     * @return
     */
    public boolean createDocument(String indexName, String id, Object object) {
        //组装数据
        //创建请求
        IndexRequest request = new IndexRequest(indexName);
        //设置规则
        request.timeout(TimeValue.timeValueSeconds(1));
        if (id != null && "".equals(id)) {
            //设置id
            request.id(id);
        }
        String s = JSONObject.toJSONString(object);
        //将数据放入请求对象里面
        request.source(s, XContentType.JSON);
        try {
            //发送请求  获取响应
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            RestStatus status = response.status();
            if (status.equals(RestStatus.OK) || status.equals(RestStatus.CREATED)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建文档
     *
     * @param indexName 索引名
     * @param object    对象
     * @return
     */
    public boolean createDocument(String indexName, Object object) {
        return createDocument(indexName, null, object);
    }


    /**
     * 判断文档是否存在
     *
     * @param indexName 索引名称
     * @param id        文档id
     * @return
     */
    public boolean documentIsExists(String indexName, String id) {
        GetRequest request = new GetRequest(indexName, id);
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        try {
            return client.exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            logger.warn("判断文档是否存在异常： {}", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 根据id获取文档
     *
     * @param indexName 索引名称
     * @param id        文档id
     * @return
     */
    public Map<String, Object> getDocument(String indexName, String id) {
        GetRequest request = new GetRequest(indexName, id);
        try {
            GetResponse documentFields = client.get(request, RequestOptions.DEFAULT);
            return documentFields.getSource();
        } catch (IOException e) {
            logger.warn("获取文档失败: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id获取文档
     *
     * @param indexName 索引名称
     * @param id        文档id
     * @return T
     */
    public <T> T getDocument(String indexName, String id, Class<T> clazz) {
        Map<String, Object> map = getDocument(indexName, id);
        if (map != null) {
            JSONObject jsonObject = new JSONObject(map);
            return jsonObject.toJavaObject(clazz);
        }
        return null;
    }

    /**
     * 根据文档id修改文档信息
     *
     * @param indexName 索引名称
     * @param id        文档id
     */
    public boolean updateDocument(String indexName, String id, Object object) {
        UpdateRequest request = new UpdateRequest(indexName, id);
        try {
            request.timeout(TimeValue.timeValueSeconds(1));
            request.doc(JSONObject.toJSONString(object), XContentType.JSON);
            UpdateResponse update = client.update(request, RequestOptions.DEFAULT);
            if (update.status().equals(RestStatus.OK)) {
                return true;
            }
        } catch (IOException e) {
            logger.warn("修改文档失败: {}", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 删除文档
     *
     * @param indexName 索引名称
     * @param id        文档id
     */
    public boolean deleteDocument(String indexName, String id) {
        DeleteRequest request = new DeleteRequest(indexName, id);
        try {
            DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);
            if (delete.status().equals(RestStatus.OK)) {
                return true;
            }
        } catch (IOException e) {
            logger.warn("修改文档失败: {}", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 批量插入文档数据
     *
     * @param indexName 索引
     * @param list      批量文档
     * @return
     */
    public boolean bulkDocument(String indexName, List<Object> list) {
        BulkRequest bulkRequest = new BulkRequest(indexName);
        bulkRequest.timeout(TimeValue.timeValueSeconds(2));
        try {
            list.forEach(item -> bulkRequest.add(new IndexRequest().source(item, XContentType.JSON)));
            BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            if (bulk.status().equals(RestStatus.OK)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

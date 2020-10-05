package pers.lqresier.dis.demo.es.service;

import org.apache.commons.beanutils.BeanMap;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.lqresier.dis.demo.es.properties.ElasticsearchProperties;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/22 16:48
 * Description
 */
public abstract class BaseElasticsearchService {
    @Resource
    private RestHighLevelClient client;
    @Resource
    private ElasticsearchProperties elasticsearchProperties;
    protected static final RequestOptions COMMON_OPTIONS;
    private Logger logger = LoggerFactory.getLogger(getClass());

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();

        // 默认缓冲限制为100MB，此处修改为30MB。
        builder.setHttpAsyncResponseConsumerFactory(new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

    /**
     * 创建索引
     * @param index
     */
    protected void createIndexRequest(String index) {
        try {
            CreateIndexRequest
                    request = new CreateIndexRequest(index);
            // Settings for this index
            request.settings(Settings.builder().put("index.number_of_shards", elasticsearchProperties.getIndex().getNumberOfShards()).put("index.number_of_replicas", elasticsearchProperties.getIndex().getNumberOfReplicas()));
            CreateIndexResponse createIndexResponse = client.indices().create(request, COMMON_OPTIONS);

            logger.info(" whether all of the nodes have acknowledged the request : {}", createIndexResponse.isAcknowledged());
            logger.info(" Indicates whether the requisite number of shard copies were started for each shard in the index before timing out :{}", createIndexResponse.isShardsAcknowledged());
        } catch (IOException e) {
            throw new ElasticsearchException("创建索引 {" + index + "} 失败");
        }
    }

    /**
     * 删除索引
     * @param index
     */
    protected void deleteIndexRequest(String index) {
        DeleteIndexRequest deleteIndexRequest = buildDeleteIndexRequest(index);
        try {
            client.indices().delete(deleteIndexRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            throw new ElasticsearchException("删除索引 {" + index + "} 失败");
        }
    }

    private static DeleteIndexRequest buildDeleteIndexRequest(String index) {
        return new DeleteIndexRequest(index);
    }

    protected static IndexRequest buildIndexRequest(String index, String id, Object object) {
        return new IndexRequest(index).id(id).source(new BeanMap(object), XContentType.JSON);
    }

    /**
     * 更新指定索引内的数据
     * @param index
     * @param id
     * @param object
     */
    protected void updateRequest(String index, String id, Object object) {
        try {
            UpdateRequest updateRequest = new UpdateRequest(index, id).doc(new BeanMap(object), XContentType.JSON);
            client.update(updateRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            throw new ElasticsearchException("更新索引 {" + index + "} 数据 {" + object + "} 失败");
        }
    }

    /**
     * 删除索引内的数据
     * @param index
     * @param id
     */
    protected void deleteRequest(String index, String id) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(index, id);
            client.delete(deleteRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            throw new ElasticsearchException("删除索引 {" + index + "} 数据id {" + id + "} 失败");
        }
    }

    /**
     *
     * @param index
     * @return
     */
    protected SearchResponse search(String index) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
}

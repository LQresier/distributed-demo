package pers.lqresier.dis.demo.es.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.query.QuerySearchRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lqresier.dis.demo.es.model.Member;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/23 23:58
 * Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchUtilTest {
    @Resource
    private ElasticsearchUtil elasticsearchUtil;
    @Resource
    private RestHighLevelClient client;

    @Test
    public void testCreateIndex(){
        if(elasticsearchUtil.createIndex("es-demo")){
            System.out.println("创建索引成功");
        }else{
            System.out.println("创建索引失败");
        }
    }

    @Test
    public void testIndexExits() {
        boolean indexExits = elasticsearchUtil.indexExits("es-demo");
        System.out.println(indexExits);
    }

    @Test
    public void testDeleteIndex(){
        boolean deleteIndex = elasticsearchUtil.deleteIndex("es-demo");
        System.out.println(deleteIndex);
    }

    @Test
    public void testCreateDocument(){
        Member member = Member.MemberBuilder.aMember().withId(2L).withUsername("lqresier666").withIsAvailable((byte)1).withCreateTime(new Date()).build();
        member.setRemark("关关雎鸠，在河之洲。窈窕淑女，君子好逑。\n" +
                "参差荇菜，左右流之。窈窕淑女，寤寐求之。\n" +
                "求之不得，寤寐思服。悠哉悠哉，辗转反侧。\n" +
                "参差荇菜，左右采之。窈窕淑女，琴瑟友之。\n" +
                "参差荇菜，左右芼之。窈窕淑女，钟鼓乐之。");
        boolean result = elasticsearchUtil.createDocument("es-demo",  member);
        System.out.println(result);
    }

    @Test
    public void testDocExist(){
        boolean exists = elasticsearchUtil.documentIsExists("es-demo", "1");
        System.out.println(exists);
    }

    @Test
    public void testDeleteDocument(){
        boolean deleteDocument = elasticsearchUtil.deleteDocument("es-demo", "1");
        System.out.println(deleteDocument);
    }

    @Test
    public void testGetDocument(){
        Member member = elasticsearchUtil.getDocument("es-demo", "1", Member.class);
        System.out.println(member);
    }

    @Test
    public void testUpdateDocument(){
        Member member = Member.MemberBuilder.aMember().withId(1L).withUsername("lqresier").withIsAvailable((byte)1).withCreateTime(new Date()).build();
        boolean result = elasticsearchUtil.updateDocument("es-demo", member.getId().toString(), member);
        System.out.println(result);
    }

    @Test
    public void testQueryDocument(){
        QuerySearchRequest searchRequest = new QuerySearchRequest();
    }
}
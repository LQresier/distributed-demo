//package pers.lqresier.dis.demo.es;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.query.IndexQuery;
//import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
//import org.springframework.test.context.junit4.SpringRunner;
//import pers.lqresier.dis.demo.es.model.Member;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * Created with IDEA
// *
// * @author qiujiajin
// * @date 2020/7/21 23:52
// * Description
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SpringESDemoTest {
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    public void testCreateIndex() {
//        elasticsearchTemplate.createIndex(Member.class);
//    }
//
//    /**
//     * 添加单条数据
//     */
//    @Test
//    public void testAdd() {
//        Member member = new Member();
//        member.setId(1L);
//        member.setUsername("lqresier");
//        member.setCreateTime(new Date());
//        member.setUpdateTime(new Date());
//        member.setIsAvailable((byte) 1);
//        IndexQuery indexQuery = new IndexQueryBuilder().withIndexName("disDemo").withType(Member.class.getName()).withId(member.getId().toString()).withObject(member).build();
//        elasticsearchTemplate.index(indexQuery);
//    }
//
//    /**
//     * 添加多条数据
//     */
//    public void testBatchAdd() {
//        List<IndexQuery> indexQueries = new ArrayList<>();
//        Member member1 = Member.MemberBuilder.aMember().withId(1L).withCreateTime(new Date()).withUpdateTime(new Date()).withUsername("lqresier").withIsAvailable((byte)1).build();
//        indexQueries.add(new IndexQueryBuilder().withId(member1.getId().toString()).withObject(member1).build());
//        Member member2 = Member.MemberBuilder.aMember().withId(2L).withCreateTime(new Date()).withUpdateTime(new Date()).withUsername("yi").withIsAvailable((byte)1).build();
//        indexQueries.add(new IndexQueryBuilder().withId(member2.getId().toString()).withObject(member2).build());
//        elasticsearchTemplate.bulkIndex(indexQueries);
//    }
//
//    public void testQuery(){
//
//    }
//}

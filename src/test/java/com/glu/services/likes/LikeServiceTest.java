package com.glu.services.likes;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnit;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest({
        "spring.data.cassandra.port=9142",
        "spring.data.cassandra.keyspace-name=testkeyspace"
})
@EnableAutoConfiguration
@TestExecutionListeners({
        CassandraUnitDependencyInjectionTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class
})
@CassandraDataSet(value = "setup.cql", keyspace = "testkeyspace")
@CassandraUnit
public class LikeServiceTest {
    @Autowired
    private LikeService likeService;

    @Test
    public void noLikes() throws Exception {
        assertEquals(likeService.getLikes("noLikesUser"), 0);
    }

    @Test
    public void singleLike() throws Exception {
        likeService.like("oneLikeUser");

        assertEquals(likeService.getLikes("oneLikeUser"), 1);
    }

    @Test
    public void multipleLikesToOne() throws Exception {
        for (int i = 0; i < 10; i++) {
            likeService.like("multipleLikeUser");

            assertEquals(likeService.getLikes("multipleLikeUser"), i + 1);
        }
    }

    @Test
    public void likesToMultipleUsers() throws Exception {
        likeService.like("firstUser");
        likeService.like("secondUser");
        likeService.like("firstUser");
        likeService.like("firstUser");

        assertEquals(likeService.getLikes("secondUser"), 1);
        assertEquals(likeService.getLikes("firstUser"), 3);
        assertEquals(likeService.getLikes("thirdUser"), 0);
    }
}

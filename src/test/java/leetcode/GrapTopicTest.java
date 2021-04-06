package leetcode;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/4/6 09:55
 */
public class GrapTopicTest {

    GrapTopic grapTopic;

    @BeforeMethod
    public void setUp() {
        grapTopic = new GrapTopic();
    }

    @Test
    public void testNumIslands() {
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        int numIslands = grapTopic.numIslands(grid);
        Assert.assertEquals(3, numIslands);
    }

}
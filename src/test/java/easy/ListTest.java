package easy;

import leetcode.model.InitList;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/19 15:28
 */
public class ListTest {

    InitList initList;
    List list;

    @BeforeMethod
    public void setUp() {
        initList = new InitList();
        list = new List();
    }

    @Test
    public void testHasCycle() {
        boolean hasCycle = List.hasCycle(initList.init141());
        Assert.assertEquals(hasCycle, true);
    }
}
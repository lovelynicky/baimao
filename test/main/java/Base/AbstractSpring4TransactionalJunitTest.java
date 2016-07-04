package main.java.Base;

import com.rocky.springcontext.Context;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 带有事务的单元测试抽象基类
 * Created by liluoqi on 16/6/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = Context.class)
@ContextConfiguration(locations = "classpath*:ApplicationContext.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class AbstractSpring4TransactionalJunitTest {
}

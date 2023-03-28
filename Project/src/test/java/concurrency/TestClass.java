package concurrency;

import static org.junit.Assert.assertFalse;

import org.springframework.transaction.annotation.Transactional;

import com.hrilke.project.beans.UserBean;

@Transactional
public class TestClass {
	UserBean loginUserBean = new UserBean();

	@org.junit.Test
	public void test2() {
		assertFalse(loginUserBean.isUserLogin());
//		assertTrue(loginUserBean.isUserLogin());
	}

}

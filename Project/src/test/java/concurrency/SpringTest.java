package concurrency;

import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hrilke.project.config.RootAppContext;

public class SpringTest {

	@Test
	void test1() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(RootAppContext.class);

		// 스프링 빈의 바이트 조작 : class
		// com.hrilke.project.config.RootAppContext$$EnhancerBySpringCGLIB$$80385cb0
		// 스프링이 RootAppContext클래스를 상속받은 임의의 다른클래스를 만들어서 그걸 컨테이너에 등록!
		RootAppContext bean = ac.getBean(RootAppContext.class);
		System.out.println(bean.getClass());

		// 스프링 컨테이너에 등록되지 않은 클래스는 : class concurrency.SpringTest
		SpringTest springTest = new SpringTest();
		System.out.println(springTest.getClass());

		// 싱글톤 객체 확인
		Object bean1 = ac.getBean("loginUserBean");
		Object bean2 = ac.getBean("loginUserBean");
		assertSame(bean2, bean1);
		
		ac.close();
	}

}

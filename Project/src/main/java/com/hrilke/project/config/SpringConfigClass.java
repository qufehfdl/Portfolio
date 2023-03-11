package com.hrilke.project.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
 
public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer {
	// DispatcherServlet에 매핑할 요청 주소를 셋팅
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	// Spring MVC 프로젝트 설정을 위한 클래스를 지정
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ServletAppContext.class };
	}

	// 프로젝트에서 사용할 Bean들을 정의기 위한 클래스를 지정
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootAppContext.class };
	}

	// 파라미터 인코딩 필터 설정
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] { encodingFilter };
	}

	// 파일 업로드
	@Override
	protected void customizeRegistration(Dynamic registration) {
		super.customizeRegistration(registration);

		// 매개변수 순서의 의미
		// 클라이언트가 보낸 파일데이터를 저장해놓는 임시파일의 경로 : null값이면 아파치톰캣에서 정한 임시폴더로 세팅됨
		// 업로드된 파일의 최대 용량
		// 파일데이터를 포함한 전체 요청 정보의 용량(파일데이터 + 사용자가 입력한 데이터)
		// 파일의 임계값 : 0으로 주게되면 알아서 저장하겠다 란 뜻
		MultipartConfigElement config1 = new MultipartConfigElement(null, 52428800, 524288000, 0);
		registration.setMultipartConfig(config1);
	}
}

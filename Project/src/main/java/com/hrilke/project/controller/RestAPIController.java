package com.hrilke.project.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hrilke.project.beans.forecast.ForeCastBean;
import com.hrilke.project.beans.weather.WeatherBean;
import com.hrilke.project.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestAPIController {

	private final UserService userService;

	// 아이디 중복확인
	@GetMapping("/user/checkUserIdExist/{user_id}")
	public String checkUserIdExist(@PathVariable String user_id) {
		boolean checkUserIdExist = userService.checkUserIdExist(user_id);
		return checkUserIdExist + "";
	}

	// 어제 날씨 정보
	@GetMapping("weather/yesterDay")
	public WeatherBean abc(Model model) {
		// 어제 날짜 구하기
		SimpleDateFormat yesterdaysdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterdayDate = yesterdaysdf.format(cal.getTime());
		// 인코딩 인증키
		String API_KEY = "3dcUCKZJXGljuEQDtWBezwCMqWu010u0uiPp84mzLQo3oMVbMgIVMM21hjVPMp8vmFPZYAZ2M8O8cpOLIZvEzA%3D%3D";
		String API_URL = "http://apis.data.go.kr/1360000/AsosDalyInfoService/getWthrDataList?numOfRows=10&pageNo=1&dateCd=DAY&startDt="
				+ yesterdayDate + "&endDt=" + yesterdayDate + "&stnIds=108&dataCd=ASOS&dataType=JSON&serviceKey="
				+ API_KEY;
		RestTemplate restTemplate = new RestTemplate();
		URI uri = null;
		try {
			uri = new URI(API_URL);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// 어제 날씨
		WeatherBean weatherBean = restTemplate.getForObject(uri, WeatherBean.class);
		return weatherBean;
	}

	// 날씨 예보
	@GetMapping("weather/foreCast")
	public ForeCastBean aaa(Model model) {

		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();

		// 어제 오후 6시까지의 시간 구하기
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 18);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date yesterday6pm = calendar.getTime();

		// 어제 오후 6시 이전이면 어제 날짜, 그렇지 않으면 오늘 날짜 사용
		String pattern = "yyyyMMddHHmm";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateStr;
		if (now.before(yesterday6pm)) {
			calendar.add(Calendar.DATE, 1);
			dateStr = sdf.format(calendar.getTime());
			dateStr = dateStr.substring(0, 8) + "1800"; // hhss를 항상 1800으로 설정
		} else {
			dateStr = sdf.format(now);
			dateStr = dateStr.substring(0, 8) + "0600"; // hhss를 항상 0600으로 설정
		}

		// 인코딩 인증키
		String API_KEY = "3dcUCKZJXGljuEQDtWBezwCMqWu010u0uiPp84mzLQo3oMVbMgIVMM21hjVPMp8vmFPZYAZ2M8O8cpOLIZvEzA%3D%3D";
		String API_URL = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst?numOfRows=10&pageNo=1&regId=11B00000&tmFc="
				+ dateStr + "&dataType=json&serviceKey=" + API_KEY;
		RestTemplate restTemplate = new RestTemplate();
		URI uri = null;
		try {
			uri = new URI(API_URL);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		ForeCastBean foreCastBean = restTemplate.getForObject(uri, ForeCastBean.class);
		return foreCastBean;
	}
}

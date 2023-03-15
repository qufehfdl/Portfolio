package concurrency;

import org.assertj.core.api.Assertions;

public class Test {
	private static long count = 0;

	@org.junit.jupiter.api.Test
	void threadNotSafe() throws Exception {
		int maxCnt = 10;

		for (int i = 0; i < maxCnt; i++) {
			new Thread(() -> {
				count++;
				System.out.println(count);
			}).start();
		}

		Thread.sleep(100); // 모든 스레드가 종료될 때까지 잠깐 대기
		Assertions.assertThat(count).isEqualTo(maxCnt);
		
	}
}

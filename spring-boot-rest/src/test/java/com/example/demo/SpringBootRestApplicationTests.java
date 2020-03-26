package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
class SpringBootRestApplicationTests extends SpringBootRestApplication {

	@Test
	void MainTest() {
		String [] args = new String[0];
		main(args);
	}

}

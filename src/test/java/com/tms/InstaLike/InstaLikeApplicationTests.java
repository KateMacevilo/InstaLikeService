package com.tms.InstaLike;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.AntPathMatcher;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class InstaLikeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	 void testPathMatcher() {
		final AntPathMatcher pathMatcher = new AntPathMatcher();
		assertTrue(pathMatcher.match("/images/*", "/images/heart.png"));
	}

}

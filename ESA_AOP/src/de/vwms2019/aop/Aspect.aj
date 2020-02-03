package de.vwms2019.aop;

import java.time.Duration;
import java.time.Instant;

public aspect Aspect {
	Instant start;
	Instant end;
	
	pointcut logMethod() : call(* Main.*(..));
	
	before() : logMethod() {
		start = Instant.now();
		System.out.println("start method: " + start);
	};

	after() : logMethod() {
		end = Instant.now();
		System.out.println("end method: " + end);

		System.out.println("method duration: " + Duration.between(start, end));
	};
}

package com.hrilke.project.controller.concurrent;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ConcurrentHashMapController {

	private final ConcurrentHashMap<String, String> concurrentHashMap;

	@GetMapping("/myConcurrent/{str}")
	public String myConcurrent(@PathVariable String str ) {
		return "ok";
	}

}

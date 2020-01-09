package com.github.marceloleite2604.isstracker.site.controller;

import com.github.marceloleite2604.isstracker.site.service.MapService;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")
public class MapController {
	
	@Inject
	private MapService mapService;
	
	@GetMapping
	public void get(HttpServletResponse httpServletResponse) {
		mapService.get(httpServletResponse);
	}
}

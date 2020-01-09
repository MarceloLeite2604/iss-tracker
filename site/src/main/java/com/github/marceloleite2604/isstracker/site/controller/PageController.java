package com.github.marceloleite2604.isstracker.site.controller;

import com.github.marceloleite2604.isstracker.site.service.PageService;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

	@Inject
	private PageService pageService;

	@GetMapping
	public String get(Model model) {
		return pageService.get(model);
	}
}

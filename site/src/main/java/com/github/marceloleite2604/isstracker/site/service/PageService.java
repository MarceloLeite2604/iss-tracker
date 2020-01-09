package com.github.marceloleite2604.isstracker.site.service;

import com.github.marceloleite2604.isstracker.site.bo.IssPositionBO;
import com.github.marceloleite2604.isstracker.site.model.db.IssPosition;
import com.github.marceloleite2604.isstracker.site.model.dto.IssInformationDTO;
import com.github.marceloleite2604.isstracker.site.model.mapper.ListIssPositionToIssInformationDtoMapper;
import com.github.marceloleite2604.isstracker.site.util.thymeleaf.Attributes;
import com.github.marceloleite2604.isstracker.site.util.thymeleaf.TemplateFiles;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class PageService {

	@Inject
	private IssPositionBO issPositionBO;

	@Inject
	private ListIssPositionToIssInformationDtoMapper listIssPositionToIssInformationDtoMapper;

	public String get(Model model) {

		addIssInformationOnModel(model);
		return TemplateFiles.INDEX;
	}

	private void addIssInformationOnModel(Model model) {
		List<IssPosition> issPositions = issPositionBO.findLastHour();
		IssInformationDTO issInformation = listIssPositionToIssInformationDtoMapper
				.map(issPositions);

		model.addAttribute(Attributes.ISS_INFORMATION, issInformation);
	}

}

package com.github.marceloleite2604.isstracker.inquisitor.util;

import com.github.marceloleite2604.blimp.Blimp;
import com.github.marceloleite2604.isstracker.inquisitor.exception.InquisitorHttpRequestException;
import com.github.marceloleite2604.isstracker.inquisitor.model.ExchangeRequest;
import com.github.marceloleite2604.isstracker.inquisitor.util.message.ErrorMessage;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateUtil.class);

	@Inject
	private Blimp blimp;

	public <I, O> ResponseEntity<O> exchange(RestTemplate restTemplate, ExchangeRequest<I, O> exchangeRequest) throws InquisitorHttpRequestException {
		HttpEntity<I> httpEntity = createHttpEntity(exchangeRequest.getInputObject(),
				exchangeRequest.getInputMediaType(), exchangeRequest.getAcceptedMediaTypes(),
				exchangeRequest.getHeaderValues());

		try {
			return restTemplate.exchange(exchangeRequest.getUrl(), exchangeRequest.getHttpMethod(),
					httpEntity, exchangeRequest.getOutputObjectType());
		} catch (HttpServerErrorException exception) {

			String responseBody = exception.getResponseBodyAsString();
			HttpStatus httpStatus = exception.getStatusCode();
			String logMessage = blimp.getMessage(ErrorMessage.STATUS_CODE_MESSAGE_EXCHANGING_ERROR,
					httpStatus, responseBody);
			LOGGER.error(logMessage);

			throw new InquisitorHttpRequestException(exception, ErrorMessage.ERROR_EXCHANGING,
					exchangeRequest);
		} catch (Exception exception) {
			throw new InquisitorHttpRequestException(exception, ErrorMessage.ERROR_EXCHANGING,
					exchangeRequest);
		}
	}

	private <I> HttpEntity<I> createHttpEntity(I object, MediaType inputMediaType,
			List<MediaType> acceptedMediaTypes, Map<String, String> headerValues) {
		return new HttpEntity<>(object,
				criarHttpHeader(inputMediaType, acceptedMediaTypes, headerValues));
	}

	private HttpHeaders criarHttpHeader(MediaType mediaType, List<MediaType> acceptedMediaTypes,
			Map<String, String> headerValues) {
		HttpHeaders httpHeaders = new HttpHeaders();
		Optional.ofNullable(mediaType)
				.ifPresent(httpHeaders::setContentType);
		Optional.ofNullable(acceptedMediaTypes)
				.ifPresent(httpHeaders::setAccept);
		Optional.ofNullable(headerValues)
				.ifPresent(httpHeaders::setAll);
		return httpHeaders;
	}
}
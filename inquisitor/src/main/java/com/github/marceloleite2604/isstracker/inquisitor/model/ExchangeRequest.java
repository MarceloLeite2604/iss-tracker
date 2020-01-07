package com.github.marceloleite2604.isstracker.inquisitor.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

public class ExchangeRequest<I, O> {

	private String url;

	private List<NameValuePair> parameters;

	private I inputObject;

	private MediaType inputMediaType;

	private List<MediaType> acceptedMediaTypes;

	private HttpMethod httpMethod;

	private ParameterizedTypeReference<I> inputObjectType;

	private ParameterizedTypeReference<O> outputObjectType;

	private Map<String, String> headerValues;

	private ExchangeRequest(Builder<I, O> builder) {
		this.url = builder.url;
		this.parameters = builder.parameters;
		this.inputObject = builder.inputObject;
		this.inputMediaType = builder.inputMediaType;
		this.acceptedMediaTypes = builder.acceptedMediaTypes;
		this.httpMethod = builder.httpMethod;
		this.outputObjectType = builder.outputObjectType;
		this.inputObjectType = builder.inputObjectType;
		this.headerValues = builder.headerValues;
	}

	public String getUrl() {
		return url;
	}

	public List<NameValuePair> getParameters() {
		return parameters;
	}

	public I getInputObject() {
		return inputObject;
	}

	public MediaType getInputMediaType() {
		return inputMediaType;
	}

	public List<MediaType> getAcceptedMediaTypes() {
		return acceptedMediaTypes;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public ParameterizedTypeReference<I> getInputObjectType() {
		return inputObjectType;
	}

	public ParameterizedTypeReference<O> getOutputObjectType() {
		return outputObjectType;
	}

	public Map<String, String> getHeaderValues() {
		return headerValues;
	}

	@Override
	public String toString() {
		return "ExchangeRequest [url=" + url + ", parameters=" + parameters + ", inputObject="
				+ inputObject + ", inputMediaType=" + inputMediaType + ", acceptedMediaTypes="
				+ acceptedMediaTypes + ", httpMethod=" + httpMethod + ", inputObjectType="
				+ inputObjectType + ", outputObjectType=" + outputObjectType + ", headerValues="
				+ headerValues + "]";
	}

	public static Builder<Object, Object> builder() {
		return new Builder<>(new ParameterizedTypeReference<Object>() {
		}, new ParameterizedTypeReference<Object>() {
		});
	}

	public static <I, O> Builder<I, O> builder(ParameterizedTypeReference<I> inputObjectType,
			ParameterizedTypeReference<O> outputObjectType) {
		return new Builder<>(inputObjectType, outputObjectType);
	}

	public static final class Builder<I, O> {

		private String url;

		private List<NameValuePair> parameters = new LinkedList<>();

		private I inputObject;

		private MediaType inputMediaType = MediaType.TEXT_PLAIN;

		private List<MediaType> acceptedMediaTypes = Arrays.asList(MediaType.APPLICATION_JSON);

		private HttpMethod httpMethod = HttpMethod.GET;

		private ParameterizedTypeReference<I> inputObjectType;

		private ParameterizedTypeReference<O> outputObjectType;

		private Map<String, String> headerValues = new HashMap<>();

		public Builder(ParameterizedTypeReference<I> inputObjectType,
				ParameterizedTypeReference<O> outputObjectType) {
			this.inputObjectType = inputObjectType;
			this.outputObjectType = outputObjectType;
		}

		public Builder<I, O> url(String url) {
			this.url = url;
			return this;
		}

		public Builder<I, O> headerValues(Map<String, String> headerValues) {
			this.headerValues = headerValues;
			return this;
		}

		public Builder<I, O> parameters(List<NameValuePair> parameters) {
			this.parameters = parameters;
			return this;
		}

		public Builder<I, O> parameters(NameValuePair... parameters) {
			this.parameters = Arrays.asList(parameters);
			return this;
		}

		public Builder<I, O> inputObject(I inputObject) {
			this.inputObject = inputObject;
			return this;
		}

		public Builder<I, O> inputMediaType(MediaType inputMediaType) {
			this.inputMediaType = inputMediaType;
			return this;
		}

		public Builder<I, O> acceptedMediaTypes(List<MediaType> acceptedMediaTypes) {
			this.acceptedMediaTypes = acceptedMediaTypes;
			return this;
		}

		public Builder<I, O> acceptedMediaType(MediaType acceptedMediaType) {
			this.acceptedMediaTypes = Arrays.asList(acceptedMediaType);
			return this;
		}

		public Builder<I, O> httpMethod(HttpMethod httpMethod) {
			this.httpMethod = httpMethod;
			return this;
		}

		public ExchangeRequest<I, O> build() {
			return new ExchangeRequest<>(this);
		}
	}

}

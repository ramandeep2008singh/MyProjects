package com.practice.StepDefinitions;

import static org.testng.Assert.assertNull;

import java.io.IOException;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;

/**
 *
 */
public class ApiStepDefinition {

	@Test
	public void restTest() throws ClientProtocolException, IOException {
		// Given
		// String name = RandomStringUtils.randomAlphabetic(8);
		HttpUriRequest request = new HttpGet("http://dummy.restapiexample.com/api/v1/employees");

		// When
		CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		// Then
		// assertThat(httpResponse.getStatusLine().getStatusCode(),
		// equalTo(HttpStatus.SC_NOT_FOUND));
		JsonNode jsonNode = new ObjectMapper().readTree(httpResponse.getEntity().getContent());
		//String jsonResponse = EntityUtils.toString(httpResponse.getEntity());
		JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance();

		JsonSchema schema = schemaFactory.getSchema(getClass().getResourceAsStream("/other/event-schema.json"));
		// Validate the JSON against the schema.
		Set<ValidationMessage> validationErrors = schema.validate(jsonNode);
		assertNull(validationErrors);
	}
}

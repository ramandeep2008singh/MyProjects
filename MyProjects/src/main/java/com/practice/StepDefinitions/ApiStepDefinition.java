package com.practice.StepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.practice.util.Constants;



/**
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiStepDefinition {

	
	private static String CREATED_EMPLOYEE_ID;
	private static  String CREATED_EMPLOYEE_NAME="test_gk_unique"+generateRandomNos();
	/**
	 * Method to test the scenario:
	 * "Get all employees and validate the schema returned in the response"
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void atestSchemaTest() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet(Constants.GET_ALL_EMPLOYEES);
		CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		JsonNode jsonNode = new ObjectMapper().readTree(httpResponse.getEntity().getContent());
		JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance();
		JsonSchema schema = schemaFactory.getSchema(getClass().getResourceAsStream("/other/event-schema.json"));
		// Validate the JSON against the schema.
		Set<ValidationMessage> validationErrors = schema.validate(jsonNode);
		assertTrue(validationErrors.size()==0);
		
	}
	
	/**
	 * Method to test the scenario:
	 * "Post a new employee and validate the response"
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void btestEmployeeCreation() throws ClientProtocolException, IOException {
		
		HttpPost request = new HttpPost(Constants.CREATE_URL);
		StringEntity body = new StringEntity("{\"name\":\"" + CREATED_EMPLOYEE_NAME +"\",\"salary\":\"123\",\"age\":\"23\"}");
		request.setEntity(body);
		CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		JsonNode jsonNode = new ObjectMapper().readTree(httpResponse.getEntity().getContent());
		CREATED_EMPLOYEE_ID = jsonNode.get("id").asText();
		assertEquals(200, httpResponse.getStatusLine().getStatusCode());
		assertNotNull(CREATED_EMPLOYEE_ID);
				
	}
	
	/**
	 * Method to check the scenario:
	 * "Get the newly created employee and validate the response"
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void cTestNewEmployeeData() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet(Constants.GET_EMPLOYEE+CREATED_EMPLOYEE_ID);
		CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		JsonNode jsonNode = new ObjectMapper().readTree(httpResponse.getEntity().getContent());
		/* Get the newly created employee and validate the response */
		assertEquals(CREATED_EMPLOYEE_NAME, jsonNode.get("employee_name").asText());
		assertEquals("123", jsonNode.get("employee_salary").asText());
		assertEquals("23", jsonNode.get("employee_age").asText());
	}
	
	/**
	 * Method to check the scenario:
	 * "Update the newly created employee"
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Test
	public void dUpdateNewlyCreatedEmployee() throws ClientProtocolException, IOException {
		
		HttpPut request = new HttpPut(Constants.UPDATE_URL+CREATED_EMPLOYEE_ID);
		int expectedSalary = generateRandomNos();
		StringEntity body = new StringEntity("{\"name\":\"" + CREATED_EMPLOYEE_NAME +"\",\"salary\":\""+expectedSalary+"\",\"age\":\"23\"}");
		request.setEntity(body);
		CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		JsonNode jsonNode = new ObjectMapper().readTree(httpResponse.getEntity().getContent());
		int actualSalary = Integer.parseInt(jsonNode.get("salary").asText());
		assertEquals(200, httpResponse.getStatusLine().getStatusCode());
		assertEquals(expectedSalary, actualSalary);
		
	}
	
	/**
	 * Method to check:
	 * "Delete the newly created employee"
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Test
	public void eTestEmployeeDeletion() throws ClientProtocolException, IOException {
		
		HttpDelete request = new HttpDelete(Constants.DELETE_URL+CREATED_EMPLOYEE_ID);
		CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		JsonNode jsonNode = new ObjectMapper().readTree(httpResponse.getEntity().getContent());
		assertEquals(200, httpResponse.getStatusLine().getStatusCode());
		assertEquals("successfully! deleted Records", jsonNode.get("success").get("text").asText());
	}
	
	
	public static int generateRandomNos() {
		
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(999);
		return randomInt;
		
	}
}

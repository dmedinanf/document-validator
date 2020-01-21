package com.example.validator;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidatorTest {

    @Autowired
    private TestRestTemplate request;

    private static final String INVALID_CPF = "02512170003";
    private static final String VALID_CPF = "02512170004";
    private static final String INVALID_CNPJ = "54366367000151";
    private static final String VALID_CNPJ = "54366367000152";

    @Test
    public void shouldReturnErrorWhenDocumentCPFIsInvalid() throws Exception{
        DataTest dataTest = new DataTest();
        dataTest.setDocument(INVALID_CPF);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DataTest> test = new HttpEntity<>(dataTest, headers);

        ResponseEntity<?> responseEntity = request.exchange("/validate", HttpMethod.POST, test, getClass());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturnErrorWhenDocumentCNPJIsInvalid() throws Exception{
        DataTest dataTest = new DataTest();
        dataTest.setDocument(INVALID_CNPJ);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DataTest> test = new HttpEntity<>(dataTest, headers);

        ResponseEntity<?> responseEntity = request.exchange("/validate", HttpMethod.POST, test, getClass());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturnOKWhenDocumentCPFIsValid() throws Exception{
        DataTest dataTest = new DataTest();
        dataTest.setDocument(VALID_CPF);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DataTest> test = new HttpEntity<>(dataTest, headers);

        ResponseEntity<?> responseEntity = request.exchange("/validate", HttpMethod.POST, test, getClass());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnOKWhenDocumentCNPJIsValid() throws Exception{
        DataTest dataTest = new DataTest();
        dataTest.setDocument(VALID_CNPJ);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DataTest> test = new HttpEntity<>(dataTest, headers);

        ResponseEntity<?> responseEntity = request.exchange("/validate", HttpMethod.POST, test, getClass());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnOKWhenDocumentIsNull() throws Exception{
        DataTest dataTest = new DataTest();
        dataTest.setDocument(null);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DataTest> test = new HttpEntity<>(dataTest, headers);

        ResponseEntity<?> responseEntity = request.exchange("/validate", HttpMethod.POST, test, getClass());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

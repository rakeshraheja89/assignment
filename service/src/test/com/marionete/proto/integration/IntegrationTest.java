

package com.marionete.proto.integration;

import com.marionete.proto.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    void testInValidUserAccount() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<UserAccount> response = restTemplate.exchange(
                createURLWithPort("/marionete/userAccount?username=1&password=2"), HttpMethod.POST, entity, UserAccount.class);
        int actual = response.getStatusCodeValue();
        Assertions.assertEquals(500,actual);
    }

    @Test
    @Disabled
    void testValidUserAccount() throws Exception {
            /* start scala service
                UserInfoMock.start()
                AccountInfoMock.start()
            */
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<UserAccount> response = restTemplate.exchange(
                createURLWithPort("/marionete/userAccount?username=1&password=2"), HttpMethod.POST, entity, UserAccount.class);
        int actual = response.getStatusCodeValue();
        Assertions.assertEquals("200",actual);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}



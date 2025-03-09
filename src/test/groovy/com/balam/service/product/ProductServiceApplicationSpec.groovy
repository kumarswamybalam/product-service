package com.balam.service.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationSpec extends Specification {

    @Value('${spring.application.name}')
    private String appName

    @Autowired
    private TestRestTemplate restTemplate

    def 'has heartbeat' () {
        when:
        ResponseEntity<Map<String, String>> response = restTemplate.getForEntity("/heartbeat", Map)

        then:
        response.statusCode == HttpStatus.OK
    }

    def "should check database health from /health-check"() {
        when:
        ResponseEntity<Map> response = restTemplate.getForEntity("/health-check", Map)

        then:
        response.statusCode == HttpStatus.OK
    }
}

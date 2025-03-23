package com.balam.service.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:unit-test.properties")
@ActiveProfiles("local")
class ProductServiceApplicationSpec extends Specification {

    @Value('${spring.application.name}')
    private String appName

    @Autowired
    private TestRestTemplate restTemplate

    @Autowired
    private ApplicationContext context

    def "verify Spring context and beans are loaded"() {
        expect:
        context != null
        restTemplate != null
    }

    def "debug context beans"() {
        when:
        def beanNames = context != null ? context.getBeanDefinitionNames() : []
        println(beanNames.join("\n"))

        then:
        beanNames.size() > 0 // Ensure at least some beans are loaded
    }

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

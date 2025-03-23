package com.balam.service.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ProductServiceApplicationSpec extends BaseSpecification {

    @Value('${spring.application.name}')
    private String appName

    @Value('${spring.datasource.url}')
    private String dbUrl

    @Autowired
    private TestRestTemplate restTemplate

    def 'has heartbeat' () {
        when:
        ResponseEntity<Map<String, String>> response = restTemplate.getForEntity("/heartbeat", Map) as ResponseEntity<Map<String, String>>

        then:
        response.statusCode == HttpStatus.OK
        response.body.get('appName') == appName
        response.body.get('status') == 'running'
        response.body.get('environment') == 'local'
    }

    def "should check database health from /health-check"() {
        when:
        ResponseEntity<Map> response = restTemplate.getForEntity("/health-check", Map)

        then:
        response.statusCode == HttpStatus.OK
        response.body.get('database').getAt('provided') == dbUrl
        response.body.get('database').getAt('status') == 'success'
    }
}

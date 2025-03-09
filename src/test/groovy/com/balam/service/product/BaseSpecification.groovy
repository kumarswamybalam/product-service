package com.balam.service.product

import org.apache.commons.io.FileUtils
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

import java.nio.charset.Charset

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(["local"])
@TestPropertySource("classpath:unit-test.properties")
class BaseSpecification  extends Specification {

    static String readFile(String path){
        return FileUtils.readFileToString(new File(path), Charset.defaultCharset())
    }

    def 'sample' () {
        given:
        String x = "hello"

        when:
        String y = x.reverse()

        then:
        y == "olleh"
    }

}

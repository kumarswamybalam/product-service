package com.balam.service.product

import org.apache.commons.io.FileUtils
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

import java.nio.charset.Charset

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:unit-test.properties")
@ActiveProfiles("local")
class BaseSpecification  extends Specification {

    static String readFile(String path){
        return FileUtils.readFileToString(new File(path), Charset.defaultCharset())
    }
}

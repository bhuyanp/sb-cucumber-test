package com.example.sbtest;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.example.sbtest.cucumber")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@CucumberContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {SbCucumberApplication.class}
)
@EnableFeignClients
public class RunCucumberTest {


}

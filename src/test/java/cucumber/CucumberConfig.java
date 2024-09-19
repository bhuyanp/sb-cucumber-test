package cucumber;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CucumberConfig {
    @Before()
    void beforeProductTestScenario(){
        log.info("beforeProductTestScenario");
    }
    @BeforeStep
    void beforeProductTestStep(){

        log.info("beforeProductTestStep");
    }
}

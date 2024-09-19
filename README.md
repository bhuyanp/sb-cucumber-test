## SpringBoot and Cucumber
Cucumber is an open source BDD framework. Unfortunately there are not many first party documentation on 
how to make it seamlessly work with SpringBoot. 

This sample project will save you a lot of time and
help you get started with your SpringBoot and Cucumber journey.

### BOM
- Java 21
- SpringBoot 3.3.3
- Cucumber 7.18.1
- JUnit 5.10.3

### Execute
Use below command to run all the test
```
mvn clean test
```
Use below command to run specific scenario or feature
```
mvn clean test -Dcucumber.filter.tags="@PRODUCT"
mvn clean test -Dcucumber.filter.tags="@GET_PRODUCT" 
```


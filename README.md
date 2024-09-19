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
To run all the tests
```
mvn clean test
```
To run all product tests
```
mvn clean test -Dcucumber.filter.tags="@Product"
```
To run all add product tests
```
mvn clean test -Dcucumber.filter.tags="@AddProduct"
```

To run all negative test cases
```
mvn clean test -Dcucumber.filter.tags="@Negative"
```

To run all positive product test cases
```
mvn clean test -Dcucumber.filter.tags="@Product and not @Negative"
```


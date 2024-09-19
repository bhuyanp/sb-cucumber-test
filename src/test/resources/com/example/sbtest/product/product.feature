@Product
Feature: Product Service
  Users should be able to fetch, search, get all and add products using this feature

  @GetProduct
  Scenario: Fetch product by valid product id should return 200
    Given product with id 1 exists
    When user fetches the product
    Then product has id 1
    And response status is 200

  @GetProduct @Negative
  Scenario: Fetch product by invalid product id should return 404
    Given product with id 100 does not exist
    When user fetches the product
    Then response status is 404

  @AddProduct
  Scenario: Add new product with title and price should return 201
    When user adds new product with title 'Test Product New' and price 45.45
    Then response status is 201
    And product title is 'Test Product New'
    And product price is 45.45

  @AddProduct @Negative
  Scenario: Add new product with without title or price should return 200
    When user adds new product with title '' and price 45.45
    Then response status is 400
    And error message contains 'Bad Request'
    When user adds new product with title 'Test' and price -1
    Then response status is 400
    And error message contains 'Bad Request'
    When user adds new product with title '' and price -1
    Then response status is 400
    And error message contains 'Bad Request'

  @UpdateProduct
  Scenario: Update an existing product should return 201
    When user updates product with title 'Test Product New' and price 45.45
    Then response status is 201
    And product title is 'Test Product New'
    And product price is 45.45

  @UpdateProduct @Negative
  Scenario: Update a non existing product should return 404
    When user updates product with title 'Test Product New' and price 45.45
    Then response status is 201
    And product title is 'Test Product New'
    And product price is 45.45

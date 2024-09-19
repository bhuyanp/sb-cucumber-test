@PRODUCT
Feature: Product Service
  Users should be able to fetch, search, get all and add products using this feature

  @GET_PRODUCT
  Scenario: Fetch product by valid product id
    Given product with id 1 exists
    When user fetches the product
    Then product has id 1
    And response status is 200

  @GET_INVALID_PRODUCT
  Scenario: Fetch product by invalid product id
    Given product with id 100 does not exist
    When user fetches the product
    Then response status is 404

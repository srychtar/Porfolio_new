Feature: Making order

  Scenario Outline: User can log and put products to basket
    Given I'm logged in to page using <email> and <password>
    And I search and pick "Hummingbird Printed Sweater"
    And I set parameters and add to cart
    When I set order details and make order
    Then I can see order success message with text "YOUR ORDER IS CONFIRMED"
    And I can do screenshot of the results
    And I close shop browser



    Examples:
      | email                          | password     |
      | "iuswyxvttmuqqodjwo@ytnhy.com" | "zaliczenie" |
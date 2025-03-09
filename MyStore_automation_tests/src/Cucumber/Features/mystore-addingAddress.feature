Feature: Creating and checking address

  Scenario Outline: User can log in and add new address
    Given I'm logged in to page using <email> and <password>
    When I create new address <alias> <address> <city> <postal code> <country> and <phone>
    Then I can see success message with text "Address successfully added!"
    And I can see correct entered information <alias> <address> <city> <postal code> <country> and <phone>
    And I close shop browser

    Examples:
      | email                          | password     | alias  | address  | city     | postal code | country          | phone       |
      | "iuswyxvttmuqqodjwo@ytnhy.com" | "zaliczenie" | "Home" | "Pawia"  | "Krakow" | "30-334"    | "United Kingdom" | "123123123" |



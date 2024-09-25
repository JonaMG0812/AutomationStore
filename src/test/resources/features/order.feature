Feature: Order Search

  Scenario: Create accounts and search for orders
    Given I create 5 accounts
    And I place orders for each account
    When I search for each order in the store
    Then I should see the correct order information

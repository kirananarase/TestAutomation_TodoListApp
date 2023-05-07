@multiple_todoitems
Feature: Todo Items Management As a User I want to manage my todo items effectively by adding editing deleting
  completing and activating them. Additionally I want to be able to filter my todos by
  their completion status

  Background:
    Given I am on "My Todos" page

  Scenario Outline: Add more than 5 todo items
    When I add more than 5 todo items "<todoDetails>", "<addTodoItemCount>"
    Then I should be able to see the extra items on the next page "<Next>"
    And I should be able to navigate to the next page "<Next>"
    And I should be able to navigate back to the previous page "<Previous>"
    And I should see all the added todo items

    Examples:
      | todoDetails | addTodoItemCount | Next | Previous |
      | Todo Item | 6                  | next | prev     |

  Scenario Outline: Filter Active todos with more than 5 items
    When I click on the "Active" filter
    Then I should see only the active todos in the todo list
    And the number of active todos in the list should be correct
    And I should be able to see the extra items on the next page "<Next>"
    And I should be able to navigate to the next page "<Next>"
    And I should be able to navigate back to the previous page "<Previous>"
    And I should see all the added active todos

    Examples:
      | Next | Previous |
      | next | prev     |

  Scenario Outline: Filter Completed todos with more than 5 items
    And I have completed more than 5 todos
    When I click on the "Completed" filter tab
    Then I should see only the completed todos in the todo list
    And the number of completed todos in the list should be correct
    And I should be able to see the extra completed items on the next page "<Next>"
    And I should be able to navigate to the next page "<Next>"
    And I should be able to navigate back to the previous page "<Previous>"
    And I should see all the added completed todos.
    And I should close the browser

    Examples:
      | Next | Previous |
      | next | prev     |
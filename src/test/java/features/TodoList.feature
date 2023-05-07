@todoitems
Feature: Todo Items Management As a User I want to manage my todo items effectively by adding editing deleting
  completing and activating them. Additionally I want to be able to filter my todos by
  their completion status

  Background:
    Given I am on "My Todos" page

  Scenario Outline: Add a todo item
    When I click on "Add new Todo Item" textbox
    Then I should be able to enter the todo details "<todoDetails>", "<addTodoItemCount>"
    When I press enter
    And I should be able to save the todo item
    And the todo should appear in the todos list "<todoDetails>"

    Examples:
      | todoDetails | addTodoItemCount |
      | Todo Item | 1                  |

    Scenario: Edit a todo
      When I click on the todo item in the todos list
      Then I should see the todo details in the textbox
      And I should be able to edit the todo details
      When I press enter
      And I should be able to save the changes
      And the updated todo detail should appear in the todos list

  Scenario: Delete a todo
    When I click on the "(x)" (Delete) button for a todo
    Then the todo should be deleted from the todo list
    And the number of todos in the list should decrease by one


  Scenario: Complete a todo
    When I click on the "[ ]" (Complete) checkbox for a todo
    Then the todo should be marked as completed
    And the todo should appear in the completed todo list

  Scenario: Activate a todo
    When I click on the "[*]" (Complete) checkbox for a completed todo
    Then the todo should be marked as active
    And the todo should appear in the active todo list

  Scenario: Filter Active todos
    When I click on the "Active" filter
    Then I should see only the active todos in the todo list
    And the number of active todos in the list should be correct

  Scenario: Filter Completed todos
    When I click on the "Completed" filter tab
    Then I should see only the completed todos in the todo list
    And the number of completed todos in the list should be correct
    And I should close the browser

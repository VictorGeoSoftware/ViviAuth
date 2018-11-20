Feature: First start
  Load app for first time, and go to Store view

  Scenario: Load app, retrieve user information without favourite store, and go to Store view
    Given a user launch the app for first time
    When home screen is shown
    And profiles list is requested
    Then list is fulfilled



Feature: First start
  Load app for first time, and go to Store view

  Scenario: Load app, log in, and retrieve a doctor list
    Given a user launch the app for first time
    When home screen is shown
    And medics list is requested
    Then list is fulfilled



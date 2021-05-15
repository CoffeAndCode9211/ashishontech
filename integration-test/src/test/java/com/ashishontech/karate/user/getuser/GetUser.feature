Feature: Test User Get Info by UserName

  Background:
    * def baseUrl = karate.properties['baseUrl']
    * url baseUrl

  Scenario: Get All Users
    Given path '/karate/users'
    When method GET
    Then status 200
    * print 'Value of Response: ', response[0].userName
    And assert response[0].userName == 'test'

  Scenario: Get User by userName
    Given path '/karate/users'
    And request { userName: 'test' }
    When method GET
    Then status 200
    * print 'Value of Response: ', response[0].userName
    And assert response[0].userName == 'test'

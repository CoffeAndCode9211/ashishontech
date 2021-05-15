Feature: Test Save User Detail

  Background:
    # global variable for this feature file
    * def baseUrl = karate.properties['baseUrl']
    * url baseUrl
    * def postJson = {userName:'Hello',password:'123'}

     # call once before any test execution. Useful for Data setup
#    * callonce read('classpath:preCall.feature');

    # call after all scenarios are executed in this feature file. Useful for Data clean up
    * configure afterFeature = function(){ karate.print('post feature logic goes here'); }

  Scenario: Save User Detail

    Given path '/karate/users'
    And request postJson
    When method POST
    Then status 200
    And match response == {'message' : 'success' }

  Scenario Outline: <index> - <useCase>:
  validate different scenarios for bad request

    Given path '/karate/users'
    And request <requestJson>
    When method POST
    Then status <status>
    And match response == <response>

    Examples:

      | index | useCase            | requestJson                        | status | response                                                                                                            |
      | 1     | No Request Body    | null                               | 400    | {"timestamp":"#string","status":400,"error":"#string","trace":"#string","message":"#string","path":"/karate/users"} |
      | 2     | Wrong Request Body | 'test'                             | 415    | {"timestamp":"#string","status":415,"error":"#string","trace":"#string","message":"#string","path":"/karate/users"} |
      | 3     | Empty Request Body | {}                                 | 400    | {"password":"must not be null","userName":"must not be blank"}                                                      |
      | 4     | UserName is Null   | {'password': '123'}                | 400    | {"userName":"must not be blank"}                                                                                    |
      | 5     | UserName is Empty  | {"userName": "","password": "123"} | 400    | {"userName":"must not be blank"}                                                                                    |
      | 6     | Password is Null   | {"userName": "Test"}               | 400    | {"password":"must not be null"}                                                                                     |

# geocodingMockServer
mock geocoding server to test client side code speed


==mockServer==
* using Gretty plugin to start tomcat. Details http://akhikhl.github.io/gretty-doc/Feature-overview.html
* to start go to directory mockServer and use gradle clean appRunWar
* the application main page is http://localhost:8081/mockServer/ or https://localhost:8444/mockServer
* to hit the counter use http://localhost:8081/mockServer/geocode or https://localhost:8444/mockServer/geocode

The server is just dummy server to count the requests per second. Does return constant geocoding results. Only what is changing is the lat, long and place id.
The request report is generated per second into standard output but only if there was at least one request since last report.



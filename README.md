# geocodingMockServer
mock geocoding server to test client side code speed


==mockServer==
* using Gretty plugin to start tomcat. Details http://akhikhl.github.io/gretty-doc/Feature-overview.html
* to start go to directory mockServer and use gradle clean appRunWar
* the application main page is http://localhost:8080/mockServer/
* to hit the counter use http://localhost:8080/mockServer/geocode

The server is now just dummy server to count the requests per second. Does not return any goecoding result.
The report is generated per second but only if there was at least one request since last report.

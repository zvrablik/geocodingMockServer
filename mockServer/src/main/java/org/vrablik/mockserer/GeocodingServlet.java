/* --------------------------------------------------------------*\
| Copyright (C) e-Spatial Solutions Limited, All rights reserved. |
\* --------------------------------------------------------------*/
package org.vrablik.mockserer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by zvrablik on 10/28/16.
 */
@WebServlet("/geocode")
public class GeocodingServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(GeocodingServlet.class);

    private static final RequestsMonitor monitor = new RequestsMonitor();

    private static final RandomOrdinatesGenerator ordinatesGenerator = new RandomOrdinatesGenerator();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        monitor.startRequest();

        try {
            Thread.sleep(50L);//change this to 2000 to simulate slow server response
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Charset charset = Charset.forName(res.getCharacterEncoding());
        byte[] responseBytes = createResponse().getBytes(charset);

        res.setStatus(200);//change this to 500 or 404 to simulate server internal error
        res.setContentLength(responseBytes.length);
        res.setContentType("application/json");
        res.getOutputStream().write(responseBytes);
        res.flushBuffer();

        monitor.finishRequest();
    }

    /**
     * Create static response with random lat and long.
     * @return
     */
    private String createResponse() {
        StringBuilder msg = new StringBuilder(1000);
        double randomX = ordinatesGenerator.getRandomX();
        double randomXEast = randomX - 0.001D;
        double randomXWest = randomX + 0.001D;

        double randomY = ordinatesGenerator.getRandomY();
        double randomYNorth = randomY + 0.001D;
        double randomYSouth = randomY - 0.001D;

        String placeId = ordinatesGenerator.getRandomPlaceId();

        //comment out any part of the json response to simulate malformed json response
        msg.append("{");
        msg.append("\"results\" : [");
        msg.append(         "{");
        msg.append(            "\"address_components\" : [");
        msg.append(            "{");
        msg.append(               "\"long_name\" : \"1600\",");
        msg.append(               "\"short_name\" : \"1600\",");
        msg.append(               "\"types\" : [ \"street_number\" ]");
        msg.append(            "},");
        msg.append(            "{");
        msg.append(               "\"long_name\" : \"Amphitheatre Parkway\",");
        msg.append(               "\"short_name\" : \"Amphitheatre Pkwy\",");
        msg.append(               "\"types\" : [ \"route\" ]");
        msg.append(            "},");
        msg.append(            "{");
        msg.append(               "\"long_name\" : \"Mountain View\",");
        msg.append(              "\"short_name\" : \"Mountain View\",");
        msg.append(               "\"types\" : [ \"locality\", \"political\" ]");
        msg.append(            "},");
        msg.append(            "{");
        msg.append(               "\"long_name\" : \"Santa Clara County\",");
        msg.append(               "\"short_name\" : \"Santa Clara County\",");
        msg.append(               "\"types\" : [ \"administrative_area_level_2\", \"political\" ]");
        msg.append(            "},");
        msg.append(            "{");
        msg.append(               "\"long_name\" : \"California\",");
        msg.append(               "\"short_name\" : \"CA\",");
        msg.append(               "\"types\" : [ \"administrative_area_level_1\", \"political\" ]");
        msg.append(            "},");
        msg.append(            "{");
        msg.append(               "\"long_name\" : \"United States\",");
        msg.append(               "\"short_name\" : \"US\",");
        msg.append(               "\"types\" : [ \"country\", \"political\" ]");
        msg.append(            "},");
        msg.append(            "{");
        msg.append(               "\"long_name\" : \"94043\",");
        msg.append(               "\"short_name\" : \"94043\",");
        msg.append(               "\"types\" : [ \"postal_code\" ]");
        msg.append(            "}");
        msg.append(         "],");
        msg.append(         "\"formatted_address\" : \"1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA\",");
        msg.append(         "\"geometry\" : {");
        msg.append(            "\"location\" : {");
        msg.append(               "\"lat\" :" + randomY + ",");
        msg.append(               "\"lng\" : " + randomX);
        msg.append(            "},");
        msg.append(                    "\"location_type\" : \"ROOFTOP\",");
        msg.append(            "\"viewport\" : {");
        msg.append(               "\"northeast\" : {" );
        msg.append(                  "\"lat\" : " + randomYNorth + ",");
        msg.append(                  "\"lng\" : " + randomXEast );
        msg.append(               "}," );
        msg.append(               "\"southwest\" : {" );
        msg.append(                  "\"lat\" : " + randomYSouth + "," );
        msg.append(                  "\"lng\" : " + randomXWest );
        msg.append(               "}" );
        msg.append(            "}" );
        msg.append(         "}," );
        msg.append(         "\"place_id\" : \"" + placeId + "\"," );
        msg.append(         "\"types\" : [ \"street_address\" ]" );
        msg.append(      "}" );
        msg.append(   "]," );
        msg.append(   "\"status\" : \"OK\"" );
        msg.append("}");

        return msg.toString();
    }
}

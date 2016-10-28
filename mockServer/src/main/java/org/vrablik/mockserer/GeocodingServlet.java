/* --------------------------------------------------------------*\
| Copyright (C) e-Spatial Solutions Limited, All rights reserved. |
\* --------------------------------------------------------------*/
package org.vrablik.mockserer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by zvrablik on 10/28/16.
 */
@WebServlet("/geocode")
public class GeocodingServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(GeocodingServlet.class);

    private static final RequestsMonitor monitor = new RequestsMonitor();

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        monitor.startRequest();

        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        monitor.finishRequest();
    }
}

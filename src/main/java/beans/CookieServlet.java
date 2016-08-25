package beans;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/cookie.html")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Setting a cookie
        resp.addCookie(new Cookie("myName", "Jimi"));

        // Note use myColor here for both is not required. Cookie and parameters are two different things.
        // good idea to name them the same though to not confuse too much.
        Cookie colorCookie = new Cookie("myColor", extractParameterOrDefault(req, "myColor", "green"));
        colorCookie.setMaxAge(Integer.MAX_VALUE);
        resp.addCookie(colorCookie);

        resp.getWriter().println("Have to say something, because being silent is awkward...");

        // Reading cookies (possibly another servlet)
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("myName")) {
                // This is the one!
                resp.getWriter().println("Found previous cookie: " + cookie.getValue());
            }
        }



    }

    private String extractParameterOrDefault(HttpServletRequest req, String parameterName, String defaultValue) {
        String parameterValue = req.getParameter(parameterName);
        if(parameterValue == null) {
            System.out.println("Parameter not present on page. Should be called with ?" + parameterName + "=" + defaultValue + ". Using default, which is " + defaultValue );
            parameterValue = defaultValue;
        }
        return parameterValue;
    }
}
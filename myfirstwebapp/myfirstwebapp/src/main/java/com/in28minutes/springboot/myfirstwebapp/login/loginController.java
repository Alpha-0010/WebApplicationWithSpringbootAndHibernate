package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


/*
* If we want to retain values for the subsequent requests we can use sessions. And since name is updated by modalMap in LoginControlller
* we can use it in TodoController as well.
* */
@Controller
@SessionAttributes("name")
public class loginController {


    private final LoginAuthenticationService loginAuthenticationService;

    @Autowired
    public loginController(LoginAuthenticationService loginAuthenticationService) {
        super();
        this.loginAuthenticationService = loginAuthenticationService;
    }

    /*
    * Logs are preferred over System.out.println() because we can control the log level but in the case of print() we can't do the same.
    * Log Levels :-
    * Error, warn, info, debug(Highest if the logs are set to debug then all the logs will be printed).
    * */
//    private final Logger logger = LoggerFactory.getLogger(getClass());

    // http://localhost:8080/login?name=shashwat
    /*
    * To pass the name to the JSP view we will use ModelMap. We can access the name in login.jsp by calling ${name}
    * */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String goToLoginPage(/* @RequestParam String name  ,ModelMap model*/) {
//        model.put("name",name);
//        System.out.println("Request params is :" + name); // Not Recommended for Prod. This prints name value(shashwat) in the system log.
//        logger.debug("Request params is : {}", name);
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String goToWelcomePage(@RequestParam String name, @RequestParam String password,ModelMap model) {

        // Hardcoded Authentication Logic...
        if (loginAuthenticationService.Authenticate(name,password)) {
            model.put("name",name);
//            model.put("password",password);
            return "welcome";
        }

        return "login";
    }
}

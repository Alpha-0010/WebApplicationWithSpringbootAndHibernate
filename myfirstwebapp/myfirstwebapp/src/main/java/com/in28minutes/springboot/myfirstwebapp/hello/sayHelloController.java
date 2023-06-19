package com.in28minutes.springboot.myfirstwebapp.hello;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class sayHelloController {
    // "say-hello" => "Hello there!"

    /*
    * If we have a ResponseBody then Spring MVC assumes that we want to return whatever we are returning in the method.
    * */
    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello there!";
    }

    /*
    * It will become tiresome if we render html like this so instead of this we can create views and render them and JSP is one of
    * the popular APIs to create Views.
    * */
    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title> My First HTML Page - Changed</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("My first html page with body - Changed");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

    // "say-hello-jsp" => sayHello.jsp
    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp() {
        return "sayHello";
    }

}

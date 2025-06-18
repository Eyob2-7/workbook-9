package com.pluralsight.NorthwindTradersAPI;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

        @GetMapping("/")
        public String homePage(@RequestParam(defaultValue = "World") String country){
                return "Hello " + country;
        }

        // this method will respond to http://localhost:8080/
//        @RequestMapping(path="/", method= RequestMethod.GET)
//        public String index(
//                //@RequestParam(defaultValue="Hello World") String name
//                @RequestParam(name="country", defaultValue = "world") String country
//        ) {
//            return "Hello " + country + "!";}
//


}


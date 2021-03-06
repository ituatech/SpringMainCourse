package com.it_uatech.controllers;

import com.it_uatech.services.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.it_uatech.model.Friend;

@Controller
public class GreetingController {
    private final GreetingService singletonGreetingService;
    private final GreetingService prototypeGreetingService1;
    private final GreetingService prototypeGreetingService2;
    private final GreetingService prototypeGreetingService3;
    private final GreetingService sessionGreetingService;
    private final GreetingService requestGreetingService;

    private final Friend friend1;
    private final Friend friend2;


    public GreetingController(@Qualifier("SingletonGreetingService") GreetingService singletonGreetingService,
                              @Qualifier("PrototypeGreetingService")GreetingService prototypeGreetingService1,
                              @Qualifier("PrototypeGreetingService")GreetingService prototypeGreetingService2,
                              @Qualifier("PrototypeGreetingService")GreetingService prototypeGreetingService3,
                              @Qualifier("SessionGreetingService")GreetingService sessionGreetingService,
                              @Qualifier("RequestGreetingService")GreetingService requestGreetingService,
                              @Qualifier("Friend1") Friend friend1,
                              @Qualifier("Friend2") Friend friend2
    ) {
        this.singletonGreetingService = singletonGreetingService;
        this.prototypeGreetingService1 = prototypeGreetingService1;
        this.prototypeGreetingService2 = prototypeGreetingService2;
        this.prototypeGreetingService3 = prototypeGreetingService3;
        this.sessionGreetingService = sessionGreetingService;
        this.requestGreetingService = requestGreetingService;
        this.friend1 = friend1;
        this.friend2 = friend2;
    }

    @GetMapping("/")
    public String greetingPage(Model model) {
        model.addAttribute("singletonGreeting", singletonGreetingService.greeting());
        model.addAttribute("prototype2Greeting", prototypeGreetingService1.isFirstGreetingSuccess()? prototypeGreetingService2.greeting(): "Пока жду");
        model.addAttribute("prototype1Greeting", prototypeGreetingService1.greeting());
        model.addAttribute("prototype3Greeting", prototypeGreetingService3.goodbye());
        model.addAttribute("sessionGreeting", sessionGreetingService.greeting());
        model.addAttribute("requestGreeting", requestGreetingService.greeting());
        model.addAttribute("friend1", friend1.getName());
        model.addAttribute("friend2", friend2.getName());
        return "index";
    }
}

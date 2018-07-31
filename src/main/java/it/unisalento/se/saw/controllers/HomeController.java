package it.unisalento.se.saw.controllers;


import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

                  // metodo con spring

    @Autowired
    IUserServices userServices;


    @Autowired
    public HomeController() {
        super();
    }
    public HomeController(IUserServices userServices){
        this.userServices = userServices;
    }


}

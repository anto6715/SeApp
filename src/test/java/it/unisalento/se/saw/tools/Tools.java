package it.unisalento.se.saw.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.se.saw.restapi.UserRestController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

public class Tools {

    public static String getJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static MockMvc getMockMvc(Object controllerRest) {
        return MockMvcBuilders.standaloneSetup(controllerRest)
                .setViewResolvers(viewResolver())
                .build();
    }

    public static ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}

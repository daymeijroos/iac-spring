package com.daymeijroos.iacspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LoginController {
    @GetMapping("/login")
    public RedirectView login() {
        // Replace YOUR_AUTH0_DOMAIN with your Auth0 domain
        // Replace YOUR_CLIENT_ID with your client ID
        // Replace YOUR_CALLBACK_URL with your callback URL
        String authorizationUrl = "https://YOUR_AUTH0_DOMAIN/authorize?" +
                "client_id=YOUR_CLIENT_ID" +
                "&response_type=code" +
                "&redirect_uri=YOUR_CALLBACK_URL" +
                "&scope=openid profile email" +
                "&state=STATE";

        return new RedirectView(authorizationUrl);
    }
}

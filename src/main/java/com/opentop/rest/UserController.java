package com.opentop.rest;

import com.opentop.entity.AuthResponse;
import com.opentop.entity.User;
import com.opentop.service.AuthUserDetailService;
import com.opentop.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private AuthenticationManager authenticationManager;
    private AuthUserDetailService authUserDetailService;
    private JWTService jwtService;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setAuthUserDetailService(AuthUserDetailService authUserDetailService) {
        this.authUserDetailService = authUserDetailService;
    }

    @Autowired
    public void setJwtService(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @RequestMapping(
            value = "/authenticate",
            method = RequestMethod.POST
    )
    public AuthResponse authenticate(@RequestBody User user) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUserName(),
                            user.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect user");
        }

        UserDetails userDetails = authUserDetailService.loadUserByUsername(user.getUserName());
        String jwt = jwtService.createToken(userDetails);

        return new AuthResponse(jwt);
    }
}

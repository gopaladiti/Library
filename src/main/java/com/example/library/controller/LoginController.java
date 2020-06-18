package com.example.library.controller;

import com.example.library.model.AuthRequest;
import com.example.library.model.AuthResponse;
import com.example.library.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
/*@RequestMapping("/login")*/
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Books Library!!!";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    //public ResponseEntity<?> authenticateAndGenerateToken(@RequestBody AuthRequest authRequest) throws Exception{
    public String authenticateAndGenerateToken(@RequestBody AuthRequest authRequest) throws Exception{
        //System.out.println(authRequest.getUsername());
        //System.out.println(authRequest.getPassword());
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch(Exception ex) {
            throw new Exception("invalid username or password");
        }
        String jwt = jwtUtil.generateToken(username);
        //return ResponseEntity.ok(new AuthResponse(jwt));
        return jwt;
    }
}

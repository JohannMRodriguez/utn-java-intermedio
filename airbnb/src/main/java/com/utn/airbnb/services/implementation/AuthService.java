package com.utn.airbnb.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.airbnb.application.exceptions.BadRequestException;
import com.utn.airbnb.application.exceptions.UnauthorizedException;
import com.utn.airbnb.dto.request.RequestAuthDto;
import com.utn.airbnb.dto.response.ResponseAuthDto;
import com.utn.airbnb.repositories.AuthRepository;
import com.utn.airbnb.services.IAuthService;
import com.utn.airbnb.utils.JwtUtil;
import com.utn.airbnb.utils.Mensajes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthRepository repository;

    public ResponseAuthDto authenticate(RequestAuthDto credentials) {

        var user = repository.findByUsername(credentials.getUsername());

        if (user.isEmpty()) { throw new BadRequestException(Mensajes.CLIENT_NOT_FOUND); }

        var usernameRegistered = user.get().getUsername();
        var passwordRegistered = user.get().getPassword();

        if (!StringUtils.equals(credentials.getUsername(), usernameRegistered) ||
            !StringUtils.equals(credentials.getPassword(), passwordRegistered)) {
            throw new UnauthorizedException(Mensajes.BAD_AUTHENTICATION);
        }

        var token = jwtUtil.generateToken(usernameRegistered);
        return new ResponseAuthDto(token);
    }

}

package com.utn.airbnb.services;

import com.utn.airbnb.dto.request.RequestAuthDto;
import com.utn.airbnb.dto.response.ResponseAuthDto;

public interface IAuthService {
    ResponseAuthDto authenticate(RequestAuthDto credentials);
}

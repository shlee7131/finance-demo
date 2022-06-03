package com.shlee7131.financedemo;

import com.shlee7131.financedemo.service.dto.AuthInfoDto;
import com.shlee7131.financedemo.service.dto.UserReqDto;
import com.shlee7131.financedemo.service.dto.UserRespDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@NoArgsConstructor
public class SessionStorage {
    private final Map<String, AuthInfoDto> store = new HashMap<>();

    public AuthInfoDto put(String sessionId, AuthInfoDto authInfoDto) {
        store.put(sessionId, authInfoDto);
        return authInfoDto;
    }

    public AuthInfoDto get(String sessionId) {
        return store.get(sessionId);
    }
}

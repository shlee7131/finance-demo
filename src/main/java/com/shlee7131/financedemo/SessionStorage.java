package com.shlee7131.financedemo;

import com.shlee7131.financedemo.service.dto.AuthInfoDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Component
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

package com.shlee7131.financedemo;

import com.shlee7131.financedemo.service.dto.AuthInfoDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@NoArgsConstructor
public class SessionStorage {
    private final Map<String, AuthInfoDto> store = new HashMap<>();

    public AuthInfoDto put(String sessionId, AuthInfoDto authInfoDto) {
        log.info("SessionStorage: Put {},{}",sessionId, authInfoDto);
        store.put(sessionId, authInfoDto);
        return authInfoDto;
    }

    public AuthInfoDto get(String sessionId) {
        log.info("SessionStorage: Get { }",sessionId);
        return store.get(sessionId);
    }
}

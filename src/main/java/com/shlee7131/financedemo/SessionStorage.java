package com.shlee7131.financedemo;

import com.shlee7131.financedemo.service.dto.UserReqDto;
import com.shlee7131.financedemo.service.dto.UserRespDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@NoArgsConstructor
public class SessionStorage {
    private final Map<String, UserRespDto> store = new HashMap<>();

    public UserRespDto put(String sessionId, UserRespDto userRespDto) {
        store.put(sessionId, userRespDto);
        return userRespDto;
    }

    public UserRespDto get(String sessionId) {
        return store.get(sessionId);
    }
}

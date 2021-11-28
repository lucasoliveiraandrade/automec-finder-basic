package com.automecfinder.basic.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
@AllArgsConstructor
public class HttpUtil {

    private final ObjectMapper objectMapper;

    public Optional<String> serialize(Object obj) {
        try {
            return ofNullable(objectMapper.writeValueAsString(obj));
        } catch (Exception e) {
            log.error("Object could not be serialized as a json string. Error: \n{}", e.getMessage());
        }
        return empty();
    }

//    public Optional<DNA> deserialize(String dnaSerialized) {
//        try {
//            return ofNullable(objectMapper.readValue(dnaSerialized, DNA.class));
//        } catch (Exception e) {
//            log.error("DNA could not be deserialized as a object. Error: \n{}", e.getMessage());
//        }
//        return empty();
//    }
}

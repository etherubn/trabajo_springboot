package com.apirest.mitocode.ruben.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperUtil {
    private final ModelMapper  modelMapper;

    public <S,T> T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public <S,T> List<T> map(List<S> sourceList, Class<T> targetClass) {
        return sourceList.stream().map(source -> map(source, targetClass)).toList();
    }
}

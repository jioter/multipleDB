package com.multipledb.configs.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultModelMapper extends ModelMapper {

    public DefaultModelMapper() {
        this.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
            .setAmbiguityIgnored(true)
            .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
            .setSkipNullEnabled(true);
    }
}

package com.vladwild.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@PropertySource("application.properties")
@ContextConfiguration(classes = DefaultFormattingConversionService.class) //почему-то нужно обязательно указать
public abstract class JpaH2Runner {
}

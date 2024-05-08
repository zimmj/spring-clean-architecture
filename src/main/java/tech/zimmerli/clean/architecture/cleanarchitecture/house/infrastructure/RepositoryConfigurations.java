package tech.zimmerli.clean.architecture.cleanarchitecture.house.infrastructure;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import tech.zimmerli.clean.architecture.cleanarchitecture.house.useCases.IHouseRepository;

@Configuration
public class RepositoryConfigurations {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public IHouseRepository houseRepository() {
        return new HouseRepository();
    }
}

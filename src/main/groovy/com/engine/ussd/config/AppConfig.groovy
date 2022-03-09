package com.engine.ussd.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.CookieLocaleResolver

@Configuration
class AppConfig {
    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:messages/ussd")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }


    /*@Bean
     MessageSource messageSource () {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource()
        messageSource.setBasenames("messages/ussd")
        return messageSource
    }
*/

}

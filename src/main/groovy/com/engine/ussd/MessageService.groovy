package com.engine.ussd

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service

@Service
class MessageService {
    @Autowired
    private MessageSource messageSource

    def msg(String code, List args = null){
       return messageSource.getMessage(code, args?.toArray(), Locale.default)
    }
}

package com.engine.ussd.controller

import com.engine.ussd.MessageService
import com.engine.ussd.config.Constants
import com.engine.ussd.utils.Utils
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import java.util.regex.Pattern

import static com.engine.ussd.config.Constants.*
import static com.engine.ussd.utils.Utils.menuTextMatch

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
class UssdApiController {


    @Autowired
    private MessageService message

    @PostMapping(path = "/ussd", produces = [MediaType.TEXT_PLAIN_VALUE])
    @ResponseBody()
    def index(
            @RequestParam(name = "sessionId", required = false) String sessionId,
            @RequestParam(name = "serviceCode", required = false) String serviceCode,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "text", required = false) String text
    ) {

        try {
            println("USSD MENU CALL");
            println("sessionId =" + sessionId + " \n serviceCode =" + serviceCode + "\n phoneNumber =" + phoneNumber + "\n text = $text");
            String ussdResponseString = message.msg('xeno.ussd.main', [USSD_CON])

            if (text == null) {
                return ussdResponseString
            }

            if (text == "0") {
                ussdResponseString = message.msg('xeno.ussd.exit', [USSD_END])
                return ussdResponseString
            }

            //==== Item One Menu ====
            if (text == "1") {
                ussdResponseString = message.msg('xeno.ussd.en.enter_name', [USSD_CON])
                return ussdResponseString
            } else if (menuTextMatch(text, Pattern.compile("(1\\*\\w+)"))) {
                ussdResponseString = message.msg('xeno.ussd.en.enter_age', [USSD_CON])
                return ussdResponseString
            } else if (menuTextMatch(text, Pattern.compile("(1\\*\\w+\\*\\d+)"))) {
                ussdResponseString = message.msg('xeno.ussd.en.package.select', [USSD_CON])
                return ussdResponseString
            } else if (menuTextMatch(text, Pattern.compile("(1\\*\\w+\\*\\d+\\*\\d+)"))) {
                ussdResponseString = message.msg('xeno.ussd.en.package.opt1', [USSD_CON])
                return ussdResponseString
            } else if (menuTextMatch(text, Pattern.compile("(1\\*\\w+\\*\\d+\\*\\d+\\*\\d+)"))) {
                def paramsArr = text.split("\\*")
                String name = paramsArr[1]

                ussdResponseString = message.msg('xeno.ussd.en.package.thank_you', [USSD_END, name])
                return ussdResponseString
            }


            return ussdResponseString
        } catch (Exception e) {
            e.printStackTrace()
        }

        return "Application Error"
    }

}

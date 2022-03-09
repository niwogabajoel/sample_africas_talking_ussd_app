package com.engine.ussd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class UssdEngineAppApplication {

	static void main(String[] args) {
		Locale.setDefault(Locale.US)
		SpringApplication.run(UssdEngineAppApplication, args)
	}

}

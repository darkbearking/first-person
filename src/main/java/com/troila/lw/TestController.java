package com.troila.lw;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@Configuration
public class TestController {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@GetMapping("/router")
	@ResponseBody
	public String router() {
		RestTemplate rtl = getRestTemplate();
		//下面的first-police的出處，在first-police工程的application.yml配置文件的“spring:application:”這個屬性中
		//因為服務的提供者是警察工程，而非eureka的服務端（亦即並非first-114工程）。因此這裡要寫服務提供者的服務名
		//當然也可以直接寫成
		String json = rtl.getForObject("http://first-police/call/10", String.class);
		return json;
	}
}

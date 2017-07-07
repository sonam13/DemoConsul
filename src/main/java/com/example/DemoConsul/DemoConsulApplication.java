package com.example.DemoConsul;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//@Configuration
//@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
//@EnableConfigurationProperties
//@EnableFeignClients
//@Slf4j
@SpringBootApplication
public class DemoConsulApplication {
	@Autowired
	private LoadBalancerClient loadBalancer;

	@Autowired
	private org.springframework.cloud.client.discovery.DiscoveryClient discoveryClient;

/*    @Autowired
	private Environment env;
    */
   
/*
	@Autowired
	private SampleClient sampleClient;
*/
	@Autowired
	private RestTemplate restTemplate;

	@Value("${appName}")
	private String appName;

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	@RequestMapping("")
	public String me() {
		org.springframework.cloud.client.ServiceInstance serviceInstance =  discoveryClient.getInstances(appName).get(0);
	String url = loadBalancer.choose(appName).getUri().toString()+ "";
	String response = restTemplate.getForObject(url, String.class);
		return response;
	}

	/*@RequestMapping("/")
	public ServiceInstance lb() {
		return loadBalancer.choose(appName);
	}

	@RequestMapping("/rest")
	public String rest() {
		return this.restTemplate.getForObject("http://"+appName+"/me", String.class);
	}

	@RequestMapping("/choose")
	public String choose() {
		return loadBalancer.choose(appName).getUri().toString();
	}*/

	/*@RequestMapping("/myenv")
	public String env(@RequestParam("prop") String prop) {
		return env.getProperty(prop, "Not Found");
	}*/

	/*@RequestMapping("/prop")
	public String prop() {
		return sampleProperties().getProp();
	}*/

	/*@RequestMapping("/instances")
	public List<ServiceInstance> instances() {
		return discoveryClient.getInstances(appName);
	}
*/
	/*@RequestMapping("/feign")
	public String feign() {
		return sampleClient.choose();
	}*/

	/*@Bean
	public SubtypeModule sampleSubtypeModule() {
		return new SubtypeModule(SimpleRemoteEvent.class);
	}*/

	/*@Bean
	public SampleProperties sampleProperties() {
		return new SampleProperties();
	}*/
/*
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}*/


	public static void main(String[] args) {
		SpringApplication.run(DemoConsulApplication.class, args);
	}
}

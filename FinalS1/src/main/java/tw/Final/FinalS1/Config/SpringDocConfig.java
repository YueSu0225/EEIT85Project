package tw.Final.FinalS1.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class SpringDocConfig {

	@Bean
	public OpenAPI baseOpenAPI() {
		OpenAPI openApi = new OpenAPI();
		Components components = new Components();
		Info info = new Info();
		info.title("w2w API 文件").version("V.2024092301").description("very good");
		
		
		openApi
			.info(info)
			.components(components);
				
		
		
		return openApi;
	}
	
}

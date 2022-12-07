package pro.sky.graduate_work_group5_team1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//В папку resources надо добавить свой файл "custom.properties" и в нем прописать данные для базы
@PropertySource(value = "classpath:custom.properties", encoding = "UTF-8")
@SpringBootApplication
@OpenAPIDefinition
public class GraduateWorkGroup5Team1Application {

	public static void main(String[] args) {
		SpringApplication.run(GraduateWorkGroup5Team1Application.class, args);
	}

}

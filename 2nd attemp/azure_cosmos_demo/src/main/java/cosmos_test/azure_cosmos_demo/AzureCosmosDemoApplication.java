package cosmos_test.azure_cosmos_demo;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AzureCosmosDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzureCosmosDemoApplication.class, args);
	}

}

@Slf4j
@Component
@AllArgsConstructor
class DataLoader{
	private final UserRepository userRepository;

	@PostConstruct
	void loadData(){
		userRepository.deleteAll().thenMany(Flux.just(new User("yasuo", "yone","DongAnh"),
				new User("lucian","ezreal","Hanoi")))
				.flatMap(userRepository::save)
				.thenMany(userRepository.findAll())
				.subscribe(user -> log.info(user.toString()));

	}

}



@RestController
@AllArgsConstructor
class CosmosSqlController{

	private final UserRepository userRepository;

	@GetMapping
	Flux<User> getAllUsers(){
		return userRepository.findAll();
	}


}


interface UserRepository extends ReactiveCosmosRepository<User,String>{}


@Container(containerName = "data")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
class User {
	@Id
	@GeneratedValue
	private String id;
	@NonNull
	private String firstName;
	@NonNull
	@PartitionKey
	private String lastName;
	@NonNull
	private String address;

}

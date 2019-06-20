package ac.daffodil.amirul.app;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.spring.annotation.SpringComponent;
import ac.daffodil.amirul.backend.UserRepository;
import ac.daffodil.amirul.backend.data.Role;
import ac.daffodil.amirul.backend.data.entity.User;

@SpringComponent
public class DataGenerator implements HasLogger {

	private User baker;
	private User barista;

	@Bean
	public CommandLineRunner loadData(UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			if (hasData(userRepository)) {
				getLogger().info("Using existing database");
				return;
			}

			getLogger().info("Generating demo data");
			getLogger().info("... generating users");
			createUsers(userRepository, passwordEncoder);			
			getLogger().info("Generated demo data");
		};
	}
	
	private boolean hasData(UserRepository userRepository) {
		return userRepository.count() != 0L;
	}

	private void createUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		baker = userRepository.save(new User("rigan@project.com", "Rigan", passwordEncoder.encode("user"), Role.USER));
		User user = new User("user@project.com", "Malin", passwordEncoder.encode("user"), Role.USER);
		user.setLocked(true);
		barista = userRepository.save(user);
		user = new User("admin@project.com", "Amirul", passwordEncoder.encode("admin"), Role.ADMIN);
		user.setLocked(true);
		userRepository.save(user);
	}
	
	/*private void createAccountType(AccountTypeRepository accountTypeRepository) {
		accountTypes.add(new AccountType("Assets",""));
		accountTypes.add(new AccountType("Revenew",""));
		accountTypes.add(new AccountType("Liabilities",""));
		accountTypes.add(new AccountType("Expenses",""));
		accountTypes.add(new AccountType("Owner's Equity",""));
		
		accountTypeRepository.save(accountTypes);
		
	}*/
	
	/*private void createAccountType(AccountTypeRepository accountTypeRepository) {
		for (int i = 0; i < ACCOUNTTYPE.length; i++) {
			AccountType accountType = new AccountType();
			accountType.setAccountType(getRandom(ACCOUNTTYPE));
			
			accountTypes.add(accountTypeRepository.save(accountType));
			
		}
	}*/
}

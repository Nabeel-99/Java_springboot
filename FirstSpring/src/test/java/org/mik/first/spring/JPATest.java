package org.mik.first.spring;

import org.junit.jupiter.api.Test;
import org.mik.first.spring.domain.Country;
import org.mik.first.spring.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@DataJpaTest
class JPATest {

	@Autowired
	private CountryRepository countryRepository;
	@Test
	void countryTest() {
		Country c = Country.builder().name("Hungary")
				.sign("HU")
				.build();
		assertThat(c).isNotNull();
		assertThat(c.getName()).isNotNull();
		assertThat(c.getName().equals("Hungary")).isTrue();
		countryRepository.save(c);
		assertThat(c.getId()).isNotNull();

		countryRepository.save(Country.builder()
						.name("USA")
						.sign("US")
				.build()

		);

		countryRepository.save(Country.builder()
				.name("Betelgeues")
				.sign("BT")
				.build()

		);

		countryRepository.save(Country.builder()
				.name("Ukraine")
				.sign("UA")
				.build()

		);
		List<Country> all=countryRepository.findAll();
		all.stream().filter(cy-> cy.getName().equals("Hungary")).findFirst().ifPresentOrElse(
				System.out::println,
				()->fail("Hungary is not found")
		);
	}

}

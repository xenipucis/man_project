package at.tiby

import org.springframework.boot.test.SpringApplicationConfiguration;

import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration;

import at.tiby.TestConfig;
import at.tiby.repository.DogeRepository
import at.tiby.repository.TestDogeRepository

import org.springframework.beans.factory.annotation.Autowired;


@ActiveProfiles("test")
@SpringApplicationConfiguration(TestConfig.class)
@IntegrationTest
class SampleTestSpec extends spock.lang.Specification {

	@Autowired
	DogeRepository dogeRepository;// = new TestDogeRepository();
	
	def "some test method"() {
		expect:
			2 == 2
			dogeRepository
	}
}

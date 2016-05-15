package at.tiby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import at.tiby.repository.DogeRepository;
import at.tiby.service.DogeService;


@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class DogenessTest extends AbstractTestNGSpringContextTests {


    @Autowired
    DogeService dogeService;

    @Autowired
    DogeRepository dogeRepository;

	@Test
	public void testDogeService() throws Exception {
        Assert.assertFalse(dogeService.requiresDogeness(), "Unit Test profile sets wow to 4, should have been false");
	}

    @Test
    public void testDogeRepository() throws Exception {
        Assert.assertEquals("wow", dogeRepository.getDogeData(), "Test Repository was not injected");
    }
}

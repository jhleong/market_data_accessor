package com.eigen.junit.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eigen.iface.finder.YahooDataFinder;
import com.eigen.junit.config.TestConfig;
import com.eigen.model.MHistData;
import com.eigen.model.MProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class YahooDataFinderTest {

	@Autowired
	private YahooDataFinder yahooDataFinder;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void test_get_hist_data() {
		MProfile rtProfile = new MProfile();
		rtProfile.setRic_name("GOOG");
		List<MHistData> ls = null;
		try {
			ls = yahooDataFinder.getHistData(
					rtProfile,
					dateFormat.parse("2015-01-01"),
					dateFormat.parse("2015-12-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(ls);
		Assert.assertFalse("Should not be empty.", ls.isEmpty());
	}

}

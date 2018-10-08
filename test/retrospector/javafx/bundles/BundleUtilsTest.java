package retrospector.javafx.bundles;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class BundleUtilsTest {
	@Test
	public void getResourceBundles_Core_GetsBundles() {
	  Set<ResourceBundle> bundles = BundleUtils.getResourceBundles(BundleType.Core);	

	  Assert.assertTrue(0 < bundles.size());
	  Assert.assertTrue(hasEnglishBundle(bundles));
	}

	@Test
	public void getResourceBundles_Achievement_GetsBundles() {
	  Set<ResourceBundle> bundles = BundleUtils.getResourceBundles(BundleType.Achievement);	

	  Assert.assertTrue(0 < bundles.size());
	  Assert.assertTrue(hasEnglishBundle(bundles));
	}
	
	private boolean hasEnglishBundle(Collection<ResourceBundle> bundles) {
		return bundles.stream().anyMatch(this::isEnglishBundle);
	}

	private boolean isEnglishBundle(ResourceBundle bundle) {
		return bundle.getLocale().getLanguage().equals(new Locale("en").getLanguage());
	}
}

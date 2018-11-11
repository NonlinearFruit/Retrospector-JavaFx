package retrospector.javafx.bundles;

import java.util.Collection;
import java.util.Enumeration;
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

  @Test
  public void CoreBundles_HaveAllEnglishKeys() {
    BundleType type = BundleType.Core;
    ResourceBundle english = getEnglishBundle(type);
    Enumeration<String> keys = english.getKeys();

    for (ResourceBundle bundle : BundleUtils.getResourceBundles(type))
      while (keys.hasMoreElements())
        VerifyBundleHasKey(bundle, keys.nextElement());
  }
  
  @Test
  public void AchievementBundles_HaveAllEnglishKeys() {
    BundleType type = BundleType.Achievement;
    ResourceBundle english = getEnglishBundle(type);
    Enumeration<String> keys = english.getKeys();

    for (ResourceBundle bundle : BundleUtils.getResourceBundles(type))
      while (keys.hasMoreElements())
        VerifyBundleHasKey(bundle, keys.nextElement());
  }

  private void VerifyBundleHasKey(ResourceBundle bundle, String key) {
    Assert.assertNotNull(bundle.getString(key));
  }
	
  private ResourceBundle getEnglishBundle(BundleType type) {
    return BundleUtils
            .getResourceBundles(type)
            .stream()
            .filter(this::isEnglishBundle)
            .findFirst()
            .get();
  }
  
	private boolean hasEnglishBundle(Collection<ResourceBundle> bundles) {
		return bundles
            .stream()
            .anyMatch(this::isEnglishBundle);
	}

	private boolean isEnglishBundle(ResourceBundle bundle) {
		return bundle
            .getLocale()
            .getLanguage()
            .equals(
                    new Locale("en").getLanguage()
            );
	}
}

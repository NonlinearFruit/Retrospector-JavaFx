package retrospector.javafx.bundles;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

public class BundleUtils {
	private static final String bundlePath = "retrospector.javafx.bundles.";

	public static Set<ResourceBundle> getResourceBundles(BundleType bundleType) {
		Set<ResourceBundle> bundles = new HashSet<>();
		for (Locale locale : Locale.getAvailableLocales()) {
			ResourceBundle bundle = tryToGetResourceBundle(bundleType, locale);
			if (bundle != null)
				bundles.add(bundle);
		}
		return bundles;
	}

  public static ResourceBundle getResourceBundle(BundleType type, String language) {
    return BundleUtils
            .getResourceBundles(type)
            .stream()
            .filter((b)->IsCorrectBundle(b, language))
            .findFirst()
            .get();
  }

	private static ResourceBundle tryToGetResourceBundle(BundleType bundleType, Locale locale) {
		try {
			return getResourceBundle(bundleType, locale);
		} catch(MissingResourceException ex) { }
		return null;
	}

	private static ResourceBundle getResourceBundle(BundleType bundleType, Locale locale) {
		String path = bundlePath + bundleType.name().toLowerCase();
		return ResourceBundle.getBundle(path, locale);
	}
	
	private static boolean IsCorrectBundle(ResourceBundle bundle, String language) {
		return bundle
            .getLocale()
            .getLanguage()
            .equals(
                    new Locale(language).getLanguage()
            );
	}
}

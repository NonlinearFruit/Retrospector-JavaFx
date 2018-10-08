package retrospector.javafx.bundles;

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
}

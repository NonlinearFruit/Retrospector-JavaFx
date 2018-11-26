package retrospector.javafx.view;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class ResourceBundleTestDouble extends ResourceBundle {

  @Override
  protected Object handleGetObject(String key) {
    return key;
  }

  @Override
  public Enumeration<String> getKeys() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}

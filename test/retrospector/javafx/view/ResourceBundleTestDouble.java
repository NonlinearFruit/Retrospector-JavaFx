package retrospector.javafx.view;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class ResourceBundleTestDouble extends ResourceBundle {
  private String filler = "x";

  @Override
  protected Object handleGetObject(String key) {
    return filler;
  }

  public String getFiller() {
    return filler;
  }

  @Override
  public Enumeration<String> getKeys() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}

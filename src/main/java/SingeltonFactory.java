import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by kuabhay on 7/30/17.
 */
public class SingeltonFactory {
  static final Injector injector = Guice.createInjector();
  public static Object getInstace(Class clazz) {
    return injector.getInstance(clazz);
  }
}

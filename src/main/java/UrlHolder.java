import com.google.inject.Singleton;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by kuabhay on 7/30/17.
 */
public class UrlHolder {

  private static final ConcurrentLinkedQueue<UrlInfo> queue = new ConcurrentLinkedQueue<UrlInfo>();

  public static void  insert(UrlInfo url) {
    queue.add(url);
  }

  public static UrlInfo next() {
    return queue.poll();
  }

  public static int size() {
    return queue.size();
  }

}

import lombok.Data;

/**
 * Created by kuabhay on 7/29/17.
 */
@Data
public class UrlInfo {
  private String urlName;
  private int depth;

  public UrlInfo(String urlName,int depth) {
    this.urlName=urlName;
    this.depth = depth;
  }

  public UrlInfo(String urlName) {
    this.urlName = urlName;
    this.depth = 0;
  }

}

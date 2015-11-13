package app.com.example.foodie.foodieandroid.Model;

/**
 * Created by Jennifer on 11/11/15.
 */
public class Image {
    private int image_id;
    private String url;

    public Image(int image_id, String url) {
        this.image_id = image_id;
        this.url = url;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package app.com.example.foodie.foodieandroid.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jennifer on 11/11/15.
 */
public class Image {
    private int image_id;
    private String urlString;
    private Bitmap image;

    private static final String TAG = "IMAGE";

    public Image(String urlString) {
        this.urlString = urlString;
        loadBitmap();
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getUrl() {
        return urlString;
    }

    public void setUrl(String urlString) {
        this.urlString = urlString;
    }

    public Bitmap loadBitmap() {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            image = BitmapFactory.decodeStream(input);
            Log.e(TAG,"Bitmap returned");
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,e.getMessage());
            return null;
        }
    }
}

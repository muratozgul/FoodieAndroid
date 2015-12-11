package app.com.example.foodie.foodieandroid.DAO;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by muratozgul on 10/12/15.
 */
public class GsonRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Type listType;
    private final boolean isList;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;

    public GsonRequest(String url, Class<T> classType, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.clazz = classType;
        this.isList = false;
        this.listType = null;
        this.headers = headers;
        this.listener = listener;
    }

    public GsonRequest(String url, Class<T> classType,
                        Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        headers = null;
        this.clazz = classType;
        this.isList = false;
        this.listType = null;
        this.listener = listener;
    }

    public GsonRequest(String url, Type listType,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        headers = null;
        this.clazz = null;
        this.isList = true;
        this.listType = listType;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            T parsedObject = null;

            if(isList){
                parsedObject = gson.fromJson(json, listType);
            } else {
                parsedObject = gson.fromJson(json, clazz);
            }

//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            return Response.success(parsedObject, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

}

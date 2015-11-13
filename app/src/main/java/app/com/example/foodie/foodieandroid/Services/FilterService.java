package app.com.example.foodie.foodieandroid.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FilterService extends Service {
    public FilterService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

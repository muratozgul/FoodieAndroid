package app.com.example.foodie.foodieandroid.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import app.com.example.foodie.foodieandroid.Application.FoodieApp;
import app.com.example.foodie.foodieandroid.R;

public class HomeActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView customerImg;
    private TextView checkoutButton;
    private View header;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        header = navigationView.inflateHeaderView(R.layout.nav_header_home);
        customerImg = (ImageView) header.findViewById(R.id.customerImg);
        ImageView camera = (ImageView) header.findViewById(R.id.camera);
        customerImg.setOnClickListener(this);
        camera.setOnClickListener(this);

        findViewById(R.id.chef_list).setOnClickListener(this);
        findViewById(R.id.dish_list).setOnClickListener(this);
        findViewById(R.id.categories).setOnClickListener(this);
        findViewById(R.id.location_map).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checkout, menu);

        View checkout = menu.findItem(R.id.checkout).getActionView();
        checkoutButton = (TextView) checkout.findViewById(R.id.cart_qty);

        int cartSize = FoodieApp.getInstance().getCart().size();
        checkoutButton.setText(Integer.toString(cartSize));

        checkout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CheckoutActivity.class);
                context.startActivity(intent);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.checkout) {
            Intent checkoutIntent = new Intent(this, CheckoutActivity.class);
            startActivity(checkoutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        //Customer Orders (History)
        if (id == R.id.nav_orders) {
            Intent ordersActivity = new Intent(this, OrdersActivity.class);
            startActivity(ordersActivity);
        }

        // TODO: Review History
        else if (id == R.id.nav_review) {
            Toast.makeText(getApplicationContext(), "This will display review history!",
                    Toast.LENGTH_SHORT).show();
        }

        // TODO: User settings
        else if (id == R.id.nav_settings) {
            Toast.makeText(getApplicationContext(), "This will display settings!",
                    Toast.LENGTH_SHORT).show();
        }

        // Share with friends
        else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "", null));
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Try this awesome Foodie app!");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Look what I found! \n An awesome foodie app!");
            startActivity(Intent.createChooser(shareIntent, "Send email..."));
        }

        // Send us feedback
        else if (id == R.id.nav_send) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "foodie@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "[Feedback]");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Foodies, \n...");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }
        // About foodie
        else if (id == R.id.nav_about) {
            String url = "https://github.com/muratozgul/FoodieAndroid";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Icons on main page
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chef_list:
                Intent chefActivity = new Intent(this, ChefActivity.class);
                startActivity(chefActivity);
                break;
            case R.id.dish_list:
                Intent dishMenuActivity = new Intent(this, DishMenuActivity.class);
                startActivity(dishMenuActivity);
                break;
            case R.id.categories:
                Intent categoryActivity = new Intent(this, CategoryActivity.class);
                startActivity(categoryActivity);
                break;
            case R.id.location_map:
                Intent locationMapActivity = new Intent(this, MapsActivity.class);
                startActivity(locationMapActivity);
                break;
            // When user click customer image or camera icon, it will invoke the camera
            case R.id.customerImg:
            case R.id.camera:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
                break;
            default:
                Toast.makeText(this, getResources().getResourceEntryName(v.getId()) + " clicked", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    // Return image captured by camera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            customerImg.setImageBitmap(getCircularBitmapWithWhiteBorder(imageBitmap, 6));
        }
    }

    // Display user image in a round circle
    public static Bitmap getCircularBitmapWithWhiteBorder(Bitmap bitmap,
                                                          int borderWidth) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }

        final int width = bitmap.getWidth() + borderWidth;
        final int height = bitmap.getHeight() + borderWidth;

        Bitmap canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        Canvas canvas = new Canvas(canvasBitmap);
        float radius = width > height ? ((float) height) / 2f : ((float) width) / 2f;
        canvas.drawCircle(width / 2, height / 2, radius, paint);
        paint.setShader(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(borderWidth);
        canvas.drawCircle(width / 2, height / 2, radius - borderWidth / 2, paint);
        return canvasBitmap;
    }
}

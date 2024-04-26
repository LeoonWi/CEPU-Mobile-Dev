package com.example.appvolley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    OkHttpClient client;
    ImageButton buttonPrev,buttonNext;
    ImageView imageView;
    List<String> listOfCats;
    private LruCache<String, Bitmap> imageCache;

    private static final String Url = "https://api.thecatapi.com/v1/images/search";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        // Initialize cache with maximum of 10 entries (adjust as needed)
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        imageCache = new LruCache<>(cacheSize);

        client = new OkHttpClient();
        listOfCats = new ArrayList<>();
        buttonNext = findViewById(R.id.ivNext);
        buttonPrev = findViewById(R.id.ivPrevious);
        buttonNext.setOnClickListener(v -> getNext());
        buttonPrev.setOnClickListener(v -> getPrev());
        imageView = findViewById(R.id.vpSlider);

    }
    private void getNext() {
        Request request = new Request.Builder().url(Url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {e.printStackTrace();}

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //Принимаю JSON файл со всеми ключами
                            String jsonResponse = response.body().string();
                            JSONArray jsonArray = new JSONArray(jsonResponse);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String imageUrl = jsonObject.getString("url");
                            //загружаю ключ url, взятую из JSON файла, в imageView
                            listOfCats.add(imageUrl);
                            loadImage(imageUrl);
                        }
                        catch (IOException | JSONException e) {e.printStackTrace();}
                    }
                });
            }
        });
    }
    private void getPrev() {
        if (listOfCats.size() > 1) {
            listOfCats.remove(listOfCats.size() - 1);
            String prevImageUrl = listOfCats.get(listOfCats.size() - 1);
            Bitmap prevImage = imageCache.get(prevImageUrl);
            if (prevImage != null) {
                imageView.setImageBitmap(prevImage);
            } else {
                loadImage(prevImageUrl);
            }
        }
    }
    private void loadImage(String imageUrl) {
        Bitmap cachedImage = imageCache.get(imageUrl);
        if (cachedImage != null) {
            imageView.setImageBitmap(cachedImage);
        } else {
            // If image is not in cache, download it and add to cache
            Request request = new Request.Builder().url(imageUrl).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    imageCache.put(imageUrl, bitmap);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }
            });
        }
    }
}
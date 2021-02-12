package com.example.retrofittestrxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableScalarXMap;
import io.reactivex.rxjava3.internal.schedulers.RxThreadFactory;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private Adapter mAdapter;

    private ArrayList<String> productsImageString=new ArrayList<>();


    private RecyclerView mNumbersList;

    private URL weatherRequestUrl;

    ArrayList<Product> products=new ArrayList<>();

    static Bitmap noImageBitmap ;
    private Object RxThreadFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumbersList = findViewById(R.id.rv_numbers);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://budmagas.herokuapp.com/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductApiRetrofit productApiRetrofit=retrofit.create(ProductApiRetrofit.class);


        Observable <List<Product>> productObservable=productApiRetrofit.getProductsObservable();

        final int[] ii = {0};

        productObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .  subscribe( (List<Product> s) -> {System.out.println(s.get(0).getName());

                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                mNumbersList.setLayoutManager(layoutManager);
                mAdapter = new Adapter(s);
                mNumbersList.setAdapter(mAdapter);
                mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                                                 @Override
                                                 public void onChanged() {
                                                     super.onChanged();
                                                 }
                                             }
        );


                            Observable <Bitmap> productObservableImage =Observable.create(img->{

                        for(Product g : s)

                            img.onNext(getBitmap("https://budmagas.herokuapp.com/get-image?image=" + g.getImage()));


                    });

                            productObservableImage.subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<Bitmap>() {
                                           @Override
                                           public void onSubscribe(@NonNull Disposable d) {

                                           }

                                           @Override
                                           public void onNext(@NonNull Bitmap bitmap) {

                                               s.get(ii[0]).setImageBitmap(bitmap);
                                               mAdapter.notifyDataSetChanged();
                                               ii[0]++;

                                           }

                                           @Override
                                           public void onError(@NonNull Throwable e) {

                                               e.printStackTrace();

                                           }

                                           @Override
                                           public void onComplete() {
                                           }
                                  }
                            );

                },
                        (Throwable::printStackTrace));



//        Call<List<Product>> messages = productApiRetrofit.getProducts();
//
//        messages.enqueue(new Callback<List<Product>>() {
//            @Override
//            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//
//                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//                mNumbersList.setLayoutManager(layoutManager);
//                mAdapter = new Adapter(response);
//                mNumbersList.setAdapter(mAdapter);
//                mAdapter = new Adapter( response);
//
//                System.out.println("response " + response.getClass());
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t) {
//
//            }
//        });
//



//        Observable<String> obString=  Observable.fromCallable(() -> {
//
//            return getResponse();
//        });
//
//
//        obString.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
//
//                (String st)->{
//                    products= JsonUtils.getDetailsFromJson(st, "VseDlyaRemontu");
//                    productsImageString=JsonUtils.getImageStringFromJson(st, "VseDlyaRemontu");
//                    mAdapter = new Adapter(products);
//                    mNumbersList.setAdapter(mAdapter);
//
//
//                    final int[] ii = {0};
//
////
//
//                    Observable<Bitmap>bn=Observable.create(s->{
//
//                        for(String g : productsImageString)
//
//
//                            s.onNext(getBitmap("https://budmagas.herokuapp.com/get-image?image=" + g));
//
//
//                    });
//
//
//                    bn.subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Observer<Bitmap>() {
//                                           @Override
//                                           public void onSubscribe(@NonNull Disposable d) {
//
//                                           }
//
//                                           @Override
//                                           public void onNext(@NonNull Bitmap bitmap) {
//
//                                               products.get(ii[0]).setImageBitmap(bitmap);
//                                               mAdapter.notifyDataSetChanged();
//                                               ii[0]++;
//
//                                           }
//
//                                           @Override
//                                           public void onError(@NonNull Throwable e) {
//
//                                               e.printStackTrace();
//
//                                           }
//
//                                           @Override
//                                           public void onComplete() {
//                                           }
//                                  }
//                            );
//                });
//
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//        mNumbersList.setLayoutManager(layoutManager);
//        mAdapter = new Adapter(products);
//        mNumbersList.setAdapter(mAdapter);
//        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//                                                 @Override
//                                                 public void onChanged() {
//                                                     super.onChanged();
//                                                 }
//                                             }
//        );
//
   }

//
//    public String getResponse()
//    {
//        try {
//            weatherRequestUrl = new URL("https://budmagas.herokuapp.com/retrofittest");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//
//        String jsonWeatherResponse = "";
//
//        try {
//            jsonWeatherResponse = getResponseFromHttpUrl(weatherRequestUrl); // json строка з сервера
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return jsonWeatherResponse;
//    }
//


    public static Bitmap getBitmap(String url) throws IOException {
        try {

            InputStream is = (InputStream) new URL(url).getContent();



            return BitmapFactory.decodeStream(is);
        } catch (Exception e) {

            return noImageBitmap;
        }
    }

//    public  String getResponseFromHttpUrl(URL url) throws IOException {
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        try {
//            InputStream in = urlConnection.getInputStream();
//
//            Scanner scanner = new Scanner(in);
//            scanner.useDelimiter("\\A");
//
//            boolean hasInput = scanner.hasNext();
//            if (hasInput) {
//            //    System.out.println(scanner.next());
//                return scanner.next();
//            } else {
//                return null;
//            }
//        } finally {
//            urlConnection.disconnect();
//        }
//    }
}

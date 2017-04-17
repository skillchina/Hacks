package club.iandroid.hack50.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import club.iandroid.hack50.R;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxjavaActivity extends AppCompatActivity {

    private static final String TAG = "RxjavaActivity";

    private Subscriber<String> subscriber;
    private Observable<String> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

//        Flowable.just("hello world").subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Toast.makeText(RxjavaActivity.this, s, Toast.LENGTH_SHORT).show();
//            }
//        });
        createSubscriber();

        findViewById(R.id.btn_subscribe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createObservable();
            }
        });
    }

    private void bindSubscriber() {
        observable.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe();
    }

    private void createSubscriber() {
        /**
         * 创建观察者
         */
        subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext");
                Toast.makeText(RxjavaActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted");
            }
        };
    }

    private void createObservable() {
        /**
         * 创建一个被观察者（发布者）
         */
        observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                subscriber.onNext(getHello() + "1");
                subscriber.onNext(getHello() + "2");
                subscriber.onNext(getHello() + "3");
                subscriber.onNext(getHello() + "4");

                subscriber.onComplete();
            }
        });
        bindSubscriber();

    }

    private String getHello() {
        return "Hello RxAndroid";
    }
}

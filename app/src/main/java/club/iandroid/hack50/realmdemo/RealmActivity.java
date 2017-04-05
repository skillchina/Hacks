package club.iandroid.hack50.realmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import club.iandroid.hack50.R;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

/**
 * realm测试
 */
public class RealmActivity extends AppCompatActivity {

    public static final String TAG = RealmActivity.class.getName();
    private LinearLayout rootLayout = null;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);

        rootLayout = ((LinearLayout) findViewById(R.id.container));
        rootLayout.removeAllViews();

//        realm = Realm.getDefaultInstance();

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("test.realm")//文件名
                .schemaVersion(1)//版本号
//                .encryptionKey()//指定数据库的密钥
//                .migration()//指定迁移操作的迁移类
//                .deleteRealmIfMigrationNeeded()//声明版本冲突时自动删除原数据库
//                .inMemory()//声明数据库只在内存中持久化
                .build();
        realm = Realm.getInstance(configuration);
        basicCRUD(realm);

        initViews();
    }

    private void showStatus(String txt) {
        Log.i(TAG, txt);
        TextView tv = new TextView(this);
        tv.setText(txt);
        rootLayout.addView(tv);
    }

    private Person personBean;

    private void initViews(){
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = realm.executeTransactionAsync(
                        new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
//                realm.copyToRealmOrUpdate(person);

                                Person person = realm.createObject(Person.class);
                                person.setId(1);
                                person.setName("tom");
                                person.setAge(14);
                            }
                        },
                        new Realm.Transaction.OnSuccess() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(RealmActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Realm.Transaction.OnError() {
                            @Override
                            public void onError(Throwable error) {
                                Log.e(TAG, error.getMessage());
                            }
                        }
                );
            }
        });
        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(personBean == null){
                    return;
                }
                realm.executeTransaction(new Realm.Transaction(){
                    @Override
                    public void execute(Realm realm) {
                        personBean.setName("bruce");
                        personBean.setAge(99);
                        showStatus(personBean.getName()+" got older:"+personBean.getAge());
                    }
                });
            }
        });
        findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doQuery();
            }
        });
        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.delete(Person.class);
                    }
                });
            }
        });
    }

    private RealmAsyncTask transaction;

    private void doQuery(){
        RealmQuery query = realm.where(Person.class);
        if(query.count()>0) {
            personBean = realm.where(Person.class).findFirst();
            showStatus(personBean.getName() + " : " + personBean.getAge());
        }else {
            showStatus("no data");
        }
    }

    private void basicCRUD(final Realm realm) {
        showStatus("操作 基本的 增、删、改、查 操作");

//        final Person person = new Person();
//        person.setId(1);
//        person.setName("tom");
//        person.setAge(14);

        //事务执行
        /*realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
//                realm.copyToRealmOrUpdate(person);

                Person person = new Person();
                person.setId(1);
                person.setName("tom");
                person.setAge(14);
            }
        });*/





        //update person in a transaction
        /*realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
                personBean.setName("bruce");
                personBean.setAge(99);
                showStatus(personBean.getName()+" got older:"+personBean.getAge());
            }
        });

        //delete all persons
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Person.class);
            }
        });*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(transaction!=null && !transaction.isCancelled()){
            transaction.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}

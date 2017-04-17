package club.iandroid.hack50.collection.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.mydemo.R;
import com.example.mydemo.util.DataBaseHelper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;


public class SQLiteActivity extends ActionBarActivity {

    private EditText txt_name;
    private EditText txt_age;
    private Button btn_save;
    private Button btn_update;
    private ListView myListView;

    private DataBaseHelper dbhelper;
    private SQLiteDatabase db;
    private ArrayList<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        //SQLiteDatabase db = openOrCreateDatabase("user.db", MODE_PRIVATE, null);

        txt_name = (EditText)findViewById(R.id.txt_name);
        txt_age = (EditText)findViewById(R.id.txt_age);
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_update = (Button)findViewById(R.id.btn_update);
        myListView = (ListView)findViewById(R.id.myListView);
        //数据库文件自动会保存在程序的数据文件夹的databases目录下
        dbhelper = new DataBaseHelper(this, "mysqlitedb.db3",4);
        //db = dbhelper.getReadableDatabase();//只能查询 不能写入 不能更新
        db = dbhelper.getWritableDatabase();
        /*dbhelper.onUpgrade(dbhelper.getReadableDatabase(),dbhelper.getReadableDatabase().getVersion(),
                dbhelper.getReadableDatabase().getVersion()+1);*/
        list = new ArrayList<Map<String, Object>>();
        searchData();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = txt_name.getText().toString();
                    int age = Integer.parseInt(txt_age.getText().toString());

                    //插入数据
                    insertData(name, age);

                    searchData();
                } catch (Exception e) {
                    Log.e("SQlite", e.getMessage());
                }
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = txt_name.getText().toString();
                    int age = Integer.parseInt(txt_age.getText().toString());

                    //插入数据
                    updateData(name, age);

                    searchData();
                }catch (Exception e){
                    Log.e("SQlite",e.getMessage());
                }
            }
        });

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = list.get(position);
                String _id = item.get("_id").toString();
                db.delete("user", "_id=?", new String[]{_id});
                searchData();
                return true;
            }
        });
    }

    private void insertData(String name, int age){
        //db.execSQL("insert into user values(null,?,?,?)",new Object[]{name, age,"Male"});
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("age", age);
        values.put("sex", "Male");
        db.insert("user", null, values);
    }

    private void updateData(String name, int age){
        //db.execSQL("insert into user values(null,?,?,?)",new Object[]{name, age,"Male"});
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("age",age);
        values.put("sex","Female");
        db.update("user",values,"_id>?", new String[]{"3"});

    }

    private void searchData(){
        Cursor cursor = db.rawQuery("select * from user",null);
        if(cursor!=null) {
            list.clear();
            while (cursor.moveToNext()) {
                Map<String, Object> map = new Hashtable<String, Object>();
                map.put("_id",cursor.getInt(cursor.getColumnIndex("_id")));
                map.put("name", cursor.getString(1));
                map.put("age", cursor.getInt(cursor.getColumnIndex("age")));
                if (cursor.isNull(3)) {
                    map.put("sex", "");
                } else {
                    map.put("sex", cursor.getString(3));
                }


                list.add(map);
            }
            cursor.close();

            SimpleAdapter adapter = new SimpleAdapter(SQLiteActivity.this, list,
                    R.layout.line, new String[]{"name", "age", "sex"}, new int[]{R.id.name, R.id.age, R.id.sex});
            myListView.setAdapter(adapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dbhelper!=null){
            dbhelper.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sqlite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

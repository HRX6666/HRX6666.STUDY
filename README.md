# **Activity**

- [ ] ```
  setContentView(R.layout.**activity_second**);
  需要和LAYOUT>XML文件名一样
  
  ```

### Menu

res下建一个menu的Directory在menu下建Menu resource file

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:id="@+id/list_item"
        android:title="ListView效果"/>//菜单名和显示文字
    <item android:id="@+id/recycler_item"
        android:title="RecyclerView效果"/>
    <item  android:id="@+id/waterfall_item"
        android:title="瀑布流效果"/>

</menu>
在MainActivity中复写onCreateOptionsMenu方法并添加
getMenuInflater().inflate(R.menu.menu,menu);
```











## 转换更多INTENT方法

1. 在<activity>标签下配置<intent-filter>内容，可指定当前活动可以响应action和category，打开AndroidManifest.xml

2. 贴入以下代码

3. ```
   <intent-filter>
       <action android:name="com.example.activitytest.ACTION_START" />
   
       <category android:name="android.intent.category.DEFAULT"/>
       <category android:name="com.example.activitytest.MY_CATEGORY" /> />
   </intent-filter>
   ```

3.修改需要改动页面的按钮点击事件

```
button1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:110" ));
        startActivity(intent);
    }
});//跳转到拨打电话的页面


button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(“com.example.activitytest.ACTTON.START");
                intent.addCategory("com.example.activitytest.MY_CAEGORY");
                startActivity(intent);
            }
        });//从这个页面跳转到下一个页面
        
        
button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VOEW);
                intent.setData(Uri.parse("https://zhuanlan.zhihu.com/p/430975159"));
                startActivity(intent);
            }
        });//跳转到一个网页去
        
button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:38.899577.03647633,-" ));
                startActivity(intent);
            }
        });//打开地图
        更多详情[链接http://t.csdn.cn/imGmP


    
```

##### firstactivity

```
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data="Hello SecondActivity";
                Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
                intent.putExtra("extra_data",data );
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"对不起跑不出去了",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"由于程序被怪兽吃掉，退出失败",Toast.LENGTH_SHORT).show();
                break;
            case R.id.recharge_item:
                Toast.makeText(this,"涉嫌洗钱被逮捕",Toast.LENGTH_SHORT).show();
                break;
            default:

        }
        return true;
    }

}
```

##### Second Activity

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

​    @Override
​    protected void onCreate(Bundle savedInstanceState) {
​        super.onCreate(savedInstanceState);
​        setContentView(R.layout.activity_second);
​        Intent intent=getIntent();
​        String data= intent.getStringExtra("extra_data");
​        Log.d("SecondActivity",data);
​    }
}
**主页面标签**
<intent-filter>
​                <action android:name="android.intent.action.MAIN" />
​                <category android:name="android.intent.category.LAUNCHER" />
​            </intent-filter>

### UI页面显示

```
在XML中修改
android:gravity="center"//字居中显示
android:textSize="29sp"//字号
android:textColor="#FF03DAC5"//颜色
 <EditText
            android:id="@+id/edit_text"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"//插入输入对话框
            android:hint="Type something here"/>提示性文字
 在MainActivity中修改
 public class MainActivity extends AppCompatActivity **implements View.OnClickListener**{
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button_3);
     **editText = (EditText) findViewById(R.id.edit_text);**
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_3:
        **String inputText=editText.getText().toString();**         **Toast.makeText(MainActivity.this,inputText,Toast.LENGTH_SHORT).show();**
            default:
                break;
        }
    }

}

在页面中添加照片
1.在drawable中复制照片，命名以.png或.xml结尾
2.在activity_main.xml中添加
 <ImageView
        android:id="@+id/iamge_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
       android:src="@drawable/img_1"/> //img_1为照片名。
点击按钮更换照片
1.在drawable中复制照片，命名以.png或.xml结尾
2.添加
  private ImageView imageView;
        imageView=(ImageView)findViewById(R.id.iamge_view);
        在switch语句中添加：
         imageView.setImageResource(R.drawable.img_2);
 点击按钮实现缓冲符号的显示和消失
 添加
  private ProgressBar progressBar;
  progressBar=(ProgressBar) findViewById(R.id.progress_bar);
   switch(v.getId()){
            case R.id.button_3:
                if(progressBar.getVisibility()==View.GONE){
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    progressBar.setVisibility((View.GONE));
                }
            default:
                break;
        }
      **Progressbar用法**//各种加载样式  详情见
      http://t.csdn.cn/kYnep
      
添加浮窗显示重要提醒(AlertDialog)
@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_3:
                imageView.setImageResource(R.drawable.img_2);
               ** AlertDialog.Builder dialog=new       AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("谢谢你的点击！");
                dialog.setMessage("我来查岗喽");
                dialog.setCancelable(false);
                dialog.setPositiveButton("好捏",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                    }
                });
                dialog.show();**
                break;
            default:
                break;
        }
    }
提醒用户耐心等待(ProgressDialog)
 switch (v.getId()) {
            case R.id.button_3:
                imageView.setImageResource(R.drawable.img_2);
                ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("我来查岗喽！");
                progressDialog.setMessage("诶呀卡住了");
        progressDialog.setCancelable(true);//如果为false则break键不能取消掉提示
                progressDialog.show();
                break;
            default:
                break;
        }
  对话框适配效果：
  <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Button
        android:id="@+id/start_normal_activity"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/start_normalactivity" />

    <Button
        android:id="@+id/start_dialog_activity"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/start_dialogactivity">

    </Button>
        <EditText
            android:id="@+id/edit_text"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="在这里输入文字欧"/>
    <Button
        android:id="@+id/button_3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="28sp"
        android:textColor="#FF03DAC5"
        android:text="点我点我点我！" />
    <ImageView
        android:id="@+id/iamge_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
       android:src="@drawable/img_1"/>
    <ProgressBar
        android:id="@+id/progress_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
</LinearLayout>

相对布局：(RelativeLayout)
以下是围绕页面布置的按钮：
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <Button
        android:id="@+id/button_1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentLeft="true"
        android:layout_alignParentTop="true"
        android:text="点我！" />//左上

    <Button
        android:id="@+id/button_2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"
        android:layout_alignParentTop="true"
        android:text="点我！">//右上
    </Button>
    <Button
        android:id="@+id/button_3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="点我点我点我！" />//中间
    <Button
        android:id="@+id/button_4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_alignParentLeft="true"
        android:text="点我！" />//左下
    <Button
        android:id="@+id/button_5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_alignParentRight="true"
        android:text="点我！" />//右下
    <ImageView
        android:id="@+id/iamge_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/img_2"/>//背景图
    <ProgressBar
        android:id="@+id/progress_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
</RelativeLayout>
以下是围绕一个按钮布置的界面：
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <Button
        android:id="@+id/button_3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="点我！" />

    <Button
        android:id="@+id/button_1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        **android:layout_above="@id/button_3"
        android:layout_toLeftOf="@id/button_3"**
        android:text="点我1！">
    </Button>
    <Button
        android:id="@+id/button_2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
       ** android:layout_above="@id/button_3"
        android:layout_toRightOf="@id/button_3"**
        android:text="点我点我点我！" />
    <Button
        android:id="@+id/button_4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
       ** android:layout_below="@id/button_3"
        android:layout_toLeftOf="@id/button_3"**
        android:text="点我！" />
    <Button
        android:id="@+id/button_5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        **android:layout_below="@id/button_3"
        android:layout_toRightOf="@id/button_3"**
        android:text="点我！" />
    <ImageView
        android:id="@+id/iamge_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/img_2"/>
    <ProgressBar
        android:id="@+id/progress_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
</RelativeLayout>

帧布局(FrameLayout)
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <TextView
        android:id="@+id/test_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="left"//text位置居左对齐
        android:textSize="29dp"//字体大小
        android:text="This is a ......."/>
    <ImageView
        android:id="@+id/iamge_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="right"//居右对齐
        android:src="@mipmap/ic_launcher"/>//图标图案
</FrameLayout>
		LIst View
		实现可以上下滑动在MAIN Activity中添加

public class MainActivity extends AppCompatActivizty {
    
    private String[] data = {"Tizzy T","Trouble Z","JSore","Only J","Pow","Slient","Gs",
            "Lil_Jet","DouDou","沙一汀","Doc","空匪","Gai"};
    @Override
    protected void onCreate(Bundle saveInstanceStance){
        super.onCreate(saveInstanceStance);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(
        MainActivity.this, android.R.layout.simple_list_item_1,data);
        ListView listView=(ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);
    }
}

比List View 强大的滚动控件RecyclerView
1.**非常非常非常重要的一点！**
打开app/build.gradle文件，在dependencies闭包中添加recyclerview依赖：implementation'androidx.recyclerview:recyclerview:1.1.0'
点击Sync Now同步
2.修改activity_main,xml文件
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
</LinearLayout>
3.为RecyclerView准备一个适配器，新建类让这个适配器继承自RecyclerView.Adapter并泛指到RapperAdapter.ViewHolder
ViewHolder是内部类
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RapperAdapter extends RecyclerView.Adapter<RapperAdapter.ViewHolder> {
    private List<Rapper>mRapperList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView rapperImage;
        TextView rapperName;

        public ViewHolder(View view) {
            super(view);
            rapperImage = (ImageView) view.findViewById(R.id.rapper_image);
            rapperName=(TextView)view.findViewById(R.id.rapper_name);
        }
    }
    public RapperAdapter(List<Rapper> rapperList){
        mRapperList=rapperList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rapper_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Rapper rapper=mRapperList.get(position);
        holder.rapperImage.setImageResource(rapper.getImageId());
        holder.rapperName.setText(rapper.getName());
    }
    @Override
    public int getItemCount(){

        return mRapperList.size();//告诉RecyclerView有多少项子项
    }
}

4.使用RecyclerView修改MainActivity代码：

package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Rapper> rapperList=new ArrayList<>();
    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRappers();//初始化数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RapperAdapter adapter = new RapperAdapter(rapperList);
        recyclerView.setAdapter(adapter);
    }
        private void initRappers(){
        for (int i=0;i<2;i++){
            Rapper TizzyT=new Rapper("TizzyT", R.drawable.t);
            rapperList.add(TizzyT);
            Rapper  TroubleZ=new Rapper("TizzyT",R.drawable.gtz);
            rapperList.add(TroubleZ);
            Rapper JSore=new Rapper("TizzyT",R.drawable.zjs);
            rapperList.add(JSore);
            Rapper OnlyJ=new Rapper("TizzyT", R.drawable.jc);
            rapperList.add(OnlyJ);
        }
    }
}
横着滑动
在fruit_item.xml中加入
LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="100dp"
    android:layout_height="wrap_content">
    <ImageView
        android:id="@+id/rapper_image"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"/>
    <TextView
        android:id="@+id/rapper_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_vertical"
        android:layout_marginLeft="10dp"/>
</LinearLayout>

Main ACTIVITY中加入
layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
RecyclerView的点击事件，即插入Toast方法，详情见前面.


```

区别getSharedPreferences与getDefaultSharedPreferences

http://t.csdn.cn/KLU8E









### LitePal数据库

1. 在app/build.gradle中加入依赖                                                          implementation 'org.litepal.guolindev:core:3.1.1'。

2. 配置LitePal                                                                                                                                        app/src/main下新建 assets目录litepal.xml                                                                      <?xml version="1.0" encoding="UTF-8" ?>
   <litepal>
       <dbname value="BookStore"></dbname>
       <version value="1"></version>
       <list>
         <mapping class="com.examlple.app.Book"></mapping>
       </list>
   </litepal>

   在AndroidManifest.xml添加android:name                                    <application

   android:name="org.litepal.LitePalApplication"
   ...
   </activity>
   </application>

   实现登录注册记住密码的操作：

   注册界面：Enroll;

   ```java
   package com.example.app;
   
   import android.os.Bundle;
   import android.view.View;
   
   import android.widget.Toast;
   import android.content.Intent;
   import android.widget.*;
   
   import androidx.appcompat.app.AppCompatActivity;
   
   import org.litepal.tablemanager.Connector;
   
   
   public class Enroll extends AppCompatActivity implements View.OnClickListener {
       private Button register;
       private EditText username,userpassord;
   
   @Override
       protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       ActivityCollector.addActivity(this);
       setContentView(R.layout.enroll);
        username= (EditText) findViewById(R.id.username1);
       userpassord = (EditText) findViewById(R.id.password);
   
       register = (Button) findViewById(R.id.go_to_main);
       Connector.getDatabase();
       register.setOnClickListener(this);
   }
              public void onClick(View v) {
   
                  if (v.getId() == R.id.go_to_main) {
   
                      String name = username.getText().toString();
                      String password = userpassord.getText().toString();
                      password = SHA256.md5(password);
                      Toast mToast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
   
   
                          User user = new User();
                          user.setUsername(name);
                          user.setPassword(password);
                          user.setRemember(0);
                          user.save();
   
                              mToast.setText("注册成功");
                              mToast.show();
                              Intent intent = new Intent(Enroll.this, Enter.class);
                              startActivity(intent);
   
   
                  }
              }
   
   
   
       }
   ```

   登录界面，Enter：

   ```java
   package com.example.app;
   
   import android.content.Intent;
   import android.content.SharedPreferences;
   import android.preference.PreferenceManager;
   import android.util.Log;
   import android.view.View;
   import android.widget.EditText;
   
   import java.util.List;
   import android.widget.*;
   import android.widget.Toast;
   import android.os.Bundle;
   
   import androidx.appcompat.app.AppCompatActivity;
   
   import org.litepal.LitePal;
   
   public class Enter extends AppCompatActivity implements View.OnClickListener {
       boolean login_flag,isRemenber;
       private EditText loginUsername;
       private EditText loginPassword;
       private SharedPreferences.Editor editor;
       private Button login,registe;
       String account,password;
       private CheckBox rememberPassword;
       private SharedPreferences pref;
   
       @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.enter);
           ActivityCollector.addActivity(this);
           login=(Button) findViewById(R.id.enter);
           rememberPassword=(CheckBox)findViewById(R.id.remeber_password);
           registe=(Button) findViewById(R.id.change_enroll);
           loginUsername= (EditText) findViewById(R.id.loginName);
           loginPassword=(EditText) findViewById(R.id.loginPassword);
           pref=PreferenceManager.getDefaultSharedPreferences(this);
           isRemenber = pref.getBoolean("remanber_password", false);
   
           rememberPassword.setOnClickListener(this);
           login.setOnClickListener(this);
           registe.setOnClickListener(this);//监听
           if(!isRemenber) {
               account = pref.getString("username", "");
               password = pref.getString("password", "");
               loginUsername.setText(account);
               loginPassword.setText(password);
               rememberPassword.setChecked(true);
           }
               List<User> users=LitePal.findAll(User.class);
               for(User u:users){
                   if(isRemenber){
                       Intent intent1=new Intent(Enter.this,MusicMain.class);
                       startActivity(intent1);
                       Toast.makeText(Enter.this, "账户" + u.getUsername() + "登陆成功",Toast.LENGTH_SHORT).show();
                       break;
                   }
               }
   
       }
   
           @Override
       public void onClick(View v) {
               String name = loginUsername.getText().toString();
               String password = loginPassword.getText().toString();
               if (v.getId() == R.id.change_enroll) {
                   Intent intent = new Intent(Enter.this, Enroll.class);
                   startActivity(intent);
               } else if (v.getId() == R.id.enter) {
                   login_flag = false;
                   User user= LitePal.where("username=?",name).findLast(User.class);
                   if (password.equals("12345678")) password = user.getPassword();
                   if (user.checkPassword(password)) {
                       editor=pref.edit();
                       if (rememberPassword.isChecked()) {
   
                         editor.putBoolean("remeber_password",true);
                         editor.putString("username",name);
                         editor.putString("password",password);
                           editor.apply();
                       } else {
                           editor.putBoolean("remeber_password",false);
                           editor.apply();
                           user.setRemember(0);
                       }
                       user.update(user.getId());
                       LoginUser.getInstance().login(user);
                       Intent intent1 = new Intent(Enter.this, MusicMain.class);
                       startActivity(intent1);
                       finish();
                       login_flag = true;
                       Toast.makeText(Enter.this, "账户" + user.getUsername() + "登陆成功", Toast.LENGTH_SHORT).show();
                   } else {
                       user.setRemember(0);
                   }
                   if (login_flag == false) {
                       Toast.makeText(Enter.this, "登录失败", Toast.LENGTH_SHORT).show();
   
                   }
   
               }
           }
   
   }
   ```

   短暂储存数据LoginUser:

   ```java
   package com.example.app;
   
   import android.app.Application;
   
   public class LoginUser extends Application {
       private static LoginUser loginUser = new LoginUser();
       private static User _user;
       private int id;
       private String username;
       private byte[] portrait;
       private String region;
       private String gender;
       private String brithday;
   
       public static LoginUser getInstance() {
           return loginUser;
       }
   
       private User getUser() {
           return _user;
       }
   
       public void update() {
           if (_user.getId() == this.id) {
               _user.setUsername(this.username);
               _user.setPortrait(this.portrait);
               _user.setGender(this.gender);
               _user.setRegion(this.region);
               _user.setBrithday(this.brithday);
               _user.update(_user.getId());
           }
       }
   
       public void reinit() {
           loginUser.id = _user.getId();
           loginUser.username = _user.getUsername();
           loginUser.portrait = _user.getPortrait();
           loginUser.region = _user.getRegion();
           loginUser.gender = _user.getGender();
           loginUser.brithday = _user.getBrithday();
       }
   
       public boolean login(User user) {
           _user = user;
           loginUser.id = _user.getId();
           loginUser.username = _user.getUsername();
           loginUser.portrait = _user.getPortrait();
           loginUser.region = _user.getRegion();
           loginUser.gender = _user.getGender();
           loginUser.brithday = _user.getBrithday();
           return true;
       }
   
       private static boolean logout() {
           if (loginUser.id == -1) {
               return false;
           }
           loginUser.id = -1;
           loginUser.username = null;
           loginUser.portrait = null;
           loginUser.region = null;
           loginUser.gender = null;
           loginUser.brithday = null;
           return true;
       }
   
       public String toString() {
           return "LoginUser{" +
                   "id=" + id +
                   ", name='" + username + '\'' +
                   ", portrait ='" + portrait + '\'' +
                   ", region='" + region + '\'' +
                   ", gender='" + gender + '\'' +
                   ", brithday='" + brithday + '\'' +
                   '}';
   
       }
   }
   ```

   主界面 MainActivity:

   ```java
   package com.example.app;
   
   import androidx.appcompat.app.AppCompatActivity;
   
   import android.content.Intent;
   import android.os.Bundle;
   import android.view.View;
   import android.widget.Button;
   
   public class MainActivity extends AppCompatActivity {
       private Button mEnter;
       private Button mEnroll;
   
       @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);
           mEnter = findViewById(R.id.enter);
           mEnroll=findViewById(R.id.enroll);
           setListeners();
       }
       private void setListeners() {
           OnClick onClick = new OnClick();
           mEnter.setOnClickListener(onClick);
           mEnroll.setOnClickListener(onClick);
       }
       private class OnClick implements View.OnClickListener{
           @Override
           public void onClick(View v) {
               Intent intent = null;
               switch (v.getId()){
                   case R.id.enter:
                       intent =new Intent(MainActivity.this, Enter.class);
                       break;
                   case R.id.enroll:
                       intent=new Intent(MainActivity.this,Enroll.class);
                       break;
               }
               startActivity(intent);
           }
   
   
       }
   }
   ```

音乐软件主界面MusicMain：

```java
package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MusicMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_main);
    }
}
```

加密SHA256类：

```java
package com.example.app;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class SHA256 {
    public static String md5(String content){
        byte[]hash;
        try{
            hash=MessageDigest.getInstance("SHA-256").digest(content.getBytes());
            StringBuilder hex=new StringBuilder(hash.length*2);
            for (byte b:hash){
                int number=b&0xFF;
                if(number<0x10){
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException("NoSuchAlgorithmExcepation",e);
        }
    }
}
```

User表；

```java
package com.example.app;

import android.util.Log;

import androidx.annotation.NonNull;

import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport implements Comparable<User> {

    private static String TAG = "User";
    private int id;
    private Integer remember;
    private byte[] portrait;
    private String region;
    private String gender;
    private String brithday;
    private String username;
    private String password;
    private String password2;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", remember=" + remember +
                ", portrait='" + portrait + '\'' +
                ", region='" + region + '\'' +
                ", gender='" + gender + '\'' +
                ", brithday='" + brithday + '\'' +
                '}';
    }

    public boolean checkPassword(String str) {
        Log.d(TAG, "checkPassword: " + str);
        if (remember.equals(0))
            str = SHA256.md5(str);
        Log.d(TAG, "checkPassword: " + str);
        if (password.equals(str))
            return true;
        else
            return false;
    }

    public int getId() {
        return id;
    }

    public String getRegion() {
        return region;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPortrait() {
        return portrait;
    }

    public void setPortrait(byte[] portrait) {
        this.portrait = portrait;
    }

    public Integer getRemember() {
        return remember;
    }

    public void setRemember(Integer remember) {
        this.remember = remember;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            User UserInfo = (User) o;
            return (getId() == UserInfo.getId());
        } else {
            return false;
        }
    }

    public int compareTo(@NonNull User User) {
        return this.getUsername().compareTo(User.getUsername());
    }

}
```

drawble：

enter_color.xml:

```java
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:drawable="@color/black"
        android:state_pressed="false"/>
    <item
        android:drawable="@color/white"
        android:state_pressed="true"/>
</selector>
```

enter background.xml:

```java
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
            <solid android:color="#00ff00ff"/>
            <corners android:radius="8px"/>
            <stroke
                android:width="1px"
                android:color="#cccccc"/>
        </shape>
```

layout:

activity_main.xml:

```java
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/tp"
    android:label="xxxx">

    <Button
        android:id="@+id/enter"
        android:layout_width="1000dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="473dp"
        android:background="@drawable/enter_color"
        android:text="登录"
        android:textSize="20dp" />

    <Button
        android:id="@+id/enroll"
        android:layout_width="1000dp"
        android:layout_height="wrap_content"
        android:layout_below="@+id/enter"
        android:layout_marginTop="473dp"
        android:background="@drawable/enter_color"
        android:text="注册"
        android:textSize="20dp" />
</RelativeLayout>
```

activity_music.xml:

```java
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MusicMain"
    android:background="@drawable/lr">

</androidx.constraintlayout.widget.ConstraintLayout>
```

enroll.xml:

```java
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:background="@drawable/tp">


    <LinearLayout
        android:id="@+id/enroll1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="4dp"
        android:orientation="horizontal"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"></LinearLayout>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="272dp"
        android:text="账      号："
        android:textColor="@color/black"
        android:textSize="25sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />


    <EditText
        android:id="@+id/username1"
        android:layout_width="223dp"
        android:layout_height="0dp"
        android:layout_marginTop="280dp"
        android:layout_marginEnd="36dp"
        android:background="@drawable/enterbackground"
        android:hint="请输入您的用户名"
        android:inputType="text"
        android:textColor="@color/colorAccent"
        android:textSize="18sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="340dp"
        android:text="密      码："
        android:textColor="@color/black"
        android:textSize="25sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/password"
        android:layout_width="250dp"
        android:layout_height="28dp"
        android:layout_marginTop="344dp"
        android:background="@drawable/enterbackground"
        android:hint="请输入您的密码！"
        android:inputType="numberPassword"
        android:textColor="@color/colorAccent"
        android:textSize="18sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.937"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="4dp"
        android:layout_marginTop="404dp"
        android:text="确认密码："
        android:textColor="@color/black"
        android:textSize="25sp"
        app:layout_constraintEnd_toStartOf="@+id/editText"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/editText"
        android:layout_width="258dp"
        android:layout_height="0dp"
        android:layout_marginTop="404dp"
        android:background="@drawable/enterbackground"
        android:hint="请再一次输入密码"
        android:inputType="numberPassword"
        android:textColor="@color/colorAccent"
        android:textSize="18sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="4dp"
        android:orientation="horizontal"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

    </LinearLayout>

    <Button
        android:id="@+id/go_to_main"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="4dp"
        android:layout_marginTop="456dp"
        android:background="@color/white"
        android:text="注册"
        android:textSize="25sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <RadioButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="508dp"
        android:text="同意"
        android:textColor="@color/white"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />


</androidx.constraintlayout.widget.ConstraintLayout>
```

enter.xml

```java
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:background="@drawable/tp">


    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="4dp"
        android:orientation="horizontal"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"></LinearLayout>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="268dp"
        android:text="账号:"
        android:textColor="@color/black"
        android:textSize="25sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/loginName"
        android:layout_width="302dp"
        android:layout_height="27dp"
        android:layout_marginStart="88dp"
        android:layout_marginTop="268dp"
        android:background="@drawable/enterbackground"
        android:hint="请输入您的用户名"
        android:inputType="text"
        android:textSize="18sp"
        android:textColor="@color/colorAccent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />


    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="12dp"
        android:layout_marginTop="380dp"
        android:text="密码："
        android:textColor="@color/black"
        android:textSize="25sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/loginPassword"
        android:layout_width="302dp"
        android:layout_height="27dp"
        android:layout_marginStart="92dp"
        android:layout_marginTop="384dp"
        android:background="@drawable/enterbackground"
        android:hint="请输入您的密码！"
        android:inputType="numberPassword"
        android:textColor="@color/colorAccent"
        android:textSize="18sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="4dp"
        android:orientation="horizontal"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

    </LinearLayout>

    <CheckBox
        android:id="@+id/remeber_password"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="64dp"
        android:layout_marginTop="416dp"
        android:text="记住密码"
        android:textColor="@color/black"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <CheckBox
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="232dp"
        android:layout_marginTop="416dp"
        android:text="自动登录"
        android:textColor="@color/black"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/enter"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="480dp"
        android:background="@color/white"
        android:text="登录"
        android:textSize="25sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/change_enroll"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="right"
        android:layout_marginStart="128dp"
        android:layout_marginTop="608dp"
        android:text="还没有账号？"
        android:textColor="@color/white"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />


</androidx.constraintlayout.widget.ConstraintLayout>
```

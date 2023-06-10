

Activity

一般启动模式为standard

当Activity启动模式指定为singleTop时，不会再创建新的Activity实例

当Activity启动模式指定为singleTask时， 程序上下文中只存在一个实例

当Activity启动模式指定为singleInstance时， 可解决共享Activity实例的问题

![](C:\Users\HAN\Desktop\屏幕截图 2023-02-03 234657.png)

### 随心所欲的退出程序

1.用单例类管理Activity,ActivityCollector

2.用单例类中的addActivity添加活动，removeActivity销毁活动

## UI

### 对话框AlertDialog

普通对话框：public void tipDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    builder.setTitle("提示：");
    builder.setMessage("这是一个普通对话框，");
    builder.setIcon(R.mipmap.ic_launcher);
    builder.setCancelable(true);            //点击对话框以外的区域是否让对话框消失

    //设置正面按钮
    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(MainActivity.this, "你点击了确定", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    });
    //设置反面按钮
    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(MainActivity.this, "你点击了取消", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    });
    //设置中立按钮
    builder.setNeutralButton("保密", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(MainActivity.this, "你选择了中立", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    });

 








    AlertDialog dialog = builder.create();      //创建AlertDialog对象
    //对话框显示的监听事件
    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
        @Override
        public void onShow(DialogInterface dialog) {
            Log.e(TAG, "对话框显示了");
        }
    });
    //对话框消失的监听事件
    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialog) {
            Log.e(TAG, "对话框消失了");
        }
    });
    dialog.show();                              //显示对话框
    }

- setTitle：设置对话框的标题，比如“提示”、“警告”等； 
- setMessage：设置对话框要传达的具体信息； 
- setIcon： 设置对话框的图标； 
- setCancelable： 点击对话框以外的区域是否让对话框消失，默认为true； 
- setPositiveButton：设置正面按钮，表示“积极”、“确认”的意思，第一个参数为按钮上显示的文字，下同； 
- setNegativeButton：设置反面按钮，表示“消极”、“否认”、“取消”的意思； 
- setNeutralButton：设置中立按钮； 
- setOnShowListener：对话框显示时触发的事件； 
- setOnCancelListener：对话框消失时触发的事件

普通列表对话框：

private void itemListDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    builder.setTitle("选择你喜欢的课程：");
    builder.setCancelable(true);
    final String[] lesson = new String[]{"语文", "数学", "英语", "化学", "生物", "物理", "体育"};
    builder.setIcon(R.mipmap.ic_launcher);
    builder.setIcon(R.mipmap.tab_better_pressed)
            .setItems(lesson, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "你选择了" + lesson[which], Toast.LENGTH_SHORT).show();
                }
            }).create();
    //设置正面按钮
    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
    //设置反面按钮
    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
    AlertDialog dialog = builder.create();     //创建AlertDialog对象
    dialog.show();  //显示对话框

}

单选对话框：

ublic void singleChoiceDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

    builder.setTitle("你现在居住地是：");
    final String[] cities = {"北京", "上海", "广州", "深圳", "杭州", "天津", "成都"};
     
    builder.setSingleChoiceItems(cities, chedkedItem, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(), "你选择了" + cities[which], Toast.LENGTH_SHORT).show();
            chedkedItem = which;
        }
    });
    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
     
    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
     
    AlertDialog dialog = builder.create();  //创建AlertDialog对象
    dialog.show();                           //显示对话框
    }



建议看这篇笔记：[(22条消息) AlertDialog(对话框)详解_streate的博客-CSDN博客_alertdialog](https://blog.csdn.net/streate/article/details/90899515?ops_request_misc=%7B%22request%5Fid%22%3A%22167544124416800186536321%22%2C%22scm%22%3A%2220140713.130102334..%22%7D&request_id=167544124416800186536321&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-90899515-null-null.142^v73^insert_down1,201^v4^add_ask,239^v1^insert_chatgpt&utm_term=AlertDialog&spm=1018.2226.3001.4187)

### RecyclerView

加入依赖

implementation‘androidx.recyclerview:recyclerview:1.0.0'

需要为它创建一个强大的适配器继承至RecyclerView.Adapter,ViewHolder为内部类用于获取实例

onCreateViewHolder()用于创建实例将布局传进来：

```
public AddSmartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.add_smart,parent,false);
    AddSmartHolder addSmartHolder=new AddSmartHolder(view);
    return addSmartHolder;
}
```

onBindViewHolder()用于对RecyclerView赋值

```
public void onBindViewHolder(@NonNull AddSmartHolder holder, int position) {
    AddSmartHelper addSmartHelper=addSmartHelpers.get(position);
    holder.imageView.setImageResource(addSmartHelper.getImage());
    holder.title.setText(addSmartHelper.getTitle());

}
```

#### 点击事件的处理：

在Adpter中onCreateViewAdapter中注册点击事件，拿到position后拿到相应的实例

[(22条消息) recyclerview单击事件和多条目点击事件_IT芷君的博客-CSDN博客_recyclerview 点击](https://blog.csdn.net/VX_WJ88950106/article/details/126330729?ops_request_misc=%7B%22request%5Fid%22%3A%22167544272316782425155208%22%2C%22scm%22%3A%2220140713.130102334.pc%5Fall.%22%7D&request_id=167544272316782425155208&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~times_rank-1-126330729-null-null.142^v73^insert_down1,201^v4^add_ask,239^v1^insert_chatgpt&utm_term=recyclerview点击事件&spm=1018.2226.3001.4187)

## Fragment

可以嵌入Activity的Ui片段，建议使用supprort-v4中最新的fragment库

​        public class LeftFragment extends Fragment {         

   @Override        

​    public View onCreateView(LayoutInflater inflater, ViewGroup container,   Bundle savedInstanceState) {    

​            View view = inflater.inflate(R.layout.left_fragment, container, false);

​               return view;   //绑定布局     

  }      

  }

在<fragment中添加name绑定设置的上述布局；

### 动态添加碎片

​        public class MainActivity extends AppCompatActivity implements View.OnClickListener {     

​       @Override   

​         protected void onCreate(Bundle savedInstanceState) {       

​         super.onCreate(savedInstanceState);               

 setContentView(R.layout.activity_main);   

 Button button = (Button) findViewById(R.id.button);        

​        button.setOnClickListener(this);      

​          replaceFragment(new RightFragment());            }  

​          @Override            public void onClick(View v) {         

​       switch (v.getId()) {              

​      case R.id.button:                 

​       replaceFragment(new AnotherRightFragment());       //创建 待添加的碎片实例。        

​       break;        

​            default:          

​              break;          

​      }   

​         }        

​    private void replaceFragment(Fragment fragment) {          

​      FragmentManager fragmentManager = getSupportFragmentManager();   

​             FragmentTransaction transaction = fragmentManager.beginTransaction();    //开启一个事务

​            transaction.replace(R.id.right layout, fragment);    //替换碎片 

​           transaction.commit();       //提交事务

​     }     

   }

### 在碎片中模拟返回栈

FragmentTransation中addToBackStack(null)可将一个事务添加到返回栈中

调用FragmentManger中的findFragmentById()方法可以在活动中使用碎片。

❑ onAttach()。当碎片和活动建立关联的时候调用。

❑ onCreateView()。为碎片创建视图（加载布局）时调用。

❑ onActivityCreated()。确保与碎片相关联的活动一定已经创建完毕的时候调用。

❑ onDestroyView()。当与碎片关联的视图被移除的时候调用。

❑ onDetach()。当碎片和活动解除关联的时候调用。

添加细线

<View

android:layout_width="1dp"

android:layout_height="1dp"/>

## 全局大喇叭——广播机制

### 创建一个广播接收器

创建一个类继承于Broadcast-Receiver并重写父类的onReceive()方法

InterFiler指的是意图过滤器

发送自定义广播逻辑：

Intent intent=new Intent("com.example.broadcasttest.MY BROADCAST");//植入广播

sendBroadcast(intent);//发送标准广播

send-OrderedBroadcast(intent);//发送顺序广播

## 持久化存储技术

getSharePreferences()中第一个参数指定文件名称，第二个参数用于指定操作模式

（1）SharePreferences中edit()获取Share的Preferences.Editor的对象。

(2) 向SharedPreferences.Editor对象中添加数据，比如添加一个布尔型数据就使用putBoolean()方法，添加一个字符串则使用putString()方法，以此类推。

(3) 调用apply()方法将添加的数据提交，从而完成数据存储操作。

用get方法获取已经存储的数据

### SQLite数据库存储

integer表示整型

real表示浮点型

text表示文本类型

blob表示二进制类型

### LitePal数据库

添加依赖：

implementa‘org.litepal.android.core:1.4.1';

这样我们就把LitePal成功引入到当前项目中了，接下来需要配置litepal.xml文件。右击app/src/main目录→New→Directory，创建一个assets目录，然后在assets目录下再新建一个litepal.xml文件，接着编辑litepal.xml文件中的内容，其中，<dbname>标签用于指定数据库名，<version>标签用于指定数据库版本号，<list>标签用于指定所有的映射模型，我们稍后就会用到。

注意ORM映射模式很重要

 select()方法用于指定查询哪几列的数据，对应了SQL当中的select关键字。比如只查name和author这两列的数据，就可以这样         

 List<Book> books = DataSupport.select("name", "author").find(Book.class);写：

where()方法用于指定查询的约束条件，对应了SQL当中的where关键字。比如只查页数大于400的数据，就可以这样写：

​          List<Book> books = DataSupport.where("pages > ? ", "400").find(Book.class);

order()方法用于指定结果的排序方式，对应了SQL当中的order by 关键字。比如将查询结果按照书价从高到低排序，就可以这样写：

​          List<Book> books = DataSupport.order("price desc").find(Book.class); //desc表示降序，asc或者不写表示升序

limit()方法用于指定查询结果的数量，比如只查表中的前3条数据，就可以这样写：List<Book> books =DataSupport.limit(3).find(Book.class);❑ offset()方法用于指定查询结果的偏移量，比如查询表中的第2条、第3条、第4条数据，就可以这样写：

​          List<Book> books = DataSupport.limit(3).offset(1).find(Book.class);

## 跨平台共享数据——探究内容提供器

在http://developer.android.google.cn/reference/android/Manifest.permission.html中查看权限大全哦！！！！！

对于每一个应用程序来说，如果想要访问内容提供器中共享的数据，就一定要借助Content-Resolver类，可以通过Context中的getContentResolver()方法获取到该类的实例。

insert()方法用于添加数据

update()方法用于更新数据

delete()方法用于删除数据

query()方法用于查询数据

cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);//查询联系人数据

最后关闭cursor：cursor.close();

### 创建自己的内容提供器

```java
public class MyProvider extends ContentProvider {
    private static UriMatcher sUriMatcher;
    static {
        sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI("com.example.app2.provid","table1",0);
        sUriMatcher.addURI("com.example.app2.provid","table1/",1);
        sUriMatcher.addURI("com.example.app2.provid","table2",2);
        sUriMatcher.addURI("com.example.app2.provid","table1/",3);
    }

    @Override
    public boolean onCreate() {
        //初始化内容提供器的时候调用。通常会在这里完成对数据库的创建和升级等操作，返回true表示内容提供器初始化成功，返回false则表示失败
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //从内容提供器中查询数据。使用uri参数来确定查询哪张表，projection参数用于确定查询哪些列，selection和selectionArgs参数用于约束查询哪些行，
        // sortOrder参数用于对结果进行排序，查询的结果存放在Cursor对象中返回。
        switch (sUriMatcher.match(uri)){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        //根据传入的内容URI来返回相应的MIME类型。
        // 它是所有的内容提供器都必须提供的一个方法，用于获取Uri对象所对应的MIME类型。
        // 一个内容URI所对应的MIME字符串主要由3部分组成，Android对这3个部分做了如下格式规定。
        // ❑ 必须以vnd开头。
        // ❑ 如果内容URI以路径结尾，则后接android.cursor.dir/，如果内容URI以id结尾，则后接android.cursor.item/。
        // ❑ 最后接上vnd.<authority>.<path>。
        switch (sUriMatcher.match(uri)){
            case 0:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table1";
            //......
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //向内容提供器中添加一条数据。使用uri参数来确定要添加到的表，待添加的数据保存在values参数中。添加完成后，返回一个用于表示这条新记录的UR
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
       //从内容提供器中删除数据。使用uri参数来确定删除哪一张表中的数据，selection和selectionArgs参数用于约束删除哪些行，被删除的行数将作为返回值返回。
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
       //更新内容提供器中已有的数据。使用uri参数来确定更新哪一张表中的数据，新数据保存在values参数中，selection和selectionArgs参数用于约束更新哪些行，受影响的行数将作为返回值返回

        return 0;
    }

}
```

## 运用手机多媒体

### 通知的使用

首先需要一个NotificationManager来对通知进行管理，可以调用Context的getSystem-Service()方法获取到。getSystemService()方法接收一个字符串参数用于确定获取系统的哪个服务，这里我们传入Context.NOTIFICATION_SERVICE即可

NotificationManager manager=(NotificationManager)

gerSystemService(Context.NOTIFICATION_SERVICE);

​        Notification notification = new NotificationCompat.Builder(context)                .setContentTitle("This is content title")   //指定标题内容             .setContentText("This is content text")    //指定正文内容            .setWhen(System.currentTimeMillis())    //指定创建时间            .setSmallIcon(R.drawable.small_icon)    //指定通知的小图标            .setLargeIcon(BitmapFactory.decodeResource(getResources(),                    R.drawable.large_icon))                .build();

调用        manager.notify(1, notification);即可显示通知出来

PendingIntent为延迟执行的Intent.

PendingIntent的用法同样很简单，它主要提供了几个静态方法用于获取PendingIntent的实例，可以根据需求来选择是使用getActivity()方法、getBroadcast()方法，还是getService()方法。这几个方法所接收的参数都是相同的，第一个参数依旧是Context，不用多做解释。第二个参数一般用不到，通常都是传入0即可。第三个参数是一个Intent对象，我们可以通过这个对象构建出PendingIntent的“意图”。第四个参数用于确定PendingIntent的行为，有FLAG_ONE_SHOT、FLAG_NO_CREATE、FLAG_CANCEL_CURRENT和FLAG_UPDATE_CURRENT这4种值可选，每种值的具体含义你可以查看文档，通常情况下这个参数传入0就可以了。

​     Notification notification = new NotificationCompat.Builder(this)                        .setAutoCancel(true)                .build();//setAutoCancel()方法传入true，就表示当点击了这个通知的时候，通知会自动取消掉。

.setVibrate(new long[]{0,1000,1000,1000}).build();//手机震动，需要加入权限<uses-permission android:name="android.permission.VIBRATE"/>

.setLights(Color.GREEN,1000,1000).build();//设置呼吸灯

​        Notification notification = new NotificationCompat.Builder(this)                ...                .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build                    notifications, send and sync data, and use voice actions. Get the official                    Android IDE and developer tools to build apps for Android."))                .build();//显示长文字

​        Notification notification = new NotificationCompat.Builder(this)                ...                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture                    (BitmapFactory.decodeResource(getResources(), R.drawable.big image)))                .build();//显示大照片

## 网络技术

### WebView的用法

```java
<WebView
    android:id="@+id/web_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```



```java
public class WebViewActivity extends AppCompatActivity {
    private static final String TAG = "WebViewActivity";
    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        wv = findViewById(R.id.web_view);
        //加载本地中的html文件
//        wv.loadUrl("file:///android_asset/web.html");

        //设置支持js否则有些网页无法打开
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new MyClient());
        wv.setWebChromeClient(new MyWebChromeClient());
        //加载网络url
        wv.loadUrl("https://m.baidu.com");
    }

    class MyClient extends WebViewClient{
        //监听到页面发生跳转的情况，默认打开web浏览器
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            //在webView中加载要打开的链接
            view.loadUrl(request.getUrl().toString());
            return true;
        }
        //页面开始加载
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.e(TAG, "onPageStarted: ");
        }
        //页面加载完成的回调方法
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.e(TAG, "onPageFinished: ");
            //在webView中加载js代码
            wv.loadUrl("javascript:alert('hello')");
        }
    }

    class MyWebChromeClient extends WebChromeClient{
        //监听网页进度 newProgress进度值在0-100
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        //设置Activity的标题与 网页的标题一致
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果用户按的是返回键 并且WebView页面可以返回
        if (keyCode==event.KEYCODE_BACK&&wv.canGoBack()){
            //让WebView返回
            wv.goBack();
            return true;
        }
        //如果WebView不能返回 则调用默认的onKeyDown方法 退出Activity
         return super.onKeyDown(keyCode, event);
    }

}
```

使用HTTP协议访问网络：

工作原理：客户端向服务器发送一条HTTP请求，服务器收到请求之后返回数据给客户端，然后客户端对这些数据进行解析和处理。

首先需要获取到HttpURLConnection的实例，一般只需new出一个URL对象，并传入目标的网络地址，然后调用一下openConnection()方法即可，如下所示：

```java
URL url=new URL("http：//www.baidu.com");
HttpURLConnection connection=(HttpURLConnection)url.openConnection();
```

在得到了HttpURLConnection的实例之后，我们可以设置一下HTTP请求所使用的方法。常用的方法主要有两个：GET和POST。GET表示希望从服务器那里获取数据，而POST则表示希望提交数据给服务器。

```
connection.setRequestMethod("GET");
connection.setConnectTimeout(8000);//设置连接超时秒数
connection.setReadTimeiut(8000);//设置读取超时秒数
InputStream in=connection.getInputStream();//获取服务器返回的输入流，读取输入流。
connection.disconnect();//关闭HTTP连接
```

借助SrollView控件可以滚动看屏幕以外的内容

```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_drow);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {
            sendRequestWithHttpURLConnection();
        }
    }
    private void sendRequestWithHttpURLConnection() {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }
}
```

提交数据给服务器：

```java
connection.setRequestMethod("POST");
DataOutputStream out = new DataOutputStream(connection.getOutputStream()); out.writeBytes("username=admin&password=123456");//数据与数据之间用&隔开
```

### 使用OkHttp:

### https://github.com/square/okhttp。

添加依赖：implementation‘com.squateup.okhttp3:okttp:3.4.1';

创建Request对象

Request request=new Request.Builder().**url("http://www.baidu.com")**.build();//设置目标网络地址。

 Response response = client.newCall(request).execute();//发送请求并获取服务器返回的数据

### 解析XML数据

### 解析JSPN格式数据

学习一下如何解析JSON格式的数据了。比起XML, JSON的主要优势在于它的体积更小，在网络上传输的时候可以更省流量。但缺点在于，它的语义性较差，看起来不如XML直观。

## 后台服务service

### 线程的基本用法

定义一个线程只需要新建一个类继承自Thread,然后重写父类run()方法，然后在里面编写逻辑。

启动线程：new出MyThread的实例，并且调用start()方法

更多时候因为耦合性太高会选用实现Runnable接口的方式来定义线程

```java
        class MyThread implements Runnable {
            @Override
            public void run() {
                // 处理具体的逻辑
            }
        }
```

开启线程的方法：

```java
        MyThread myThread = new MyThread();
        new Thread(myThread).start();
```

和许多其他的GUI库一样，Android的UI也是线程不安全的。也就是说，如果想要更新应用程序里的UI元素，则必须在主线程中进行，否则就会出现异常。子线程中不能进行UI操作

#### 异步处理消息的方法：

```java
        public class MainActivity extends AppCompatActivity implements View.OnClickListener {
            public static final int UPDATE TEXT = 1;
            private TextView text;
            private Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case UPDATE TEXT:
                            // 在这里可以进行UI操作
                            text.setText("Nice to meet you");
                            break;
                        default:
                            break;
                    }
                }
            };
            ...
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.change_text:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message message = new Message();
                                message.what = UPDATE TEXT;
                                handler.sendMessage(message); // 将Message对象发送出去
                            }
                        }).start();
                        break;
                    default:
                        break;
                }
            }
        }
```

1．Message

Message是在**线程之间传递**的消息，它可以在内部携带少量的信息，用于在不同线程之间**交换数据**。上一小节中我们使用到了Message的what字段，除此之外还可以使用arg1和arg2字段来携带一些整型数据，使用obj字段携带一个Object对象。

2．Handler

Handler顾名思义也就是**处理者**的意思，它主要是**用于发送和处理消息**的。发送消息一般是使用Handler的sendMessage()方法，而发出的消息经过一系列地辗转处理后，最终会传递到Handler的handleMessage()方法中。

3．MessageQueue

MessageQueue是**消息队列**的意思，它主要用于**存放所有通过Handler发送的消息**。这部分消息会一直存在于消息队列中，等待被处理**。每个线程中只会有一个**MessageQueue对象。

4．Looper

Looper是每个线程中的MessageQueue的**管家**，调用Looper的loop()方法后，就会进入到一个无限循环当中，然后每当发现MessageQueue中存在一条消息，就会将它取出，并传递到Handler的handleMessage()方法中。每个线程中也只会有一个Looper对象。

#### 使用AsyncTask：

首先来看一下AsyncTask的基本用法，由于AsyncTask是一个抽象类，所以如果我们想使用它，就必须要创建一个子类去继承它。在继承时我们可以为AsyncTask类指定3个泛型参数，这3个参数的用途如下。

❑ Params。在执行AsyncTask时需要传入的参数，可用于在后台任务中使用。

❑ Progress。后台任务执行时，如果需要在界面上显示当前的进度，则使用这里指定的泛型作为进度单位。

❑ Result。当任务执行完毕后，如果需要对结果进行返回，则使用这里指定的泛型作为返回值类型。

1．onPreExecute()

这个方法会在后台任务开始执行之前调用，用于进行一些界面上的初始化操作，比如**显示一个进度条对话框**等。

2．doInBackground(Params...)

这个方法中的所有代码都会在子线程中运行，我们应该在这里去**处理所有的耗时**任务。任务一旦完成就可以通过return语句来将任务的执行结果返回，如果AsyncTask的第三个泛型参数指定的是Void，就可以不返回任务执行结果。注意，在这个方法中是不可以进行UI操作的，如果需要更新UI元素，比如说反馈当前任务的执行进度，可以调用publishProgress (Progress...)方法来完成。

3．onProgressUpdate(Progress...)

当在后台任务中调用了publishProgress(Progress...)方法后，onProgressUpdate (Progress...)方法就会很快被调用，该方法中携带的参数就是在后台任务中传递过来的。**在这个方法中可以对UI进行操作，利用参数中的数值就可以对界面元素进行相应的更新。**

4．onPostExecute(Result)

当后台任务执行完毕并通过return语句进行返回时，这个方法就很快会被调用。返回的数据会作为参数传递到此方法中，可以利用返回的数据来进行一些UI操作，比如说提醒任务执行的结果，以及**关闭掉进度条对话框**等。

启动任务

```
new DownloadTask().execute();
```

### 服务 的基本用法

onCreate()方法会在服务创建的时候调用，onStartCommand()方法会在每次服务启动的时候调用，onDestroy()方法会在服务销毁的时候调用

如果我们希望服务一旦启动就立刻去执行某个动作，就可以将逻辑写在onStartCommand()方法里

**服务也需要在AndroidManifest.xml文件中注册**

服务的启动和停止主要借助Intent来实现

启动服务：startService(startIntent);

停止服务：startService(stopIntent);

其实onCreate()方法是在服务第一次创建的时候调用的，而onStartCommand()方法则在每次启动服务的时候都会调用，由于刚才我们是第一次点击Start Service按钮，服务此时还未创建过，所以两个方法都会执行，之后如果你再连续多点击几次Start Service按钮，你就会发现只有onStartCommand()方法可以得到执行了。

新建一个类继承于Binder,使用内部类开始下载和查看下载进度

服务和活动的生命周期类似

#### 使用前台服务

如果你希望服务可以一直保持运行状态，而不会由于系统内存不足的原因导致被回收，就可以考虑使用前台服务。前台服务和普通服务最大的区别就在于，它会一直有一个正在运行的图标在系统的状态栏显示，下拉状态栏后可以看到更加详细的信息，非常类似于通知的效果。

只不过这次在构建出Notification对象后并没有使用NotificationManager来将通知显示出来，而是调用了startForeground()方法。这个方法接收两个参数，第一个参数是通知的id，类似于notify()方法的第一个参数，第二个参数则是构建出的Notification对象。调用startForeground()方法后就会让MyService变成一个前台服务，并在系统状态栏显示出来。

#### IntentService

我们应该在服务的每个具体的方法里开启一个子线程，然后在这里去处理那些耗时的逻辑。

## 高级UI使用

### 滑动菜单

DrawerLayout:

DrawerLayout中放置了两个直接子控件，第一个子控件是FrameLayout，用于作为主屏幕中显示的内容，当然里面还有我们刚刚定义的Toolbar。第二个子控件这里使用了一个TextView，用于作为滑动菜单中显示的内容，其实使用什么都可以，DrawerLayout并没有限制只能使用固定的控件。但是关于第二个子控件有一点需要注意，layout_gravity这个属性是必须指定的，因为我们需要告诉DrawerLayout滑动菜单是在屏幕的左边还是右边。

```java
        public class MainActivity extends AppCompatActivity {
            private DrawerLayout mDrawerLayout;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);//获取实例
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer layout);
                ActionBar actionBar = getSupportActionBar();
                if (actionBar ! = null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setHomeAsUpIndicator(R.drawable.ic menu);
                }
            }
            ...
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case android.R.id.home:
                        mDrawerLayout.openDrawer(GravityCompat.START);
                        break;
                    ...
                    default:
                }
                return true;
            }
        }
```

NavigationView:

圆形图片：

https://github.com/hdodenhof/CircleImageView

```java
        <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="180dp"
            android:padding="10dp"
            android:background="? attr/colorPrimary">
            <de.hdodenhof.circleimageview.CircleImageView
                android:id="@+id/icon_image"
                android:layout_width="70dp"
                android:layout_height="70dp"
                android:src="@drawable/nav_icon"
                android:layout_centerInParent="true" />
            <TextView
                android:id="@+id/mail"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentBottom="true"
                android:text="tonygreendev@gmail.com"
                android:textColor="#FFF"
                android:textSize="14sp" />
            <TextView
                android:id="@+id/username"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_above="@id/mail"
                android:text="Tony Green"
                android:textColor="#FFF"
                android:textSize="14sp" />
        </RelativeLayout>
```

### 悬浮按钮和可交互提示

FlatingActionButton

colorAccent可以作为按钮的颜色

app:elevation属性来给FloatingActionButton指定一个高度值，高度值越大，投影范围也越大，但是投影效果越淡，高度值越小，投影范围也越小，

监听和普通的Button一样。

Snackbar

它允许在提示当中加入一个可交互按钮，当用户点击按钮的时候可以执行一些额外的逻辑操作。

```java
Snackbar.make(view,"Data deleted",Snackbar.LENGTH SHORT).setAction("Undo",new View.OnClickListener(){
@Override
public void onClick(View v){
Toast.makeText(MainActivity.this,"Data retored",Toast.LENGTH SHORT).show();
}

}).show();
```

CoordinatorLayout:

oordinatorLayout可以说是一个加强版的FrameLayout。

### 卡片式布局

实际上，CardView也是一个FrameLayout，只是额外提供了圆角和阴影等效果，看上去会有立体的感觉。

另外还可以通过app:elevation属性指定卡片的高度，高度值越大，投影范围也越大，但是投影效果越淡，高度值越小，投影范围也越小，但是投影效果越浓，这一点和FloatingActionButton是一致的。

### 下拉刷新

SwipeRefreshLayout就是用于实现下拉刷新功能的核心类，它是由support-v4库提供的。我们把想要实现下拉刷新功能的控件放置到SwipeRefreshLayout中，就可以迅速让这个控件支持下拉刷新。那么在MaterialTest项目中，应该支持下拉刷新功能的控件自然就是RecyclerView了。

可以看到，这里我们在RecyclerView的外面又嵌套了一层SwipeRefreshLayout，这样RecyclerView就自动拥有下拉刷新功能了。

### 可折叠式标题栏

CollapsingToolbarLayout：

它在设计的时候就被限定只能作为**AppBarLayout**的直接子布局来使用。而AppBarLayout又必须是**CoordinatorLayout**的子布局

app:contentScrim属性用于指定CollapsingToolbarLayout在趋于折叠状态以及折叠之后的背景色。app:layout_scrollFlags属性我们也是见过的，只不过之前是给Toolbar指定的，现在也移到外面来了。其中，scroll表示CollapsingToolbarLayout会随着水果内容详情的滚动一起滚动，exitUntilCollapsed表示当CollapsingToolbarLayout随着滚动完成折叠之后就保留在界面上，不再移出屏幕。

NestedScollView比起ScollView增加了相应滚动事件的功能。

https://material.google.com官方学习。

## 视差动画

- 内容：元素差异滚动，形成视差效果(*)

- 背景：随着界面的滑动，颜色由深蓝色渐变为浅蓝色(*)

- 文字：底部提示文案会随页面变动而切换，有简单的淡入淡出效果

- 界面动画：界面打开，元素的出场动画（第一页以及最后一页）

  这里的视差滚动效果，主要表现为内容元素滚动速率的差异上。比如在 `ViewPager` 中滑动了 `1px` ，而 **A** 元素移动 `2px` ， **B** 元素移动 `1.5px` ，这种移动差距的比率，我称之为 `parallaxCofficient` ，即 **视差系数** 或者 **视差速率** ，正是同一个界面中的元素，由于层级不同，赋予的视差系数不同，在移动速度上的差异形成了视差的错觉，这就是我们要追求的效果。

  在viewPager中的抽象方法transformPage(View page,float position)可以用于视差动画。

  **差异滚动的实现规则：**

  - 　　背景层的滚动(最慢)
  - 　　贴图层(内容层和背景层之间的元素)的滚动(次慢)
  - 　　内容层的滚动(可以和页面的滚动速度一致

[PageTransformer](https://developer.android.com/reference/android/support/v4/view/ViewPager.PageTransformer.html) 在 `ViewPager` 滑动时被触发，它为我们自定义页面中进行视图变换打开了一扇大门。

ViewPager加载Fragment,子页面都是Fragment让其显示，用LayoutInflater加载控件，得到布局定义的一些属性

通过LayoutInflater打标签的形式让Fragementmt拿到List<View>使其动起来

attrs中添加自定义View中需要添加的属性名称

ViewPager中添加滑动监听

## ViewPager.PageTransformer

## 动画

#### Tween Animation 分类

Tween Animation 包括五类动画，分别是：

1. AlphaAnimation：主要用于控制 View 的可见性（显示|隐藏）。
2. ScaleAnimation：主要用于缩放 View 大小。
3. TranslateAnimation：主要用于移动 View 的位置。
4. RotateAnimation：主要用于旋转 View。
5. AnimationSet：某些场景仅靠上面单一类型的动画是无法实现的，需要多个类型的动画组合才能达到最终的效果，此时 AnimationSet 就派上用场了，AnimationSet 的主要作用就是组合各类 Tween Animation。

## API

API 代表应用程序编程接口。在 API 环境中，应用程序一词指的是任何具有独特功能的软件。接口可以看作是两个应用程序之间的服务合约。该合约定义了两者如何使用请求和响应相互通信。它们的 API 文档包括与开发人员如何构建这些请求和响应有关的信息。

### 工作原理

API 架构通常从客户端和服务器的角度来解释。发送请求的应用程序称为客户端，发送响应的应用程序称为服务器。以天气为例，气象局的天气数据库是服务器，而移动应用程序是客户端。 

RPC API 远程调用，客户端在服务器完成函数，服务器将输出发回客户端。

mdoifier调用顺序会影响UI呈现效果因此使用offset修饰符转移再用backgrou修饰符绘制背景色

## Modifier修饰符

### matchParentSize

matchParentSize是只能在BoxScope中使用的作用域限定修饰符。当使用matchParentSize设置尺寸时，可以保证当前组件的尺寸与父组件相同。而父组件默认的是wrapContent，会根据UserInfo的尺寸确定自身的尺寸

### weight

在RowScope与ColumnScope中，可以使用专属的weight修饰符来设置尺寸。与size修饰符不同的是，weight修饰符允许组件通过百分比设置尺寸，也就是允许组件可以自适应适配各种屏幕尺寸的移动终端设备。

```kotlin
@Composable
fun WeightDemo(){
    Column(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Yellow)

        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Gray)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Magenta)
        )
    }
    }

```

### Modifier.border and Moddifier.padding

绘制边框

```kotlin
@Composable
fun biankuang(){
    Row{
        Box(
            Modifier
                .padding(8.dp)//绘制外间隙
                .border(2.dp, Color.Gray, shape = RoundedCornerShape(2.dp))//边框
                .padding(8.dp)//内间隙
        ){
            Spacer(
                Modifier
                    .size(width = 100.dp, height = 10.dp)
                    .background(Color.Gray))
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset(x = 100.dp, y = 100.dp)
                .background(Color.Gray)
        )

    }
}
```

Compose中只有padding修饰符

### Modifier.offset

修饰组件的位置，分别传入水平和竖直方向偏移量即可

其中调用offest方法是有顺序的，需要根据具体偏移的对象确定方法调用的位置

在Compose的DSL中，一般只需要调用当前作用域的方法，像上面这样的Receiver跨级访问会成为写代码时的“噪声”，加大出错的概率。Compose考虑到了这个问题，可以通过@LayoutScopeMarker注解来规避Receiver的跨级访问。常用组件Receivier作用域类型均已使用@LayoutScopeMarker注解进行了声明。

outer与inner作为private属性不能被外部直接访问，因此提供了专门的foldOut()与foldln()遍历Modifier链

Modifier.pointerInput()定制手势监听处理

foldIn和foldOut的方法相同：initial是折叠计算的初始值，operation是具体计算方法。Element参数表示当前遍历到的Modifier，返回值也是R类型，表示本轮计算的结果，会作为下一轮R类型参数传入。

## 基础控件

### Text文本

fontWeight//字体粗细

fontSize//字体大小

lineHeight//行高

letterSpacing//字体间距

```kotlin
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyKT1Theme {

        Text(
            text= buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 25.sp)){
                    append("你现在学的章节是")//插入文本
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.W900, fontSize = 25.sp//-绘制文本时使用的字体厚度(例如，加粗)。
                    )
                ){
                    append("TEXT")
                }
                append("\n")
                withStyle(style = ParagraphStyle(lineHeight = 24.sp)){
                    append("在刚刚的章节中我们学会了如何使用文字样式，在后续的课程中我们会更加深入的讲解")
                    append("\n")
                    append("现在进入下一个章节")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.W900, textDecoration = TextDecoration.None, color = Color(0XFF59A869)
                        )//textDecration也可以选择其他的样式。。。。。
                    ){
                        append("开始")
                    }
                }
            }

        )


    }
}
```

#### maxLines参数

帮助我们将文本限制在指定行数之间，当文本超过了参数设置的值时，文本会被截断，overflow可以处理文字过多的场景

```kotlin
@Composable
fun maxLinesTest(){
     Column() {
        Text(text ="你好世界，我正在使用ssssssssss来开发我的APP界面哦",
            style = MaterialTheme.typography.bodyLarge)

        Text(text ="你好世界，我正在使用ssssssssss来开发我的APP界面哦",
            style = MaterialTheme.typography.bodyLarge, maxLines = 1,)

        Text(text ="你好世界，我正在使用ssssssssss来开发我的APP界面哦",
            style = MaterialTheme.typography.bodyLarge, maxLines = 1, overflow = TextOverflow.Ellipsis)
         Text(text ="你好世界，我正在使用ssssssssss来开发我的APP界面哦",
             style = MaterialTheme.typography.bodyLarge, maxLines = 4, overflow = TextOverflow.Clip)

    }
```

### TextFiald输入框

![image-20230606185357592](C:\Users\HAN\AppData\Roaming\Typora\typora-user-images\image-20230606185357592.png)

```kotlin
@Composable
fun TextFieldSample(){
     var text by remember {
         mutableStateOf("")
     }
    Box(modifier = Modifier
        .fillMaxSize()//填满父布局
        .background(Color(0xFFD3D3D3)),
        contentAlignment = Alignment.Center//将Box里面的组件放置在Box容器中
    ){
        BasicTextField(value = text, onValueChange = {
            text=it
        },
        decorationBox = {innerTextField ->
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier= Modifier
                    .padding(vertical = 2.dp, horizontal = 8.dp)
            ){
                Icon(imageVector =Icons.Filled.Search,
                    contentDescription = stringResource(R.string.app_name ) )
                Box(modifier = Modifier
                    .padding(horizontal = 10.dp),
                contentAlignment =Alignment.CenterStart){
                    if (text.isEmpty()){
                        Text(text = "输入点什么捏",
                            style = TextStyle(
                                color = Color(0,0,0,128)
                            )
                        )
                    }
                }
                innerTextField
            }


        },
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .background(Color.White, CircleShape)
            .height(30.dp)
            .fillMaxWidth())
    }


}
```

#### Icon的五种类型

![image-20230606192942561](C:\Users\HAN\AppData\Roaming\Typora\typora-user-images\image-20230606192942561.png)

#### Button

Button有一个参数interactionSource，在前面的组件中也出现过。它是一个可以监听组件状态的事件源，通过它我们可以根据组件状态设置不同的样式，比如按钮按下时什么效果，正常时什么效果，类似传统视图中的Selector。interactionSource通过以下方法获取当前组件状态：• interactionSource.collectIsPressedAsState()判断是否按下状态。• interactionSource.collectIsFocusedAsState()判断是否获取焦点的状态。• interactionSource.collectIsDraggedAsState()判断是否拖动。

```kotlin
@Composable
fun ButtonSample(){
    val interactionSource= remember {
        MutableInteractionSource()
    }
    val pressState=interactionSource.collectIsPressedAsState()
    val bordeColor=if (pressState.value)Color.Cyan else Color.Magenta
    Button(
        onClick = {}
    , modifier = Modifier.padding(60.dp),
        border = BorderStroke(2.dp,color=bordeColor),
        interactionSource = interactionSource
            ){
        Icon(imageVector = Icons.Filled.Info,
            contentDescription = stringResource(R.string.app_name),
        modifier = Modifier.size(ButtonDefaults.IconSize))
        Spacer(modifier =Modifier.size(ButtonDefaults.IconSize))
            Text("PRESS")
        }

}
```

#### Checkbox复选框

```kotlin
@Composable
fun CheckBoxSample(){
    val checkedState= remember {
        mutableStateOf(true)
    }
    Checkbox(
        checked=checkedState.value,
        onCheckedChange={ checkedState.value=it},
        colors=CheckboxDefaults.colors(
            checkedColor = Color(0xFF0079D3)
        )
    )
}
```

很多时候，我们的复选框会有很多个，并且希望能够统一选择或者取消

这个时候就可以用TriStateCheckBox组件。

```kotlin
@Composable
fun CheckBoxSampleall(){
    val (state,onStateChange)= remember { mutableStateOf(true) }
    val(state2,onStateChange2)= remember { mutableStateOf(true) }//设置CheckBox状态
    val parenState= remember(state ,state2) {
        if (state&&state2) ToggleableState.On
        else if (!state&&!state2) ToggleableState.Off
        else ToggleableState.Indeterminate//不确定的状态
        //TriStateCheckBox判断从属复选框的状态
    }

        val onParentClick={
            val  s = parenState != ToggleableState.On
            onStateChange(s)
            onStateChange2(s)
        }
        TriStateCheckbox(state =parenState, onClick =onParentClick,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary
            )
        )
    Column(Modifier.padding(10.dp,0.dp,0.dp,0.dp)) {
        Checkbox(state,onStateChange)
        Checkbox(state2, onStateChange2)
    }
    }

```

在子复选框全选中时，TriCheckBox显示已完成的状态，而如果只有部分复选框选中时，TriCheckBox则显示不确定的状态，用ToggleableState判断CheckBox状态。

### Switch组件

Switch组件可以控制单个项目的开启或关闭状态

### Slider滑杆组件

Slider类似于SeekBar的使用，其中colors参数设置的是滑杆部位的颜色，可分区设置，step参数讲进度条平分成step+1段

```kotlin
@Composable
fun SliderDemo(){
    val silderDemo by remember { mutableStateOf(0f) }
    Text(text = "%.lf".format(silderDemo * 100 )+ "%")
    Slider(value=silderDemo,onValueChange={
        silderDemo = it
    })
}

```

### Dialog对话框

其中content允许我们通过传入自己的Composable组件来描述Dialog页面

在Dialog组件显示过程中，当我们点击对话框以外区域时，onDismissRequest会触发执行，修改openDialog状态为false，触发DialogSample重组，此时判断openDialog为false, Dialog无法被执行，对话框消失

在Dialog组件显示过程中，当我们点击对话框以外区域时，onDismissRequest会触发执行，修改openDialog状态为false，触发DialogSample重组，此时判断openDialog为false, Dialog无法被执行，对话框消失

#### 警告对话框AlertDialog

AlertDialog组件是Dialog组件的更高级别的封装，同时遵守着Material Design设计标准。它已经帮助我们定位好了标题、内容文本、按钮组的位置。我们只需要提供相应的组件即可。

```kotlin
@Composable
fun AlertDialogDemo(){
    val openDiaglog= remember { mutableStateOf(true) }
    if(openDiaglog.value){
        AlertDialog(
            onDismissRequest = {
                openDiaglog.value=false
            }, title = {
                Text(text = "开启位置服务")
            }, text = {
                Text( "这意味这意味这意味这意味这意味这意味这意味这意味这意味这意味这意味这意味这意味这意味这意味这意味这意味这意味这")
            }, confirmButton = {
                TextButton(onClick = {
                    openDiaglog.value = false//其他需要执行的业务需求
                }) { Text("同意") }
            }, dismissButton = {
                TextButton(onClick = { openDiaglog.value = false })

                {
                Text("取消")

                }
            }

        )
    }
}

```

### 进度条

```kotlin
@Composable
fun Demo2(){
    var progress by remember {
        mutableStateOf(0f) 
    }
    
    //创建一个动画
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.requiredHeight(10.dp))
        CircularProgressIndicator(progress=progress.absoluteValue)
        Spacer(modifier = Modifier.requiredHeight(10.dp))
        TextButton(onClick ={
            if (progress.absoluteValue<1.0f)progress.absoluteValue=progress.absoluteValue+0.1f },
            modifier = Modifier.background(Color.DarkGray)
        ){
            Text(text = "增加进度")
            
        }
    }
}
```

## 布局

### 线性布局Column

在对齐效果的影响下，Modifier.align修饰符会优先于Column的horizontalAlignment参数。

对于垂直布局中的子项，Modifier.align只能设置自己在水平方向的位置，反之水平布局的子项，只能设置自己在垂直方向的位置。

这很好理解，我们以Column为例，当Column中有多个子项时，它们在垂直方向永远是线性排列。

如果各子项被允许单独设置，可能会出现Bad Case，比如Column中有A、B、C三个子项，如果配置A的对齐方向是Aligment. Bottom, B为Aligment. Top，那么这显然是无法实现的。

所以Clumen的子项在垂直方向的布局只能通过verticalArragnement进行整体设置。

### Row

Row的horizontalArrangement参数帮助我们合理配置了按钮的水平位置。

可以看到，喜欢和分享按钮呈左右两端对齐。

Arrangment定义了很多子项位置的对齐方式，除了Center（居中）、Start（水平靠左）、End（水平靠右）等常见的对齐方式，还有一些特定场景下可能用到的对齐方式，例如Space Between、Space Evenly等

### 帧布局

#### Box组件

Box组件是一个能够将里面的子项依次按照顺序**堆叠**的布局组件，在使用上类似于传统视图中的FrameLayout

#### Surface

Surface从字面上来理解，是一个平面，在Material Design设计准则中也同样如此，我们可以**将很多的组件摆放在这个平面之上**，可以设置这个平面的边框、圆角、颜色等。

```kotlin
@Composable
fun buju(){
    Surface(
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 10.dp,
        modifier = Modifier
            .width(300.dp)
            .height(100.dp)
    ) {
        Row(modifier = Modifier.clickable {}
        )
        {
            Image(painter = painterResource(id = R.drawable.sun), contentDescription = stringResource(
                R.string.app_name
            ) , modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop)
            Spacer(Modifier.padding(horizontal = 12.dp))
            Column(modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center) {
                Text(text = "Alice", style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Text(text = "礼物")
            }
            
        }
        
    }
}
```

### 约束布局ConstrainLayout

首先添加依赖

```
 implementation('androidx.constraintlayout:constraintlayout-compose:1.0.1')
```

Compose版本的ConstraintLayout中，可以主动创建引用并绑定至某个具体组件上，从而实现资源ID相似的功能。

每个组件都可以利用其他组件的引用获取到其他组件的摆放位置信息，从而确定自己应摆放的位置。

在Compose中有两种创建引用的方式：createRef()和createRefs()。

createRef()每次只会创建一个引用，而createRefs()每次可以创建多个引用（最多16个）

接下来可以使用Modifier.constrainAs()修饰符将前面创建的引用绑定到某个具体组件上。

可以在constrainAs尾部Lambda内指定组件的约束信息。

值得注意的是，我们只能在ConstraintLayout尾部的Lambda中使用createRef()、createRefs()创建引用，并使用Modifier.constrainAs()来绑定引用

这是因为ConstrainScope尾部Lambda的Reciever是一个ConstraintLayoutScope作用域对象。

#### Barrier分界线

我们使用createEndBarrier创建一条结尾分界线，此时分界线位置位于两个文本中较长文本的结尾处

接下来设置输入框的约束信息，将左侧起始位置指定为分界线后10dp的位置。

#### 引导线


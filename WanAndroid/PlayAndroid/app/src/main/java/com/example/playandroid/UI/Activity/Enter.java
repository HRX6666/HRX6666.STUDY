package com.example.playandroid.UI.Activity;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.playandroid.R;
import com.example.playandroid.Tool.POSTConnection_3;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class  Enter extends AppCompatActivity {
    private EditText editText_account;
    private EditText editText_password;
    private  TextView tv_register;
    private Button button_log_in;
    private ImageView imageView_eye;
    private ImageView imageView_user;
    private ImageView imageView_clock;
    TextView textView;
    TextView textView_visitor_log;
    TextView textView_way_of_log;
    private POSTConnection_3 post_connection = new POSTConnection_3();
    private String responseData;
    private static int i = 2;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String m = (String) msg.obj;
                    Toast.makeText( Enter.this, m, Toast.LENGTH_SHORT).show();
                    Log.e("错误信息",m);
                    break;
                case 2:
                    String n = (String) msg.obj;
                    Toast.makeText( Enter.this, n, Toast.LENGTH_SHORT).show();
                    Log.e("请求超时","请求超时");
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        imageView_user = findViewById(R.id.im_enter_username);
        imageView_clock = findViewById(R.id.im_enter_clock);
        editText_account = findViewById(R.id.edit_enter_account);
        editText_password = findViewById(R.id.edit_enter_password);
        button_log_in = findViewById(R.id.bt_enter);
        tv_register = findViewById(R.id.tv_enroll_intent);
        imageView_eye = findViewById(R.id.edit_eye);
        textView_visitor_log = findViewById(R.id.visitor_enter);
        textView_way_of_log = findViewById(R.id.way_of_log);

        SharedPreferences.Editor save_data = getSharedPreferences("user_data", MODE_PRIVATE).edit();
        SharedPreferences.Editor cookie_data = getSharedPreferences("cook_data", MODE_PRIVATE).edit();
        SharedPreferences save_da = getSharedPreferences("cook_data", MODE_PRIVATE);
        String u = save_da.getString("cookie", "");

        if (!TextUtils.isEmpty(u)) {
            Intent intent = new Intent( Enter.this, HomePageActivity.class);
            startActivity(intent);
        }

        textView_visitor_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Enter.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

        textView_way_of_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Enter.this,"暂不支持其他方式登录",Toast.LENGTH_SHORT).show();
            }
        });



        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Enter.this,  Eroll.class);
                startActivity(intent);
            }
        });

        button_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText_account.getText().toString().trim();
                String password = editText_password.getText().toString().trim();
                Log.e("点击", "进入");

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(Enter.this, "账号和密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("username", username);
                    map.put("password", password);
                    Log.e("分支", "进入");

                    ProgressDialog progressDialog = new ProgressDialog(Enter.this);
                    progressDialog.setTitle("正在登录...");
                    progressDialog.setMessage("Loading...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    new Thread(() -> {
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        List<String> list = new ArrayList<>();
                        list = post_connection.sendGetNetRequest("https://www.wanandroid.com/user/login", map);
                        responseData = list.get(0);
                        Log.e("联接response", responseData);
                        try {
                            if (responseData.equals("1")) {
                                progressDialog.dismiss();
                                showResponse("请求超时", 2);
                            } else {
                                String cook = list.get(1);
                                cookie_data.putString("cookie", cook);
                                cookie_data.apply();
                                JSONObject jsonObject = new JSONObject(responseData);
                                int jsonerrorCode = jsonObject.getInt("errorCode");

                                if (jsonerrorCode == -1) {
                                    progressDialog.dismiss();
                                    String jsonerrorMsg = jsonObject.getString("errorMsg");
                                    showResponse(jsonerrorMsg, 1);
                                    Log.e("错误", "信息");
                                } else {
                                    progressDialog.dismiss();
                                    JSONObject jsonRightData = jsonObject.getJSONObject("data");
                                    String jsonUsername = jsonRightData.getString("username");
                                    int user_id = jsonRightData.getInt("id");

                                    save_data.putString("username", username);
                                    save_data.putString("password", password);
                                    save_data.putInt("user_id", user_id);
                                    save_data.apply();


                                    Intent intent = new Intent(Enter.this, HomePageActivity.class);
                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }

            }
        });


        editText_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        imageView_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i % 2 == 0) {
                    //如果选中，显示密码
                    imageView_eye.setImageResource(R.drawable.eye);
                    editText_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    i++;
                } else {
                    //否则隐藏密码
                    imageView_eye.setImageResource(R.drawable.closeeye);
                    editText_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    i++;
                }
            }
        });

        editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int mlongth = editText_password.length();
                if (mlongth == 0) {
                } else if (mlongth < 6 && mlongth > 0) {
                    editText_password.setHint("密码不能为空");//不能设置int，会闪退
                    editText_password.setHintTextColor(Color.parseColor("#FA1065"));
                    imageView_clock.setImageResource(R.drawable.clock_bule);
                } else {
                    imageView_clock.setImageResource(R.drawable.lock);
                }
            }
        });

        editText_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int mlongth = editText_account.length();
                if (mlongth == 0) {
                } else if (mlongth < 4 && mlongth > 0) {
                    editText_account.setHint("账号不能为空");//不能设置int，会闪退
                    editText_account.setHintTextColor(Color.parseColor("#FA1065"));
                    imageView_user.setImageResource(R.drawable.have_user);
                } else {
                    imageView_user.setImageResource(R.drawable.no_user);
                }
            }
        });
    }

    private void showResponse(String st, int num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = num;
                message.obj = st;
                handler.sendMessage(message);
            }
        }).start();
    }
}

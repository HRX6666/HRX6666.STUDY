package com.example.playandroid.UI.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.playandroid.Adapter.CoinRankAdapter;
import com.example.playandroid.R;
import com.example.playandroid.Tool.AppManager;
import com.example.playandroid.Tool.DataCleanUtil;
import com.example.playandroid.Tool.GETConnection;
import com.example.playandroid.Tool.JsonAnalyze;
import com.example.playandroid.UI.Activity.AboutActivity;
import com.example.playandroid.UI.Activity.Enter;
import com.example.playandroid.UI.Activity.FirstActivity;
import com.example.playandroid.UI.Activity.GuideActivity;
import com.example.playandroid.UI.Activity.MyCollectActivity;
import com.example.playandroid.UI.Activity.MyMessageActivity;
import com.example.playandroid.UI.Activity.MyShareActivity;
import com.example.playandroid.UI.Activity.SetActivity;

import java.util.ArrayList;
import java.util.List;
import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.playandroid.Tool.Head.BitmapUtils;
import com.example.playandroid.Tool.Head.CameraUtils;
import com.example.playandroid.Tool.Head.SPUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyselfFragment extends Fragment {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1;
    //权限请求
    private RxPermissions rxPermissions;

    //是否拥有权限
    private boolean hasPermissions = false;

    //底部弹窗
    private BottomSheetDialog bottomSheetDialog;
    //弹窗视图
    private View bottomView;
    //存储拍完照后的图片
    private File outputImagePath;
    //启动相机标识
    public static final int TAKE_PHOTO = 1;
    //启动相册标识
    public static final int SELECT_PHOTO = 2;
    //图片控件
    private RoundedImageView ivHead;
    //Base64
    private String base64Pic;
    //拍照和相册获取图片的Bitmap
    private Bitmap orc_bitmap;

    //Glide请求图片选项配置
    private RequestOptions requestOptions = RequestOptions.circleCropTransform()//圆形裁剪
            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(true);//不做内存缓存

    private View view;
    private TextView textView_myCollect;
    private TextView textView_about;
    private TextView textView_logOff;
    private TextView textView_jifen;
    private TextView textView_name;
    private TextView textView_share;
    private TextView tv_paiming;
    private TextView textView_message;
    private TextView textView_email;
    private TextView textView_set;
    private String userId;
    private String email;
    private String responseData;
    DataCleanUtil dataCleanUtil;
    GETConnection get_connection = new GETConnection();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private List<String> list2 = new ArrayList<>();
    private List<Integer> list3 = new ArrayList<>();
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tv_paiming.setText("" + list2.get(1) + " ");
                    textView_name.setText(list2.get(0));
                    textView_jifen.setText("" + String.valueOf(list3.get(0)));
                    textView_email.setText("id:"+userId);
                    break;
                case 2:
                    Toast.makeText(getActivity(), "请求超时", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    tv_paiming.setText(" 0 ");

            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myself, container, false);
        initView();
        SharedPreferences user_da = getActivity().getSharedPreferences("user_data", MODE_PRIVATE);
        userId = String.valueOf(user_da.getInt("user_id", 0));
        if (userId.equals("0")) {
            textView_name.setText("点击登录");
            textView_email.setVisibility(View.GONE);
            showResponse(3);
        } else {
            new Thread(() -> {
                responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/user/" + userId + "/share_articles/0/json");
                if (responseData.equals("1")) {
                    showResponse(2);
                } else {
                    jsonAnalyze.JsonDataGet_shareUser_data(responseData, list2,list3);
                    showResponse(1);
                }
            }).start();
        }
        initClick();
        initHead();
        return view;
    }

    private void initHead() {
        ivHead = view.findViewById(R.id.iv_head);
        //检查版本
        checkVersion();
        // 取出缓存
        String imageUrl = SPUtils.getString("imageUrl",null,getActivity());
        if(imageUrl != null){
            Glide.with(getActivity()).load(imageUrl).apply(requestOptions).into(ivHead);
        }
        ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(getContext());
                bottomView = getLayoutInflater().inflate(R.layout.dialog_bottom, null);
                bottomSheetDialog.setContentView(bottomView);
                bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundColor(Color.TRANSPARENT);
                TextView tvTakePictures = bottomView.findViewById(R.id.tv_take_pictures);
                TextView tvOpenAlbum = bottomView.findViewById(R.id.tv_open_album);
                TextView tvCancel = bottomView.findViewById(R.id.tv_cancel);
                //拍照
                tvTakePictures.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        takePhoto();
                        showMsg("拍照");
                        bottomSheetDialog.cancel();
                    }
                });
                tvOpenAlbum.setOnClickListener(new View.OnClickListener() {  //打开相册
                    @Override
                    public void onClick(View v) {
                        openAlbum();
                        showMsg("打开相册");
                        bottomSheetDialog.cancel();
                    }
                });
                tvCancel.setOnClickListener(new View.OnClickListener() { //取消
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.cancel();
                    }
                });
                //底部弹窗显示
                bottomSheetDialog.show();
            }
        });
    }

  //检查版本
    private void checkVersion() {
        //Android6.0及以上版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果你是在Fragment中，则把this换成getActivity()
            rxPermissions = new RxPermissions(getActivity());
            //权限请求
            rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) {//申请成功
                            Log.e("xx","已获得权限");
                            hasPermissions = true;
                        } else {//申请失败
                            showMsg("权限未开启");
                            hasPermissions = false;
                        }
                    });
        } else {
            //Android6.0以下
            showMsg("无需请求动态权限");
        }
    }
    /**
     * 更换头像
     *
     * @param view
     */
    public void changeAvatar1(View view) {

    }

    /**
     * 拍照
     */
    private void takePhoto() {
        if (!hasPermissions) {
            showMsg("未获取到权限");
            checkVersion();
            return;
        }
        SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                "yyyy_MM_dd_HH_mm_ss");
        String filename = timeStampFormat.format(new Date());
        outputImagePath = new File(getActivity().getExternalCacheDir(),
                filename + ".jpg");
        Intent takePhotoIntent = CameraUtils.getTakePhotoIntent(getActivity(), outputImagePath);
        // 开启一个带有返回值的Activity，请求码为TAKE_PHOTO
        startActivityForResult(takePhotoIntent, TAKE_PHOTO);
    }

    /**
     * 打开相册
     */
    private void openAlbum() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }
        if (!hasPermissions) {
            showMsg("未获取到权限");
            checkVersion();
            return;
        }
        startActivityForResult(CameraUtils.getSelectPhotoIntent(), SELECT_PHOTO);
    }

    /**
     * 返回到Activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //拍照后返回
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //显示图片
                    displayImage(outputImagePath.getAbsolutePath());
                }
                break;
            //打开相册后返回
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    String imagePath = null;
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                        //4.4及以上系统使用这个方法处理图片
                        imagePath = CameraUtils.getImageOnKitKatPath(data, getActivity());
                    } else {
                        imagePath = CameraUtils.getImageBeforeKitKatPath(data, getActivity());
                    }
                    //显示图片
                    displayImage(imagePath);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 通过图片路径显示图片
     */
    private void displayImage(String imagePath) {

        if (!TextUtils.isEmpty(imagePath)) {
            //放入缓存
            SPUtils.putString("imageUrl",imagePath,getActivity());
            //显示图片
            Glide.with(getActivity()).load(imagePath).apply(requestOptions).into(ivHead);
            //压缩图片
            orc_bitmap = CameraUtils.compression(BitmapFactory.decodeFile(imagePath));
            //转Base64
            base64Pic = BitmapUtils.bitmapToBase64(orc_bitmap);

        } else {
            showMsg("图片获取失败");
        }
    }


    /**
     * Toast提示
     *
     * @param msg
     */
    private void showMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    private void initView() {
        textView_email=view.findViewById(R.id.t);
        textView_about = view.findViewById(R.id.about_bt);
        textView_logOff = view.findViewById(R.id.log_off_bt);
        textView_myCollect = view.findViewById(R.id.my_collect_bt);
        textView_name=view.findViewById(R.id.tv_username);
        textView_jifen=view.findViewById(R.id.tv_jifen);
        textView_share=view.findViewById(R.id.my_share_bt);
        tv_paiming=view.findViewById(R.id.tv_paiming);
        textView_message=view.findViewById(R.id.tv_message);
    }


    private void initClick() {
//        textView_set.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), SetActivity.class);
//                startActivity(intent);
//            }
//        });
        textView_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId.equals("0")){
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(), MyMessageActivity.class);
                    startActivity(intent);
                }
            }
        });
        textView_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId.equals("0")){
                    Intent intent = new Intent(getActivity(), Enter.class);
                    startActivity(intent);
                }
            }
        });
        textView_myCollect.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (userId.equals("0")){
                Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(getActivity(), MyCollectActivity.class);
                startActivity(intent);
            }

        }
    });
        textView_logOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor save_data = getActivity().getSharedPreferences("user_data", MODE_PRIVATE).edit();
                SharedPreferences.Editor cookie_data = getActivity().getSharedPreferences("cook_data", MODE_PRIVATE).edit();
                if (userId.equals("0")){
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }else{

                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Warning");
                    dialog.setMessage("你确定要退出登录吗？");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            save_data.clear();
                            cookie_data.clear();
                            save_data.apply();
                            cookie_data.apply();
                            dataCleanUtil.cleanApplicationData(getContext());
                            AppManager.getAppManager().AppExit(getContext());
                            onDestroy();
                            getActivity().finish();
                            Intent intent=new Intent(getActivity(), Enter.class);
                            startActivity(intent);
                            Toast.makeText(getActivity(),"已退出登录",Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"您取消了退出登录",Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();
                }

            }
        });

        textView_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });
        textView_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId.equals("0")){
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent1=new Intent(getActivity(), MyShareActivity.class);
                    startActivity(intent1);
                }
            }
        });
    }
    private void showResponse(int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = i;
                handler.sendMessage(message);
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
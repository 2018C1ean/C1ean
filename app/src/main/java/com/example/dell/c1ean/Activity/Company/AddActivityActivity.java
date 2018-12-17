package com.example.dell.c1ean.Activity.Company;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.example.dell.c1ean.Application.BaseApplication;
import com.example.dell.c1ean.Application.SystemApplication;
import com.example.dell.c1ean.Bean.CompanyActivity;
import com.example.dell.c1ean.DAO.CompanyActivityDao;
import com.example.dell.c1ean.R;
import com.gyf.barlibrary.ImmersionBar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DELL on 2018/12/7.
 */

public class AddActivityActivity extends AppCompatActivity {

    private ImageView img1, img2, img3;
    private ImageView back;
    private TextView activity_type, price, star_time, end_time;
    private RadioGroup user_times;
    private EditText describe, title;
    private Button post;
    public static final int PHOTO_REQUEST_CAMERA = 11;// 拍照
    public static final int IMAGE_REQUEST_CODE = 21;
    public static final int CAMERA_CROP_PHOTO = 31;
    public static final int ALBUM_CROP_PHOTO = 41;
    public static final int PHOTO_REQUEST_CAMERA_2 = 12;// 拍照
    public static final int IMAGE_REQUEST_CODE_2 = 22;
    public static final int CAMERA_CROP_PHOTO_2 = 32;
    public static final int ALBUM_CROP_PHOTO_2 = 42;
    public static final int PHOTO_REQUEST_CAMERA_3 = 13;// 拍照
    public static final int IMAGE_REQUEST_CODE_3 = 23;
    public static final int CAMERA_CROP_PHOTO_3 = 33;
    public static final int ALBUM_CROP_PHOTO_3 = 43;
    private static Uri camera_img_uri; //拍完照照片的uri
    private File tempFile;  //使用相机拍照后保存的地址
    public Uri album_img_uri;   //相册中选取照片的uri
    private static int current_api_version;   //手机的api版本
    private Long company_id;
    private CompanyActivityDao companyActivityDao;
    private String img_file1, img_file2, img_file3;
    private String[] typeArray = {"专业保洁", "家电清洗", "家居养护", "洗护服务"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.company_post_events);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    //设置全屏
        ImmersionBar.with(this).init();
        company_id = ((BaseApplication) getApplication()).getUSER_ID();  //获取当前的用户id
        SystemApplication.getInstance().addActivity(this);
        initView();
    }

    private void initView() {

        title = findViewById(R.id.activity_title);
        back = findViewById(R.id.back);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        activity_type = findViewById(R.id.activity_type);
        price = findViewById(R.id.activity_price);
        star_time = findViewById(R.id.start_time);
        end_time = findViewById(R.id.end_time);
        user_times = findViewById(R.id.user_times);
        describe = findViewById(R.id.activity_describe);
        post = findViewById(R.id.post);

        activity_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(AddActivityActivity.this);
                b.setTitle("活动类型")
                        .setSingleChoiceItems(typeArray,  //装载数组信息
                                //默认选中第一项
                                0,
                                //为列表添加监听事件
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case 0:
                                                activity_type.setText(typeArray[0]);
                                                break;
                                            case 1:
                                                activity_type.setText(typeArray[1]);
                                                break;
                                            case 2:
                                                activity_type.setText(typeArray[2]);
                                                break;
                                        }
                                        dialog.cancel();  //用户选择后，关闭对话框
                                    }
                                })
                        .create()
                        .show();
            }
        });

        star_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickDialog datePickDialog = new DatePickDialog(AddActivityActivity.this);
                //设置上下年分限制
                datePickDialog.setYearLimt(5);
                //设置标题
                datePickDialog.setTitle("选择时间");
                //设置类型
                datePickDialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                datePickDialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                datePickDialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                datePickDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        //设置时间格式
                        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        star_time.setText(timeStampFormat.format(date));
                    }
                });

                datePickDialog.show();
            }
        });

        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickDialog datePickDialog = new DatePickDialog(AddActivityActivity.this);
                //设置上下年分限制
                datePickDialog.setYearLimt(5);
                //设置标题
                datePickDialog.setTitle("选择时间");
                //设置类型
                datePickDialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                datePickDialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                datePickDialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                datePickDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        //设置时间格式
                        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        end_time.setText(timeStampFormat.format(date));
                    }
                });

                datePickDialog.show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //点击进行图片设置或更换照片
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog(1);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog(2);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog(3);
            }
        });
        //发布活动
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = activity_type.getText().toString();
                String activity_price = price.getText().toString();
                String start = star_time.getText().toString();
                String end = end_time.getText().toString();
                String activity_describe = describe.getText().toString();
                String activity_title = title.getText().toString();

                if (img_file1 == null) {
                    showText("活动的照片不能为空！");
                } else {
                    if (type.isEmpty()) {
                        showText("活动的类型不能为空！");
                    } else {
                        if (activity_title.isEmpty()) {
                            showText("活动标题不能为空！");
                        } else {
                            if (!parseToFloat(activity_price)) {
                                showText("价格的格式错误！");
                            } else {
                                if (isGetRadioBtn(user_times.getCheckedRadioButtonId())) {
                                    String times = ((RadioButton) findViewById(user_times.getCheckedRadioButtonId())).getText().toString();
                                    Float price = Float.parseFloat(activity_price); //价格
                                    if (activity_describe.isEmpty()) {
                                        showText("活动描述不能为空！");
                                    } else {
                                        //创建一个companyActivity实体
                                        CompanyActivity companyActivity = new CompanyActivity(null, activity_title, type, img_file1, img_file2, img_file3, start, end, price, company_id, times, activity_describe);
                                        //实例化dao
                                        companyActivityDao = ((BaseApplication) getApplication()).getCompanyActivityDao();
                                        //向数据库插入实体
                                        companyActivityDao.insert(companyActivity);
                                        //操作成功后跳转主页面
                                        Intent intent = new Intent(AddActivityActivity.this, CompanyMainPageActivity.class);
                                        startActivity(intent);
                                    }
                                } else {
                                    showText("请选择用户次数！");
                                }
                            }
                        }
                    }
                }
            }
        });


    }

    private void setDialog(final int i) {

        final Dialog dialog = new Dialog(this, R.style.BottomDialog);  //设置对话框的style为下弹下单（自定义的）
        LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.add_image_dialog, null); //加载对话框布局
        dialog.setContentView(layout);  //设置布局

        //跳转相机拍照
        layout.findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取系統sdk版本
                current_api_version = android.os.Build.VERSION.SDK_INT;

                // 激活相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // 判断存储卡是否可以用，可用进行存储
                if (hasSdcard()) {
                    //设置时间格式
                    SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
                    //获取当前系统时间
                    String filename = timeStampFormat.format(new Date());
                    //设置照片的文件名为 时间.jpg
                    tempFile = new File(Environment.getExternalStorageDirectory() + File.separator + "DCIM", filename + ".jpg");

                    if (current_api_version < 24) {   // 如果系统的sdk从文件中创建uri

                        camera_img_uri = Uri.fromFile(tempFile);

                        //指定输出地址
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, camera_img_uri);
                        Toast.makeText(AddActivityActivity.this, "保存在" + camera_img_uri, Toast.LENGTH_LONG).show();
                    } else {

                        //Android 7.0之后调用相机的方式不允许以file://的方式调用，需要以共享文件的方式
                        ContentValues contentValues = new ContentValues(1);

                        /**
                         * 检查是否有存储权限，以免崩溃
                         * ContextCompat 一个检查app是否有某种权限的工具
                         * ContentResolver就是按照一定规则访问内容提供者（ContentProvider）的数据
                         */
                        if (ContextCompat.checkSelfPermission(AddActivityActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {

                            Toast.makeText(AddActivityActivity.this, "请开启存储权限", Toast.LENGTH_SHORT).show();
                            return; //如果没有开启存储权限，退出此方法

                        }

                        //MediaStore.Images.Media.EXTERNAL_CONTENT_URI 设备照片存储的uri地址
                        camera_img_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

                        //指定拍照的输出地址
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, camera_img_uri);
                    }

                    if (i == 1) {
                        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
                    } else if (i == 2) {
                        startActivityForResult(intent, PHOTO_REQUEST_CAMERA_2);
                    } else {
                        startActivityForResult(intent, PHOTO_REQUEST_CAMERA_3);
                    }
                } else {
                    Toast.makeText(AddActivityActivity.this, "存储卡不可用", Toast.LENGTH_LONG).show();
                }
            }
        });

        //跳转系统相册选取照片
        layout.findViewById(R.id.album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用意图直接调用手机相册
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (i == 1) {

                    startActivityForResult(intent, IMAGE_REQUEST_CODE);
                } else if (i == 2) {
                    startActivityForResult(intent, IMAGE_REQUEST_CODE_2);

                } else {
                    startActivityForResult(intent, IMAGE_REQUEST_CODE_3);

                }
            }
        });

        //取消
        layout.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = getResources().getDisplayMetrics().widthPixels; // 宽度
        layout.measure(0, 0);
        lp.height = layout.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        window.setAttributes(lp);
        dialog.show();  //显示对话框
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PHOTO_REQUEST_CAMERA:  //拍照

                if (resultCode == RESULT_OK) {  //如果拍好了照
                    Intent intent = getCameraIntent();
                    startActivityForResult(intent, CAMERA_CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case PHOTO_REQUEST_CAMERA_2:
                if (resultCode == RESULT_OK) {  //如果拍好了照
                    Intent intent = getCameraIntent();
                    startActivityForResult(intent, CAMERA_CROP_PHOTO_2); // 启动裁剪程序
                }
                break;
            case PHOTO_REQUEST_CAMERA_3:
                if (resultCode == RESULT_OK) {  //如果拍好了照
                    Intent intent = getCameraIntent();
                    startActivityForResult(intent, CAMERA_CROP_PHOTO_3); // 启动裁剪程序
                }
                break;
            case CAMERA_CROP_PHOTO: //拍完照后的剪裁
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(camera_img_uri));
                        img_file1 = savePicture(bitmap);
                        img1.setImageBitmap(bitmap);
                        img2.setVisibility(View.VISIBLE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CAMERA_CROP_PHOTO_2:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(camera_img_uri));
                        img_file2 = savePicture(bitmap);
                        img2.setImageBitmap(bitmap);
                        img3.setVisibility(View.VISIBLE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CAMERA_CROP_PHOTO_3:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(camera_img_uri));
                        img_file3 = savePicture(bitmap);
                        img3.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case IMAGE_REQUEST_CODE:    //从相册选取照片
                if (resultCode == RESULT_OK) {
                    Intent intent = getImageIntent();
                    intent.setDataAndType(data.getData(), "image/*"); //设置路径和文件类型为图片
                    album_img_uri = data.getData();
                    startActivityForResult(intent, ALBUM_CROP_PHOTO); // 启动裁剪程序
                }
                break;

            case IMAGE_REQUEST_CODE_2:
                if (resultCode == RESULT_OK) {
                    Intent intent = getImageIntent();
                    intent.setDataAndType(data.getData(), "image/*"); //设置路径和文件类型为图片
                    album_img_uri = data.getData();
                    startActivityForResult(intent, ALBUM_CROP_PHOTO_2); // 启动裁剪程序
                }
                break;
            case IMAGE_REQUEST_CODE_3:
                if (resultCode == RESULT_OK) {
                    Intent intent = getImageIntent();
                    intent.setDataAndType(data.getData(), "image/*"); //设置路径和文件类型为图片
                    album_img_uri = data.getData();
                    startActivityForResult(intent, ALBUM_CROP_PHOTO_3); // 启动裁剪程序
                }
                break;
            case ALBUM_CROP_PHOTO:  //从照片剪裁出来的
                if (resultCode == RESULT_OK && data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    img_file1 = savePicture(bitmap);
                    img1.setImageBitmap(bitmap);
                    img2.setVisibility(View.VISIBLE);
                }
                break;
            case ALBUM_CROP_PHOTO_2:
                if (resultCode == RESULT_OK && data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    img_file2 = savePicture(bitmap);
                    img2.setImageBitmap(bitmap);
                    img3.setVisibility(View.VISIBLE);
                }
                break;
            case ALBUM_CROP_PHOTO_3:
                if (resultCode == RESULT_OK && data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    img_file3 = savePicture(bitmap);
                    img3.setImageBitmap(bitmap);
                }
                break;
        }
    }

    /**
     * 判断sdcard是否被挂载
     */
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public static String savePicture(Bitmap bitmap) {
        //设置时间格式
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        //获取当前系统时间
        String filename = timeStampFormat.format(new Date());
        String fileName = Environment.getExternalStorageDirectory().toString()
                + File.separator
                + "DCIM"
                + File.separator
                + filename + ".jpg";
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();//创建文件夹
        }
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);//向缓冲区压缩图片
            bos.flush();
            bos.close();
            return fileName;
        } catch (Exception e) {
            return null;
        }
    }

    //提示
    private void showText(String text) {
        Toast.makeText(AddActivityActivity.this, text, Toast.LENGTH_LONG).show();
    }

    private boolean parseToFloat(String string) {
        try {
            float n = Float.parseFloat(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Intent getCameraIntent() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(camera_img_uri, "image/*"); //设置路径和文件类型为图片
        intent.putExtra("crop", true);
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, camera_img_uri);
        return intent;
    }

    public static Intent getImageIntent() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.putExtra("crop", true);
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.putExtra("return-data", true);
        return intent;
    }

    private boolean isGetRadioBtn(int btn_id) {
        RadioButton radioButton = findViewById(btn_id);
        if (radioButton != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

}

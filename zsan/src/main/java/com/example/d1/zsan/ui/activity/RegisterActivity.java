package com.example.d1.zsan.ui.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.d1.library.net.NetCallback;
import com.example.d1.library.net.NetJsonUtil;
import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseActivity;
import com.example.d1.zsan.ui.adapter.HandAdapter;
import com.example.d1.zsan.utils.Check;
import com.example.d1.zsan.utils.Constant;

import java.util.Calendar;

public class RegisterActivity extends ZBaseActivity implements View.OnClickListener {
    private LinearLayout mActivityRegister;
    private EditText mEtFaiName;
    private EditText mEtFaiPwd;
    private RadioGroup mRgFai;
    private RadioButton mRbManRgFai;
    private RadioButton mRbWomanFgFai;
    private RadioGroup mRgStutas;
    private RadioButton mRbUserRg;
    private RadioButton mRbDoctorRg;
    private RadioButton mRbAdminRg;
    private Button mBtBirthFai;
    private ImageView mIvLl1;
    private Button mBtTouxiangLl1;
    private Button mRegRegBtn;
    private Button mRegCancleBtn;

    private DatePickerDialog dialog;
    private int[] headimgs = { R.drawable.img0, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8 };
    private Dialog handdialog;
    private RecyclerView mRecyclerView;
    private NetJsonUtil mNetJsonUtil;
    private String name,pwd,status,sex;

    @Override
    public boolean isNeedTitle() {
        return true;
    }

    @Override
    public int setRootView() {
        return R.layout.activity_register;
    }

    @Override
    public void initViews() {
        setTitleCenter("注册");
        mActivityRegister = (LinearLayout) findViewById(R.id.activity_register);
        mEtFaiName = (EditText) findViewById(R.id.et_fai_name);
        mEtFaiPwd = (EditText) findViewById(R.id.et_fai_pwd);
        mRgFai = (RadioGroup) findViewById(R.id.rg_fai);
        mRbManRgFai = (RadioButton) findViewById(R.id.rb_man_rg_fai);
        mRbWomanFgFai = (RadioButton) findViewById(R.id.rb_woman_fg_fai);
        mRgStutas = (RadioGroup) findViewById(R.id.rg_stutas);
        mRbUserRg = (RadioButton) findViewById(R.id.rb_user_rg);
        mRbDoctorRg = (RadioButton) findViewById(R.id.rb_doctor_rg);
        mRbAdminRg = (RadioButton) findViewById(R.id.rb_admin_rg);
        mBtBirthFai = (Button) findViewById(R.id.bt_birth_fai);
        mIvLl1 = (ImageView) findViewById(R.id.iv_ll1);
        mBtTouxiangLl1 = (Button) findViewById(R.id.bt_touxiang_ll1);
        mRegRegBtn = (Button) findViewById(R.id.reg_reg_btn);
        mRegCancleBtn = (Button) findViewById(R.id.reg_cancle_btn);

        mBtBirthFai.setOnClickListener(this);
        mBtTouxiangLl1.setOnClickListener(this);
        mRegCancleBtn.setOnClickListener(this);
        mRegRegBtn.setOnClickListener(this);


    }
    @Override
    public void initDatas() {
        createDataDialog();
        createHandDialog();

    }
    private void createHandDialog() {
        handdialog = new Dialog(this);
        handdialog.setTitle("选择头像");
        handdialog.setContentView(R.layout.item_hand);
        mRecyclerView = (RecyclerView) handdialog.findViewById(R.id.recyc_hand);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        HandAdapter handAdapter = new HandAdapter(this,headimgs);
        handAdapter.setOnItemClickListener(new HandAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mIvLl1.setImageResource(headimgs[position]);
                handdialog.cancel();
            }
        });
        mRecyclerView.setAdapter(handAdapter);
    }

    private void createDataDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                mBtBirthFai.setText(i + "年" + (i1+1) + "月" + i2 + "日");
            }
        },year,monthOfYear,dayOfMonth);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_birth_fai:
                dialog.show();
                break;
            case R.id.bt_touxiang_ll1:
                handdialog.show();
                break;
            case R.id.reg_reg_btn:
                reg();
                break;
            case R.id.reg_cancle_btn:
                finish();
                break;
        }
    }

    private void reg() {
        name =mEtFaiName.getText().toString();
        pwd = mEtFaiPwd.getText().toString();
        switch ((mRgFai.getCheckedRadioButtonId())){
            case R.id.rb_man_rg_fai:
                sex =mRbManRgFai.getText().toString();
                break;
            case R.id.rb_woman_fg_fai:
                sex =mRbWomanFgFai.getText().toString();
                break;
        }
        switch (mRgStutas.getCheckedRadioButtonId()){
            case R.id.rb_user_rg:
                status = mRbUserRg.getText().toString();
                break;
            case R.id.rb_doctor_rg:
                status = mRbDoctorRg.getText().toString();
                break;
            case R.id.rb_admin_rg:
                status = mRbAdminRg.getText().toString();
                break;
        }
        if(Check.check(name)&&Check.check(pwd)){
            if (status.equals("管理员")) {
                Toast.makeText(mActivitySelf, "管理员身份不可以申请", Toast.LENGTH_SHORT).show();
            }else {
                System.out.println(sex);
                System.out.println(status);
                submit();
            }
        }else {
            Toast.makeText(this, "格式不对", Toast.LENGTH_SHORT).show();
        }
    }

    private void submit() {
        mNetJsonUtil = new NetJsonUtil(Constant.REGISTER_URL,new RegisterNetCallback());
        mNetJsonUtil.mRequestParams.addParameter("subname",name);
        mNetJsonUtil.mRequestParams.addParameter("subpwd",pwd);
        mNetJsonUtil.mRequestParams.addParameter("subsex",sex);
        mNetJsonUtil.mRequestParams.addBodyParameter("substutas",status);
        mNetJsonUtil.execute();
    }


    private class RegisterNetCallback extends NetCallback<String> {
        @Override
        public void onError(Throwable e) {
            Log.e("qqq", "onError: ");
            Toast.makeText(mActivitySelf, "home-url-error", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onOK(String entity) {
            Log.e("qqq", "oncg: ");
            System.out.println("成功");
            switch (entity){
                case "用户已被注册":
                    Toast.makeText(mActivitySelf, entity, Toast.LENGTH_LONG).show();
                    break;
                case "注册成功":
                    Toast.makeText(mActivitySelf, entity, Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case "申请已提交":
                    Toast.makeText(mActivitySelf, entity, Toast.LENGTH_LONG).show();
                    finish();
                    break;
//                case "管理员身份不可以申请":
//                    Toast.makeText(mActivitySelf, entity, Toast.LENGTH_SHORT).show();
//                    break;
            }
        }

        @Override
        public void onFinish() {
            Log.e("qqq", "onfs: ");
        }
    }
}

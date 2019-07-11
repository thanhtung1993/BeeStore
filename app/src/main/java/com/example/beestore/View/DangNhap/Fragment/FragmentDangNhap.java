package com.example.beestore.View.DangNhap.Fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beestore.Model.DangNhap.ModelDangNhap;
import com.example.beestore.R;
import com.example.beestore.View.TrangChu.TrangChuActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class FragmentDangNhap extends Fragment  implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    Button btnDangNhapFB,btnDangNhapGG,btnDangNhap;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInClient mGoogleSignInClient;
    ModelDangNhap modelDangNhap;
    EditText edtTenDangNhap,edtMatKhau;
    public static int SIGN_GOOGLEPLUS=123;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //tạo view cho fragment dang nhập
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);

        // tạo api đăng nhập google
        ModelDangNhap modelDangNhap=new ModelDangNhap();
        mGoogleApiClient= modelDangNhap.LayGoogleApiClient(getContext(),this);

//đăng nhập fb
        callbackManager=CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
              //  Log.d("kiemtra","thanhcong");
                Intent iTrangChu=new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }


            @Override
            public void onCancel() {
               // Log.d("kiemtra","thoat");

            }

            @Override
            public void onError(FacebookException error) {
              //  Log.d("kiemtra","loi");

            }
        });

        btnDangNhapFB=view.findViewById(R.id.btnDangNhapFB);
        btnDangNhapGG=view.findViewById(R.id.btnDangNhapGG);
        btnDangNhap=view.findViewById(R.id.btnDangNhap);
        edtTenDangNhap=view.findViewById(R.id.edtDiaChiEmail);
        edtMatKhau=view.findViewById(R.id.edtMatKhauDN);
        btnDangNhapFB.setOnClickListener(this);
        btnDangNhapGG.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);


        return view;

    }

    @Override
    public void onClick(View v) {

        int id=v.getId();
        switch (id)

        {
            case R.id.btnDangNhapFB://mở popup fb
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
                break;
            case R.id.btnDangNhapGG:
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, SIGN_GOOGLEPLUS);
                break;
            case R.id.btnDangNhap:
                String tendangnhap=edtTenDangNhap.getText().toString();
                String matkhau=edtMatKhau.getText().toString();
                boolean kiemtra=  modelDangNhap.KiemTraDangNhap(getActivity(),tendangnhap,matkhau);
                Log.d("kiemtra", String.valueOf(kiemtra));
                if (kiemtra==true)
                {
                    Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                    startActivity(iTrangChu);
                }
                else
                {
                    Toast.makeText(getActivity(),"Tên đăng nhập và mật khẩu không đúng !",Toast.LENGTH_SHORT).show();
                }


                break;

        }




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

        if (requestCode ==SIGN_GOOGLEPLUS)
        {
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
          if (result.isSuccess())
          {
              Intent iTrangchu=new Intent(getActivity(),TrangChuActivity.class);
              startActivity(iTrangchu);
          }
        }
          // Log.d("google",result.getSignInAccount().getEmail());
        }




    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}



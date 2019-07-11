package com.example.beestore.Model.DangNhap;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;


import com.example.beestore.Connectinternet.DownloadJSON;
import com.example.beestore.View.TrangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangNhap {
     AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;
    public AccessToken LayTokenFacebookHienTai()
    {

       accessTokenTracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {


                    accessToken =currentAccessToken;

            }
        };

       accessToken=AccessToken.getCurrentAccessToken();
        return accessToken;
    }

    public String LayCachedDangNhap(Context context)
     {

        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String tennv = cachedDangNhap.getString("tennv","");
        return tennv;
    }
    public void CapNhatCachedDangNhap(Context context,String tennv)
    {
       SharedPreferences cachedDangNhap=context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
       SharedPreferences.Editor editor=cachedDangNhap.edit();
       editor.putString("tennv",tennv);
       editor.commit();
    }

    public boolean KiemTraDangNhap(Context context,String tendangnhap,String matkhau)
    {
        Boolean kiemtra=false;
        String duongdan= TrangChuActivity.SERVER_NAME+"/webbeestore/loaisanpham.php";
        List<HashMap<String,String>> attrs=new ArrayList<>();

        HashMap<String,String> hsHam=new HashMap<>();
        hsHam.put("ham","KiemTraDangNhap");

        HashMap<String,String> hsTenDangNhap=new HashMap<>();
        hsTenDangNhap.put("tendangnhap",tendangnhap);

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",matkhau);

        attrs.add(hsHam);
        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);

        DownloadJSON downloadJSON=new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieu=downloadJSON.get();
            JSONObject jsonObject=new JSONObject(dulieu);
            String jsonketqua=jsonObject.getString("ketqua");
            if (jsonketqua.equals("true"))
            {
                kiemtra=true;
                String tennhanvien=jsonObject.getString("tennv");

                CapNhatCachedDangNhap(context,tennhanvien);



            }
            else
            {
                kiemtra=false;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return kiemtra;
    }
    public void huyToken()
    {
        accessTokenTracker.stopTracking();
    }
    public GoogleApiClient LayGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener){
        GoogleApiClient mGoogleApiClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(((AppCompatActivity)context),failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        return mGoogleApiClient;
    }
   /* public GoogleSignInResult LayThongDangNhapGoogle(GoogleApiClient googleApiClient){
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            return opr.get();
        }else{
            return null;
        }
    }*/

}

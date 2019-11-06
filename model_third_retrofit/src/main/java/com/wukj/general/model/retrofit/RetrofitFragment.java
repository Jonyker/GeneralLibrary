package com.wukj.general.model.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wukj.general.common.fragment.SupListFragment;
import com.wukj.general.model.retrofit.api.GetRequest_Interface;
import com.wukj.general.model.retrofit.api.PostRequest_Interface;
import com.wukj.general.model.retrofit.entity.Translation;
import com.wukj.general.model.retrofit.entity.Translation2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFragment extends SupListFragment {

    public static final String[] TITLES = {"requestGET", "requestPOST", "title3", "title4", "title5"};

    @Override
    protected int getCreateVID() {
        return R.layout.fragment_retrofit;
    }

    @Override
    protected void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //设置ListView为单选模式
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // 给listView设置adapter显示列表
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, TITLES));
        //默认选中第一个item
        getListView().setItemChecked(0, true);

    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        switch (position){
            case 0:
                requestGET();
                break;
            case 1:
                requestPOST();
                break;
        }
    }

    public void requestGET() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<Translation> call = request.getCall();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                // 步骤7：处理返回的数据结果
                response.body().show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }


    public void requestPOST() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        PostRequest_Interface request = retrofit.create(PostRequest_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<Translation2> call = request.getCall("I love you");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation2>() {

            //请求成功时回调
            @Override
            public void onResponse(Call<Translation2> call, Response<Translation2> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                System.out.println(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation2> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }
        });


    }


}

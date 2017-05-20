package com.example.d1.zsan.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.d1.library.net.NetCallback;
import com.example.d1.library.net.NetJsonUtil;
import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseFragment;
import com.example.d1.zsan.entity.Department;
import com.example.d1.zsan.ui.activity.SearchActivity;
import com.example.d1.zsan.ui.adapter.HandAdapter;
import com.example.d1.zsan.ui.adapter.ItemSearchAdapter;
import com.example.d1.zsan.ui.adapter.ItemlvSearchFragAdapter;
import com.example.d1.zsan.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends ZBaseFragment {
    private AutoCompleteTextView mActvSearchFragSearch;
    private Button mBtSearchFragSearch;
    private RecyclerView mRvFragSearch;
    private List<String> strings;
    private NetJsonUtil mNetJsonUtil;
    private  boolean havename = false;
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public int setRootView() {
        return R.layout.fragment_search;
    }

    @Override
    public void initViews() {
        setTitleCenter("搜索");
        mActvSearchFragSearch = (AutoCompleteTextView) findViewById(R.id.actv_search_frag_search);
        mBtSearchFragSearch = (Button) findViewById(R.id.bt_search_frag_search);
        mRvFragSearch = (RecyclerView) findViewById(R.id.rv_frag_search);
        mBtSearchFragSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mActvSearchFragSearch.getText().toString();
                if(name==null){
                    Toast.makeText(mActivitySelf, "没输入科室", Toast.LENGTH_SHORT).show();
                }else {
                    for(String s:strings){
                        if(name.equals(s)){
                            havename=true;
                        }
                    }
                }
                if(havename){
                    Intent intent = new Intent(mActivitySelf, SearchActivity.class);
                    intent.putExtra("name",name);
                    mActivitySelf.startActivity(intent);
                }else{
                    Toast.makeText(mActivitySelf, "没有这个科室", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void initDatas() {
        mNetJsonUtil = new NetJsonUtil(Constant.SEARCHFRAG,new SearchNetCallback());
        mNetJsonUtil.execute();
    }
    public ArrayAdapter<String> mArrayAdapter;
    private class SearchNetCallback extends NetCallback<Department> {

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onOK(final Department entity) {
            strings =new ArrayList<>();
            for(Department.ListDepart listDepart:entity.getList()){
                strings.add(listDepart.getName());
            }
            mArrayAdapter =new ArrayAdapter<String>(mActivitySelf,android.R.layout.simple_expandable_list_item_1,strings);
            mActvSearchFragSearch.setAdapter(mArrayAdapter);
            mActvSearchFragSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    AutoCompleteTextView actview = (AutoCompleteTextView) view;
                    if(b){
                        actview.showDropDown();
                    }
                }
            });
//            mActvSearchFragSearch.setAdapter(new ArrayAdapter<String>(mActivitySelf,android.R.layout.simple_expandable_list_item_1,strings));




            mRvFragSearch.setLayoutManager(new LinearLayoutManager(mActivitySelf));
            ItemlvSearchFragAdapter itemlvSearchFragAdapter = new ItemlvSearchFragAdapter(mActivitySelf,entity.getList());
            itemlvSearchFragAdapter.setOnItemClickListener(new HandAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mActivitySelf, SearchActivity.class);
                    intent.putExtra("name",entity.getList().get(position).getName());
                    mActivitySelf.startActivity(intent);
                }
            });
            mRvFragSearch.setAdapter(itemlvSearchFragAdapter);
        }

        @Override
        public void onFinish() {

        }
    }

}

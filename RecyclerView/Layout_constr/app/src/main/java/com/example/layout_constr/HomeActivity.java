package com.example.layout_constr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layout_constr.demo.HomeData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * https://juejin.cn/post/6844903725270581261
 */
public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<HomeData> mlistArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.hone_recylerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        initData();

    }

    private void initData(){
        mlistArray = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            HomeData homeData = new HomeData();
            homeData.setTitle("title"+i);
            homeData.setName("name"+i);
            homeData.setContent("content"+i);
            homeData.setNum(""+i);
            homeData.setTime(new Date().toString());
            homeData.setType("type"+i);
            homeData.setImgUrl("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1732627441,2510723839&fm=26&gp=0.jpg");
            mlistArray.add(homeData);
        }

        HomeAdapter homeAdapter = new HomeAdapter(mlistArray);
        homeAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("");
            }
        });
        recyclerView.setAdapter(homeAdapter);
    }

    /**
     * 创建ViewHolder类，用来缓存item中的子控件，避免不必要的findViewById
     */
    public class MyViewHoder extends RecyclerView.ViewHolder {
        TextView titleView;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.home_title);
        }
    }

    public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        //数据源
        private List<HomeData> mList;

        //点击监听
        private AdapterView.OnItemClickListener mOnItemClickListener;

        //数据源
        public HomeAdapter(ArrayList<HomeData> list) {
            mList = list;
        }

        public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
            mOnItemClickListener = onItemClickListener;
        }

        /**
         * 创建ViewHolder
         *
         * @param
         * @param
         * @return
         */
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
            RecyclerView.ViewHolder myViewHoder = new MyViewHoder(inflate);
            return myViewHoder;
        }

        /**
         * 通过ViewHolder对item中的控件进行控制（如：显示数据等等）
         *
         * @param
         * @param
         */
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            MyViewHoder homeHoder = (MyViewHoder) holder;
            HomeData homeData = mList.get(position);
            homeHoder.titleView.setText(homeData.getTitle());
        }

        /**
         * 返回列表长度
         *
         * @return
         */
        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}

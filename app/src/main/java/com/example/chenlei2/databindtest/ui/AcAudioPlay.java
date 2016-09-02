package com.example.chenlei2.databindtest.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.apkfuns.logutils.LogUtils;
import com.example.chenlei2.databindtest.BR;
import com.example.chenlei2.databindtest.R;
import com.example.chenlei2.databindtest.ServMusicPlayer;
import com.example.chenlei2.databindtest.model.db.MMediaFile;
import com.example.chenlei2.databindtest.ui.base.BaseActivity;

import java.util.List;

/**
 * Created by chenlei2 on 2016/9/1 0001.
 */
public class AcAudioPlay extends BaseActivity implements View.OnClickListener{

    public static final String KEY_MUSIC = "music";

    ServMusicPlayer mServMusicPlayer;

    Button btn_previous,btn_pause,btn_next;
    RecyclerView rv_musicList;
    List<MMediaFile> fileList;
    ViewDataBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                LogUtils.i("service bind finished");
                mServMusicPlayer = ((ServMusicPlayer.MsgBinder)iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        bindService(new Intent(this, ServMusicPlayer.class),conn, Context.BIND_AUTO_CREATE);
        binding = DataBindingUtil.setContentView(this, R.layout.ac_audio_play);
      /*  mediaFile = (MediaFile) getIntent().getExtras().getSerializable(KEY_MUSIC);
        binding.setVariable(BR.music,mediaFile);*/
        rv_musicList = (RecyclerView) findViewById(R.id.rv_musicList);
        btn_next =$(R.id.btn_next);
        btn_pause = $(R.id.btn_pause);
        btn_previous = $(R.id.btn_previous);

        rv_musicList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_musicList.setAdapter(new MusicAdapter(fileList,this));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_next:
                break;
            case R.id.btn_pause:
                break;
            case R.id.btn_previous:
                break;
        }
    }

    public  class MusicAdapter extends RecyclerView.Adapter {
        List<MMediaFile> values;
        Context context;

        MusicAdapter(List<MMediaFile> values, Context context) {
            this.values = values;
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater
                    .from(context), R.layout.item_music, parent, false);
            ViewHolder holder = new ViewHolder(binding.getRoot());
            holder.setBinding(binding);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ViewHolder holder1 = (ViewHolder) holder;
            holder1.getBinding().setVariable(BR.musicItem, values.get(position));
            holder1.getBinding().executePendingBindings();

            ((ViewHolder) holder).getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.setVariable(BR.music,values.get(position));
                }
            });


        }


        @Override
        public int getItemCount() {
            return values.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private ViewDataBinding binding;

            public ViewHolder(View itemView) {
                super(itemView);
            }

            public ViewDataBinding getBinding() {
                return this.binding;
            }

            public void setBinding(ViewDataBinding binding) {
                this.binding = binding;
            }
        }

    }
}

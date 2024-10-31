package com.example.demo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;

import org.libpag.PAGComposition;
import org.libpag.PAGFile;
import org.libpag.PAGView;

import java.io.IOException;

public class AnimationActivity extends AppCompatActivity {

    private static final String TAG = AnimationActivity.class.getSimpleName();
    private LinearLayout mFrameLL;
    private AnimationDrawable mFameAnimation;
    private int switchStatus = 0;//0-关闭，1-开启，-1，置灰
    private int flag = 0;//0-没点过，1-点过
    private ImageView mPandaIV;
    private PAGView pagView;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        //帧动画使用----------------
        /*
        代码使用：帧动画直接作为一个属性，控制属性的播放即可
         */
        mFrameLL = findViewById(R.id.ll_frame);
        mFameAnimation = (AnimationDrawable) mFrameLL.getBackground();
        mFrameLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0) {
                    mFameAnimation.start();
                    flag = 1;
                } else {
                    mFameAnimation.stop();
                    flag = 0;
                }
            }
        });
        //补间动画使用----------------
        /*
        代码使用：进行配置，把配置好的动画加载出来用
         */
        mPandaIV = findViewById(R.id.iv_panda);
//        Animation animation=AnimationUtils.loadAnimation(this,R.anim.alpha);
//        Animation animation=AnimationUtils.loadAnimation(this,R.anim.rotate);
//        Animation animation=AnimationUtils.loadAnimation(this,R.anim.scale);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        mPandaIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPandaIV.startAnimation(animation);

            }
        });
        //属性动画的使用，首先用ValueAnimator----------------
        /*
        代码使用：代码中编写，直接控制属性即可
        适用场景：属性动画：
        复杂的动画效果，如旋转、缩放、平移等。
        需要精确控制动画过程的场景。
        补间动画：
        简单的动画效果，如淡入淡出、滑动等。
        对性能要求不高的场景。
         */
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                Log.i(TAG, "onAnimationUpdate: value=" + value);
            }
        });
        valueAnimator.start();

        //它的更佳的实例ObjectAnimator
        TextView textView = findViewById(R.id.tv_attribute_Animation);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
        //使用监听器---比较详细的回调情况，使用addListener
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {

            }
        });
        //使用Adpter,就是做了一层适配，想用哪个用哪个
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        //PAG动画使用----------------
        LinearLayout pagLL = findViewById(R.id.ll_pag);
        pagView = new PAGView(this);
        pagView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        //在使用前，如果还在播放，就停止掉，使他释放
//        if (pagView.isPlaying()) {
//            Log.i(TAG, "playPag stop current");
//            pagView.stop();
//        }
        pagLL.addView(pagView);//需要添加View，也就是动画
        // 加载 PAG 文件
        PAGComposition composition = PAGFile.Load(getAssets(), "countdown.pag");
        pagView.setComposition(composition);
        // 设置动效参数
        pagView.setRepeatCount(1);
        //播放完了就释放掉media session
        pagView.addListener(new PAGView.PAGViewListener() {
            @Override
            public void onAnimationStart(PAGView view) {
                Log.i(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(PAGView view) {
                Log.i(TAG, "onAnimationEnd: ");
            }

            @Override
            public void onAnimationCancel(PAGView view) {
                Log.i(TAG, "onAnimationCancel: ");
            }

            @Override
            public void onAnimationRepeat(PAGView view) {
            }

            @Override
            public void onAnimationUpdate(PAGView pagView) {

            }
        });
        pagView.play();
        //mp4动画使用----------------可以使用MediaPlayer 类来播放 MP4 视频
        // 视频文件的路径(放到raw上面了
        TextureView mp4Iv = findViewById(R.id.iv_mp4);
        mediaPlayer = MediaPlayer.create(this, R.raw.steering_wheel);
        mediaPlayer.start();
        //将视频内容渲染到 TextureView 上，以便在应用中显示视频。
        mp4Iv.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
                mediaPlayer.setSurface(new Surface(surface));
            }
            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {

                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {

            }
        });
//        //释放MP4的音频点
//        if (mediaPlayer!= null) {
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }


    }

    //释放media mession
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (pagView != null) {
            pagView.stop();
            pagView.freeCache();
        }
        if (mediaPlayer!= null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
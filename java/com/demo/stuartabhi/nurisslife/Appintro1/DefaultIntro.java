package com.demo.stuartabhi.nurisslife.Appintro1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.demo.stuartabhi.nurisslife.MainActivity;
import com.demo.stuartabhi.nurisslife.R;
import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by stuartabhi on 5/31/2016.
 */
public class DefaultIntro extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(SampleLayout.newInstance(R.layout.intro));
        addSlide(SampleLayout.newInstance(R.layout.intro1));
        addSlide(SampleLayout.newInstance(R.layout.intro3));
        setProgressIndicator();

    }
    private void loadMainActivity()
    {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        //super.onSkipPressed(currentFragment);
        loadMainActivity();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
       // super.onDonePressed(currentFragment);
        loadMainActivity();
    }

    public void getStarted(View v)
    {
        loadMainActivity();

    }
}

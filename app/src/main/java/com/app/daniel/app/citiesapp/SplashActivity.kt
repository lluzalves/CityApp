package com.app.daniel.app.citiesapp

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.app.daniel.app.citiesapp.base.BaseActivity
import com.app.daniel.app.citiesapp.base.MvpView
import com.app.daniel.app.citiesapp.dependency.ApplicationDependency
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity(), MvpView {

    private lateinit var animation: AnimationDrawable
    private lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ApplicationDependency.SHARED.inject(this)
        ApplicationDependency.SHARED.inject()
        container.setBackgroundResource(R.drawable.animation_splash_screen)
        animation = container.background as AnimationDrawable
        presenter = SplashPresenter()
        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        animation.start()
        onAnimationProgress(50, animation)
    }

    private fun onAnimationProgress(period: Long, animationDrawable: AnimationDrawable) {
        Handler().run {
            return@run postDelayed({
                when {
                    animationDrawable.current !== animationDrawable.getFrame(animationDrawable.numberOfFrames - 1) -> onAnimationProgress(
                        period,
                        animationDrawable
                    )
                    else -> {
                        state_info.text = getString(R.string.fetching_data)
                        presenter.startFetchData()
                    }
                }
            }, period)
        }
    }

    override fun onCompleted() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onLoading(loadingMessage: String) {
        Toast.makeText(this, loadingMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, throwable.localizedMessage, Toast.LENGTH_SHORT).show()
    }
}

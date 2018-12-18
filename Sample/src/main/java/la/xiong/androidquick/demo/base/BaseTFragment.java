package la.xiong.androidquick.demo.base;

import android.os.Bundle;

import javax.inject.Inject;

import la.xiong.androidquick.demo.MyApplication;
import la.xiong.androidquick.demo.injector.component.DaggerFragmentComponent;
import la.xiong.androidquick.demo.injector.component.FragmentComponent;
import la.xiong.androidquick.demo.injector.module.FragmentModule;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseTFragment<T extends BasePresenter> extends BaseFragment {
    @Inject
    public T mPresenter;
    protected String TAG = "BaseTFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dagger2
        initInjector();
        if (this instanceof BaseView)
            mPresenter.initVM(this);
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
        super.onDestroy();
    }

    protected void initInjector() {

    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .applicationComponent(MyApplication.getApplicationComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

}

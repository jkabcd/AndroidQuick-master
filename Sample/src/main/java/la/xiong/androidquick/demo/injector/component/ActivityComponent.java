package la.xiong.androidquick.demo.injector.component;

import android.app.Activity;
import android.content.Context;

import dagger.Component;
import la.xiong.androidquick.demo.injector.ActivityScope;
import la.xiong.androidquick.demo.injector.module.ActivityModule;
import la.xiong.androidquick.demo.ui.activity.NetworkActivity;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    Activity getActivity();
    Context getContext();

    void inject(NetworkActivity networkActivity);
}

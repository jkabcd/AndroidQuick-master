package la.xiong.androidquick.demo.ui.activity;

import android.view.View;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.ui.fragment.GreenDaoFragment;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DatabaseActivity extends BaseActivity {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_database;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.btn_ui_greendao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ui_greendao:
                readyGo(GreenDaoFragment.class);
                break;
        }
    }
}

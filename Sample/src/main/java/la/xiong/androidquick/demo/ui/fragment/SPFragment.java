package la.xiong.androidquick.demo.ui.fragment;

import android.view.View;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseTFragment;
import la.xiong.androidquick.tool.SpUtil;
import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class SPFragment extends BaseTFragment {

    private String testJsonString;

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_sp;
    }

    @OnClick({R.id.btn_tools_sp_put, R.id.btn_tools_sp_get})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tools_sp_put:
                SpUtil.setParam("test", "this is a test");
                ToastUtil.showToast("写入成功");
                break;
            case R.id.btn_tools_sp_get:
                ToastUtil.showToast((String)SpUtil.getParam("test", "default"));
                break;
        }
    }
}

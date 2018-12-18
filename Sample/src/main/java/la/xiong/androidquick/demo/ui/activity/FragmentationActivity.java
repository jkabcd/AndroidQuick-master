package la.xiong.androidquick.demo.ui.activity;

import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFActivity;
import la.xiong.androidquick.demo.base.BaseFFragment;
import la.xiong.androidquick.demo.ui.fragment.Example2Fragment;
import la.xiong.androidquick.demo.ui.fragment.Example3Fragment;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class FragmentationActivity extends BaseFActivity {

    @BindView(R.id.btn_fragmentation_test1)
    Button btnFragmentationTest1;
    @BindView(R.id.btn_fragmentation_test2)
    Button btnFragmentationTest2;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_fragmentation;
    }

    @Override
    protected void initViewsAndEvents() {
        BaseFFragment fragment = findFragment(Example2Fragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, Example2Fragment.newInstance());
        }
    }

    @OnClick({R.id.btn_fragmentation_test1, R.id.btn_fragmentation_test2})
    public void click(View v) {
        final ISupportFragment topFragment = getTopFragment();
        BaseFFragment exmaple2Fragment = (BaseFFragment) topFragment;
        switch (v.getId()) {
            case R.id.btn_fragmentation_test1:
                Example2Fragment fragment2 = findFragment(Example2Fragment.class);
                exmaple2Fragment.start(fragment2, SupportFragment.SINGLETASK);
                break;
            case R.id.btn_fragmentation_test2:
                Example3Fragment fragment3 = findFragment(Example3Fragment.class);
                if (fragment3 == null) {
                    exmaple2Fragment.startWithPopTo(Example3Fragment.newInstance(), Example2Fragment.class, false);
                } else {
                    // 如果已经在栈内,则以SingleTask模式start,以下两种方式均可
                    exmaple2Fragment.start(fragment3, SupportFragment.SINGLETASK);
//                    exmaple2Fragment.popTo(Example3Fragment.class, false);
                }
                break;
        }
    }
}

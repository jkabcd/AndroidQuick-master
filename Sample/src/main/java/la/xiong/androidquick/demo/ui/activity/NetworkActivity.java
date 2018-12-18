package la.xiong.androidquick.demo.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseActivity;
import la.xiong.androidquick.demo.constant.Constants;
import la.xiong.androidquick.demo.ui.fragment.network1.Network1Fragment;
import la.xiong.androidquick.demo.ui.fragment.network2.Network2Fragment;
import la.xiong.androidquick.demo.ui.fragment.network3.Network3Fragment;
import la.xiong.androidquick.tool.DialogUtil;
import la.xiong.androidquick.tool.LogUtil;
import la.xiong.androidquick.tool.ToastUtil;
import la.xiong.androidquick.ui.dialog.CommonDialog;
import la.xiong.androidquick.ui.permission.EasyPermissions;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class NetworkActivity extends BaseActivity {

    private String deniedPermsString;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_network;
    }

    @Override
    protected void initViewsAndEvents() {
        //动态更改状态栏沉浸式颜色
        changeStatusBarColor(R.color.colorAccent);
    }

    @OnClick({R.id.btn_network_common, R.id.btn_network_diff_url, R.id.btn_network_download, R.id.btn_network_to_get, R.id.btn_network_httpurlconnection})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_network_common:
                readyGo(Network1Fragment.class);
                break;
            case R.id.btn_network_diff_url:
                readyGo(Network2Fragment.class);
                break;
            case R.id.btn_network_download:
                permissionsCheck();
                break;
            case R.id.btn_network_to_get:
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "get");
                readyGo(Network1Fragment.class, bundle1);
            case R.id.btn_network_httpurlconnection:
                readyGo(Network3Fragment.class);
                break;
        }
    }

    private void permissionsCheck() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        performCodeWithPermission(1, Constants.RC_PERMISSION_PERMISSION_ACTIVITY, perms, new PermissionCallback() {
            @Override
            public void hasPermission(List<String> allPerms) {
                LogUtil.d(TAG, "allPerms:" + allPerms.toString());
                ToastUtil.showToast("Granted");
                readyGo(DownloadActivity.class);
            }

            @Override
            public void noPermission(List<String> deniedPerms, List<String> grantedPerms, Boolean hasPermanentlyDenied) {
                LogUtil.d(TAG, "deniedPerms:" + deniedPerms.toString());
                if (hasPermanentlyDenied) {
                    StringBuilder denied = new StringBuilder();
                    if (deniedPerms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE) || deniedPerms.contains(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        denied.append("存储文件使用");
                    }
                    deniedPermsString = denied.toString();
                    EasyPermissions.goSettingsPermissions(NetworkActivity.this, 2, Constants.RC_PERMISSION_PERMISSION_ACTIVITY, Constants.RC_PERMISSION_BASE);
                }
            }

            @Override
            public void showDialog(int dialogType, final EasyPermissions.DialogCallback callback) {
                switch (dialogType) {
                    case 1:
                        DialogUtil.getDialogBuilder(mContext).
                                setTitle(getString(R.string.app_name)).
                                setMessage(getString(R.string.dialog_storage_permission)).
                                setPositiveButton("OK").
                                setBtnClickCallBack(new CommonDialog.DialogBtnCallBack() {
                                    @Override
                                    public void onDialogButClick(boolean isConfirm) {
                                        if (isConfirm)
                                            callback.onGranted();
                                    }
                                }).show().setCancelable(false);
                        break;
                    case 2:
                        DialogUtil.getDialogBuilder(mContext).
                                setTitle(getString(R.string.app_name)).
                                setMessage(getString(R.string.dialog_rationale_ask_again, deniedPermsString)).
                                setPositiveButton("Go to setting").
                                setBtnClickCallBack(new CommonDialog.DialogBtnCallBack() {
                                    @Override
                                    public void onDialogButClick(boolean isConfirm) {
                                        if (isConfirm)
                                            callback.onGranted();
                                    }
                                }).show().setCancelable(false);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.d(TAG, "requestCode:" + requestCode);
        if (requestCode == Constants.RC_PERMISSION_BASE) {
            permissionsCheck();
        }
    }
}

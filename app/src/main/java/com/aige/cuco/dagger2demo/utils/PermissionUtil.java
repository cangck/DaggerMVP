package com.aige.cuco.dagger2demo.utils;

import com.aige.cuco.dagger2demo.global.rx.ErrorHandleSubscriber;
import com.aige.cuco.dagger2demo.global.rx.RxErrorHandler;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import timber.log.Timber;

/**
 * @package com.aige.cuco.dagger2demo.utils
 * @fileName PermissionUtil
 * @date 2018/11/14
 * @describe
 * @email shenzhencuco@gmail
 */
public class PermissionUtil {
    private static final String TAG = "Permission";

    public PermissionUtil() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public interface RequestPermission {
        /**
         * 请求权限成功回调
         */
        void onRequestPermissionSuccess();

        /**
         * 用户拒绝权限请求，权限请求失败，但还可以继续请求改权限
         *
         * @param permissions
         */
        void onRequestPermissionFailure(List<String> permissions);

        /**
         * 用户拒绝了权限请求并且用户选择以后不再询问，权限请求失败，这时候不能继续请求改权限，需要提示用户进入设置页面打开该权限
         *
         * @param permissions 请求失败的权限名
         */
        void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions);
    }

    /**
     * 请求权限
     *
     * @param requestPermission
     * @param rxPermissions
     * @param rxErrorHandler
     * @param permissions
     */
    public static void requestPermission(final RequestPermission requestPermission, RxPermissions rxPermissions,
                                         RxErrorHandler rxErrorHandler, String... permissions) {
        if (permissions == null || permissions.length == 0) return;

        List<String> needRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (!rxPermissions.isGranted(permission)) {
                needRequest.add(permission);
            }
        }
        if (needRequest.isEmpty()) {
            requestPermission.onRequestPermissionSuccess();
        } else {
            rxPermissions.requestEach(needRequest.toArray(new String[needRequest.size()]))
                    .buffer(permissions.length)
                    .subscribe(new ErrorHandleSubscriber<List<Permission>>(rxErrorHandler) {
                        @Override
                        public void onNext(List<Permission> permissions) {
                            List<String> failurePermissions = new ArrayList<>();
                            List<String> askNeverAgainPermissions = new ArrayList<>();
                            for (Permission p :
                                    permissions) {
                                if (!p.granted) {
                                    if (p.shouldShowRequestPermissionRationale) {
                                        failurePermissions.add(p.name);
                                    } else {
                                        askNeverAgainPermissions.add(p.name);
                                    }
                                }
                            }
                            if (!failurePermissions.isEmpty()) {
                                Timber.tag(TAG).d("Request permissions failure");
                                requestPermission.onRequestPermissionFailure(failurePermissions);
                            }
                            if (askNeverAgainPermissions.size() > 0) {
                                Timber.tag(TAG).d("Request permissions Again");
                                requestPermission.onRequestPermissionFailureWithAskNeverAgain(askNeverAgainPermissions);
                            }

                            if (failurePermissions.size() == 0 && askNeverAgainPermissions.size() == 0) {
                                Timber.tag(TAG).d("Request Permission Success");
                                requestPermission.onRequestPermissionSuccess();
                            }
                        }
        });
        }
    }
}

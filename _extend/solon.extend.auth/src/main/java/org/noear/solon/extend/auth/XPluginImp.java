package org.noear.solon.extend.auth;

import org.noear.solon.SolonApp;
import org.noear.solon.core.Aop;
import org.noear.solon.core.Plugin;
import org.noear.solon.extend.auth.annotation.AuthLogined;
import org.noear.solon.extend.auth.annotation.AuthPermissions;
import org.noear.solon.extend.auth.annotation.AuthRoles;
import org.noear.solon.extend.auth.validator.LoginedInterceptor;
import org.noear.solon.extend.auth.validator.PermissionsInterceptor;
import org.noear.solon.extend.auth.validator.RolesInterceptor;

/**
 * @author noear
 * @since 1.3
 */
public class XPluginImp implements Plugin {
    @Override
    public void start(SolonApp app) {
        Aop.context().beanAroundAdd(AuthPermissions.class, PermissionsInterceptor.instance);
        Aop.context().beanAroundAdd(AuthRoles.class, RolesInterceptor.instance);
        Aop.context().beanAroundAdd(AuthLogined.class, LoginedInterceptor.instance);
    }
}

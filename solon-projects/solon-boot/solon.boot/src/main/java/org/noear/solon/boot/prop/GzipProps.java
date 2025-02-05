package org.noear.solon.boot.prop;

import org.noear.solon.Solon;
import org.noear.solon.core.handle.Context;

import java.util.HashSet;
import java.util.Set;

/**
 * 静态文件压缩配置
 *
 * @author noear
 * @since 2.5
 */
public class GzipProps {
    private static boolean enable = false;
    private static long minSize = 4096;
    private static Set<String> mimeTypes = new HashSet<>();

    static {
        mimeTypes.add("text/html");
        mimeTypes.add("text/xml");
        mimeTypes.add("text/plain");
        mimeTypes.add("text/css");
        mimeTypes.add("text/javascript");
        mimeTypes.add("application/javascript");
        mimeTypes.add("application/xml");

        load();
    }

    /**
     * 是否启用
     */
    public static boolean enable() {
        return enable;
    }

    /**
     * 最小大小
     */
    public static long minSize() {
        return minSize;
    }

    /**
     * 设置是否启用
     */
    public static void enable(boolean enable) {
        GzipProps.enable = enable;
    }

    /**
     * 设置最小大小
     */
    public static void minSize(long minSize) {
        GzipProps.minSize = minSize;
    }

    /**
     * 添加 mime
     */
    public static void mimeAdd(String mime) {
        mimeTypes.add(mime);
    }

    /**
     * 移除 mime
     */
    public static void mimeRemove(String mime) {
        mimeTypes.remove(mime);
    }

    /**
     * 加载配置
     */
    public static void load() {
        if (Solon.app() == null) {
            return;
        }

        enable = Solon.cfg().getBool("server.http.gzip.enable", false);
        minSize = Solon.cfg().getLong("server.http.gzip.minSize", 4096);
        Solon.cfg().getMap("server.http.gzip.mimeTypes").forEach((key, val) -> {
            for (String mime : val.split(",")) {
                mimeTypes.add(mime);
            }
        });
    }

    /**
     * 配置需要 gzip
     */
    public static boolean requiredGzip(Context ctx, String contentType, long size) {
        if (enable && size >= minSize) {
            String ae = ctx.header("Accept-Encoding");
            if (ae != null && ae.contains("gzip") && mimeTypes.contains(resolveMime(contentType))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 配置包函 mime
     */
    public static boolean hasMime(String contentType) {
        if (enable) {
            return mimeTypes.contains(resolveMime(contentType));
        }

        return false;
    }

    /**
     * 分析 mime
     *
     * @param contentType 内容类型
     * @return mime
     */
    public static String resolveMime(String contentType) {
        int idx = contentType.indexOf(';');
        if (idx > 0) {
            return contentType.substring(0, idx);
        } else {
            return contentType;
        }
    }
}

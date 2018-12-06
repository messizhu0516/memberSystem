package com.zhuqifeng.commons.weixin.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 异常重试工具类
 * @author L.cm
 */
public class RetryUtils {
	static private Logger logger = LogManager.getLogger(RetryUtils.class);
    /**
     * 回调结果检查
     */
    public interface ResultCheck {
        boolean matching();

        String getJson();
    }

    /**
     * 在遇到异常时尝试重试
     * @param retryLimit 重试次数
     * @param retryCallable 重试回调
     * @param <V> 泛型
     * @return V 结果
     */
    public static <V extends ResultCheck> V retryOnException(int retryLimit,
            java.util.concurrent.Callable<V> retryCallable) {

        V v = null;
        for (int i = 0; i < retryLimit; i++) {
            try {
                v = retryCallable.call();
            } catch (Exception e) {
            	logger.warn("retry on " + (i + 1) + " times v = " + (v == null ? null : v.getJson()) , e);
            }
            if (null != v && v.matching()) break;
            logger.error("retry on " + (i + 1) + " times but not matching v = " + (v == null ? null : v.getJson()));
        }
        return v;
    }

    /**
     * 在遇到异常时尝试重试
     * @param retryLimit 重试次数
     * @param sleepMillis 每次重试之后休眠的时间
     * @param retryCallable 重试回调
     * @param <V> 泛型
     * @return V 结果
     * @throws java.lang.InterruptedException 线程异常
     */
    public static <V extends ResultCheck> V retryOnException(int retryLimit, long sleepMillis,
            java.util.concurrent.Callable<V> retryCallable) throws java.lang.InterruptedException {

        V v = null;
        for (int i = 0; i < retryLimit; i++) {
            try {
                v = retryCallable.call();
            } catch (Exception e) {
            	logger.warn("retry on " + (i + 1) + " times v = " + (v == null ? null : v.getJson()) , e);
            }
            if (null != v && v.matching()) break;
            logger.error("retry on " + (i + 1) + " times but not matching v = " + (v == null ? null : v.getJson()));
            Thread.sleep(sleepMillis);
        }
        return v;
    }

}

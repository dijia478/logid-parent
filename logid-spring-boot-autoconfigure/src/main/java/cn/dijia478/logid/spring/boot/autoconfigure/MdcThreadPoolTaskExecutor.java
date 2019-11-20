package cn.dijia478.logid.spring.boot.autoconfigure;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 带有父线程mdc的子线程池
 *
 * @author dijia478
 * @date 2019-11-20 14:09
 */
public class MdcThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private static final String LOG_ID = "logId";

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        Map<String, String> context = MDC.getCopyOfContextMap();
        return super.submit(() -> {
            // 将父线程的MDC内容传给子线程
            if (MapUtil.isNotEmpty(context) && StrUtil.isNotEmpty(context.get(LOG_ID))) {
                MDC.setContextMap(context);
            } else {
                // 为空则设置新值
                MDC.put(LOG_ID, ObjectId.next());
            }

            T result = null;
            try {
                result = task.call();
            } finally {
                MDC.clear();
            }
            return result;
        });
    }


}


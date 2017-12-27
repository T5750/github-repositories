package org.ibase4j.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;

import top.ibase4j.core.listener.ServerListener;

@Component
public class BizServerListener extends ServerListener {
    protected final Logger logger = LogManager.getLogger(this.getClass());

    public void onApplicationEvent(ApplicationReadyEvent event) {
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
        super.onApplicationEvent(event);
    }
}

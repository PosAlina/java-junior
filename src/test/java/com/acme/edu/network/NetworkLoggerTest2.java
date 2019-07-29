package com.acme.edu.network;

import com.acme.edu.exceptions.LogException;
import com.acme.edu.network.client.LogClientProxy;
import org.junit.Test;

public class NetworkLoggerTest2 {
    @Test
    public void shouldSendMessage() throws LogException {
        LogClientProxy logClientProxy = new LogClientProxy();
        for (int i = 0; i < 50_000; i++) {
            logClientProxy.log("test string");
            logClientProxy.log("test strin2");
        }
        logClientProxy.close();
    }
}

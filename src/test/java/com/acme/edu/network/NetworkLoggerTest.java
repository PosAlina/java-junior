package com.acme.edu.network;

import com.acme.edu.exceptions.LogException;
import com.acme.edu.network.client.LogClientProxy;
import com.acme.edu.network.server.LogServerProxy;
import org.junit.Ignore;
import org.junit.Test;

public class NetworkLoggerTest {
    @Test
    public void shouldSendMessage() throws LogException {
        LogClientProxy logClientProxy = new LogClientProxy();
        for (int i = 0; i < 50_000; i++) {
            logClientProxy.log(2);
            logClientProxy.log(true);
        }
        logClientProxy.close();
    }
}

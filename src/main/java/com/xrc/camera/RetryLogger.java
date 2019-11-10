package com.xrc.camera;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;

import java.util.logging.Logger;

public class RetryLogger extends RetryListenerSupport {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public <T, E extends Throwable> void onError(
            RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {

        Object contextName = context.getAttribute(RetryContext.NAME);
        assert contextName != null;
        String methodSignature = contextName.toString();
        int methodParametersIx = methodSignature.indexOf("(");
        String methodName = (methodParametersIx > 0) ?
                methodSignature.substring(0, methodParametersIx) : methodSignature;

        log.warning(String.format("Retryable method '%s' threw %dth exception '%s'.",
                methodName,
                context.getRetryCount(),
                throwable
        ));
    }
}

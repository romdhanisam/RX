package eu.yayi.config;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleSubscriber implements Subscriber<String> {

    private Logger logger = LoggerFactory.getLogger(SimpleSubscriber.class);

    @Override
    public void onSubscribe(Subscription subscription) {
        logger.info("onSubscribe() -> {}", Long.MAX_VALUE);
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(String item) {
        logger.info("onNext() -> {}", item);
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("onError()", throwable);
    }

    @Override
    public void onComplete() {
        logger.info("onComplete -> All Done.");
    }
}

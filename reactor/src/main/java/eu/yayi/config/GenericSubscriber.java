package eu.yayi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.BaseSubscriber;

@Component
public class GenericSubscriber<T> extends BaseSubscriber<T> {

    private static final Logger log =
            LoggerFactory.getLogger(GenericSubscriber.class);

    @Override
    protected void hookOnNext(T value) {
        log.info("consumed {} ", value.toString());
        super.hookOnNext(value);
    }
}

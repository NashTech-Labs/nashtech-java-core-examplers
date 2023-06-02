package com.nashtech.observation.service.impl;

import com.nashtech.observation.service.ObservationService;
import com.nashtechglobal.service.CoreLogger;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class ObservationServiceImpl implements ObservationService {

    /**
     *  Inject nashtech corelogger.
     */
    @Autowired
    private CoreLogger log;

    /**
     *  Generate a Random instance.
     */
    private final SecureRandom random = new SecureRandom();
    /**
     *  Simulated latency duration.
     */
    private static final Long LATENCY = 200L;

    /**
     *  Method under observation.
     * @param argument
     */
    @Override
    @Observed(name = "exampler-metric-name",
    contextualName = "exampler-metric-contextualName")
    public void observationMethod(final String argument) {
        log.info("Running observability for the arg <{}>", argument);
        try {
            Thread.sleep(random.nextLong(LATENCY)); // simulates latency
        } catch (InterruptedException e) {
            log.error("Thread interrupted");
            Thread.currentThread().interrupt();
        }
    }
}

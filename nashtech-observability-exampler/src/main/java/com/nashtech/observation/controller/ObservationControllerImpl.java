package com.nashtech.observation.controller;

import com.nashtech.observation.service.ObservationService;
import com.nashtechglobal.service.CoreLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObservationControllerImpl implements ObservationController {
    /**
     * Inject logging dependency.
     */
    private final CoreLogger coreLogger;
    /**
     * Inject service dependency.
     */
    private final ObservationService service;

    /**
     * Constructs an {@code ObservationControllerImpl} with
     * the specified parameters.
     *
     * @param coreLoggerParam The {@link CoreLogger} instance to
     *                        be used for logging in the controller.
     *                        Must not be {@code null}.
     * @param serviceParam    The {@link ObservationService}
     *                        instance that provides
     *                        observation-related
     *                        functionality. Must not be {@code null}.
     */
    @Autowired
    public ObservationControllerImpl(final CoreLogger coreLoggerParam,
                                     final ObservationService serviceParam
    ) {
        this.coreLogger = coreLoggerParam;
        this.service = serviceParam;
    }

    /**
     *  Observation method.
     * @param argument
     */
    @Override
    public void observerMethod(final String argument) {
        coreLogger.info("Observer method in controller....");
        service.observationMethod(argument);
    }
}

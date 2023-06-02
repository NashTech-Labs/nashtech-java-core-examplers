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
    @Autowired
    private CoreLogger coreLogger;
    /**
     * Inject service dependency.
     */
    @Autowired
    private ObservationService service;

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

package com.nashtech.observation.controller;

import com.nashtech.observation.service.ObservationService;
import com.nashtechglobal.service.CoreLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class ObservationControllerImplTest {
    @Mock
    private CoreLogger coreLogger;

    @Mock
    private ObservationService observationService;

    @InjectMocks
    private ObservationControllerImpl observationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObserverMethod() {
        // Given
        String argument = "testArgument";

        // When
        observationController.observerMethod(argument);

        // Then
        // Verify that the coreLogger.info method is called with the expected message
        verify(coreLogger).info("Observer method in controller....");

        // Verify that the observationService.observationMethod is called with the expected argument
        verify(observationService).observationMethod(argument);
    }

}
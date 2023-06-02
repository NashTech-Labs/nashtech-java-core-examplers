package com.nashtech.observation.service;

import com.nashtech.observation.service.impl.ObservationServiceImpl;
import com.nashtechglobal.service.impl.CoreLoggerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ObservationServiceTest {

    @Mock
    private CoreLoggerImpl coreLogger;

    @InjectMocks
    private ObservationServiceImpl observationService;

    @Test
    void testObservationMethod() {
        observationService.observationMethod("test");
        verify(coreLogger, times(1))
                .info("Running observability for the arg <{}>",
                        "test");
    }

    @Test
    void testObservationMethodWithException() {
        Thread.currentThread().interrupt();
        observationService.observationMethod("test");
        verify(coreLogger, times(1))
                .info("Running observability for the arg <{}>",
                        "test");
        verify(coreLogger, times(1))
                .error("Thread interrupted");
    }
}

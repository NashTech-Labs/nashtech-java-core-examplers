package com.nashtechglobal.service;

import com.nashtechglobal.model.ReactiveExampleApiRequest;
import com.nashtechglobal.model.ReactiveExampleApiResponse;
import com.nashtechglobal.reactive.service.ReactiveRequestCallback;
import com.nashtechglobal.reactive.service.ReactiveService;
import com.nashtechglobal.web.model.ExternalApiRequest;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This service class provides methods to retrieve data from an external API
 * using reactive programming constructs, such as Mono and Flux.
 *
 * @see ReactiveExampleApiResponse
 * @see ReactiveExampleApiRequest
 * @see ReactiveService
 * @see Mono
 * @see Flux
 * @see ExternalApiRequest
 * @see HttpHeaders
 */
@Service
@AllArgsConstructor
public class ReactiveExampleService {

    /**
     * Autowired CoreLogger instance for logging application
     * events and messages.
     */
    @Autowired
    private CoreLogger coreLogger;

    /**
     * The {@link ReactiveService} instance.
     */
    @Autowired
    private ReactiveService reactiveService;

    /**
     * Retrieves a Flux of ReactiveExampleApiResponse objects from an external
     * API using the given ReactiveExampleApiRequest.
     *
     * @param apiRequest a ReactiveExampleApiRequest object containing the
     *                   request information
     * @return a Flux of ReactiveExampleApiResponse objects
     */
    public ResponseEntity<Flux<ReactiveExampleApiResponse>>
    fetchFluxExternalApiResponses(
            final ReactiveExampleApiRequest apiRequest) {
        final Flux<ResponseEntity<ReactiveExampleApiResponse>> responseFlux =
                reactiveService.executeFluxApiRequest(createRequest(apiRequest),
                        ReactiveExampleApiResponse.class,
                        new ReactiveRequestCallback() {
                            @Override
                            public void onSuccess(final Integer statusCode) {
                                coreLogger.info(
                                        "Fetched response with status code: {}",
                                        statusCode);
                            }

                            @Override
                            public void onError(final Throwable throwable) {
                                coreLogger.warn("Unable to fetch Response");
                            }
                        });
        coreLogger.info("Fetching Flux External API response !!!");
        Flux<ReactiveExampleApiResponse> fluxExternalApiResponse =
                Flux.from(responseFlux)
                        .filter(res -> res.getStatusCode() == HttpStatus.OK)
                        .map(ResponseEntity::getBody);
        return createResponseEntity(fluxExternalApiResponse);
    }

    /**
     * Retrieves a Mono of ReactiveExampleApiResponse objects from an external
     * API using the given ReactiveExampleApiRequest.
     *
     * @param apiRequest a ReactiveExampleApiRequest object containing the
     *                   request information
     * @return a Mono of ReactiveExampleApiResponse objects
     */
    public ResponseEntity<Mono<ReactiveExampleApiResponse>>
    fetchMonoExternalApiResponses(
            final ReactiveExampleApiRequest apiRequest) {
        final Mono<ResponseEntity<ReactiveExampleApiResponse>> responseMono =
                reactiveService.executeMonoApiRequest(createRequest(apiRequest),
                        ReactiveExampleApiResponse.class,
                        new ReactiveRequestCallback() {
                            @Override
                            public void onSuccess(final Integer statusCode) {
                                coreLogger.info(
                                        "Fetched response with status code: {}",
                                        statusCode);
                            }

                            @Override
                            public void onError(final Throwable throwable) {
                                coreLogger.warn("Unable to fetch Response");
                            }
                        });
        coreLogger.info("Fetching Mono External API response !!!");
        Mono<ReactiveExampleApiResponse> monoExternalApiResponse =
                Mono.from(responseMono)
                        .filter(res -> res.getStatusCode() == HttpStatus.OK)
                        .map(ResponseEntity::getBody);
        return createResponseEntity(monoExternalApiResponse);
    }

    /**
     * Creates HttpHeaders object from given map of header key-value pairs.
     *
     * @param httpHeadersMap map of header key-value pairs
     * @return HttpHeaders object created from given map
     */
    private HttpHeaders createHeaders(
            final Map<String, String> httpHeadersMap) {
        final HttpHeaders headers = new HttpHeaders();
        Optional.ofNullable(httpHeadersMap).ifPresent(
                map -> map.forEach(headers::add));
        return headers;
    }

    /**
     * Creates an ExternalApiRequest object using the given
     * ReactiveExampleApiRequest.
     *
     * @param examplerRequest a ReactiveExampleApiRequest object containing
     *                        the request information
     * @return an ExternalApiRequest object
     */

    public ExternalApiRequest<ReactiveExampleApiRequest> createRequest(
            final ReactiveExampleApiRequest examplerRequest) {
        if (examplerRequest != null) {
            return ExternalApiRequest.<ReactiveExampleApiRequest>builder()
                    .body(examplerRequest.getBody())
                    .httpMethod(HttpMethod.valueOf(
                            examplerRequest.getHttpMethod()))
                    .url(examplerRequest.getUrl())
                    .headers(createHeaders(
                            examplerRequest.getHeaders())).build();
        }
        return null;
    }

    /**
     * Creates a ResponseEntity object based on the provided body.
     *
     * @param body {@link Flux} or {@link Mono} t
     * @param <T>  the type of the body
     * @return a ResponseEntity object containing the body, with a status of
     * OK if the body is present, or BAD REQUEST if the body is null
     */
    private <T> ResponseEntity<T> createResponseEntity(final T body) {
        final Optional<T> optionalBody = Optional.ofNullable(body);
        return optionalBody.map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}

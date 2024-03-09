package net.protsenko;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@RestController
public class ChunkController {
    private static final List<String> jsonData = Arrays.asList(
            "{\"field\": \"value1\"}",
            "{\"field\": \"value2\"}",
            "{\"field\": \"value3\"}"
    );

    @GetMapping(value = "/data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> getData() {
        return Flux.interval(Duration.ofSeconds(5))
                .zipWithIterable(jsonData)
                .map(tuple -> ServerSentEvent.builder(tuple.getT2()).build());
    }
}

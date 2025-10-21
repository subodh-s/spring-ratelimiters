package dev.speedystack.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class RateLimitConfig {

    /**
     * Bursty limiter: allows short spikes up to a small burst, then smooths to ~5 requests/sec.
     */
    @Bean
    public RateLimiter burstyRateLimiter() {
        // 5 permits per second (choose what you like)
        return RateLimiter.create(120/60.0);
    }

    /**
     * Warming-up limiter: ramps up over 10 seconds before delivering full 5 rps.
     * Useful when your downstream needs a gentle warm-up.
     */
    @Bean
    public RateLimiter warmingUpRateLimiter() {
        return RateLimiter.create(5.0, 10, TimeUnit.SECONDS);
    }
}

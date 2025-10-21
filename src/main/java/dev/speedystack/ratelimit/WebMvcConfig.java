package dev.speedystack.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final RateLimiter limiter;

    public WebMvcConfig(RateLimiter burstyRateLimiter /* or warmingUpRateLimiter */) {
        // Pick which one you want globally:
        this.limiter = burstyRateLimiter; // swap to warmingUpRateLimiter for ramp-up behavior
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RateLimitInterceptor(limiter))
                .addPathPatterns("/api/**");
    }
}

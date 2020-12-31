package com.bsuir.herman.auth.auditor;

import com.bsuir.herman.auth.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * This method returns the username of authenticated player. If we register player,
     * {@code authentication.getPrincipal()}
     * returns string {@code "anonymousPlayer"} that's why we should check if getPrincipal() is object Player.
     * Otherwise we can see {@exception ClassCastException}
     *
     * @return username of authenticated player
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        if (authentication.getPrincipal() instanceof User) {
            return Optional.of(((User) authentication.getPrincipal()).getEmail());
        } else {
            return Optional.of((String) authentication.getPrincipal());
        }
    }
}



package com.inn.nextDoorIt.service;

import java.util.Set;

public interface TokenBlacklistService {
    void addToBlacklist(String tokenId);

    boolean isBlacklisted(String tokenId);

    Set<String> getAllBlacklistedTokens();
}

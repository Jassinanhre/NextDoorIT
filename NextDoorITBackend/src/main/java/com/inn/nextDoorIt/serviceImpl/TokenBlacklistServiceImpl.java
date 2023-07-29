package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.service.TokenBlacklistService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenBlacklistServiceImpl implements TokenBlacklistService {
    private final Set<String> blacklistedTokens = new HashSet<>();

    @Override
    public void addToBlacklist(String tokenId) {
        blacklistedTokens.add(tokenId);
    }

    @Override
    public boolean isBlacklisted(String tokenId) {
        return blacklistedTokens.contains(tokenId);
    }

    @Override
    public Set<String> getAllBlacklistedTokens() {
        return blacklistedTokens;
    }
}

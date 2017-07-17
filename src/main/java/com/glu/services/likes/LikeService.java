package com.glu.services.likes;

public interface LikeService {
    void like(String playerId);
    long getLikes(String playerId);
}

package com.glu.services.likes;


import com.glu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    private UserRepository userRepository;

    @Autowired
    public LikeServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void like(String playerId) {
        userRepository.incrementLikes(playerId);
    }

    @Override
    public long getLikes(String playerId) {
        // Never updated counters will have no record
        Long likes = userRepository.findLikes(playerId);
        return likes == null ? 0L : likes;
    }
}

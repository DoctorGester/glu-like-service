package com.glu.repository;

import com.glu.model.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<User> {
    @Query("UPDATE users SET likes = likes + 1 WHERE id = ?0")
    void incrementLikes(String id);

    @Query("SELECT likes FROM users WHERE id = ?0")
    Long findLikes(String id);
}

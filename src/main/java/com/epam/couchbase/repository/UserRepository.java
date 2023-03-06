package com.epam.couchbase.repository;

import com.epam.couchbase.entity.User;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByEmail(String email);

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND ANY sport IN sports SATISFIES sport.sportName = $1 END")
    List<User> findAllUsersBySportName(String sportName);
}

package com.isagiongo.apirestangular.repositories;

import com.isagiongo.apirestangular.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}

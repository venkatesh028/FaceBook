package com.ideas2it.ideasbook.repository;

import com.ideas2it.ideasbook.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository of the user which extends the jpaRepository
 *
 * @author  Venkatesh TM
 * @version  1.0
 * @since  28/12/2022
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

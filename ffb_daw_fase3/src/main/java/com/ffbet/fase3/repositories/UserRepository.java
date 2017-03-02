/**
 * 
 */
package com.ffbet.fase3.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ffbet.fase3.domain.User;
/**
 * @author Marco
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{

}

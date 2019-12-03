package cap.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cap.userservice.beans.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>
{
	User findByUsername(String username);
}


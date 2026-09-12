package com.mcsg.repo;

import com.mcsg.entity.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {

    public Optional<Counsellor> findByEmailAndPwd(String email, String pwd);
}

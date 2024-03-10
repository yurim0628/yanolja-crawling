package com.example.crawling.repository;

import com.example.crawling.model.YaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<YaUser, Long> {
}

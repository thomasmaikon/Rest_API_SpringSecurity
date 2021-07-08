package com.example.jwt.DB;

import com.example.jwt.model.UsinaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsinaRepository extends JpaRepository<UsinaModel, String> {
}

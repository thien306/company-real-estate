package com.codegym.repository;

import com.codegym.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeRepository extends JpaRepository<Type,Long> {
}

package com.astrapay.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.astrapay.template.model.Example;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Integer> {

	
}

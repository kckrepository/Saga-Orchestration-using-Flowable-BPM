package org.example.repository;

import org.example.entity.DetailTrx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DetailTrxRepository extends JpaRepository<DetailTrx, UUID> {}

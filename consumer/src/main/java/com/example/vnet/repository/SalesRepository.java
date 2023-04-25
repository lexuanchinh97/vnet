package com.example.vnet.repository;

import com.example.vnet.model.SalesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<SalesData, Long>, JpaSpecificationExecutor<SalesData> {
}

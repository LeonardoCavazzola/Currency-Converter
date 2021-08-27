package com.converter.api.repository;

import com.converter.api.model.Conversion;
import com.converter.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionRepository extends JpaRepository<Conversion, Long> {

    Page<Conversion> findAllByUser(User user, Pageable pageable);

}

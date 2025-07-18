package com.data.session08.repo;

import com.data.session08.model.entity.Account;
import com.data.session08.model.req.AccountRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}

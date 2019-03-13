package com.formssi.verify.server;

import com.formssi.verify.domain.Account;
import com.formssi.verify.dto.AccountDTO;

public interface AccountService {
    Account login(AccountDTO account);
}

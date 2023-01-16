package org.camunda.bpm.getstarted.loanapproval;

import java.util.List;
import java.util.Set;

public interface UserRepositoryCustom {
    List<User> findUserByEmails(Set<String> emails);
}
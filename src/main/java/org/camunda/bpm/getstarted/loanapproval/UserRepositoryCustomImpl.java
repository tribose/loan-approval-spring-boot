package org.camunda.bpm.getstarted.loanapproval;

import org.hibernate.criterion.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findUserByEmails(Set<String> emails) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);

        Path<String> emailPath = user.get("email");

        List<Predicate> predicates = new ArrayList<>();
        for (String email : emails) {
            predicates.add(cb.like(emailPath, email));
        }
        query.select(user)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        ExampleMatcher  matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("firstName", contains());
        User user1 = User.builder()
                .id(100L)
                .firstName("Tridib")
                .email("tridib@email.com")
                .build();
        Example.create(user1);



        return entityManager.createQuery(query)
            .getResultList();
    }


}
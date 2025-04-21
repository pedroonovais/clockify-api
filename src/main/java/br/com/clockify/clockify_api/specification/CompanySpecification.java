package br.com.clockify.clockify_api.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.clockify.clockify_api.model.Company;
import br.com.clockify.clockify_api.model.CompanyFilter;
import jakarta.persistence.criteria.Predicate;

public class CompanySpecification {
    
    public static Specification<Company> withFilters(CompanyFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.name() != null) {
                predicates.add(cb.like(
                        cb.lower(root.get("name")), "%" + filter.name().toLowerCase() + "%"));
            }

            if (filter.cnpj() != null) {
                predicates.add(cb.like(
                        cb.lower(root.get("cnpj")), "%" + filter.cnpj().toLowerCase() + "%"));
            }

            if (filter.address() != null) {
                predicates.add(cb.like(
                        cb.lower(root.get("address")), "%" + filter.address().toLowerCase() + "%"));
            }

            if (filter.email() != null) {
                predicates.add(cb.like(
                        cb.lower(root.get("email")), "%" + filter.email().toLowerCase() + "%"));
            }

            if (filter.startDate() != null && filter.endDate() != null) {
                predicates.add(
                        cb.between(root.get("createdAt"), filter.startDate(), filter.endDate()));
            }

            if (filter.startDate() != null && filter.endDate() == null) {
                predicates.add(
                        cb.equal(root.get("createdAt"), filter.startDate()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}

package br.com.clockify.clockify_api.model;

import java.time.LocalDate;

public record CompanyFilter(String name, String cnpj, String address, String email, LocalDate startDate, LocalDate endDate) {
}

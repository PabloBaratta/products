package org.example.sales;

import java.util.List;

public interface CommissionRules {
	double calculateCommission(List<Sale> sales);

}

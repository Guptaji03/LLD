package com.Practice.LLD.services;

import com.Practice.LLD.entities.Expense;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ExpenseValidatorService {

    public boolean validate(Expense expense) {
        return switch (expense.getSplitType()) {
            case PERCENTAGE ->
                    validateByPercentage(
                            expense.getAmount(),
                            expense.getExpenseDistribution()
                    );

            case UNEQUALLY ->
                    validateUnEqualAmount(
                            expense.getAmount(),
                            expense.getExpenseDistribution()
                    );

            case EQUALLY ->
                    validateEqualAmount(
                            expense.getAmount(),
                            expense.getExpenseDistribution()
                    );
        };
    }


    public boolean validateByPercentage(BigDecimal amount, Map<Long, BigDecimal> members){
        BigDecimal finalSum = BigDecimal.ZERO;
        log.info("checking for the Percentage validation");
        for (Map.Entry<Long, BigDecimal> entry : members.entrySet()) {
            if(entry.getValue() == null){
                return false;
            }
            finalSum = finalSum.add(entry.getValue());
        }
        return finalSum.compareTo(BigDecimal.valueOf(100)) == 0;
    }

    public boolean validateUnEqualAmount(BigDecimal amount, Map<Long,BigDecimal> members){
        BigDecimal finalSum = BigDecimal.ZERO;
       for(Map.Entry<Long,BigDecimal> entry : members.entrySet()){
           if(entry.getValue() == null){
               return false;
           }
           finalSum = finalSum.add(entry.getValue());
       }
       return finalSum.compareTo(amount) == 0;
    }

    public boolean validateEqualAmount(BigDecimal amount, Map<Long,BigDecimal> members){
        BigDecimal finalSum =  BigDecimal.ZERO;
        for(Map.Entry<Long,BigDecimal> entry : members.entrySet()){
            if(entry.getValue() == null){
                return false;
            }
            finalSum = finalSum.add(entry.getValue());
        }
        return finalSum.compareTo(amount)==0;
    }

    public Map<Long, BigDecimal> convertPercentageToAmount(
            BigDecimal totalAmount,
            Map<Long, BigDecimal> membersPercentage
    ) {
        Map<Long, BigDecimal> result = new HashMap<>();

        for (Map.Entry<Long, BigDecimal> entry : membersPercentage.entrySet()) {
            Long userId = entry.getKey();
            BigDecimal percentage = entry.getValue();

            BigDecimal amount = percentage
                    .multiply(totalAmount)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            result.put(userId, amount);
        }

        return result;
    }

}

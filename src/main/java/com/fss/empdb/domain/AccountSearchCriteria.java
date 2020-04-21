package com.fss.empdb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AccountSearchCriteria {

    String accountName;

    private Region[] region;
}

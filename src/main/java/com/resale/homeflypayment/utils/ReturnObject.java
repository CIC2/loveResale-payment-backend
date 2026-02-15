package com.resale.homeflypayment.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnObject<T> {
    String message;
    Boolean status;
    T data;

}


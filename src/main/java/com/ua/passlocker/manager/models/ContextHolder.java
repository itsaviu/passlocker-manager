package com.ua.passlocker.manager.models;

import com.ua.passlocker.manager.models.entity.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContextHolder {

    private UserDetails userDetails;

}

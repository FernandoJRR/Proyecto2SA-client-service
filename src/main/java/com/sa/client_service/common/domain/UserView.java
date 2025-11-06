package com.sa.client_service.common.domain;

import java.util.UUID;

import lombok.Value;

@Value
public class UserView {

    private UUID id;
    private String email;
}

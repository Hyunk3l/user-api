package com.fabridinapoli.userapi.infrastructure.framework.controller

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class JsonUser @JsonCreator constructor(
        @JsonProperty("name") val name: String,
        @JsonProperty("surname") val surname: String,
        @JsonProperty("email") val email: String,
        @JsonProperty("password") val password: String)
package com.fabridinapoli.userapi.infrastructure.domain.user.elastic;

import com.fabridinapoli.userapi.domain.user.User;
import com.fabridinapoli.userapi.domain.user.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ElasticSearchUserRepository implements UserRepository {
    @NotNull
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(@NotNull User user) {

    }

    @Nullable
    @Override
    public User findBy(@NotNull String email) {
        return null;
    }
}

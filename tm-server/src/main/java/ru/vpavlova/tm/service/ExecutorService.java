package ru.vpavlova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.service.IExecutorService;

import java.util.concurrent.Executors;

@NoArgsConstructor
public class ExecutorService implements IExecutorService {

    @NotNull
    private final java.util.concurrent.ExecutorService executor = Executors.newFixedThreadPool(3);

    @Override
    public void submit(@NotNull final Runnable runnable) {
        executor.submit(runnable);
    }

}
package com.daan.scripting.loading.impl;

import com.daan.scripting.loading.test.TestCompiler;
import com.daanpanis.scripting.loading.api.ScriptLoader;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScriptClassImplTest {

    @Test
    public void getMultipleAsync() throws InterruptedException {
        ScriptLoader loader = new ScriptLoaderImpl(new TestCompiler(TestClass.class));
        Collection<Class<?>> results = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Kappa", Executors.newSingleThreadExecutor());
            future = future.thenApplyAsync((val) -> val.substring(0, 1));
            future.thenAcceptAsync((val) -> {
//                System.out.println("Here 6!");
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                }
                latch.countDown();
                results.add(val.getClass());
            });
        }
        loader.stream((InputStream) null).getAsync().thenAcceptAsync(val -> {
//            System.out.println("Kappa!");
        }, Executors.newSingleThreadExecutor());/*.expectClass().getAsync().thenAccept(val -> {
            System.out.println("Here!");
            results.add(val);
            latch.countDown();
        });*/
        /*CompletableFuture.runAsync(() -> {
            System.out.println("Kappa");
        }, Executors.newSingleThreadExecutor()).thenRun(() -> {
            System.out.println("Kappa2");
        });*/
        loader.stream((InputStream) null).expectClass().getAsync().thenAcceptAsync(val -> {
//            System.out.println("Here 2!");
            results.add(val);
            latch.countDown();
        });
        assertThat(latch.await(20, TimeUnit.SECONDS), is(true));
        assertThat(results.size(), is(2));
        assertThat(results.stream().filter(Objects::isNull).count(), is(0L));
    }

}
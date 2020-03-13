package com.example.rickandmortyapp.core.pagination;


import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public final class PaginationUtil {
    public static final int DEFAULT_LIMIT = 10;

    private PaginationUtil() {
    }


    public static Observable<Integer> getPaginationObservable(final RecyclerView recyclerView, final int limit) {
        return getPaginationObservable(recyclerView, limit, 0);
    }

    public static Observable<Integer> getPaginationObservable(final RecyclerView recyclerView, final int limit, int emptyListCount) {
        return getScrollObservable(recyclerView, limit, emptyListCount)
                .subscribeOn(AndroidSchedulers.mainThread())
                .distinctUntilChanged();
    }

    private static Observable<Integer> getScrollObservable(final RecyclerView recyclerView, final int limit, int emptyListCount) {
        checkAssertion(recyclerView);
        final LMHelper lmHelper = LMHelper.getInstance(recyclerView.getLayoutManager());
        return Observable.create(subscriber -> {
            final RecyclerView.OnScrollListener sl = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView1, int dx, int dy) {
                    if (!subscriber.isDisposed()) {
                        int position = lmHelper.getLastVisibleItemPosition();
                        int updatePosition = recyclerView1.getAdapter().getItemCount() - 1 - limit / 2;
                        if (position >= updatePosition && updatePosition >= 0) {
                            int offset = Math.max(recyclerView1.getAdapter().getItemCount() - emptyListCount, 0);
                            subscriber.onNext(offset);
                        }
                    }
                }
            };
            recyclerView.addOnScrollListener(sl);
            subscriber.setCancellable(() -> recyclerView.removeOnScrollListener(sl));
            if (recyclerView.getAdapter().getItemCount() == emptyListCount) {
                int offset = recyclerView.getAdapter().getItemCount() - emptyListCount;
                subscriber.onNext(offset);
            }
        });
    }

    public static Observable<Integer> getUpPaginationObservable(final RecyclerView recyclerView, final int limit) {
        return getScrollUpObservable(recyclerView, limit)
                .subscribeOn(AndroidSchedulers.mainThread())
                .distinctUntilChanged();
    }

    private static Observable<Integer> getScrollUpObservable(final RecyclerView recyclerView, final int limit) {
        checkAssertion(recyclerView);
        final LMHelper lmHelper = LMHelper.getInstance(recyclerView.getLayoutManager());
        return Observable.create(subscriber -> {
            final RecyclerView.OnScrollListener sl = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView1, int dx, int dy) {
                    if (!subscriber.isDisposed()) {
                        final int position = lmHelper.getFirstVisibleItemPosition();
                        final int updatePosition = limit / 2;
                        if (position <= updatePosition && recyclerView1.getAdapter().getItemCount() >= limit) {
                            subscriber.onNext(recyclerView1.getAdapter().getItemCount());
                        }
                    }
                }
            };
            recyclerView.addOnScrollListener(sl);
            subscriber.setCancellable(() -> recyclerView.removeOnScrollListener(sl));
        });
    }

    private static void checkAssertion(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() == null) {
            throw new RuntimeException("layout manager is null");
        }
    }
}
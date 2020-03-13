package com.example.rickandmortyapp.core.pagination;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class LMHelper<T extends LayoutManager> {
    private final T mLayoutManager;

    private LMHelper(T layoutManager) {
        mLayoutManager = layoutManager;
    }

    public static <Type extends LayoutManager> LMHelper getInstance(@NonNull Type layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            return new LinearLMHelper((LinearLayoutManager) layoutManager);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            return new StaggeredGridLMHelper((StaggeredGridLayoutManager) layoutManager);
        }
        throw new RuntimeException("Unknown LayoutManager class: " + layoutManager.getClass());
    }

    protected T getLayoutManager() {
        return mLayoutManager;
    }

    public abstract int getFirstVisibleItemPosition();

    public abstract int getLastVisibleItemPosition();

    private static class LinearLMHelper extends LMHelper<LinearLayoutManager> {
        private LinearLMHelper(LinearLayoutManager layoutManager) {
            super(layoutManager);
        }

        @Override
        public int getFirstVisibleItemPosition() {
            return getLayoutManager().findFirstVisibleItemPosition();
        }

        @Override
        public int getLastVisibleItemPosition() {
            return getLayoutManager().findLastVisibleItemPosition();
        }
    }

    private static class StaggeredGridLMHelper extends LMHelper<StaggeredGridLayoutManager> {

        private StaggeredGridLMHelper(StaggeredGridLayoutManager layoutManager) {
            super(layoutManager);
        }

        private static List<Integer> asList(int[] ints) {
            List<Integer> integerList = new ArrayList<>();
            for (int i : ints) {
                integerList.add(i);
            }
            return integerList;
        }

        @Override
        public int getFirstVisibleItemPosition() {
            return Collections.min(asList(getLayoutManager().findFirstVisibleItemPositions(null)));
        }

        @Override
        public int getLastVisibleItemPosition() {
            int[] into = getLayoutManager().findLastVisibleItemPositions(null);
            return Collections.max(asList(into));
        }
    }


}
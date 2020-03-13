import android.os.Handler
import android.os.Looper
import com.example.rickandmortyapp.core.LoadingView
import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

object RxDecor {

    fun <T> loading(view: LoadingView, hideOnNext: Boolean = true): ObservableTransformer<T, T> =
        LoadingViewTransformer(view, hideOnNext)

    class LoadingViewTransformer<T : R, R>(
        private val loadingView: LoadingView,
        private val hideOnNext: Boolean = false,
        useMainHandler: Boolean = true
    ) : ObservableTransformer<T, R> {

        private val handler = if (useMainHandler) Handler(Looper.getMainLooper()) else null

        override fun apply(t: Observable<T>): Observable<R> {
            var observable = t.doOnSubscribe { showProgress() }
                .doOnTerminate { hideProgress() }
                .doOnDispose { hideProgress() }

            if (hideOnNext) observable = observable.doOnNext { hideProgress() }
            return observable.map { it }
        }

        private fun showProgress() {
            handler?.post { loadingView.showProgress() } ?: loadingView.showProgress()
        }

        private fun hideProgress() {
            handler?.post { loadingView.hideProgress() } ?: loadingView.hideProgress()
        }
    }

    fun loadingCompletable(view: LoadingView): CompletableTransformer =
        LoadingViewTransformerCompletable(view)

    private class LoadingViewTransformerCompletable(private val loadingView: LoadingView) :
        CompletableTransformer {

        private val handler = Handler(Looper.getMainLooper())

        override fun apply(t: Completable): Completable {
            return t.doOnSubscribe { handler.post { loadingView.showProgress() } }
                .doOnTerminate { handler.post { loadingView.hideProgress() } }
                .doOnDispose { handler.post { loadingView.hideProgress() } }
        }
    }
}
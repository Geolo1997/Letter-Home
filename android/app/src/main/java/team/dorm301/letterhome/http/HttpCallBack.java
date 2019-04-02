package team.dorm301.letterhome.http;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class HttpCallBack<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onError(-1, "");
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onError(int code, String message);

    public abstract void onFailure();
}

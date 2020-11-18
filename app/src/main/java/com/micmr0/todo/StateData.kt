package com.micmr0.todo

import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import org.jetbrains.annotations.TestOnly

class StateData<T> {
    @NonNull
    private var status: DataStatus? = null;

    @Nullable
    private var data: T? = null;

    @Nullable
    private var error: Throwable? = null;

    fun StateData() {
        this.status = DataStatus.CREATED;
        this.data = null;
        this.error = null;
    }

    fun loading(): StateData<T> {
        this.status = DataStatus.LOADING;
        this.data = null;
        this.error = null;
        return this;
    }

    fun success(@NonNull data: T): StateData<T> {
        this.status = DataStatus.SUCCESS;
        this.data = data;
        this.error = null;
        return this;
    }

    fun error(@NonNull error: Throwable): StateData<T> {
        this.status = DataStatus.ERROR;
        this.data = null;
        this.error = error;
        return this;
    }

    fun complete(): StateData<T> {
        this.status = DataStatus.COMPLETE;
        return this;
    }

    @NonNull
    fun getStatus(): DataStatus {
        return status!!
    }

    @TestOnly
    fun setStatus(dataStatus : DataStatus) {
        status = dataStatus
    }

    @Nullable
    fun getData(): T {
        return data!!
    }

    @TestOnly
    fun setData(data : T) {
        this.data = data
    }

    @Nullable
    fun getError(): Throwable {
        return error!!
    }

    enum class DataStatus {
        CREATED,
        SUCCESS,
        ERROR,
        LOADING,
        COMPLETE
    }
}
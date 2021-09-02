package jlmd.dev.android.services;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ErrorUtils {
    public static ApiError parseError(Response<?> response) {
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(Constants.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit=builder.build();
        Converter<ResponseBody, ApiError> converter=retrofit.responseBodyConverter(ApiError.class,new Annotation[0]);
        ApiError error;
        try {
            assert response.errorBody() != null;
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }
        return error;
    }
}

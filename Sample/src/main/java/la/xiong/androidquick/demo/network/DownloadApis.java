package la.xiong.androidquick.demo.network;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface DownloadApis {

    /**
     * 下载文件
     * @param fileUrl
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

}

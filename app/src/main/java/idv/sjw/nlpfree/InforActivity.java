package idv.sjw.nlpfree;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class InforActivity extends AppCompatActivity {
    WebView inforweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);
        inforweb = (WebView)findViewById(R.id.infoWeb);

        //設定隱藏狀態
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        String info = "<body style=\"font-size:15px;background-color:transparent;text-align : center ;\">\n" +
                "<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;神經語言程序學（Neuro-linguistic programming，NLP，又譯作「神經語言程式學」、「身心語言程式學」）是一套原理\n" +
                "、信念和技術，其意圖為探索心靈和神經學，語言模式和人類感知與認知，安排組織以使之成為系統化模式以及如何在互動中建立\n" +
                "主觀現實的人類行為，屬於實用心理學和行動策略的一種。\n" +
                "<br/><br/>\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;NLP身心語言程式學學員學生習觀察和分析別人的言行，把人的特徵和表現和溝通方法基本分為三種模式，即視覺型（Visual）、\n" +
                "聽覺型（Auditory）和触覺型（Kinesthetic）等三種表像系統，簡稱VAK。\n" +
                "<br/><br/>\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;有鑑於之前自我測試方法都使用問單或書本，受測者很容易受自我意識干擾，而造成測試結果往往是自已想要的型，而不是自已應有\n" +
                "的型，所以寫了本支APP，希望可以協助測試者能正確的認識自已或協助他人，最後謝謝您的支持。\n" +
                "<br/>\n" +
                "<br/><br/><br/>\n" +
                "<div style=\"text-align: center\">\n" +
                "Team RunMay/Project Club Taiwan<br/>\n" +
                "更多訊息請至網站參閱<br/>\n" +
                "www.projctclub.com.tw<br/>\n" +
                "</div>\n" +
                "</body>";



        inforweb.getSettings().setJavaScriptEnabled(true);
        inforweb.setBackgroundColor(Color.TRANSPARENT);
        inforweb.loadDataWithBaseURL("",info, "text/html", "UTF-8", "");
    }
}
package idv.sjw.nlpfree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

public class ResultActivity extends AppCompatActivity {


    WebView resultWebView;
    ShareButton shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.setApplicationId("211547195998764");
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_result);
        //設定隱藏狀態
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        Intent intent = this.getIntent();
        int[] userAnswers = intent.getIntArrayExtra("userAnswers");

        resultWebView = (WebView)findViewById(R.id.resultWeb);
        shareButton = (ShareButton)findViewById(R.id.shareFBBut);
        shareButton.setVisibility(View.INVISIBLE);





        int type = 0;

        if( (userAnswers[0] > userAnswers[1]) && (userAnswers[0] > userAnswers[2])){
            type = 0;
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://www.facebook.com/732906446875789/photos/ms.c.eJwzNzayNDIxNDKzMDcxMDLUM4fwjSF8Azjf2NTEwMzCHADkIgmB.bps.a.732924006874033.1073741828.732906446875789/732924133540687/?type=3&theater"))
                    .build();
            shareButton.setShareContent(content);
        }else if(userAnswers[1] > userAnswers[2]) {
            type = 1;
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://www.facebook.com/732906446875789/photos/ms.c.eJwzNzayNDIxNDKzMDcxMDLUM4fwjSF8Azjf2NTEwMzCHADkIgmB.bps.a.732924006874033.1073741828.732906446875789/732924126874021/?type=3&theater"))
                    .build();
            shareButton.setShareContent(content);
        }else{
            type = 2;
            ShareLinkContent content;
            content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://www.facebook.com/732906446875789/photos/ms.c.eJwzNzayNDIxNDKzMDcxMDLUM4fwjSF8Azjf2NTEwMzCHADkIgmB.bps.a.732924006874033.1073741828.732906446875789/732924136874020/?type=3&theater"))
                    .build();
            shareButton.setShareContent(content);
        }





        float total = (float) (userAnswers[0]+userAnswers[1]+userAnswers[2]);
        double pA = Math.rint((userAnswers[0]/total)*10000)/100;
        double pB = Math.rint((userAnswers[1]/total)*10000)/100;
        double pC = Math.rint((userAnswers[2]/total)*10000)/100;

        Log.d("a","0"+userAnswers[0]+"total:"+total+"pa"+pA+"b"+pB+"c"+pC);




        String[] typeOfHtml = {"<p style='text-align:center' ><strong>根據本次的測試,你比較是視覺型" +
                "（Visual）</strong></p><br />特點：<br />您習慣先用雙眼去看和接收外在信息，眼睛的學習和處理能力最快，" +
                "所以他們喜歡顏色鮮明、外觀漂亮的人事物；常坐不安定，小動作多，能夠在同一時間做幾件事；在乎事情的重點，不在乎" +
                "事情的細節，喜歡清楚、簡單的表達重點和在一開始就直入話題；喜歡節奏快的事物。<br /><br />身形／姿勢：<br />" +
                "大多數情況下視覺形的人都偏瘦，當然也有胖的。他們頭多向上昂、行動快捷，說話時手的動作多且通常手活動在胸部以上。" +
                "<br /><br />呼吸：<br />呼吸較淺，常止於胸腔上半部分。<br /><br />語調／語速：<br />語調簡單而單一，語速快。" +
                "<br /><br />語言文字：<br />常使用和表達的文字有，我看到、清晰、模糊、漂亮、悅目、焦點、速度、彩色、燦爛、觀點" +
                "、觀察、圖案、出現、凝望、光明、朦朧、明白、明顯地、鮮豔奪目、多彩多姿等等。",
                "<p style='text-align:center' ><strong>根據本次的測試,你比較是聽覺型（Auditory）</strong></p><br />特點：<br />" +
                        "您習慣先用耳朵去聽和接收信息，所以在許多時候他們的耳朵非常靈敏，十分重視寧靜的環境及聲音的質量，同時他們善於文字處" +
                        "理和在乎事情的細節，做事情非常注重程序和步驟，喜歡有條不紊的進行；善於歌唱和聆聽。<br/><br/>身形／姿勢：<br />" +
                        "您不會像視覺型那樣偏瘦也不會像觸覺型那樣強壯和有力，他們的身形可能會豐滿而鬆垮，當然他們也存在於每種身材當中。說" +
                        "話時可能會將手靠近或託住耳朵和嘴附近。<br/><br />呼吸：<br />呼吸平穩、有規律和有節奏，在胸腔與腹部之間。<br/>" +
                        "<br/>語調／語速：<br />語調抑揚頓挫、高低快慢十分有節奏感，語速中等。<br/><br/>語言文字：<br />我聽到、聆聽、響" +
                        "亮、談談、聽懂、刺耳、溝通、寧靜、韻律、耳邊風、無話可說、值得一聽、清楚、清晰、詢問、討論、悅耳等等",
                "<p style='text-align:center' ><strong>根據本次的測試,你比較是觸覺型（Kinesthetic）</strong></p><br />特點：" +
                        "<br />觸覺型包含了觸覺、嗅覺、味覺，他們慣於先用感覺去感受和接收信息，所以在許多時候他們對自我的感覺非常的注重；" +
                        "在乎與人之間的關係、感覺及重要意義；言語中​​常提及感受或經歷。<br/><br/>身形／姿勢：<br />因為視覺型和聽覺型的人對" +
                        "觸碰感的要求遠沒有觸覺型的人高，所以相比之下觸覺型的人較為強壯和結實。<br/><br/>呼吸：<br />呼吸慢而深沉，會用到胸" +
                        "部以下及腹部。<br/><br/>語調／語速：<br />語調低沉，語速慢。<br/><br/>語言文字：<br />我感覺、壓迫、氣氛、把握、" +
                        "安全、危險、激動、口福、自然、壓力、匆忙、行動、難受、謹慎、順利、開心、快樂、幸福、成功、支持、一點都不怕、趁熱打鐵、" +
                        "興奮、冰冷、緊張、等等。"};



        String chart = "<div style='text-align:center'>" +
                "<canvas id='canvas' width='200' height='200'></canvas>" +
                "</div><br/><div style='text-align:center;color:#fff'>" +
                "<samp style='width:20px;height:20px;background-color:#454FDF;font-size:16px;border-radius:5px'>&nbsp;V:" +
                pA +
                "%&nbsp;</samp>&nbsp;<samp style='width:20px;height:20px;background-color:#F28107;font-size:16px;border-radius:5px'>&nbsp;A:" +
                pB +
                "%&nbsp;</samp>&nbsp;<samp style='width:20px;height:20px;background-color:#48990B;font-size:16px;border-radius:5px'>&nbsp;K:" +
                pC +
                "%&nbsp;</samp><div style='color:#000000;text-align:left'>"+  typeOfHtml[type]  +"</div><script type='text/javascript'>" +
                "var myColor = ['#454FDF','#F28107','#48990B','#542437','#53777A'];" +
                "var myData = [" +
                pA +
                "," +
                pB +
                "," +
                pC +
                ",0,0];function getTotal(){var myTotal = 0;for (var j = 0; j < myData.length; j++) " +
                "{myTotal += (typeof myData[j] == 'number') ? myData[j] : 0;}return myTotal;}" +
                "function plotData() {var canvas;var ctx;var lastend = 0;" +
                "var myTotal = getTotal();canvas = document.getElementById('canvas');" +
                "ctx = canvas.getContext('2d');ctx.clearRect(0, 0, canvas.width, canvas.height);" +
                "for (var i = 0; i < myData.length; i++) {ctx.fillStyle = myColor[i];ctx.beginPath();" +
                "ctx.moveTo(100,100);ctx.arc(100,100,100,lastend,lastend+(Math.PI*2*(myData[i]/myTotal)),false);ctx.lineTo(100,100);ctx.fill();" +
                "lastend += Math.PI*2*(myData[i]/myTotal);}}plotData();</script><br/><br/>";

        resultWebView.getSettings().setJavaScriptEnabled(true);
        resultWebView.setBackgroundColor(Color.TRANSPARENT);
        resultWebView.loadDataWithBaseURL("", chart, "text/html", "UTF-8", "");
        shareButton.setVisibility(View.VISIBLE);

//        Log.d("Html",chart);



    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        finish();
        return true;
    }
}

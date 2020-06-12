
// メインエントリー
window.onload = function() {
    
    // play ボタンは初回非表示
    $('.play').hide();
        
    // anime.js のタイムライン生成
    var basicTimeline = anime.timeline({
        loop: 1,
        autoplay: false, 
        complete: function(anim) {
            $('.play').css({'background-color': 'darkblue'});
        }
    });
    
    // '.build' クリック：タイムラインのデータをサーバー側で動的に生成
    $('.build').click(function() {
        console.log(".build@click");
        $('.build').css({'background-color': 'lawngreen'});
        $('.play').hide();
        
        // create an ajax object.
        $.ajax({
            url: "/animesign" + "/build.html",
            type: "GET",
            dataType: "json",
            success: function(data, dataType) {
                if (data.isError) {
                    console.log("application error occurred.");
                    return;
                } else {
                    console.log("write timeline list complete.");
                }
                                
                // タイムライン構築用 javaScript 再読込 【重要】FIXME: どこから読むか？
                $.getScript("http://localhost/animesign/resources/scripts/animesign.timeline.js")
                .done(function(script, textStatus) {
                    console.log("reload the timeline javascript complete.");
                    
                    // タイムライン構築処理呼び出し
                    buildTimeline(basicTimeline);
                    
                    // 初期化処理
                    initTimeline();
                    
                    $('.build').css({'background-color': 'darkblue'});
                    $('.play').show();
                 })
                 .fail(function(jqxhr, settings, exception) {
                    console.log("the timeline javascript request error occurred.");
                 });
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("http request error occurred.");
            }
        });
        
    });
        
    // '.play' クリック：タイムラインを再生
    $('.play').click(function() {
        console.log(".play@click");
        basicTimeline.restart();
        $('.play').css({'background-color': 'magenta'});
    });
}

//////////////////////////////////////////////////////////////////////////////
// 関数
//////////////////////////////////////////////////////////////////////////////

// 吹き出しテキスト表示用
function showBalloonText(text, balloonImgId, balloonTextId, top, left) {
    if (top == null) {
        top = 200; // TODO: この値の計算
    }
    if (left == null) {
        left = 200; // TODO: この値の計算
    }
    var off = $(balloonImgId).offset();
    $(balloonTextId).offset({
        top: off.top + (top),
        left: off.left + (left)
    });
    $(balloonTextId).css({opacity: 1});    
    
    // テキストの追加
    var txtArr = text.split("");
    var count = 0;
    var pageMark = false;
    var txtCount = function() {
        // '\v' をテキストのページ送り記号として使用
        if (pageMark == false) {
            // テキストを一文字ずつ追加
            if (txtArr[count] != '\v') {
                var timer1 = setTimeout(txtCount, 100); // TODO: テキスト表示の速い遅いが可能？
                $(balloonTextId).append(txtArr[count].replace(/\n/, "<br>"));
            }
            // ページを送る前に少し待つ
            else if (txtArr[count] == '\v') {
                var timer2 = setTimeout(txtCount, 500);
                // set the page mark.
                pageMark = true;
            }
            count++;
        }
        // ページ送り(吹き出しテキスト消去)処理
        else if (pageMark == true) {
            var timer3 = setTimeout(txtCount, 150);
            pageMark = false;
            $(balloonTextId).html('');
        }
        
        if (count == txtArr.length) {
            clearTimeout(timer1);
            clearTimeout(timer2);
            clearTimeout(timer3);
        }
    }
    txtCount();
    
}

// 吹き出しテキスト消去用
function hideBalloonText(balloonTextId) {
    $(balloonTextId).html('');
}
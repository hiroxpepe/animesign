/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

///////////////////////////////////////////////////////////////////////////////////////////////////////
// global variables must need

let basicTimeline;

///////////////////////////////////////////////////////////////////////////////////////////////////////
// global functions

const onCreated = () => {
    // play ボタンは初回非表示
    $('.play').hide();
    basicTimeline = anime.timeline({
        loop: 1,
        autoplay: false,
        complete: (anime) => {
            $('.play').css({'background-color': 'darkblue'});
        }
    });
};

const onBuild = () => {
    console.log(".build@click");
    $('.build').css({'background-color': 'lawngreen'});
    $('.play').hide();
    // create an ajax object.
    $.ajax({
        url: "/animesign" + "/build.json",
        type: "GET",
        dataType: "json",
        success: (data, dataType) => {
            if (data.isError) {
                console.log("application error occurred.");
                return;
            } else {
                console.log("write timeline list complete.");
            }

            // タイムライン構築用 javaScript 再読込 【重要】FIXME: どこから読むか？
            $.getScript("http://localhost/animesign/resources/scripts/animesign.timeline.js")
            .done((script, textStatus) => {
                console.log("reload the timeline javascript complete.");

                // タイムライン構築処理呼び出し
                buildTimeline(basicTimeline);

                // 初期化処理
                initTimeline();

                $('.build').css({'background-color': 'darkblue'});
                $('.play').show();
             })
             .fail((jqxhr, settings, exception) => {
                console.log("the timeline javascript request error occurred.");
             });
        },
        error: (XMLHttpRequest, textStatus, errorThrown) => {
            console.log("http request error occurred.");
        }
    });
};

const onPlay = () => {
    console.log(".play@click");
    basicTimeline.restart();
    $('.play').css({'background-color': 'magenta'});
};

// 吹き出しテキスト表示用
const showBalloonText = (text, balloonImgId, balloonTextId, top, left) => {
    if (top == null) {
        top = 200; // TODO: この値の計算
    }
    if (left == null) {
        left = 200; // TODO: この値の計算
    }
    const off = $(balloonImgId).offset();
    $(balloonTextId).offset({
        top: off.top + (top),
        left: off.left + (left)
    });
    $(balloonTextId).css({opacity: 1});

    // テキストの追加
    let count = 0;
    let pageMark = false;
    const txtArr = text.split("");
    const txtCount = () => {
        // '\v' をテキストのページ送り記号として使用
        if (pageMark === false) {
            // テキストを一文字ずつ追加
            if (txtArr[count] !== '\v') {
                var timer1 = setTimeout(txtCount, 100); // TODO: テキスト表示の速い遅いが可能？
                $(balloonTextId).append(txtArr[count].replace(/\n/, "<br>"));
            }
            // ページを送る前に少し待つ
            else if (txtArr[count] === '\v') {
                var timer2 = setTimeout(txtCount, 500);
                // set the page mark.
                pageMark = true;
            }
            count++;
        }
        // ページ送り(吹き出しテキスト消去)処理
        else if (pageMark === true) {
            var timer3 = setTimeout(txtCount, 150);
            pageMark = false;
            $(balloonTextId).html('');
        }

        if (count === txtArr.length) {
            clearTimeout(timer1);
            clearTimeout(timer2);
            clearTimeout(timer3);
        }
    };
    txtCount();

};

// 吹き出しテキスト消去用
const hideBalloonText = (balloonTextId) => {
    $(balloonTextId).html('');
};

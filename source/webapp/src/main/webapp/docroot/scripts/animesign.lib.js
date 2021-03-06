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
// global functions

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

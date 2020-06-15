function buildTimeline(basicTimeline) {
	basicTimeline.add({
	}).add({
		targets: '#default-background',
		offset: 0,
		src: 'http://localhost/animesign/resources/images/backgrounds/background_0_1920px_1080px.png',
		duration: 0,
		easing: 'linear'
	}).add({
		targets: '#default-background',
		offset: 1000,
		src: 'http://localhost/animesign/resources/images/backgrounds/background_start_3_red_1920px_1080px.png',
		duration: 0,
		easing: 'linear'
	}).add({
		targets: '#default-background',
		offset: 2000,
		src: 'http://localhost/animesign/resources/images/backgrounds/background_start_2_green_1920px_1080px.png',
		duration: 0,
		easing: 'linear'
	}).add({
		targets: '#default-background',
		offset: 3000,
		src: 'http://localhost/animesign/resources/images/backgrounds/background_start_1_blue_1920px_1080px.png',
		duration: 0,
		easing: 'linear'
	}).add({
		targets: '#back-scroll1-to-right',
		offset: 4000,
		opacity: {value: 1, duration: 500},
		translateX: {value: -720, duration: 23000},
		translateY: {value: 0, duration: 23000},
		easing: 'linear'
	}).add({
		targets: '#default-background',
		offset: 4000,
		src: 'http://localhost/animesign/resources/images/backgrounds/background_0_1920px_1080px.png',
		duration: 0,
		easing: 'linear'
	}).add({
		targets: '#back-scroll2-to-right',
		offset: 4000,
		opacity: {value: 1, duration: 500},
		translateX: {value: -1320, duration: 23000},
		translateY: {value: 0, duration: 23000},
		easing: 'linear'
	}).add({
		targets: '#obj1-left-center',
		offset: 5000,
		opacity: {value: 1, duration: 500},
		translateX: {value: 1440, duration: 1000},
		translateY: {value: 0, duration: 1000},
		rotate: {value: 0.0, duration: 1000, easing: 'easeInOutQuint'},
		easing: 'easeInOutQuint'
	}).add({
		targets: '#balloon-mid-img-L, #balloon-mid-text-L',
		offset: 6000,
		opacity: {value: 1, duration: 500},
		translateX: {value: -1320, duration: 1000},
		translateY: {value: 360, duration: 1000},
		easing: 'easeInOutQuint',
		complete: function(anim) {
			showBalloonText('こんにちは！',
			'#balloon-mid-img-L',
			'#balloon-mid-text-L',
			null,null);
		}
	}).add({
		targets: '#balloon-mid-img-L, #balloon-mid-text-L',
		offset: 10000,
		opacity: {value: 0, duration: 500},
		easing: 'linear',
		complete: function(anim) {
			hideBalloonText('#balloon-mid-text-L');
		}
	}).add({
		targets: '#obj1-left-center',
		offset: 10000,
		opacity: {value: 0, duration: 500},
		easing: 'linear'
	}).add({
		targets: '#balloon-mid-img-L, #balloon-mid-text-L',
		offset: 11000,
		translateX: {value: 1380, duration: 0},
		translateY: {value: -360, duration: 0},
		easing: 'linear'
	}).add({
		targets: '#obj1-right-center',
		offset: 11000,
		opacity: {value: 1, duration: 500},
		translateX: {value: -1440, duration: 1000},
		translateY: {value: 0, duration: 1000},
		rotate: {value: -0.0, duration: 1000, easing: 'easeInOutQuint'},
		easing: 'easeInOutQuint'
	}).add({
		targets: '#obj1-left-center',
		offset: 11000,
		translateX: {value: -360, duration: 0},
		translateY: {value: 60, duration: 0},
		easing: 'linear'
	}).add({
		targets: '#balloon-mid-img-R, #balloon-mid-text-R',
		offset: 12000,
		opacity: {value: 1, duration: 500},
		translateX: {value: 1320, duration: 1000},
		translateY: {value: 360, duration: 1000},
		easing: 'easeInOutQuint',
		complete: function(anim) {
			showBalloonText('はいはい。\nなになに？\vとかナ',
			'#balloon-mid-img-R',
			'#balloon-mid-text-R',
			null,null);
		}
	}).add({
		targets: '#obj1-right-center',
		offset: 16000,
		opacity: {value: 0, duration: 500},
		easing: 'linear'
	}).add({
		targets: '#balloon-mid-img-R, #balloon-mid-text-R',
		offset: 16000,
		opacity: {value: 0, duration: 500},
		easing: 'linear',
		complete: function(anim) {
			hideBalloonText('#balloon-mid-text-R');
		}
	}).add({
		targets: '#obj1-left-center',
		offset: 16000,
		opacity: {value: 1, duration: 500},
		translateX: {value: 1440, duration: 1000},
		translateY: {value: 0, duration: 1000},
		rotate: {value: 0.0, duration: 1000, easing: 'easeInOutQuint'},
		easing: 'easeInOutQuint'
	}).add({
		targets: '#balloon-mid-img-R, #balloon-mid-text-R',
		offset: 17000,
		translateX: {value: -540, duration: 0},
		translateY: {value: -360, duration: 0},
		easing: 'linear'
	}).add({
		targets: '#balloon-mid-img-L, #balloon-mid-text-L',
		offset: 17000,
		opacity: {value: 1, duration: 500},
		translateX: {value: -1320, duration: 1000},
		translateY: {value: 360, duration: 1000},
		easing: 'easeInOutQuint',
		complete: function(anim) {
			showBalloonText('どうしたの？',
			'#balloon-mid-img-L',
			'#balloon-mid-text-L',
			null,null);
		}
	}).add({
		targets: '#obj1-right-center',
		offset: 17000,
		translateX: {value: 1560, duration: 0},
		translateY: {value: 60, duration: 0},
		easing: 'linear'
	}).add({
		targets: '#balloon-mid-img-L, #balloon-mid-text-L',
		offset: 21000,
		opacity: {value: 0, duration: 500},
		easing: 'linear',
		complete: function(anim) {
			hideBalloonText('#balloon-mid-text-L');
		}
	}).add({
		targets: '#obj1-left-center',
		offset: 21000,
		opacity: {value: 0, duration: 500},
		easing: 'linear'
	}).add({
		targets: '#obj1-right-center',
		offset: 22000,
		opacity: {value: 1, duration: 500},
		translateX: {value: -1440, duration: 1000},
		translateY: {value: 0, duration: 1000},
		rotate: {value: -0.0, duration: 1000, easing: 'easeInOutQuint'},
		easing: 'easeInOutQuint'
	}).add({
		targets: '#balloon-mid-img-L, #balloon-mid-text-L',
		offset: 22000,
		translateX: {value: 1380, duration: 0},
		translateY: {value: -360, duration: 0},
		easing: 'linear'
	}).add({
		targets: '#obj1-left-center',
		offset: 22000,
		translateX: {value: -360, duration: 0},
		translateY: {value: 60, duration: 0},
		easing: 'linear'
	}).add({
		targets: '#balloon-mid-img-R, #balloon-mid-text-R',
		offset: 23000,
		opacity: {value: 1, duration: 500},
		translateX: {value: 1320, duration: 1000},
		translateY: {value: 360, duration: 1000},
		easing: 'easeInOutQuint',
		complete: function(anim) {
			showBalloonText('別に～\nとかナ',
			'#balloon-mid-img-R',
			'#balloon-mid-text-R',
			null,null);
		}
	}).add({
		targets: '#back-scroll1-to-right',
		offset: 27000,
		opacity: {value: 0, duration: 500},
		easing: 'linear'
	}).add({
		targets: '#back-scroll2-to-right',
		offset: 27000,
		opacity: {value: 0, duration: 500},
		easing: 'linear'
	}).add({
		targets: '#balloon-mid-img-R, #balloon-mid-text-R',
		offset: 27000,
		opacity: {value: 0, duration: 500},
		easing: 'linear',
		complete: function(anim) {
			hideBalloonText('#balloon-mid-text-R');
		}
	}).add({
		targets: '#obj1-right-center',
		offset: 27000,
		opacity: {value: 0, duration: 500},
		easing: 'linear'
	}).add({
		targets: '#balloon-mid-img-R, #balloon-mid-text-R',
		offset: 28000,
		translateX: {value: -540, duration: 0},
		translateY: {value: -360, duration: 0},
		easing: 'linear'
	}).add({
		targets: '#back-scroll2-to-right',
		offset: 28000,
		translateX: {value: 0, duration: 0},
		translateY: {value: 0, duration: 0},
		easing: 'linear'
	}).add({
		targets: '#back-scroll1-to-right',
		offset: 28000,
		translateX: {value: 480, duration: 0},
		translateY: {value: 0, duration: 0},
		easing: 'linear'
	}).add({
		targets: '#obj1-right-center',
		offset: 28000,
		translateX: {value: 1560, duration: 0},
		translateY: {value: 60, duration: 0},
		easing: 'linear'
	});
}
function initTimeline() {
	$('#balloon-mid-text-L').html('')
	$('#balloon-mid-text-L').html('')
	$('#balloon-mid-text-L').html('')
	$('#balloon-mid-text-R').html('')
	$('#balloon-mid-text-R').html('')
	$('#balloon-mid-text-R').html('')
	$('#balloon-mid-text-L').html('')
	$('#balloon-mid-text-L').html('')
	$('#balloon-mid-text-L').html('')
	$('#balloon-mid-text-R').html('')
	$('#balloon-mid-text-R').html('')
	$('#balloon-mid-text-R').html('')
}

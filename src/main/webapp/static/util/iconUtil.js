(function($) {
	// 全局系统对象
	window['iconUtil'] = {};
	/*
	 * 定义图标样式的数组
	 */
	$.iconData = [ {
		value : '',
		text : '默认'
	}, {
		value : 'icon-user',
		text : 'icon-user'
	}, {
		value : 'icon-archives',
		text : 'icon-archives'
	}, {
		value : 'icon-busy',
		text : 'icon-busy'
	}, {
		value : 'icon-category',
		text : 'icon-category'
	}, {
		value : 'icon-communication',
		text : 'icon-communication'
	}, {
		value : 'icon-company',
		text : 'icon-company'
	}, {
		value : 'icon-contact',
		text : 'icon-contact'
	}, {
		value : 'icon-date',
		text : 'icon-date'
	}, {
		value : 'icon-graphic-design',
		text : 'icon-graphic-design'
	}, {
		value : 'icon-help',
		text : 'icon-help'
	}, {
		value : 'icon-home',
		text : 'icon-home'
	}, {
		value : 'icon-link',
		text : 'icon-link'
	}, {
		value : 'icon-lock',
		text : 'icon-lock'
	}, {
		value : 'icon-login',
		text : 'icon-login'
	}, {
		value : 'icon-networking',
		text : 'icon-networking'
	}, {
		value : 'icon-phone',
		text : 'icon-phone'
	}, {
		value : 'icon-print',
		text : 'icon-print'
	}, {
		value : 'icon-world',
		text : 'icon-world'
	}, {
		value : 'icon-shipping',
		text : 'icon-shipping'
	}, {
		value : 'icon-settings',
		text : 'icon-settings'
	}, {
		value : 'icon-my-account',
		text : 'icon-my-account'
	},

	{
		value : 'icon-reload',
		text : 'icon-reload'
	},

	{
		value : 'icon-ok',
		text : 'icon-ok'
	}, {
		value : 'icon-add',
		text : 'icon-add'
	}, {
		value : 'icon-adds',
		text : 'icon-adds'
	}, {
		value : 'icon-edit',
		text : 'icon-edit'
	}, {
		value : 'icon-remove',
		text : 'icon-remove'
	}, {
		value : 'icon-save',
		text : 'icon-save'
	}, {
		value : 'icon-cut',
		text : 'icon-cut'
	}, {
		value : 'icon-ok',
		text : 'icon-ok'
	}, {
		value : 'icon-no',
		text : 'icon-no'
	}, {
		value : 'icon-cancel',
		text : 'icon-cancel'
	}, {
		value : 'icon-reload',
		text : 'icon-reload'
	}, {
		value : 'icon-search',
		text : 'icon-search'
	}, {
		value : 'icon-color',
		text : 'icon-color'
	}, {
		value : 'icon-help',
		text : 'icon-help'
	}, {
		value : 'icon-undo',
		text : 'icon-undo'
	}, {
		value : 'icon-redo',
		text : 'icon-redo'
	}, {
		value : 'icon-back',
		text : 'icon-back'
	}, {
		value : 'icon-sum',
		text : 'icon-sum'
	}, {
		value : 'icon-tip',
		text : 'icon-tip'
	}, {
		value : 'icon-paint',
		text : 'icon-paint'
	}, {
		value : 'icon-config',
		text : 'icon-config'
	}, {
		value : 'icon-company',
		text : 'icon-company'
	}, {
		value : 'icon-sys',
		text : 'icon-sys'
	}, {
		value : 'icon-db',
		text : 'icon-db'
	}, {
		value : 'icon-pro',
		text : 'icon-pro'
	}, {
		value : 'icon-role',
		text : 'icon-role'
	}, {
		value : 'icon-end',
		text : 'icon-end'
	}, {
		value : 'icon-home',
		text : 'icon-home'
	}, {
		value : 'icon-bug',
		text : 'icon-bug'
	}, {
		value : 'icon-badd',
		text : 'icon-badd'
	}, {
		value : 'icon-bedit',
		text : 'icon-bedit'
	}, {
		value : 'icon-bdel',
		text : 'icon-bdel'
	}, {
		value : 'icon-logout',
		text : 'icon-logout'
	}, {
		value : 'icon-cstbase',
		text : 'icon-cstbase'
	}, {
		value : 'icon-item',
		text : 'icon-item'
	}, {
		value : 'icon-excel',
		text : 'icon-excel'
	}, {
		value : 'icon-time',
		text : 'icon-time'
	}

	, {
		value : 'icon-auto',
		text : 'icon-auto'
	}, {
		value : 'icon-start',
		text : 'icon-start'
	}, {
		value : 'icon-mini-add',
		text : 'icon-mini-add'
	}, {
		value : 'icon-mini-edit',
		text : 'icon-mini-edit'
	}, {
		value : 'icon-mini-refresh',
		text : 'icon-mini-refresh'
	}, {
		value : 'icon-address',
		text : 'icon-address'
	}, {
		value : 'icon-administrative-docs',
		text : 'icon-administrative-docs'
	}, {
		value : 'icon-advertising',
		text : 'icon-advertising'
	}, {
		value : 'icon-archives ',
		text : 'icon-archives '
	}, {
		value : 'icon-attibutes ',
		text : 'icon-attibutes '
	}, {
		value : 'icon-bank ',
		text : 'icon-bank '
	}, {
		value : 'icon-basket ',
		text : 'icon-basket '
	}, {
		value : 'icon-bestseller ',
		text : 'icon-bestseller '
	}, {
		value : 'icon-billing ',
		text : 'icon-billing '
	}, {
		value : 'icon-bookmark ',
		text : 'icon-bookmark '
	}, {
		value : 'icon-brainstorming ',
		text : 'icon-brainstorming '
	}, {
		value : 'icon-business-contact ',
		text : 'icon-business-contact '
	}, {
		value : 'icon-busy ',
		text : 'icon-busy '
	}, {
		value : 'icon-calendar ',
		text : 'icon-calendar '
	}, {
		value : 'icon-category ',
		text : 'icon-category '
	}, {
		value : 'icon-check ',
		text : 'icon-check '
	}, {
		value : 'icon-collaboration ',
		text : 'icon-collaboration '
	}, {
		value : 'icon-comment ',
		text : 'icon-comment '
	}, {
		value : 'icon-communication ',
		text : 'icon-communication '
	}, {
		value : 'icon-consulting ',
		text : 'icon-consulting '
	}, {
		value : 'icon-contact ',
		text : 'icon-contact '
	}, {
		value : 'icon-cost ',
		text : 'icon-cost '
	}, {
		value : 'icon-credit-card ',
		text : 'icon-credit-card '
	}, {
		value : 'icon-credit ',
		text : 'icon-credit '
	}, {
		value : 'icon-current-work ',
		text : 'icon-current-work '
	}, {
		value : 'icon-customers ',
		text : 'icon-customers '
	}, {
		value : 'icon-cv ',
		text : 'icon-cv '
	}, {
		value : 'icon-database ',
		text : 'icon-database '
	}, {
		value : 'icon-date ',
		text : 'icon-date '
	}, {
		value : 'icon-delicious ',
		text : 'icon-delicious '
	}, {
		value : 'icon-document-library ',
		text : 'icon-document-library '
	}, {
		value : 'icon-donate ',
		text : 'icon-donate '
	}, {
		value : 'icon-drawings ',
		text : 'icon-drawings '
	}, {
		value : 'icon-email ',
		text : 'icon-email '
	}, {
		value : 'icon-featured ',
		text : 'icon-featured '
	}, {
		value : 'icon-feed ',
		text : 'icon-feed '
	}, {
		value : 'icon-finished-work ',
		text : 'icon-finished-work '
	}, {
		value : 'icon-flag ',
		text : 'icon-flag '
	}, {
		value : 'icon-folder ',
		text : 'icon-folder '
	}, {
		value : 'icon-free-for-job ',
		text : 'icon-free-for-job '
	}, {
		value : 'icon-freelance ',
		text : 'icon-freelance '
	}, {
		value : 'icon-full-time ',
		text : 'icon-full-time '
	}, {
		value : 'icon-future-projects ',
		text : 'icon-future-projects '
	}, {
		value : 'icon-graphic-design ',
		text : 'icon-graphic-design '
	}, {
		value : 'icon-heart ',
		text : 'icon-heart '
	}, {
		value : 'icon-hire-me ',
		text : 'icon-hire-me '
	}, {
		value : 'icon-illustration ',
		text : 'icon-illustration '
	}, {
		value : 'icon-invoice ',
		text : 'icon-invoice '
	}, {
		value : 'icon-issue ',
		text : 'icon-issue '
	}, {
		value : 'icon-library ',
		text : 'icon-library '
	}, {
		value : 'icon-lightbulb ',
		text : 'icon-lightbulb '
	}, {
		value : 'icon-limited-edition ',
		text : 'icon-limited-edition '
	}, {
		value : 'icon-link ',
		text : 'icon-link '
	}, {
		value : 'icon-lock ',
		text : 'icon-lock '
	}, {
		value : 'icon-login ',
		text : 'icon-login '
	}, {
		value : 'icon-milestone ',
		text : 'icon-milestone '
	}, {
		value : 'icon-my-account ',
		text : 'icon-my-account '
	}, {
		value : 'icon-networking ',
		text : 'icon-networking '
	}, {
		value : 'icon-old-versions ',
		text : 'icon-old-versions '
	}, {
		value : 'icon-old-order-1 ',
		text : 'icon-old-order-1 '
	}, {
		value : 'icon-order ',
		text : 'icon-order '
	}, {
		value : 'icon-payment-card ',
		text : 'icon-payment-card '
	}, {
		value : 'icon-paypal ',
		text : 'icon-paypal '
	}, {
		value : 'icon-pen ',
		text : 'icon-pen '
	}, {
		value : 'icon-phone ',
		text : 'icon-phone '
	}, {
		value : 'icon-photography ',
		text : 'icon-photography '
	}, {
		value : 'icon-plus ',
		text : 'icon-plus '
	}, {
		value : 'icon-premium ',
		text : 'icon-premium '
	}, {
		value : 'icon-process ',
		text : 'icon-process '
	}, {
		value : 'icon-product-1 ',
		text : 'icon-product-1 '
	}, {
		value : 'icon-product-design ',
		text : 'icon-product-design '
	}, {
		value : 'icon-product ',
		text : 'icon-product '
	}, {
		value : 'icon-project ',
		text : 'icon-project '
	}, {
		value : 'icon-publish ',
		text : 'icon-publish '
	}, {
		value : 'icon-refresh ',
		text : 'icon-refresh '
	}, {
		value : 'icon-search ',
		text : 'icon-search '
	}, {
		value : 'icon-settings ',
		text : 'icon-settings '
	}, {
		value : 'icon-shipping ',
		text : 'icon-shipping '
	}, {
		value : 'icon-showreel ',
		text : 'icon-showreel '
	}, {
		value : 'icon-sign-in ',
		text : 'icon-sign-in '
	}, {
		value : 'icon-sign-out ',
		text : 'icon-sign-out '
	}, {
		value : 'icon-sign-up ',
		text : 'icon-sign-up '
	}, {
		value : 'icon-sitemap ',
		text : 'icon-sitemap '
	}, {
		value : 'icon-special-offer',
		text : 'icon-special-offer'
	}, {
		value : 'icon-star ',
		text : 'icon-star '
	}, {
		value : 'icon-statistics ',
		text : 'icon-statistics '
	}, {
		value : 'icon-suppliers ',
		text : 'icon-suppliers '
	}, {
		value : 'icon-tag ',
		text : 'icon-tag '
	}, {
		value : 'icon-ticket ',
		text : 'icon-ticket '
	}, {
		value : 'icon-twitter',
		text : 'icon-twitter'
	}, {
		value : 'icon-upcoming-work ',
		text : 'icon-upcoming-work '
	}, {
		value : 'icon-user ',
		text : 'icon-user '
	}, {
		value : 'icon-world ',
		text : 'icon-world '
	}, {
		value : 'icon-zoom ',
		text : 'icon-zoom '
	}, {
		value : 'icon-print',
		text : 'icon-print'
	} ];

})(jQuery);